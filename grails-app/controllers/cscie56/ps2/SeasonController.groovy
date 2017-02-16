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

    /*---------------------------------------------------------------------------------------------*
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
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    /* ~~~~~~~~~~~~~~~~ INDEX ~~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~(primary method)~~~~~~~~~~~ */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Season.list(params), model:[seasonCount: Season.count()]
    }

    /* ~~~~~~~~~~~~~~~~ SHOW ~~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~(displays page)~~~~~~~~~~~ */
    def show(Season season) {
        respond season
    }

    /* ~~~~~~~~~~~~~~~~ CREATE ~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~~(new season)~~~~~~~~~~~~~ */
    def create() {
        respond new Season(params)
    }

    /* ~~~~~~~~~~~~~~~~~ SAVE ~~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~~~(team data)~~~~~~~~~~~~~ */
    @Transactional
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

    /* ~~~~~~~~~~~~~~~~ EDIT ~~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~(change data)~~~~~~~~~~~~ */
    def edit(Season season) {
        respond season
    }

    /* ~~~~~~~~~~~~~~~~ UPDATE ~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~(add new data)~~~~~~~~~~~~ */
    @Transactional
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

    /* ~~~~~~~~~~~~~~~~ DELETE ~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~(remove season)~~~~~~~~~~~ */
    @Transactional
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

    /* ~~~~~~~~~~~~~~ NOT FOUND ~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~(displays error)~~~~~~~~~~~ */
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'season.label', default: 'Season'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /*---------------------------------------------------------------------------------------------*
    * ===========================================
    * FUNCTION -> CREATES DATABASE TABLES
    * ===========================================
    * INPUTS:
    *     -
    * FUNCTIONS:
    *     - playGame(homeTeam, awayTeam)
    * DESCRIPTION:
    *     - Simulates an entire season of games for every team
    * OUTPUT:
    *     - None
    *     - (Updates league list with simulated stats)
    /*---------------------------------------------------------------------------------------------*/


    /* ~~~~~~~~~~~~~ CREATE LEAGUE ~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def createLeague(String leagueName) {
        /* ---  create league  --- */
        def newLeague = new League(name: leagueName)
        newLeague.save()
    }

    /* ~~~~~~~~~~~ CREATE CONFERENCE ~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def createConference(String conferenceName) {
        /* ---  create conference  --- */
        def newConference = new Conference(name: conferenceName)
        newConference.save()
    }


    /* ~~~~~~~~~~~~~ CREATE SEASON ~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def createSeason(String seasonName) {
        /* ---  create mtea  --- */
        def newSeason = new Season(name: seasoname)
        newSeason.save()
    }

    /* ~~~~~~~~~~~~~- CREATE TEAMS ~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def createTeam(teamName, streak, wins, losses, ties,
                   scored, allowed, delta, winPercent,
                   lastResult, result) {
        /* ---  create mtea  --- */
        def newTeam = new Team(name: teamName, streak: streak, wins: wins, losses: losses, ties: ties,
                scored: scored, allowed: allowed, delta: delta, winPercent: winPercent,
                lastResult: lastResult, result: result)

        newTeam.save()
    }


    /*---------------------------------------------------------------------------------------------*
    * ===========================================
    * FUNCTION -> SIMULATE A SEASON!
    * ===========================================
    * INPUTS:
    *     - Number of Games per Season (Integer numGames)
    *     - League of Teams (List league)
    * FUNCTIONS:
    *     - playGame(homeTeam, awayTeam)
    * DESCRIPTION:
    *     - Simulates an entire season of games for every team
    * OUTPUT:
    *     - None
    *     - (Updates league list with simulated stats)
    /*---------------------------------------------------------------------------------------------*/
    def simSeason() {

        /* ---  create NBA  --- */
        //def NBA = new League(name: "NBA")
        //NBA.save()

        /* ---  create conferences  --- */

        /* ---  create teams  --- */
        //createTeam("Warriors")
        //def team = Team.findByName("Warriors")
        //numGames.each{
        //    tempLeague = league
        //    Collections.shuffle(tempLeague)
        //    gmsPerNight = (Math.floor(league.size()/2))*2
        //    for(i=0; i<numGames; i++) {
        //        for(j=0; j<gmsPerNight; j+=2) {
        //            playGame(tempLeague[j], tempLeague[j+1])
        //       }

        //    }
        //}

        render "You successfully added ${team}"
    }


    /*---------------------------------------------------------------------------------------------*
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
    def showStandings() {
        createTeam("Warriors", "w2", 1,  1, 1,  89, 76, 13, 0.5,  "w",  "l")

        render "You successfully added the ${Team.findByName("Warriors").name}"
    }

}
