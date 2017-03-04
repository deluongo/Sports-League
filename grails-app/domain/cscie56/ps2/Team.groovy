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

	/*  -------------------         *** Instantiate Variables ***       -------------------  */
	String name, location, lastResult, result
	Integer wins, losses, ties, seed, gamesPlayed, scored, allowed

	/*  -------------------           *** List Transients ***         -------------------  */
	static transients = [
			/* ___  stats  ___ */
			'homeRecord', 'roadRecord', 'delta', 'streak', 'l10', 'seed', 'winPercent', 'gamesBack',
			/* ___  helpers  ___ */
			'conferenceLeader', 'conferenceRankings', 'allGames', 'last10Games']

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






	/*  _________________________                                  ________________________  */
	/*  ========================= !!! ---*** TRANSIENTS ***--- !!! ========================  */



	/*  -------------------                *** Record ***               -------------------  */

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~ HOME RECORD ~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	String getHomeRecord() {

		/* ___  instantiate  ___ */
		Integer homeWins = 0
		Integer homeLosses = 0
		/* ___  loop home games  ___ */
		homeGames = allGames.findAll{it.homeTeam == name}

		homeGames.each{
			/* ___  home wins  ___ */
			if (it.winner == name) { homeWins += 1}
			/* ___  home losses  ___ */
			else { homeLosses += 1}
		}
		/* ___  home record  ___ */
		"${homeWins}-${homeLosses}"
	}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~ ROAD RECORD ~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
	String getRoadRecord() {
		/* ___  instantiate  ___ */
		Integer roadWins = 0
		Integer roadLosses = 0
		roadGames = roadGames.flatten().unique { a, b -> a.gameDate <=> b.gameDate }
		/* ___  loop home games  ___ */
		roadGames.each{
			/* ___  home wins  ___ */
			if (it.winner == name) { roadWins += 1}
			/* ___  home losses  ___ */
			else { roadLosses += 1}
		}
		/* ___  road record  ___ */
		"${roadWins}-${roadLosses}"
	}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~ WINNING PERCENT ~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
	BigDecimal getWinPercent() {
		if (losses == 0) {
			if (wins == 0) {
				return 00.0
			} else {
				return 100.0
			}
		} else {
			return (wins / (wins + losses))*100
		}
	}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~ LAST 10 GAMES ~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	String getL10() {
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
		"${recentWins.size()}-${recentLosses.size()}"
	}


	/*  -------------------                *** Points ***               -------------------  */

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~ POINT DIFFERENTIAL ~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	String getDelta() {
		scored - allowed
	}


	/*  ---------------              *** Parse Record ***           ---------------  */



	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~~~ STREAK ~~~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	String getStreak() {
		/*--|  get ALL GAMES -> sort by MOST RECENT  |--*/
		def schedule = allGames.reverse()
		/*--|  determine WINNER of LAST GAME  |--*/
		def lastWinner = schedule[0].winner
		/*--|  determine RESULT of LAST GAME  |--*/
		if (lastWinner == name) {
			if (schedule.drop(0).findIndexOf{ it.winner != name } == -1) {
				return "$lastResult${schedule.size()}"
			}
			else {
				/*--|  IF WIN return 'W' + INDEX of LAST LOSS as STREAK LENGTH  |--*/
				return "$lastResult${schedule.drop(0).findIndexOf { it.winner != name }}"
			}
			// SAMPLE OUTPUT: "W2"
		}
		else {
			if (schedule.drop(0).findIndexOf{ it.winner == name } == -1) {
				return "$lastResult${schedule.size()}"
			}
			else {
				/*--|  IF LOSS return 'L" + INDEX of LAST WIN as STREAK LENGTH  |--*/
				return "$lastResult${schedule.drop(0).findIndexOf { it.winner == name }}"
				// SAMPLE OUTPUT: "L1"
			}
		}
	}


	/*  ---------------                *** Standings ***            ---------------  */

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~ PLAYOFF SEEDING ~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	def getSeed() {
		/*--|  SORTED BY WIN PERCENT  |--*/
		conferenceRankings.indexOf(this)+1
	}


	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ GAMES BACK ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	Integer getGamesBack() {
		/*--|  (lead wins - trail wins) + (trail losses - lead losses  |--*/
		( (conferenceLeader.wins - wins) + (losses - conferenceLeader.losses) ) / 2
	}


	/*  ---------------                 *** Helpers ***             ---------------  */

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ CONFERENCE LEADER ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	Team getConferenceLeader() {
		/*--|  GET 1st place TEAM  |--*/
		conferenceRankings[0]
	}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~ LIST CONFERENCE STANDINGS ~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	def getConferenceRankings() {
		/*--|  LIST in conference TEAM -> SORT by WIN%  |--*/
		Team.findAllByConference(conference).sort{it.winPercent}.reverse()
	}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~~ LIST ALL GAMES ~~~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	//Timeline ordering
	def getAllGames() {
		/*--|  SORTED BY DATE -> first to last  |--*/
		(homeGames << roadGames).sort{it.gameDate}.flatten().unique { a, b -> a.gameDate <=> b.gameDate }.reverse()
	}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~ LIST LAST 10 GAMES ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	def getLast10Games() {
		/*--|  get GAMES LIST -> SORT by DATE -> get LAST 10  |--*/
		allGames.sort(it.date).take(10)
	}



	/*  ---------------------------   ( transient functions )   ---------------------------  */
	/*  -----------------------------------   ~ END ~    ----------------------------------  */





	/*  ______________________                                        _____________________  */
	/*  ====================== !!! ---*** HELPER FUNCTIONS ***--- !!! =====================  */





	/*  -------------------             *** Game Results ***            -------------------  */

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~ WINS GAME ~~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	Integer wins(String gameLocation) {
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
	/*  ----------------------------   ( helper functions )   -----------------------------  */
	/*  -----------------------------------   ~ END ~    ----------------------------------  */





	/*  __________________                                              ___________________  */
	/*  ================== !!! ---*** PRIMARY UPDATE FUNCTION ***--- !!!===================  */



	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~ STORE GAME RESULTS ~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	def storeResults(Integer ptsScored, Integer ptsAllowed, String location) {
		/*  ---------------            *** Update Properties ***           ---------------  */
		/* ___  games played  ___ */
		gamesPlayed += 1
		/* ___  scored  ___ */
		scores(ptsScored)
		/* ___  allowed  ___ */
		allows(ptsAllowed)
		/* ___ record ___ */
		if (ptsScored - ptsAllowed > 0) { wins(location) }
		else { loses(location) }
	}
	/*  ----------------------------   ( primary function )   -----------------------------  */
	/*  -----------------------------------   ~ END ~    ----------------------------------  */





}
/*  ------------------------------   ( domain class  )   ------------------------------  */
/*  -----------------------------------   ~ END ~    ----------------------------------  */