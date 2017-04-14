package cscie56.ps3
import cscie56.ps2.Team
import cscie56.ps5.User

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
    //String bio
    //Date birthDate
    //String birthPlace
    //String height // must be displayed in feet and inches (e.g., 6’1”)
    //Integer weight
    //String universityAttended
    //String pictureURL


    //PS5
    User user

    /*  -------------------              *** Constraints ***            -------------------  */
    static constraints = {
        user unique: true
    }


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
            'seasonTotals', 'perGameStats',
            'bio', 'birthDate', 'birthPlace', 'height', 'weight', 'universityAttended', 'pictureURL']

    /*  -------------------             *** GORM Mapping ***            -------------------  */
    static belongsTo = [team: Team]
    static hasMany = [gameStats: GameStats]

















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
        gameStats.size()
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
        (shotsMade/shotsAttempted * 100 as double).round(1)
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
    def getThreePointPercentage() {
        (threePointersMade/threePointersAttempted * 100 as double).round(1)
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
        (seasonTotals/gamesPlayed as double).round(1)
    }

    /*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ MINUTES ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def getMinutesPerGame() {
        (minutesPlayed/gamesPlayed as double).round(1)
    }

    /*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~ FG ATTEMPTS ~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def getShotsAttemptedPerGame() {
        (shotsAttempted/gamesPlayed as double).round(1)
    }

    /*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~ FG MAKES ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def getShotsMadePerGame() {
        (shotsMade/gamesPlayed as double).round(1)
    }

    /*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ POINTS ~~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def getPointsPerGame() {
        (points/gamesPlayed as double).round(1)
    }

    /*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ ASSISTS ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def getAssistsPerGame() {
        (assists/gamesPlayed as double).round(1)
    }

    /*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~ REBOUNDS ~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def getReboundsPerGame() {
        (rebounds/gamesPlayed as double).round(1)
    }

    /*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~ STEALS ~~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def getStealsPerGame() {
        (steals/gamesPlayed as double).round(1)
    }

    /*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~ 3PT ATTEMPTS ~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def getThreePointersAttemptedPerGame() {
        (threePointersAttempted/gamesPlayed as double).round(1)
    }

    /*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~ 3PT MAKES ~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def getThreePointersMadePerGame() {
        (threePointersMade/gamesPlayed as double).round(1)
    }

    /*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~~~~~~ FOULS ~~~~~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def getPersonalFoulsPerGame() {
        (personalFouls/gamesPlayed as double).round(1)
    }


    /*  -------------------              *** User Props ***             -------------------  */


    /*  ~~~~~~~~~~~~~~~~~~~~~~~~~
	 *  ~~~~ PLAYER BIO ~~~~~
	 *  ~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def getBio() {
        user.bio
    }
    def getBirthDate() {
        user.birthDate
    }
    def getBirthPlace() {
        user.birthPlace
    }
    def getHeight() {
        user.height
    }
    def getWeight() {
        user.weight
    }
    def getUniversityAttended() {
        user.universityAttended
    }
    def getPictureURL() {
        user.pictureURL
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
    def missed2s() {
        /* ___  generate random seed  ___ */
        Random random = new Random()
        /* ___  random misses  ___ */
        random.nextInt(15)
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~~ STEALS ~~~~~~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def missed3s() {
        /* ___  generate random seed  ___ */
        Random random = new Random()
        /* ___  random FG%  ___ */
        random.nextInt(10)
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
            Integer threes = (int) (pointsScored * percentThrees) / 6 * 6 / 3
            if(threes > 1) {
                threes -= 1
            }
            else {
                threes = 1
            }
            /*--|  get total points -> subtract pts off 3's -> get total 2's made |--*/
            Integer twos =  (pointsScored - threes * 3) / 2

            shotChart = [twos, threes]
        }
        shotChart
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ SHOTS ATTEMPTED ~~~~~~~~~~~
	 *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def calcPercent(Integer shotsMade, Integer shotAttempts) {
        if(shotAttempts == 0) {
            0.0
        }
        else {
            Math.round(shotsMade / shotAttempts * 1000) / 1000 * 100
        }

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
    def storeResults(Integer totalPts, split, gameObj) {

        Integer pointsScored = scored(totalPts, split)
        def shotChart = shotSelection(pointsScored)
        Integer madeThrees = shotChart[1]
        Integer twosMade = shotChart[0]
        Integer madeShots = madeThrees + twosMade
        Integer twosAttempted = twosMade + missed2s()
        Integer threesAttempted = madeThrees + missed3s()
        Integer shotAttempts = twosAttempted + threesAttempted

        GameStats newGameStats = new GameStats( player: this, minutesPlayed: 48,
                points: pointsScored, assists: assisted(totalPts, split),
                rebounds: rebounded(totalPts, split), steals: stole(split),
                shotsAttempted: shotAttempts,
                shotsMade: madeShots,
                shootingPercentage: calcPercent(madeShots, shotAttempts),
                threePointersAttempted: threesAttempted,
                threePointersMade: madeThrees,
                threePointPercentage: calcPercent(madeThrees, threesAttempted),
                personalFouls: getFouls(),
                game: gameObj).save(flush:true)


        this.addToGameStats(newGameStats)
        this.save(flush:true)
    }
    /*  ----------------------------   ( primary function )   -----------------------------  */
    /*  -----------------------------------   ~ END ~    ----------------------------------  */

}
/*  ------------------------------   ( domain class  )   ------------------------------  */
/*  -----------------------------------   ~ END ~    ----------------------------------  */