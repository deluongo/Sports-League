package cscie56.ps2


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

    static scaffold = Team

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
