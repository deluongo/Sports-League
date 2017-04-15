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
        def tabIndex = params.list('tabIndex')
        def validation_error = []
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

            /*  --------------             *** Default Tab Idx ***          ---------------  */
            tabIndex = tabIndex ?: "personal"


            /*  --------------              *** Display Stats ***           ---------------  */
            render params

            //render(template: "/sharedTemplates/displayAllPosts", model: [person: person, tabIndex: tabIndex, currentUser: currentUser])


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
    def savePost() {
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
        def tabIndex = params.list('tabIndex')
        def validation_error = []
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

            /*  --------------             *** Default Tab Idx ***          ---------------  */
            tabIndex = tabIndex ?: "personal"


            /*  --------------              *** Display Stats ***           ---------------  */

            //render params
            //render(view: "person/form-submit", model: [person: person, tabIndex: tabIndex, currentUser: currentUser])
            render(template: "/sharedTemplates/displayAllPosts", model: [person: person, tabIndex: tabIndex, currentUser: currentUser])
            //template: "displayAllPosts"
            //respond(person, view: "person/stats/${personIndex}")
        }

    }
}
