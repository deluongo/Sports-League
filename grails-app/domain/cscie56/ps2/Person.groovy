package cscie56.ps2

class Person {


	/*                          ==============  ***  ==============                          *
	 #  ---------------------        Class ~Person~ Definition         --------------------  #
	 *                          ===================================                          */



	/*  _________________________                                  ________________________  */
	/*  ========================= !!! ---*** PROPERTIES ***--- !!! ========================  */

	/*  -------------------         *** Instantiate Variables ***       -------------------  */

	//PS2
	String firstName
	String lastName
	String number
	String role

	//PS3
	String bio
	Date birthDate
	String birthPlace
	String height // must be displayed in feet and inches (e.g., 6’1”)
	Integer weight
	String universityAttended

	/*  -------------------          *** Database Designations ***      -------------------  */
	static mapping = {
		bio type: 'text'
	}


	/*  -------------------             *** List Transients ***         -------------------  */
	static transients = [
			/* ___  stats | totals  ___ */
			'minutesPlayed', 'points', 'assists', 'rebounds', 'steals', 'shotsAttempted', 'shotsMade', 'shootingPercentage',
			'threePointersAttempted', 'threePointersMade', 'threePointPercentage', 'personalFouls', 'gamesPlayed',
			/* ___  stats | per game  ___ */
			'minutesPerGame', 'pointsPerGame', 'assistsPerGame', 'reboundsPerGame', 'stealsPerGame', 'shotsAttemptedPerGame', 'shotsMadePerGame',
			'threePointersAttemptedPerGame', 'threePointersMadePerGame', 'personalFoulsPerGame',
			/* ___  helpers  ___ */
			'seasonTotals', 'perGameStats']

	/*  -------------------             *** GORM Mapping ***            -------------------  */
	static belongsTo = [team: Team]
	static hasMany = [gameStats: GameStats]
	/*  -------------------              *** Constraints ***            -------------------  */
	static constraints = {
	}
















	/*                          ==============  ***  ==============                          *
	 #  ---------------------                Functions                 --------------------  #
	 *                          ===================================                          */






	/*  _________________________                                  ________________________  */
	/*  ========================= !!! ---*** TRANSIENTS ***--- !!! ========================  */



