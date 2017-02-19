package cscie56.ps2

/*---------------------------------------------------------------------------------------------*
* ===========================================
*           TEAM <- DOMAIN CLASS
* ===========================================
* PROPERTIES:
*     - [Base] name, streak, location, homeRecord, roadRecord, l10, wins, losses, ties, scored,
*       allowed, delta, seed, gamesBack, gamesPlayed, winPercent, lastResult, result
*     - [Mapped Domain Classes] conference, persons, homeGames, roadGames
* FUNCTIONS:
*       ~* game results *~
*          ------------
*     - wins() <- Updates win total, last result, and result
*     - loses() <- Updates loss total, last result, and result
*     - ties() <- Updates tie total, last result, and result
*     - scores() <- Updates total points scored
*     - allows() <- Updates total points allowed
*       ~* calculate stats *~
*          ---------------
*     - calcDelta() <- Calculates the point differential
*     - calcWinPercent() <- Calculates winning percentage
*     - calcStreak() <- Calculates the winning streak
*     - calcHomeRecord() <- Calculates the record in home games
*     - calcRoadRecord() <- Calculates the record in away games
*     - calcLast10() <- Calculates the record in the last 10 games
*     - calcSeed() <- Calculates the conference ranking (AKA playoff seed)
*     - calcGamesBack() <- Calculates the record in the last 10 games
* TO DO:
*     - FINISH FUNCTIONS the calc l10, homeRecord, roadRecord, seed, and gamesBack
/*---------------------------------------------------------------------------------------------*/
class Team {


	/*                          ==============  ***  ==============                          *
	 #  ---------------------         Class ~Team~ Definition          --------------------  #
	 *                          ===================================                          */


    /*  _________________________                                  ________________________  */
	/*  ========================= !!! ---*** PROPERTIES ***--- !!! ========================  */

	/*  -------------------           *** Define Transients ***         -------------------  */


	/*  -------------------         *** Instantiate Variables ***       -------------------  */
	String name, streak, location, homeRecord, roadRecord, l10, lastResult, result
	Integer wins, losses, ties, delta, seed, gamesBack, gamesPlayed, scored, allowed
	BigDecimal winPercent

	/*  -------------------             *** GORM Mapping ***            -------------------  */
	static belongsTo = [conference:Conference]
	static hasMany = [persons: Person, homeGames: Game, roadGames: Game]
	static mappedBy = [homeGames: "hostTeam",
	                   roadGames: "guestTeam"]

	/*  -------------------              *** Constraints ***            -------------------  */
    static constraints = {
    }
	/*  ------------------------------ ( class definitions )   ----------------------------  */
	/*  -----------------------------------   ~ END ~    ----------------------------------  */

	/*                          ==============  ***  ==============                          *
	 #  ---------------------                Functions                 --------------------  #
	 *                          ===================================                          */

	/*  __________________                                              ___________________  */
	/*  ================== !!! ---*** PRIMARY UPDATE FUNCTION ***--- !!!===================  */


	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~ STORE GAME RESULTS ~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	def storeResults(Integer ptsScored, Integer ptsAllowed, String location) {
		/*  ---------------           *** Parse Properties ***          ---------------  */
		/* ___  games played  ___ */
		gamesPlayed += 1
		/* ___  scored  ___ */
		scores(ptsScored)
		/* ___  allowed  ___ */
		allows(ptsAllowed)
		/* ___ record ___ */
		if (ptsScored - ptsAllowed > 0) { wins(location) }
		else { loses(location) }
		/*  --------------             *** Calculate Stats ***          ---------------  */
		/* ___  winPercent  ___ */
		calcWinPercent()
		/* ___  delta  ___ */
		calcDelta()
		/* ___  streak  ___ */
		calcStreak()
		/* ___  last 10  ___ */
		calcLast10()
		/* ___  seed  ___ */
		Map rankedTeams = calcSeed()
		/* ___  games back  ___ */
		calcGamesBack(rankedTeams.west, rankedTeams.east)
	}


	/*  ______________________                                        _____________________  */
	/*  ====================== !!! ---*** HELPER FUNCTIONS ***--- !!! =====================  */


