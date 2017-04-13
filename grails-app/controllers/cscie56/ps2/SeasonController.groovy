package cscie56.ps2
import cscie56.ps3.Person


/* ___  domain classes  ___ */
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)



/*---------------------------------------------------------------------------------------------*
* ===========================================
* SEASON CONTROLLER!
* ===========================================
* PROPERTIES:
*     - name, startDate, endDate
* METHODS:
*     - showStandings() -> Displays season standings.
*     - simSeason() -> simulates a season
/*---------------------------------------------------------------------------------------------*/
@Secured(['permitAll'])
class SeasonController {

    /*                          ==============  ***  ==============                          *
     #  ---------------------             CRUD Operations             ---------------------  #
     *                          ===================================                          */


    /*  ______________________                                       ______________________  */
    /*  ====================== !!! ---*** BUILT IN METHODS ***--- !!! =====================  */



    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    /*--------------------------------------------------------------------*
    * ===========================================
    * BUILT-IN METHODS -> DISPLAY FORM!
    * ===========================================
    * METHODS:
    *     - save (uses POST)
    *     - update (uses PUT)
    *     - delete (uses DELETE)
    * DESCRIPTION:
    *     - Update season data
    /*---------------------------------------------------------------------------------------------*/

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~~~ INDEX ~~~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Season.list(params), model:[seasonCount: Season.count()]
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~~~ SHOW ~~~~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def show(Season season) {
        respond season
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~~ CREATE ~~~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def create() {
        respond new Season(params)
    }

    @Transactional
    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~~~ SAVE ~~~~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def save(Season season) {
        if (season == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (season.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond season.errors, view:'create'
            return
        }

        season.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'season.label', default: 'Season'), season.id])
                redirect season
            }
            '*' { respond season, [status: CREATED] }
        }
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~~~ EDIT ~~~~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def edit(Season season) {
        respond season
    }

    @Transactional
    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~~ UPDATE ~~~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def update(Season season) {
        if (season == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (season.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond season.errors, view:'edit'
            return
        }

        season.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'season.label', default: 'Season'), season.id])
                redirect season
            }
            '*'{ respond season, [status: OK] }
        }
    }

    @Transactional

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~~ DELETE ~~~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def delete(Season season) {

        if (season == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        season.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'season.label', default: 'Season'), season.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }


    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~ NOT FOUND ~~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'season.label', default: 'Season'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /*                          ==============  ***  ==============                          *
     #  ---------------------            CUSTOM FUNCTIONS             ---------------------  #
     *                          ===================================                          */



    /*  ----------------------------   ( helper functions )   -----------------------------  */
    /*  -----------------------------------   ~ END ~    ----------------------------------  */


    /*                          ==============  ***  ==============                          *
     #  ---------------------             CUSTOM RENDERING            ---------------------  #
     *                          ===================================                          */

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ SHOW STANDINGS ~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def showStandings(String conferenceIndex) {
        /*------------------------------------------*
        * ===========================================
        * FUNCTION -> SHOW STANDINGS!
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
        String nav = "standings"
        /* ___  open standings view ___ */
        render(view: "showStandings", model: [seasonName: westernConference.seasons.name.first(), teamList: team, nav: nav, conferenceIndex: conferenceIndex])
    }
    /*  ----------------------------   ( custom rendering )   -----------------------------  */
    /*  -----------------------------------   ~ END ~    ----------------------------------  */

    /*                          ==============  ***  ==============                          *
     #  ---------------------             CUSTOM RENDERING            ---------------------  #
     *                          ===================================                          */

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~ SHOW LEADERBOARD ~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def leaderboard(String conferenceName) {
        /*------------------------------------------*
        * ===========================================
        * FUNCTION -> SHOW STANDINGS!
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

        /*  --------------               *** Sim Season  ***            ---------------  */
        //simSeason('2017', 82)

        /*  --------------            *** Load All Teams ***            ---------------  */
        /* ___  Load Conference Objects  ___ */
        def westernConference = Conference.findAllByName("Western Conference")
        def easternConference = Conference.findAllByName("Eastern Conference")
        /* ___  Teams List by Conference  ___ */
        def westTeams = Team.findAllByConference(westernConference)
        def eastTeams = Team.findAllByConference(easternConference)
        def allTeams = Team.findAll()
        /* ___  Players List by Conference  ___ */
        def westPlayers = []
        westTeams.each{ westPlayers.add(it.persons)}
        westPlayers = westPlayers.flatten()
        def eastPlayers = []
        eastTeams.each{ eastPlayers.add(it.persons)}
        eastPlayers = eastPlayers.flatten()
        def allPlayers = Person.findAll()

        /* ___  Remove Coaches  ___ */
        westPlayers.removeIf { it.role != "player" }
        eastPlayers.removeIf { it.role != "player" }
        allPlayers.removeIf { it.role != "player" }

        //Person.getAll()

        /*  --------------          *** Create Conference Map  ***      ---------------  */

        /* ___  Ensure Page Load for /season/showStandings  ___ */
        def team
        def players
        if (conferenceName == 'east') {
            team = eastTeams
            players = eastPlayers
        }
        else if (conferenceName == 'west') {
            team = westTeams
            players = westPlayers
        }
        else {
            conferenceName = "show"
            team = allTeams
            players = allPlayers
        }

        /*  --------------            *** Determine Leaders  ***        ---------------  */

        /*--|  sort players by PPG -> get top 5 players  |--*/
        def pointsRankings = players.sort{it.pointsPerGame}.reverse()
        //print(pointsRankings)
        def pointsLeaders = pointsRankings.take(5)
        /*--|  sort players by APG -> get top 5 players  |--*/
        def assistsRankings = players.sort{it.assistsPerGame}.reverse()
        def assistsLeaders = assistsRankings.take(5)
        /*--|  sort players by RPG -> get top 5 players  |--*/
        def reboundsRankings = players.sort{it.reboundsPerGame}.reverse()
        def reboundsLeaders = reboundsRankings.take(5)
        /*--|  sort players by SPG -> get top 5 players  |--*/
        def stealsRankings = players.sort{it.stealsPerGame}.reverse()
        def stealsLeaders = stealsRankings.take(5)
        /*--|  sort players by FG% -> get top 5 players  |--*/
        def fgPercentRankings = players.sort{it.shootingPercentage}.reverse()
        def fgPercentLeaders = fgPercentRankings.take(5)
        /*--|  sort players by 3PT% -> get top 5 players  |--*/
        def threePercentRankings = players.sort{it.threePointPercentage}.reverse()
        def threePercentLeaders = threePercentRankings.take(5)


        /*  --------------            *** Display Standings ***         ---------------  */
        /* ___  open standings view ___ */
        render(view: "leaderboard",
                model: [seasonName: westernConference.seasons.name.first(), teamList: team,
                        scoringLeaders: pointsLeaders,  assistLeaders: assistsLeaders,
                        reboundLeaders: reboundsLeaders, stealLeaders: stealsLeaders,
                        shootingPercentLeaders: fgPercentLeaders, threePercentLeaders: threePercentLeaders
                ])
    }

    /*  ----------------------------   ( custom rendering )   -----------------------------  */
    /*  -----------------------------------   ~ END ~    ----------------------------------  */




}
/*  ----------------------------   ( season controller )   ----------------------------  */
/*  -----------------------------------   ~ END ~    ----------------------------------  */
