/* ___  packages  ___ */
package sports.league
/* ___  domain classes  ___ */
import cscie56.ps2.League
import cscie56.ps2.Conference
import cscie56.ps2.Season
import cscie56.ps2.Team
import cscie56.ps2.Game
import cscie56.ps2.Person

/*---------------------------------------------------------------------------------------------*
* ===========================================
*           INITIALIZES DATABASE
* ===========================================
* DESCRIPTION:
*     - Creates new tables and rows for Leagues, Seasons, Conferences, Teams, Persons, and Games.
*     - Simulates an 82 game NBA season, filling all tables with dummy data.
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

class BootStrap {

    /*                          ==============  ***  ==============                          *
     #  ---------------------             Initialize Server           ---------------------  #
     *                          ===================================                          */


    def init = { servletContext ->

        /*  ---------------             *** NBA ~League~ ***            ---------------  */
        League nba = new League(name: 'NBA')
        saveObject(nba)

        /*  ---------------             *** 2017 ~Season~ ***           ---------------  */
        Season season = new Season(name: '2017', startDate: new Date(), endDate: new Date(), league: nba)
        saveObject(season)

        /*  ---------------          *** Western ~Conference~ ***       ---------------  */
        Conference west = new Conference(name: 'Western Conference', seasons: season)
        saveObject(west)

        /*      ----------- !!! -----------      */
        /* ---     GOLDEN STATE WARRIORS     --- */
        /*      ---------------------------      */
        /* ___  ~Team~  ___ */
        Team warriors = saveTeam("Warriors", "W0", 0,  0, 0,  0, 0, 0, 0.0,  "-",  "-", "Golden State", west)
        /* ___  ~Person~ [starters]  ___ */
        savePlayer("Steph", "Curry", 30, 0, warriors)
        savePlayer("Klay", "Thompson", 11, 0, warriors)
        savePlayer("Kevin", "Durant", 35, 0, warriors)
        savePlayer("Draymon", "Green", 23, 0, warriors)
        savePlayer("Zaza", "Pachulia", 27, 0, warriors)
        /* ___  ~Person~ [coach]  ___ */
        saveCoach("Steve", "Kerr", warriors)

        /*      ----------- !!! -----------      */
        /* ---       SAN ANTONIO SPURS       --- */
        /*      ---------------------------      */
        /* ___  ~Team~  ___ */
        Team spurs = saveTeam("Spurs", "W0", 0,  0, 0,  0, 0, 0, 0.0,  "-",  "-", "San Antonio", west)
        /* ___  ~Person~ [starters]  ___ */
        savePlayer("Kawhi", "Leonard", 2, 0, spurs)
        savePlayer("Lamarcus", "Aldridge", 12, 0, spurs)
        savePlayer("Pau", "Gasol", 16, 0, spurs)
        savePlayer("Manu", "Ginobili", 20, 0, spurs)
        savePlayer("Danny", "Green", 12, 0, spurs)
        /* ___  ~Person~ [coach]  ___ */
        saveCoach("Gregg", "Popovich", spurs)

        /*      ----------- !!! -----------      */
        /* ---        HOUSTON ROCKETS        --- */
        /*      ---------------------------      */
        /* ___  ~Team~  ___ */
        Team rockets = saveTeam("Rockets", "W0", 0,  0, 0,  0, 0, 0, 0.0,  "-",  "-", "Houston", west)
        /* ___  ~Person~ [starters]  ___ */
        savePlayer("James", "Harden", 13, 0, rockets)
        savePlayer("Trevor", "Ariza", 1, 0, rockets)
        savePlayer("Ryan", "Anderson", 3, 0, rockets)
        savePlayer("Eric", "Gordon", 10, 0, rockets)
        savePlayer("Patrick", "Beverly", 2, 0, rockets)
        /* ___  ~Person~ [coach]  ___ */
        saveCoach("Mike", "D'Antoni", rockets)

        /*      ----------- !!! -----------      */
        /* ---           UTAH JAZZ           --- */
        /*      ---------------------------      */
        /* ___  ~Team~  ___ */
        Team jazz = saveTeam("Jazz", "W0", 0,  0, 0,  0, 0, 0, 0.0,  "-",  "-", "Utah", west)
        /* ___  ~Person~ [starters]  ___ */
        savePlayer("Gordon", "Hayward", 20, 0, jazz)
        savePlayer("George", "Hill", 3, 0, jazz)
        savePlayer("Rodney", "Hood", 5, 0, jazz)
        savePlayer("Rudy", "Gobert", 27, 0, jazz)
        savePlayer("Derrick", "Favors", 15, 0, jazz)
        /* ___  ~Person~ [coach]  ___ */
        saveCoach("Quin", "Snyder", jazz)

        /*  ---------------          *** Eastern ~Conference~ ***       ---------------  */
        Conference east = new Conference(name: 'Eastern Conference', seasons: season)
        saveObject(east)

        /*      ----------- !!! -----------      */
        /* ---       CLEVELAND CAVALIERS     --- */
        /*      ---------------------------      */
        /* ___  ~Team~  ___ */
        Team cavaliers = saveTeam("Cavaliers", "W0", 0,  0, 0,  0, 0, 0, 0.0,  "-",  "-", "Cleveland", east)
        /* ___  ~Person~ [starters]  ___ */
        savePlayer("Lebron", "James", 23, 0, cavaliers)
        savePlayer("Kyrie", "Irving", 2, 0, cavaliers)
        savePlayer("Kevin", "Love", 0, 0, cavaliers)
        savePlayer("Kyle", "Korver", 26, 0, cavaliers)
        savePlayer("Channing", "Frye", 8, 0, cavaliers)
        /* ___  ~Person~ [coach]  ___ */
        saveCoach("Tyronn", "Lue", cavaliers)

        /*      ----------- !!! -----------      */
        /* ---         BOSTON CELTICS        --- */
        /*      ---------------------------      */
        /* ___  ~Team~  ___ */
        Team celtics = saveTeam("Celtics", "W0", 0,  0, 0,  0, 0, 0, 0.0,  "-",  "-", "Boston", east)
        /* ___  ~Person~ [starters]  ___ */
        savePlayer("Isaiah", "Thomas", 4, 0, celtics)
        savePlayer("Avery", "Bradley", 0, 0, celtics)
        savePlayer("Al", "Horford", 42, 0, celtics)
        savePlayer("Jae", "Crowder", 99, 0, celtics)
        savePlayer("Marcus", "Smart", 36, 0, celtics)
        /* ___  ~Person~ [coach]  ___ */
        saveCoach("Brad", "Stevens", celtics)
        /* ---    --- */

        /*      ----------- !!! -----------      */
        /* ---      WASHINGTON WIZARDS       --- */
        /*      ---------------------------      */
        /* ___  ~Team~  ___ */
        Team wizards = saveTeam("Wizards", "W0", 0,  0, 0,  0, 0, 0, 0.0,  "-",  "-", "Washington", east)
        /* ___  ~Person~ [starters]  ___ */
        savePlayer("John", "Wall", 2, 0, wizards)
        savePlayer("Bradley", "Beal", 3, 0, wizards)
        savePlayer("Trey", "Burke", 33, 0, wizards)
        savePlayer("Marcin", "Gortat", 13, 0, wizards)
        savePlayer("Kelly", "Oubre Jr.", 12, 0, wizards)
        /* ___  ~Person~ [coach]  ___ */
        saveCoach("Scott", "Brooks", wizards)

        /*      ---------- !!! ------------      */
        /* ---        TORONTO RAPTORS        --- */
        /*      ---------------------------      */
        /* ___  ~Team~  ___ */
        Team raptors = saveTeam("Raptors", "W0", 0,  0, 0,  0, 0, 0, 0.0,  "-",  "-", "Toronto", east)
        /* ___  ~Person~ [starters]  ___ */
        savePlayer("Kyle", "Lowry", 7, 0, raptors)
        savePlayer("DeMar", "DeRozan", 10, 0, raptors)
        savePlayer("Jonas", "Valanciunas", 17, 0, raptors)
        savePlayer("DeMarre", "Carroll", 5, 0, raptors)
        savePlayer("Cory", "Joseph", 6, 0, raptors)
        /* ___  ~Person~ [coach]  ___ */
        saveCoach("Dwayne", "Casey", raptors)

        /*  ---------------           *** Simulate ~Season~ ***         ---------------  */
        simSeason('2017', 82)
    }

    def destroy = {
    }
    /*  -------------------------------   ( server init )   -------------------------------  */
    /*  -----------------------------------   ~ END ~    ----------------------------------  */



    /*                          ==============  ***  ==============                          *
     #  ---------------------             Helper Functions            ---------------------  #
     *                          ===================================                          */


    /*  ____________________________                            ___________________________  */
    /*  ============================ !!! ---*** SAVE ***--- !!! ===========================  */


    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~ CREATE TEAMS ~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def saveTeam(teamName, streak, wins, losses, ties, scored, allowed, delta, winPercent, lastResult, result, location, conference) {
        /* ___  create team  ___ */
        Team newTeam = new Team(name: teamName, streak: streak, wins: wins, losses: losses, ties: ties,
                scored: scored, allowed: allowed, delta: delta, winPercent: winPercent,
                lastResult: lastResult, result: result, location: location, conference: conference,
                seed: -1, gamesBack: -1, l10: "0-0", homeRecord: "0-0", roadRecord: "0-0", gamesPlayed: 0)
        /* ___  save team  ___ */
        saveObject(newTeam)
        /* ___  return team object ___ */
        newTeam
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ CREATE PLAYERS ~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def savePlayer(firstName, lastName, number, pointsScored, team) {
        /* ___  create player  ___ */
        Person newPlayer = new Person(firstName: firstName, lastName: lastName, number: number, role: "player", pointsScored: pointsScored, team: team)
        /* ___  save player  ___ */
        saveObject(newPlayer)
        /* ___  return player object  ___ */
        newPlayer
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ CREATE COACHES ~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def saveCoach(firstName, lastName, team) {
        /* ___  create coach  ___ */
        Person newPlayer = new Person(firstName: firstName, lastName: lastName, number: "C", role: "coach", pointsScored: 0, team: team)
        /* ___  save team  ___ */
        saveObject(newPlayer)
        /* ___  return player object  ___ */
        newPlayer
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~ SAVE OBJECTS ~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def saveObject(object) {
        if (!object.save(flush:true)) {
            object.errors.allErrors.each { println it }
        }
    }


    /*  ___________________________                                _________________________  */
    /*  =========================== !!! ---*** SIMULATE ***--- !!! -------------------------  */


    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~ PLAY GAME ~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def playGame(String homeTeamName, String awayTeamName) {
        /* -------------------------------------------------
        *  INPUTS:
        *     - Home Team (String homeTeam)
        *     - Away Team (String awayTeam)
        *     - Points 4 Home Team (Int ptsHome)
        *     - Points 4 Away Team (Int ptsAway)
        *  DESCRIPTION:
        *     - Updates wins, losses, ties, winPercent, scored, allowed, delta for Home & Away Teams
        /*---------------------------------------------------------------------------------------------*/


        /*  ---------------             *** Load ~Teams~ ***            ---------------  */
        Team homeTeam = Team.findByName(homeTeamName) //.where(season = thisSeason)
        Team awayTeam = Team.findByName(awayTeamName)

        /*  ---------------              *** Scoreboard ***             ---------------  */
        /* ___  generate random score  ___ */
        Random random = new Random()
        /* ___  w/ w/ home court advantage  ___ */
        Integer ptsHome = random.nextInt(70) + 70
        Integer ptsAway = random.nextInt(65) + 65

        /*  --------------              *** Update Stats ***            ---------------  */
        Map result = updateStats(homeTeam, awayTeam, ptsHome, ptsAway)

        /*  --------------                *** Save Game ***             ---------------  */
        /* ___  create game instance ___ */
        Game newGame = new Game(homeTeam: homeTeamName, awayTeam: awayTeamName, winner: result["winner"], loser: result["loser"], homePoints: ptsHome,
                awayPoints: ptsAway, gameDate: new Date(), location: homeTeam.location, hostTeam: homeTeam, guestTeam: awayTeam)
        /* ___  save game ___ */
        saveObject(newGame)
    }

    def updateStats(Team homeTeam, Team awayTeam, ptsHome, ptsAway) {
        /* ___  instantiate  ___ */
        String winner
        String loser

        /* ___  games played  ___ */
        homeTeam.gamesPlayed += 1
        awayTeam.gamesPlayed += 1
        /* ___  scored  ___ */
        homeTeam.scores(ptsHome)
        awayTeam.scores(ptsAway)
        /* ___  allowed  ___ */
        homeTeam.allows(ptsAway)
        awayTeam.allows(ptsHome)
        /* ___ record ___ */

        /* ___  home team gets tie break  ___ */
        if (ptsHome == ptsAway) { ptsHome += 1 }
        if (ptsHome > ptsAway) {
            /* ___  home win  ___ */
            winner = homeTeam.name
            loser = awayTeam.name
            homeTeam.wins(homeTeam.location)
            awayTeam.loses(homeTeam.location)
        }
        else {
            /* ___  away win  ___ */
            winner = awayTeam.name
            loser = homeTeam.name
            homeTeam.loses(homeTeam.location)
            awayTeam.wins(homeTeam.location)
        }

        /* ___  winPercent  ___ */
        homeTeam.calcWinPercent()
        awayTeam.calcWinPercent()
        /* ___  delta  ___ */
        homeTeam.calcDelta()
        awayTeam.calcDelta()
        /* ___  streak  ___ */
        homeTeam.calcStreak()
        awayTeam.calcStreak()

        /* __ ~Teams~ ___*/
        saveObject(homeTeam)
        saveObject(awayTeam)

        Map result = [winner: winner, loser: loser]
        result
    }


    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~ SIM SEASON ~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def simSeason(String seasonName, Integer numGames) {
        /* ---------------------------------------------
        *  INPUTS:
        *     - Number of Games per Season (Integer numGames)
        *     - League of Teams (List league)
        *  FUNCTIONS:
        *     - playGame(homeTeam, awayTeam)
        *  DESCRIPTION:
        *     - Simulates an entire season of games for every team
        *  OUTPUT:
        *     - None
        *     - (Updates league list with simulated stats)
        /*---------------------------------------------------------------------------------------------*/


        /*  --------------          *** Loop Through Each Game ***      ---------------  */
        numGames.each{

            /*  --------------             *** Load Team List ***           ---------------  */
            /* ___  current season  ___ */
            Season season = Season.findByName(seasonName)
            /* ___  participating conferences  ___ */
            def conferences = Conference.findAllBySeasons(season)
            /* ___  participating teams  ___ */
            def teamList = []
            conferences.each{ conf -> teamList.addAll(Team.findAllByConference(conf))}

            /*  --------------             *** Simulate Games ***           ---------------  */
            Integer gmsPerNight = (Math.floor(teamList.size()/2))*2
            /* ___  weeks of games  ___ */
            for(int i=0; i<numGames; i++) {
                /* ___  randomize matchups  ___ */
                Collections.shuffle(teamList)
                /* ___  each team plays once  ___ */
                for(int j=0; j<gmsPerNight; j+=2) {
                    playGame(teamList[j].name, teamList[j+1].name)
                }
            }
        }
    }
    /*  ----------------------------   ( helper functions )   -----------------------------  */
    /*  -----------------------------------   ~ END ~    ----------------------------------  */
}

