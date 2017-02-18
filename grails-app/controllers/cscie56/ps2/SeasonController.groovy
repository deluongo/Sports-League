package cscie56.ps2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

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


    /*  ______________________                                       ______________________  */
    /*  ====================== !!! ---*** PRIMARY FUNCTION ***--- !!!======================  */


    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	*   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~ SIM SEASON ~~~~~~~~~~~~~~
	*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def simSeason(String seasonName, Integer numGames) {
        /* ---------------------------------------------
        *  INPUTS:
        *     - Number of Games per Season (Integer numGames)
        *     - League of Teams (List league)
        *  FUNCTIONS:
        *     - playGame(homeTeam, awayTeam)
        *  DESCRIPTION:
        *     - Simulates an entire season of games for every team
        *  OUTPUT:
        *     - None
        *     - (Updates league list with simulated stats)
        /*---------------------------------------------------------------------------------------------*/


        /*  --------------          *** Loop Through Each Game ***      ---------------  */
        numGames.each{

            /*  --------------             *** Load Team List ***           ---------------  */
            /* ___  current season  ___ */
            Season season = Season.findByName(seasonName)
            /* ___  set calendar  ___ */
            Date date = season.startDate - 7
            def seasonDates = []
            /* ___  participating conferences  ___ */
            def conferences = Conference.findAllBySeasons(season)
            /* ___  participating teams  ___ */
            def teamList = []
            conferences.each{ conf -> teamList.addAll(Team.findAllByConference(conf))}

            /*  --------------             *** Simulate Games ***           ---------------  */
            Integer gmsPerNight = (Math.floor(teamList.size()/2))*2
            /* ___  weeks of games  ___ */
            for(int i=0; i<numGames; i++) {
                /* ___  randomize matchups  ___ */
                Collections.shuffle(teamList)
                /* ___  set calender week  ___ */
                date += 7
                seasonDates << date
                /* ___  each team plays once  ___ */
                for(int j=0; j<gmsPerNight; j+=2) {
                    playGame(teamList[j].name, teamList[j+1].name, seasonDates[i])
                }
            }
        }
    }


    /*  _____________________                                         _____________________  */
    /*  ===================== !!! ---*** SIMULATION HELPERS ***--- !!!=====================  */


    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~ PLAY GAME ~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def playGame(String homeTeamName, String awayTeamName, Date date) {
        /* -------------------------------------------------
        *  INPUTS:
        *     - Home Team (String homeTeam)
        *     - Away Team (String awayTeam)
        *     - Points 4 Home Team (Int ptsHome)
        *     - Points 4 Away Team (Int ptsAway)
        *  DESCRIPTION:
        *     - Updates wins, losses, ties, winPercent, scored, allowed, delta for Home & Away Teams
        /*---------------------------------------------------------------------------------------------*/


        /*  ---------------             *** Load ~Teams~ ***            ---------------  */
        Team homeTeam = Team.findByName(homeTeamName) //.where(season = thisSeason)
        Team awayTeam = Team.findByName(awayTeamName)

        /*  ---------------               *** Play Game ***             ---------------  */
        /* ___  generate random score  ___ */
        Random random = new Random()
        /* ___  w/ home court advantage  ___ */
        Integer ptsHome = random.nextInt(70) + 70
        Integer ptsAway = random.nextInt(65) + 65

        /*  ---------------           *** Determine Winner ***          ---------------  */
        Map result = determineResult(homeTeam, ptsHome, awayTeam, ptsAway)

        /*  --------------                *** Save Game ***             ---------------  */
        /* ___  create game instance ___ */
        Game newGame = new Game(homeTeam: homeTeamName, awayTeam: awayTeamName, winner: result["winner"], loser: result["loser"], homePoints: ptsHome,
                awayPoints: ptsAway, gameDate: date, location: homeTeam.location, hostTeam: homeTeam, guestTeam: awayTeam)
        /* ___  save game ___ */
        saveObject(newGame)

    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~ DETERMINE RESULT ~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def determineResult(Team homeTeam, ptsHome, Team awayTeam, ptsAway) {
        Map result
        /* ___  home team gets tie break  ___ */
        if (ptsHome == ptsAway) { ptsHome += 1 }
        /* ___  determine result  ___ */
        if (ptsHome > ptsAway) {
            /* ___  update team stats  ___ */
            homeTeam.storeResults(ptsHome, ptsAway, homeTeam.location)
            awayTeam.storeResults(ptsAway, ptsHome, homeTeam.location)
            /* ___  home team wins  ___ */
            result = [winner: homeTeam.name, loser:awayTeam.name]

        }
        else {
            /* ___  update team stats  ___ */
            homeTeam.storeResults(ptsHome, ptsAway, homeTeam.location)
            awayTeam.storeResults(ptsAway, ptsHome, homeTeam.location)
            /* ___  visiting team wins  ___ */
            result = [winner: awayTeam.name, loser:homeTeam.name]
        }
        result
    }
    /*  ---------------------------   ( simulate functions )   ----------------------------  */
    /*  -----------------------------------   ~ END ~    ----------------------------------  */



    def showStandings() {
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

        render(view: "showStandings")
    }


    /*                          ==============  ***  ==============                          *
     #  ---------------------                 SCRIPTS                 ---------------------  #
     *                          ===================================                          */


    /*  ______________________                                       ______________________  */
    /*  ====================== !!! ---*** SIMULATE SEASON ***--- !!! ======================  */
    simSeason('2017', 82)

    /*  ____________________                                           ____________________  */
    /*  ==================== !!! ---*** LOAD STANDINGS VIEW ***--- !!! ====================  */
    simSeason('2017', 82)

}
