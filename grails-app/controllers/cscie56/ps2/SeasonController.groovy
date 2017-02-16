package cscie56.ps2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)



/*---------------------------------------------------------------------------------------------*
* ===========================================
* CONTROLLER -> SEASON!
* ===========================================
* PROPERTIES:
*     - name, startDate, endDate
* METHODS:
*     - showStandings() -> Displays season standings.
*     - wins() -> Updates Team wins, result & last result
*     - loses() -> Updates Team losses
*     - ties() -> Updates Team ties
*     - scores() -> Updates Team's total points scored
*     - allowed() -> Updates Team total points allowed
*     - calcDelta() -> Calculates Team's point differential
*     - calcWinPercent() -> Calculates Team's winning percentage
*     - calcStreak() -> Calculates the teams winning or loosing streaks
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
    def simSeason(List league, Integer numGames) {
        /* ---  create league  --- */
        def NBA = new League(name: "NBA")
        NBA.save()
        /* ---  create conferences  --- */

        /* ---  create teams  --- */

        numGames.each{
            tempLeague = league
            Collections.shuffle(tempLeague)
            gmsPerNight = (Math.floor(league.size()/2))*2
            for(i=0; i<numGames; i++) {
                for(j=0; j<gmsPerNight; j+=2) {
                    playGame(tempLeague[j], tempLeague[j+1])
                }

            }
        }
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
        league.sort{-it.winPercent}
        printf("--------------------\n")
        printf("Basketball Standings\n")
        printf("--------------------\n\n")
        printf("%-8s  %5s   %2s   %-6s   %-6s %1s %9s %6s %8s%n", "Team", "Wins", "Losses", "Ties", "%", "Scored", "Allowed", "Delta", "Streak")
        printf("---------------------------------------------------------------------------%n");
        league.each {
            System.out.printf("%-7s  %5d   %4d   %6d   %6.1f%% %7d  %7s %7s %7s%n", it.name, it.wins, it.losses, it.ties, it.winPercent, it.scored, it.allowed, it.delta, it.streak)
        }
    }

}
