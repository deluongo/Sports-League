package cscie56.ps2


/*---------------------------------------------------------------------------------------------*
* ===========================================
* CLASS -> TEAM!
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
class Team {
	/* ~~~~~~~~~~~~~~ PROPERTIES ~~~~~~~~~~~~~ */
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	String name, streak
	Integer wins, losses, ties, scored, allowed, delta
	BigDecimal winPercent
	Character lastResult, result
    def coachingStaff
    def roster
    Integer id

	/* ~~~~~~~~~~~~~~~ 1-to-Many ~~~~~~~~~~~~~ */
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    static hasMany = [persons:Person]


    static constraints = {

    }
}
