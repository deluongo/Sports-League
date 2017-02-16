package cscie56.ps2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)


/*---------------------------------------------------------------------------------------------*
* ===========================================
* TEAM CONTROLLER!
* ===========================================
* PROPERTIES:
*     - name, streak, wins, losses, ties, scored, allowed, delta, winPercent, lastResult, result
* METHODS:
*     - wins() -> Updates Team wins, result & last result
*     - loses() -> Updates Team losses
*     - ties() -> Updates Team ties
*     - scores() -> Updates Team's total points scored
*     - allowed() -> Updates Team total points allowed
*     - calcDelta() -> Calculates Team's point differential
*     - calcWinPercent() -> Calculates Team's winning percentage
*     - calcStreak() -> Calculates the teams winning or loosing streaks
/*---------------------------------------------------------------------------------------------*/
class TeamController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /* ~~~~~~~~~~~~~~~~ INDEX ~~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~(primary method)~~~~~~~~~~~ */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Team.list(params), model:[teamCount: Team.count()]
    }

    /* ~~~~~~~~~~~~~~~~ SHOW ~~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~(displays page)~~~~~~~~~~~ */
    def show(Team team) {
        respond team
    }

    /* ~~~~~~~~~~~~~~~~ CREATE ~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~~(new season)~~~~~~~~~~~~~ */
    def create() {
        respond new Team(params)
    }

    /* ~~~~~~~~~~~~~~~~~ SAVE ~~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~~~(team data)~~~~~~~~~~~~~ */
    @Transactional
    def save(Team team) {
        if (team == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (team.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond team.errors, view:'create'
            return
        }

        team.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'team.label', default: 'Team'), team.id])
                redirect team
            }
            '*' { respond team, [status: CREATED] }
        }
    }

    /* ~~~~~~~~~~~~~~~~ EDIT ~~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~(change data)~~~~~~~~~~~~ */
    def edit(Team team) {
        respond team
    }

    /* ~~~~~~~~~~~~~~~~ UPDATE ~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~(add new data)~~~~~~~~~~~~ */
    @Transactional
    def update(Team team) {
        if (team == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (team.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond team.errors, view:'edit'
            return
        }

        team.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'team.label', default: 'Team'), team.id])
                redirect team
            }
            '*'{ respond team, [status: OK] }
        }
    }

    /* ~~~~~~~~~~~~~~~~ DELETE ~~~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~(remove season)~~~~~~~~~~~ */
    @Transactional
    def delete(Team team) {

        if (team == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        team.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'team.label', default: 'Team'), team.id])
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
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'team.label', default: 'Team'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }



    /*---------------------------------------------------------------------------------------------*
    * ===========================================
    * FUNCTIONS -> CALCULATE & UPDATE STATS!
    * ===========================================
    * INPUTS:
    *     -
    * FUNCTIONS:
    *     - wins()
    *     - loses()
    *     - ties()
    *     - scores(pts)
    *     - allows(pts)
    *     - calcDelta()
    *     - calcWinPercent()
    *     - calcStreak()
    * DESCRIPTION:
    *     - A suite of functions used to update team stats
    * OUTPUT:
    *     -
    /*---------------------------------------------------------------------------------------------*/

    /* ~~~~~~~~~~~~ UPDATE STATS ~~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    /* ---  Team Wins Game  --- */
    Integer wins() {
        wins += 1
        lastResult = result
        result = "W"
    }
    /* ---  Team Loses Game  --- */
    Integer loses() {
        losses += 1
        lastResult = result
        result = "L"
    }
    /* ---  Team Ties Game  --- */
    Integer ties() {
        ties += 1
        lastResult = result
        result = "T"
    }
    /* ---  Update Points Scored  --- */
    Integer scores(pts) {
        scored += pts
    }
    /* ---  Update Points Allowed  --- */
    Integer allows(pts) {
        allowed += pts
    }

    /* ~~~~~~~~~~ CALCULATE STATS ~~~~~~~~~~~~ */
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    /* ---  Calculate Point Differential  --- */
    Integer calcDelta()
    {
        delta = scored - allowed
        delta
    }
    /* ---  Calculate Winning Percentage  --- */
    BigDecimal calcWinPercent() {
        if (losses == 0) {
            if (wins == 0) {
                winPercent = 00.0
                return winPercent
            } else {
                winPercent = 100.0
                return winPercent
            }
        } else {
            winPercent = (wins / (wins + losses))*100
            return winPercent
        }
    }
    /* ---  Calculate Winning Streak  --- */
    String calcStreak() {
        Integer streakLength
        if (result == lastResult) {
            streakLength = streak.reverse().take(1).reverse().toInteger()
            streakLength = streakLength + 1
            streak = "$result$streakLength"
        }
        else {
            streak = "${result}1"
        }
        streak
    }

}