	/*  -------------------                *** Totals ***               -------------------  */

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~ SEASON TOTALS ~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getSeasonTotals() {
			gameStats.sum()
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~ GAMES PLAYED ~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		Integer getGamesPlayed() {
			print(shotsAttempted)
			2
			//gameStats.size()
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ MINUTES ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		Integer getMinutesPlayed() {
			seasonTotals.minutesPlayed
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ POINTS ~~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		Integer getPoints() {
			seasonTotals.points
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ ASSISTS ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		Integer getAssists() {
			seasonTotals.assists
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~ REBOUNDS ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		Integer getRebounds() {
			seasonTotals.rebounds
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ STEALS ~~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		Integer getSteals() {
			seasonTotals.steals
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~ FG ATTEMPTS ~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		Integer getShotsAttempted() {
			seasonTotals.shotsAttempted
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~ FG MAKES ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		Integer getShotsMade() {
			seasonTotals.shotsMade
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~ FG PERCENTAGE ~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getShootingPercentage() {
			def pct
			if(shotsAttempted == 0) {
				pct = 0
			}
			else {
				pct = shotsMade/shotsAttempted
			}
			pct
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~ 3PT ATTEMPTS ~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		Integer getThreePointersAttempted() {
			seasonTotals.threePointersAttempted
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~ 3PT MAKES ~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		Integer getThreePointersMade() {
			seasonTotals.threePointersMade
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~ 3PT PERCENTAGE ~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		BigDecimal getThreePointPercentage() {
			def pct
			if(threePointersAttempted == 0) {
				pct = 0
			}
			else {
				pct = threePointersMade/threePointersAttempted
			}
			pct
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~~ FOULS ~~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		Integer getPersonalFouls() {
			seasonTotals.personalFouls
		}


	/*  -------------------               *** Per Game ***              -------------------  */


	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~ PER GAME STATS ~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getPerGameStats() {
			seasonTotals/gamesPlayed
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ MINUTES ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getMinutesPerGame() {
			minutesPlayed/gamesPlayed
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~ FG ATTEMPTS ~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getShotsAttemptedPerGame() {
			shotsAttempted/gamesPlayed
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~ FG MAKES ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getShotsMadePerGame() {
			shotsMade/gamesPlayed
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ POINTS ~~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getPointsPerGame() {
			points/gamesPlayed
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ ASSISTS ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getAssistsPerGame() {
			assists/gamesPlayed
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~ REBOUNDS ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getReboundsPerGame() {
			rebounds/gamesPlayed
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ STEALS ~~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getStealsPerGame() {
			steals/gamesPlayed
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~ 3PT ATTEMPTS ~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getThreePointersAttemptedPerGame() {
			threePointersAttempted/gamesPlayed
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~ 3PT MAKES ~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getThreePointersMadePerGame() {
			threePointersMade/gamesPlayed
		}

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~~ FOULS ~~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getPersonalFoulsPerGame() {
			personalFouls/gamesPlayed
		}



	/*  ---------------------------   ( transient functions )   ---------------------------  */
	/*  -----------------------------------   ~ END ~    ----------------------------------  */





	/*  ______________________                                        _____________________  */
	/*  ====================== !!! ---*** HELPER FUNCTIONS ***--- !!! =====================  */





	/*  -------------------             *** Game Results ***            -------------------  */


	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ POINTS SCORED ~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def scored(Integer totalPoints, ratio) {
			(int)(totalPoints * ratio)
		}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~ ASSISTS ~~~~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def assisted(Integer totalPoints, ratio) {
			def totalAssists = totalPoints / 4
			(int)(totalAssists * ratio)
		}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~ REBOUNDS ~~~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def rebounded(Integer totalPoints, ratio) {
			def totalRebounds = totalPoints * 2 / 3 - 20
			(int)(totalRebounds * ratio)
		}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~~ STEALS ~~~~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def stole(ratio) {
			def totalSteals = 16
			(int)(totalSteals * ratio)
		}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~~~ FG % ~~~~~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def shooting() {
			/* ___  generate random seed  ___ */
			Random random = new Random()
			/* ___  random FG%  ___ */
			random.nextInt(25) + 30
		}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~~ STEALS ~~~~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def sniping() {
			/* ___  generate random seed  ___ */
			Random random = new Random()
			/* ___  random FG%  ___ */
			random.nextInt(30) + 20
		}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~ 3PT vs 2PT SHOTS ~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def shotSelection(Integer pointsScored) {
			/* ___  generate random seed  ___ */
			Random random = new Random()
			/* ___  random % points off 3's ___ */
			def percentThrees = random.nextInt(8) / 10
			def shotChart
			/* ___  total 3's and 2's ___ */
			if(pointsScored % 2) {
				/*--|  get pts off 3's -> round to multiple of 6 -> get total 3's made |--*/
				Integer threes = (int) (pointsScored * percentThrees) / 6 * 6 / 3
				/*--|  get total points -> subtract pts off 3's -> get total 2's made |--*/
				Integer twos =  (pointsScored - threes * 3) / 2

				shotChart = [twos, threes]
			}
			else {
				/*--|  get pts off 3's -> round to multiple of 6 -> get total 3's made -> ensure odd number|--*/
				Integer threes = (int) (pointsScored * percentThrees) / 6 * 6 / 3 - 1
				/*--|  get total points -> subtract pts off 3's -> get total 2's made |--*/
				Integer twos =  (pointsScored - threes * 3) / 2

				shotChart = [twos, threes]
			}
			shotChart
		}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ SHOTS ATTEMPTED ~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getAttempts(Integer shotsMade, percentMade) {
			shotsMade / percentMade
		}

	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ SHOTS ATTEMPTED ~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def getFouls() {
			/* ___  generate random seed  ___ */
			Random random = new Random()
			/* ___  random FG%  ___ */
			random.nextInt(5)
		}

	/*  ----------------------------   ( helper functions )   -----------------------------  */
	/*  -----------------------------------   ~ END ~    ----------------------------------  */





	/*  __________________                                              ___________________  */
	/*  ================== !!! ---*** PRIMARY UPDATE FUNCTION ***--- !!!===================  */



	/*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~ STORE GAME RESULTS ~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		def storeResults(Integer totalPts, split) {

			Integer pointsScored = scored(totalPts, split)
			def fgPercent = shooting()
			def threePercent = sniping()
			def shotChart = shotSelection(pointsScored)
			Integer threesMade = shotChart[1]
			Integer twosMade = shotChart[0]
			Integer shotsMade = threesMade + twosMade
			Integer twosAttempted = getAttempts(twosMade, fgPercent)
			Integer threesAttempted = getAttempts(threesMade, threePercent)
			Integer shotAttempts = twosAttempted + threesAttempted

			GameStats newGameStats = new GameStats( player: this, minutesPlayed: 48,
					points: pointsScored, assists: assisted(totalPts, split),
					rebounds: rebounded(totalPts, split), steals: stole(split),
					shotsAttempted: shotAttempts,
					shotsMade: shotsMade,
					shootingPercentage: fgPercent,
					threePointersAttempted: threesAttempted,
					threePointersMade: threesMade,
					threePointPercentage: threePercent,
					personalFouls: getFouls()).save(flush:true)

			this.addToGameStats(newGameStats)
			this.save(flush:true)
			print(gameStats)
		}
	/*  ----------------------------   ( primary function )   -----------------------------  */
	/*  -----------------------------------   ~ END ~    ----------------------------------  */

}
/*  ------------------------------   ( domain class  )   ------------------------------  */
/*  -----------------------------------   ~ END ~    ----------------------------------  */


