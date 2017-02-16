package cscie56.ps2

class GameController {

    static scaffold = Game

    /*---------------------------------------------------------------------------------------------*
* ===========================================
* FUNCTION -> PLAY GAME!
* ===========================================
* INPUTS:
*     - Home Team (String homeTeam)
*     - Away Team (String awayTeam)
*     - Points 4 Home Team (Int ptsHome)
*     - Points 4 Away Team (Int ptsAway)
* DESCRIPTION:
*     - Updates wins, losses, ties, winPercent, scored, allowed, delta for Home & Away Teams
*     -
/*---------------------------------------------------------------------------------------------*/
    def playGame(homeTeam, awayTeam) {

        /* ~~~~~~~~~~~ RANDOM WINNER ~~~~~~~~~~~~~ */
        /* ~~~~~~ (w/ home court advantage) ~~~~~~ */
        Random random = new Random()
        Integer ptsHome = random.nextInt(70) + 70
        Integer ptsAway = random.nextInt(65) + 65

        /* ~~~~~~~~~~ UPDATE TEAM STATS ~~~~~~~~~~ */
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        /* ---  scored  --- */
        homeTeam.scores(ptsHome)
        awayTeam.scores(ptsAway)
        /* ---  allowed  --- */
        homeTeam.allows(ptsAway)
        awayTeam.allows(ptsHome)
        /* --- record --- */
        String winner
        if (ptsHome > ptsAway) {
            winner = homeTeam.name
            homeTeam.wins()
            awayTeam.loses()
        }
        else if (ptsHome < ptsAway) {
            winner = awayTeam.name
            homeTeam.loses()
            awayTeam.wins()
        }
        else {
            winner = null
            homeTeam.ties()
            awayTeam.ties()
        }
        /* ---  winPercent  --- */
        homeTeam.calcWinPercent()
        awayTeam.calcWinPercent()
        /* ---  delta  --- */
        homeTeam.calcDelta()
        awayTeam.calcDelta()
        /* ---  streak  --- */
        homeTeam.calcStreak()
        awayTeam.calcStreak()

        /* ~~~~~~~~~~~~ PRINT RESULTS ~~~~~~~~~~~~ */
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        //println """
        //$winner Wins!
        //==============
        //$homeTeam.name $ptsHome
        //$awayTeam.name $ptsAway
        //"""
    }

}
