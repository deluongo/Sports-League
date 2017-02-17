package cscie56.ps2


/*---------------------------------------------------------------------------------------------*
* ===========================================
*           TEAM <- DOMAIN CLASS
* ===========================================
* FUNCTIONS:
*     - saveTeam() <- creates a new Team
*     - savePlayer() <- creates a new Person of type Player
*     - saveCoach() <- creates a new Person of type Coach
*     - playGame() <- Simulates a game between two teams & updates Teams Tables with results
*     - simSeason() <- Simulates a season & updates all Teams Tables with results & stats
* TO DO:
*     - Create COMPLETE PLAYER ROSTER
*     - Add skill grade to IMPROVE SIMULATION
/*---------------------------------------------------------------------------------------------*/
class Team {

	/* ~~~~~~~~~~~~~~ PROPERTIES ~~~~~~~~~~~~~ */
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	String name, streak, location, homeRecord, roadRecord, l10
	Integer wins, losses, ties, scored, allowed, delta, seed, gamesBack, gamesPlayed
	BigDecimal winPercent
	Character lastResult, result

	static belongsTo = [conference:Conference]

	static hasMany = [persons: Person, homeGames: Game, roadGames: Game]
	static mappedBy = [homeGames: "hostTeam",
	                   roadGames: "guestTeam"]

    //static hasMany = [persons:Person]

    static constraints = {

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