	/*  -------------------            *** Property Update***           -------------------  */

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~ WINS GAME ~~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	Integer wins(String gameLocation) {
		/*  ---------------              *** Parse Record ***           ---------------  */
		/* ___  home splits  ___ */
		Integer homeWins = homeRecord.split("-")[0].toInteger()
		Integer homeLosses = homeRecord.split("-")[1].toInteger()
		/* ___  road splits  ___ */
		Integer roadWins = roadRecord.split("-")[0].toInteger()
		Integer roadLosses = roadRecord.split("-")[1].toInteger()

		/*  ---------------              *** Update Stats ***           ---------------  */
		/* ___  home record  ___ */
		if(gameLocation == location) {homeRecord = "${homeWins+1}-${homeLosses}"}
		/* ___  away record  ___ */
		else {roadRecord = "${roadWins+1}-${roadLosses}"}
		/* ___  win total  ___ */
		wins += 1
		/* ___  recent results  ___ */
		lastResult = result
		result = "W"
	}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~ LOSES GAME ~~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	Integer loses(String gameLocation) {
		/*  ---------------              *** Parse Record ***           ---------------  */
		/* ___  home splits  ___ */
		Integer homeWins = homeRecord.split("-")[0].toInteger()
		Integer homeLosses = homeRecord.split("-")[1].toInteger()
		/* ___  road splits  ___ */
		Integer roadWins = roadRecord.split("-")[0].toInteger()
		Integer roadLosses = roadRecord.split("-")[1].toInteger()
		/*  ---------------              *** Update Stats ***           ---------------  */
		/* ___  home record  ___ */
		if(gameLocation == location) {homeRecord = "${homeWins}-${homeLosses+1}"}
		/* ___  away record  ___ */
		else {roadRecord = "${roadWins}-${roadLosses+1}"}
		/* ___  loss total  ___ */
		losses += 1
		/* ___  recent results  ___ */
		lastResult = result
		result = "L"
	}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~ TIES GAME ~~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	Integer ties() {
		ties += 1
		lastResult = result
		result = "T"
	}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~ POINTS SCORED ~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	Integer scores(pts) {
		scored += pts
		scored
	}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ POINTS ALLOWED ~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	Integer allows(pts) {
		allowed += pts
		allowed
	}


	/*  -------------------          *** Stat Calculations ***          -------------------  */

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~ POINT DIFFERENTIAL ~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	Integer calcDelta()
	{
		delta = scored - allowed
		delta
	}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~ WINNING PERCENTAGE ~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
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

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ WINNING STREAK ~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
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

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~ LAST 10 Games ~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	String calcLast10() {

		/*  --------------             *** List of Games ***            ---------------  */
		/* ___  games won ___ */
		def gamesWon = Game.findAllByWinner(name)
		/* ___  games lost  ___ */
		def gamesLost = Game.findAllByLoser(name)
		/* ___  all games  ___ */
		def gamesList = []
		if(gamesWon) { gamesList.addAll(gamesWon) }
		if(gamesLost) { gamesList.addAll(gamesLost) }
		/*  --------------           *** Find Last 10 Games ***         ---------------  */
		/* ___  sort by date played  ___ */
		def last10Games = gamesList.sort{it.gameDate}.reverse().take(10)
		/*  --------------         *** Find Games Won & Lost ***        ---------------  */
		/* ___  wins in last 10  ___ */
		def recentWins = last10Games.findAll{it.winner == name}
		/* ___  losses in last 10  ___ */
		def recentLosses = last10Games.findAll{it.loser == name}
		/*  --------------             *** Update Record ***            ---------------  */
		/* ___  calculate wins & losses  ___ */
		l10 = "${recentWins.size()}-${recentLosses.size()}"
	}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~ LAST 10 Games ~~~~~~~~~~~~
 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
def calcSeed() {

		/*  --------------            *** Load All Teams ***            ---------------  */
		/* ___  Load Conference Objects  ___ */
		def westernConference = Conference.findAllByName("Western Conference")
		def easternConference = Conference.findAllByName("Eastern Conference")
		/* ___  Teams List by Conference  ___ */
		def westTeams = Team.findAllByConference(westernConference)
		def eastTeams = Team.findAllByConference(easternConference)

		/*  --------------         *** Get Ranked Team List ***         ---------------  */
		/* ___  western conference teams  ___ */
		westTeams = westTeams.sort{it.wins}.reverse().take(10)
		/* ___  eastern conference teams  ___ */
		eastTeams = eastTeams.sort{it.wins}.reverse().take(10)

		/*  --------------              *** Assign Seeds ***            ---------------  */
		/* ___  seed west  ___ */
		westTeams.eachWithIndex{ team, idx -> team.seed = idx + 1}
		/* ___  seed east  ___ */
		eastTeams.eachWithIndex{ team, idx -> team.seed = idx + 1}

		/*  --------------        *** Return Ranked Team List ***       ---------------  */
		/* ___  join conference lists  ___ */
		Map rankedLists = [west: westTeams, east: eastTeams]
		/* ___  return  ___ */
		rankedLists
	}


	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~ Games Back ~~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	def calcGamesBack(westTeams, eastTeams) {

		/*  --------------          *** Determine First Place ***        ---------------  */
		/* ___  1st place wins  ___ */
		Integer westMaxWins = westTeams[0].wins
		Integer eastMaxWins = eastTeams[0].wins
		/* ___  1st place losses  ___ */
		Integer westMaxLosses = westTeams[0].losses
		Integer eastMaxLosses = eastTeams[0].losses

		/*  --------------          *** Calculate Games Back ***        ---------------  */
		/* ___  west teams  ___ */
		westTeams.eachWithIndex{ team, idx ->
			team.gamesBack = ( (westMaxWins - team.wins) + (team.losses - westMaxLosses) ) / 2
		}
		/* ___  east teams  ___ */
		eastTeams.eachWithIndex{ team, idx ->
			team.gamesBack = ( (eastMaxWins - team.wins) + (team.losses - eastMaxLosses) ) / 2
		}

	}
	/*  --------------------------------   ( functions )   --------------------------------  */
	/*  -----------------------------------   ~ END ~    ----------------------------------  */
}
/*  ------------------------------   ( domain class  )   ------------------------------  */
/*  -----------------------------------   ~ END ~    ----------------------------------  */