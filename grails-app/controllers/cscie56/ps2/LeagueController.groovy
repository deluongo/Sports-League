package cscie56.ps2
import cscie56.ps3.Person
import cscie56.ps5.Role
import cscie56.ps5.User
import cscie56.ps5.BlogEntry
import cscie56.ps5.Comment
import grails.plugin.springsecurity.annotation.Secured


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional



@Secured([Role.ROLE_USER, Role.ROLE_ADMIN, Role.ROLE_ANONYMOUS])
@Transactional(readOnly = true)
class LeagueController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond League.list(params), model:[leagueCount: League.count()]
    }

    def show(League league) {
        respond league
    }

    def create() {
        respond new League(params)
    }

    @Transactional
    def save(League league) {
        if (league == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (league.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond league.errors, view:'create'
            return
        }

        league.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'league.label', default: 'League'), league.id])
                redirect league
            }
            '*' { respond league, [status: CREATED] }
        }
    }

    def edit(League league) {
        respond league
    }

    @Transactional
    def update(League league) {
        if (league == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (league.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond league.errors, view:'edit'
            return
        }

        league.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'league.label', default: 'League'), league.id])
                redirect league
            }
            '*'{ respond league, [status: OK] }
        }
    }

    @Transactional
    def delete(League league) {

        if (league == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        league.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'league.label', default: 'League'), league.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'league.label', default: 'League'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /*                          ==============  ***  ==============                          *
     #  ---------------------             CUSTOM RENDERING            ---------------------  #
     *                          ===================================                          */

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ SHOW STANDINGS ~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def season(String conferenceIndex) {

        /*  --------------               *** Sim Season  ***            ---------------  */
        //simSeason('2017', 82)

        /*  --------------            *** Load All Teams ***            ---------------  */
        /* ___  Load Conference Objects  ___ */
        def westernConference = Conference.findAllByName("Western Conference")
        def easternConference = Conference.findAllByName("Eastern Conference")
        /* ___  Teams List by Conference  ___ */
        def westTeams = Team.findAllByConference(westernConference)
        def eastTeams = Team.findAllByConference(easternConference)

        /*  --------------          *** Create Conference Map  ***      ---------------  */
        /* ___  Ensure Page Load for /season/showStandings  ___ */
        if (conferenceIndex == null) {conferenceIndex = 1}
        def team
        if (conferenceIndex == '1') { team = westTeams }
        else { team = eastTeams}

        /*  --------------            *** Display Standings ***         ---------------  */
        /* ___  open standings view ___ */
        render(view: "season/showStandings/${conferenceIndex}", model: [seasonName: westernConference.seasons.name, teamList: team])
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~ SHOW PLAYER STATS ~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    @Secured([Role.ROLE_USER, Role.ROLE_ADMIN])
    def person(String personIndex, String tabIndex) {
        /*------------------------------------------*
        * ===========================================
        * FUNCTION -> SHOW PLAYER STATS!
        * ===========================================
        * INPUTS:
        *     -
        * FUNCTIONS:
        *     - playGame(homeTeam, awayTeam)
        * DESCRIPTION:
        *     - Displays league standings in the browser
        * OUTPUT:
        *     -
        /*---------------------------------------------------------------------------------------------*/

        /*  --------------              *** Select Player ***           ---------------  */
        personIndex = personIndex ?: "1"
        def person = Person.get(personIndex)

        /*  --------------             *** Default Tab Idx ***          ---------------  */
        tabIndex = tabIndex ?: "personal"

        /*  --------------              *** Display Stats ***           ---------------  */
        def currentUser = springSecurityService.currentUser

        render(view: "person/stats", model: [person: person, tabIndex: tabIndex, currentUser: currentUser])
	    //respond(person, view: "person/stats/${personIndex}")
    }

    def saveObject(object) {
        if (!object.save(flush:true)) {
            object.errors.allErrors.each { println it }
        }
    }

    @Secured([Role.ROLE_USER, Role.ROLE_ADMIN])
    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~ PUBLISH A BLOG POST ~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def publishPost() {
        //String personIndex, String tabIndex, String postTitle, String postDescription, String backgroundImage, String blogText

        /*------------------------------------------*
        * ===========================================
        * FUNCTION -> SHOW PLAYER STATS!
        * ===========================================
        * INPUTS:
        *     -
        * FUNCTIONS:
        *     - playGame(homeTeam, awayTeam)
        * DESCRIPTION:
        *     - Displays league standings in the browser
        * OUTPUT:
        *     -
        /*---------------------------------------------------------------------------------------------*/

        def personIndex = params.list('personIndex')
        //String personIndex, String tabIndex,




        /*  --------------              *** Select Player ***           ---------------  */
        personIndex = personIndex ?: "1"
        def person = Person.get(personIndex)

        /*  --------------            *** Authenticate User ***         ---------------  */
        def currentUser = springSecurityService.currentUser
        if(person.user != currentUser) {
            flash.message = "You aren't authorized to create new blog posts for ${person.firstName} ${person.lastName}"
        }
        else {
            /*  --------------            *** Load Form Results ***         ---------------  */
            def postTitle = params.list('postTitle')
            def postDescription = params.list('postDescription')
            def blogText = params.list('blogText')
            def backgroundImage = params.list('backgroundImage')

            /*  --------------            *** Add New Blog Post ***         ---------------  */
            BlogEntry newPost = new BlogEntry(published:true, title: postTitle, description: postDescription, text: blogText, pictureURL: backgroundImage, dateCreated: new Date(), datePublished: new Date(), author: currentUser)

            /*  --------------             *** Add Post to DB ***           ---------------  */
            if (newPost.validate()) {
                saveObject(newPost)
                person.user.addToBlogPosts(newPost)
                saveObject(person.user)

            }

            /*  --------------             *** Display Errors ***           ---------------  */
            else {
                def response = []
                return response
            }

            print(params)
            /*  --------------              *** Display Stats ***           ---------------  */
            //render params

            render(template: "/sharedTemplates/posts/displayAllPosts", model: [person: person, currentUser: currentUser])


            //render params
            //render(view: "person/form-submit", model: [person: person, tabIndex: tabIndex, currentUser: currentUser])

            //template: "displayAllPosts"
            //respond(person, view: "person/stats/${personIndex}")
        }

    }

    @Secured([Role.ROLE_USER, Role.ROLE_ADMIN])
    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~ SHOW PLAYER STATS ~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def newPost() {
        //String personIndex, String tabIndex, String postTitle, String postDescription, String backgroundImage, String blogText

        /*------------------------------------------*
        * ===========================================
        * FUNCTION -> SHOW PLAYER STATS!
        * ===========================================
        * INPUTS:
        *     -
        * FUNCTIONS:
        *     - playGame(homeTeam, awayTeam)
        * DESCRIPTION:
        *     - Displays league standings in the browser
        * OUTPUT:
        *     -
        /*---------------------------------------------------------------------------------------------*/

        def personIndex = params.list('personIndex')
        //def validation_error = []
        //String personIndex, String tabIndex,




        /*  --------------              *** Select Player ***           ---------------  */
        personIndex = personIndex ?: "1"
        def person = Person.get(personIndex)

        /*  --------------            *** Authenticate User ***         ---------------  */
        def currentUser = springSecurityService.currentUser
        if(person.user != currentUser) {
            flash.message = "You aren't authorized to create new blog posts for ${person.firstName} ${person.lastName}"
        }
        else {
            /*  --------------            *** Load Form Results ***         ---------------  */
            def postTitle = params.list('postTitle')
            def postDescription = params.list('postDescription')
            def blogText = params.list('blogText')
            def backgroundImage = params.list('backgroundImage')


            /*  --------------            *** Add New Blog Post ***         ---------------  */
            BlogEntry newPost = new BlogEntry(published:false, title: postTitle, description: postDescription, text: blogText, pictureURL: backgroundImage, dateCreated: new Date(), author: currentUser)

            /*  --------------             *** Add Post to DB ***           ---------------  */
            if (newPost.validate()) {
                saveObject(newPost)
                person.user.addToBlogPosts(newPost)
                saveObject(person.user)

            }

            /*  --------------             *** Display Errors ***           ---------------  */
            else {
                def response = []
                return response
            }


            /*  --------------              *** Display Stats ***           ---------------  */

            //render params
            //render(view: "person/form-submit", model: [person: person, tabIndex: tabIndex, currentUser: currentUser])
            render(template: "/sharedTemplates/displayAllPosts", model: [person: person, currentUser: currentUser])
            //template: "displayAllPosts"
            //respond(person, view: "person/stats/${personIndex}")
        }
    }




    @Secured([Role.ROLE_USER, Role.ROLE_ADMIN])
    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~ PUBLISH A BLOG POST ~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def submitComment() {
        //String personIndex, String tabIndex, String postTitle, String postDescription, String backgroundImage, String blogText

        /*------------------------------------------*
        * ===========================================
        * FUNCTION -> SHOW PLAYER STATS!
        * ===========================================
        * INPUTS:
        *     -
        * FUNCTIONS:
        *     - playGame(homeTeam, awayTeam)
        * DESCRIPTION:
        *     - Displays league standings in the browser
        * OUTPUT:
        *     -
        /*---------------------------------------------------------------------------------------------*/
        print(params)

        def personIndex = params.list('personIndex')
        def postId = params.list('postIndex')
        //String personIndex, String tabIndex,




        /*  --------------              *** Select Player ***           ---------------  */
        //personIndex = personIndex ?: "1"
        def person = Person.get(personIndex)
        BlogEntry post = BlogEntry.get(postId)

        /*  --------------            *** Authenticate User ***         ---------------  */
        def currentUser = springSecurityService.currentUser
        if(person.user != currentUser) {
            flash.message = "Please LOG IN. Anonymous users are not permitted to submit a comments."
        }
        else {
            /*  --------------            *** Load Form Results ***         ---------------  */
            def commentText = params.list('textarea')

            /*  --------------            *** Add New Blog Post ***         ---------------  */
            Comment newComment = new Comment(text: commentText, dateCreated: new Date(), author: currentUser, approved: false)

            /*  --------------             *** Add Post to DB ***           ---------------  */
            if (newComment.validate()) {
                saveObject(newComment)
                person.user.addToComments(newComment)
                post.addToComments(newComment)
                saveObject(person.user)
                saveObject(person.user)

            }

            /*  --------------             *** Display Errors ***           ---------------  */
            else {
                def response = []
                return response
            }


            /*  --------------              *** Display Stats ***           ---------------  */
            //render params

            render(template: "/sharedTemplates/comments/displayAllComments", model: [person: person, post: post, postIndex: postId, currentUser: currentUser])


            //render params
            //render(view: "person/form-submit", model: [person: person, tabIndex: tabIndex, currentUser: currentUser])

            //template: "displayAllPosts"
            //respond(person, view: "person/stats/${personIndex}")
        }
    }

    @Secured([Role.ROLE_USER, Role.ROLE_ADMIN])
    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~ PUBLISH A BLOG POST ~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def approveComment() {
        //String personIndex, String tabIndex, String postTitle, String postDescription, String backgroundImage, String blogText

        /*------------------------------------------*
        * ===========================================
        * FUNCTION -> SHOW PLAYER STATS!
        * ===========================================
        * INPUTS:
        *     -
        * FUNCTIONS:
        *     - playGame(homeTeam, awayTeam)
        * DESCRIPTION:
        *     - Displays league standings in the browser
        * OUTPUT:
        *     -
        /*---------------------------------------------------------------------------------------------*/
        print(params)

        def personIndex = params.list('personIndex')
        def commentIndex = params.list('commentIndex')
        //String personIndex, String tabIndex,




        /*  --------------              *** Select Player ***           ---------------  */
        //personIndex = personIndex ?: "1"
        def person = Person.get(personIndex)
        Comment comment = Comment.get(commentIndex)

        /*  --------------            *** Authenticate User ***         ---------------  */
        def currentUser = springSecurityService.currentUser
        if(person.user != currentUser) {
            flash.message = "Please LOG IN. Anonymous users are not permitted to submit a comments."
        }
        else {

            /*  --------------             *** Add Post to DB ***           ---------------  */
            if (comment.validate()) {
                comment["approved"] = true
                saveObject(comment)
            }

            /*  --------------             *** Display Errors ***           ---------------  */
            else {
                def response = []
                return response
            }


            /*  --------------              *** Display Stats ***           ---------------  */
            //render params
            //String commentApprovalAction = "league/approveComment"

            //render(template: "/sharedTemplates/management/comments/pending", model: [person: person, currentUser: currentUser]) //, commentApprovalAction: commentApprovalAction
            render(template: "/sharedTemplates/management/comments/pending", model: [person: person, currentUser: currentUser])


            //render params
            //render(view: "person/form-submit", model: [person: person, tabIndex: tabIndex, currentUser: currentUser])

            //template: "displayAllPosts"
            //respond(person, view: "person/stats/${personIndex}")
        }
    }




}
