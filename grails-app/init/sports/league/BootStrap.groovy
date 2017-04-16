/* ___  packages  ___ */
package sports.league
/* ___  domain classes  ___ */
import cscie56.ps2.League
import cscie56.ps2.Conference
import cscie56.ps2.Season
import cscie56.ps2.Team
import cscie56.ps2.Game
import cscie56.ps3.Person
import cscie56.ps5.BlogEntry
import cscie56.ps5.Role
import cscie56.ps5.User
import cscie56.ps5.UserRole
import cscie56.ps5.Comment

/*---------------------------------------------------------------------------------------------*
* ===========================================
*           INITIALIZES DATABASE
* ===========================================
* DESCRIPTION:
*     - Creates new tables and rows for Leagues, Seasons, Conferences, Teams, Persons, and Games.
*     - Simulates an 82 game NBA season, filling all tables with dummy data.
* FUNCTIONS:
*     - saveTeam() <- creates a new Team
*     - savePlayer() <- creates a new cscie56.ps3.Person of type Player
*     - saveCoach() <- creates a new cscie56.ps3.Person of type Coach
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
        /* ___  dates  ___ */
        def startDate = Date.parse("MM-dd-yyyy", "1-01-2017")
        def endDate = Date.parse("MM-dd-yyyy", "12-31-2017")
        /* ___  save  ___ */
        Season season = new Season(name: '2017', startDate: startDate, endDate: endDate, league: nba)
        saveObject(season)

        /*  ---------------          *** Western ~Conference~ ***       ---------------  */
        Conference west = new Conference(name: 'Western Conference', seasons: season)
        saveObject(west)

        /*  ---------------          *** User Roles ~UserRole~ ***       ---------------  */
        User admin = new User(username: "admin", password: "supersecret",
                bio: 'Some Bio', birthDate: new Date(), birthPlace: "Home Town", height: "6'2", weight: 160, universityAttended: "Some School", pictureURL: "https://pbs.twimg.com/media/Ce55dhKUYAAVEzF.jpg")

        saveObject(admin)
        User user = new User(username: "deluongo", password:"Stephany",
                bio: 'Some Bio', birthDate: new Date(), birthPlace: "Home Town", height: "6'2", weight: 160, universityAttended: "Some School", pictureURL: "https://pbs.twimg.com/media/Ce55dhKUYAAVEzF.jpg")
        saveObject(user)

        Role adminRole = new Role(authority: Role.ROLE_ADMIN)
        saveObject(adminRole)
        Role userRole = new Role(authority: Role.ROLE_USER)
        saveObject(userRole)

        UserRole.create(admin, adminRole)
        UserRole.create(admin, userRole)
        UserRole.create(user, userRole)

        /*      ----------- !!! -----------      */
        /* ---     GOLDEN STATE WARRIORS     --- */
        /*      ---------------------------      */
        /* ___  ~Team~  ___ */
        Team warriors = saveTeam("Warriors", "W0", 0,  0, 0,  0, 0, 0, 0.0,  "-",  "-", "Golden State", west)
        /* ___  ~cscie56.ps3.Person~ [starters]  ___ */
        User stephcurry = saveUser(userRole, "stephcurry", "stephcurry", "Born in Ohio in 1988 to former NBA player Dell Curry, Stephen Curry garnered national attention for his impressive play at Davidson College. He was drafted in 2009 by the Golden State Warriors, and eventually developed into one of pro basketball's top players with his stellar shooting skills. After garnering Most Valuable Player honors and helping the Warriors win the NBA championship in 2015, Curry led the team to a league-record 73 wins the following season. In May 2016, Curry became the first person to be named Most Valuable Player by unanimous vote in NBA history, and one of only 11 players to win the MVP award two years in a row. ",
                Date.parse("MM-dd-yyyy", "3-14-1988"), "Akron, OH", "6'3\"", 190, "Davidson", "https://static01.nyt.com/images/2016/02/29/sports/basketball/STEPHCURRY/STEPHCURRY-articleLarge.jpg")
        savePlayer(stephcurry, "Steph", "Curry", 30, 0, warriors)

        Comment comment1 = saveComment("We continuosly seek between design and technology. For over a decade, we've helped businesses to craft honest, emotional experiences through strategy, brand development, graphic design, our team hand picked to provide the right balance of skills to work", new Date(), user)
        Comment comment2 = saveComment("We continuosly seek between design and technology. For over a decade, we've helped businesses to craft honest, emotional experiences through strategy, brand development, graphic design, our team hand picked to provide the right balance of skills to work", new Date(), admin)

        BlogEntry blog = saveBlog("TEST Title", "TEST Description", "TEST TEXT - ipsum lorem sdff sdfs", new Date(), new Date(), Boolean.TRUE, "http://www.mrltactics.com/content/images/2017/01/Test_sign.jpg", stephcurry)
        BlogEntry blog2 = saveBlog("TEST Title 2", "TEST Description 2", "TEST TEXT2 - ipsum lorem sdff sdfs", new Date(), new Date(), Boolean.TRUE, "http://www.mrltactics.com/content/images/2017/01/Test_sign.jpg", stephcurry)
        //blog.addToAuthors(name:"Dierk Koenig")

        blog2.addToComments(comment1)
        blog2.addToComments(comment2)
        blog2.save(flush:true)

        savePlayer(saveUser(userRole, "klaythompson", "klaythompson", "Klay Thompson was born on 8th February 1990 in Los Angeles, California to mother Julie Thompson and former NBA player, Mychal Thompson. When Klay was 2 years old he moved with his family to Lake Oswego, Oregon. And when he was 14 years old he moved to Ladera Ranch, California along with his parents and two siblings. His older brother Mychel played basketball for Pepperdine University whereas his younger brother Trayce Thompson was drafted in the 2009 MLB Draft by the Chicago White Sox.\n" +
                "\n" +
                "Klay went to Santa Margararita Catholic High School in California where in his junior year, he was named the All-Area second Team and to Orange County third team for his excellent basketball skills. Later in Klay’s senior year he averaged 21 points per game. He attended the Washington State University in 2008. As a freshman, Klay played 33 games and averaged 12.5 points per game. While in his sophomore years, Klay went on to score 43 points in a tournament single game which was the third highest single game point total in Washington’s history. He went on to average 19.6 points. In his junior year, Thompson went on to finish the season with 733 points.  Later he announced in 2011 that he was ready to be drafted in the 2011 NbA draft. So he was selected by the Golden State Warriors in the first round as the 11th overall pick. He began as a no.11 shooting Guard in Golden State Warriors. In 2012 Klay announced that he was ready to be selected in the 2012 NBA All-star Weekend Rising Stars Challenge but was not selected. Later after the decision was made, improvement in all fields was seen in Thompson’s play. He went on to average 12.5 points per game from the previous 7.6, 2.8 rebounds from 1.6 from the previous and 1.5 steals from the previous 1.3. In his 2012-2013 seasons with the States, Klay recorded a career high 34 points and 14 rebounds in a game against San Antonio. In the 2012-2014 seasons, Klay went on to average 18.4 points, 3.1 rebounds and 2.2 assists on the year. In the 2014 -2015 season, Klay scored a career high 52 points with 11 three pointers in a game against Sacramento Kings.\n" +
                "\n" +
                "Klay was previously dating internet sensation, Hannah Stocking but they recently had a breakup over an instagram dispute. There have been rumors that Klay is currently dating Chris Brown’s ex, Karrueche Tran .However the couple have not confirmed anything amidst  the media. \n" +
                "\n" +
                "Klay is currently 25 years old, weighs around 98 kgs and is 6 feet 7 inches tall. He was named NbA All-Star, NBA All-Rookie First Team, 2x First Team All-Pac-10. There was a controversy when he was suspended during the final season with Washington State University after his misconduct criminal quotation for marijuana possession was subjected. ",
                Date.parse("MM-dd-yyyy", "2-08-1990"), "Los Angeles, CA", "6'7\"", 215, "Washington State", "http://nba.cdn.turner.com/nba/big/teams/warriors/2017/02/08/1486586537065-020817-Shootaround_Klay-1212280-6.600x336.jpg"), "Klay", "Thompson", 11, 0, warriors)

        savePlayer(saveUser(userRole, "kevindurant", "kevindurant", "Kevin Wayne Durant (born September 29, 1988) is an American professional basketball player for the Golden State Warriors of the National Basketball Association (NBA). Durant has won an NBA Most Valuable Player Award, four NBA scoring titles, the NBA Rookie of the Year Award, and two Olympic gold medals. He has also been selected to six All-NBA teams and eight All-Star teams.\n" +
                "\n" +
                "Durant was a heavily recruited high school prospect. He played one season of college basketball for the University of Texas, where he won numerous year-end awards and became the first freshman to be named Naismith College Player of the Year. In the 2007 NBA draft, he was selected with the second overall pick by the Seattle SuperSonics. After his rookie season, the team relocated to Oklahoma City and became the Thunder. Durant helped lead Oklahoma City to the 2012 NBA Finals, losing to the Miami Heat in five games. He played nine seasons for the Thunder organization before joining the Warriors in 2016.",
                Date.parse("MM-dd-yyyy", "9-29-1988"), "Washington, D.C.", "6'9\"", 240, "Texas", "https://pbs.twimg.com/media/CfyD-8gW8AM2m_-.jpg"), "Kevin", "Durant", 35, 0, warriors)

        savePlayer(saveUser(userRole, "draymongreen", "draymongreen", "Coming Soon!!!",
                Date.parse("MM-dd-yyyy", "1-01-1900"), "Unknown Birthplace", "5'0\"", 100, "Unknown College", "https://pbs.twimg.com/media/Ce55dhKUYAAVEzF.jpg"), "Draymon", "Green", 23, 0, warriors)

        savePlayer(saveUser(userRole, "zazapachulia", "zazapachuilia", "Coming Soon!!!",
                Date.parse("MM-dd-yyyy", "1-01-1900"), "Unknown Birthplace", "5'0\"", 100, "Unknown College", "http://nba.cdn.turner.com/nba/big/teams/warriors/2017/02/01/1485984774906-020117-Shootaround-Zaza-HD-1193865-4.600x336.jpg"), "Zaza", "Pachulia", 27, 0, warriors)
        /* ___  ~cscie56.ps3.Person~ [coach]  ___ */
        saveCoach(saveUser(userRole, "stevekerr", "stevekerr", "Coming Soon!!!",
                Date.parse("MM-dd-yyyy", "1-01-1900"), "Unknown Birthplace", "5'0\"", 100, "Unknown College", "http://www.tacticmusic.com/wp-content/uploads/2015/11/coming-soon.jpg"), "Steve", "Kerr", warriors)


        /*      ----------- !!! -----------      */
        /* ---       SAN ANTONIO SPURS       --- */
        /*      ---------------------------      */
        /* ___  ~Team~  ___ */
        Team spurs = saveTeam("Spurs", "W0", 0,  0, 0,  0, 0, 0, 0.0,  "-",  "-", "San Antonio", west)
        /* ___  ~cscie56.ps3.Person~ [starters]  ___ */
        savePlayer(saveUser(userRole, "kawhileonard", "kawhileonard", "Coming Soon!!!",
                Date.parse("MM-dd-yyyy", "1-01-1900"), "Unknown Birthplace", "5'0\"", 100, "Unknown College", "http://i2.cdn.turner.com/nba/nba/dam/assets/160424143242-kawhi-leonard-san-antonio-spurs-v-memphis-grizzlies--game-four.600x336.jpeg"), "Kawhi", "Leonard", 2, 0, spurs)
        savePlayer(saveUser(userRole, "lamarcusaldridge", "lamarcusaldridge", "Coming Soon!!!",
                Date.parse("MM-dd-yyyy", "1-01-1900"), "Unknown Birthplace", "5'0\"", 100, "Unknown College", "http://photo.jpgm.co.id//picture/normal/20140118_131442/131442_541847_lamarcus_aldridge.jpg"), "Lamarcus", "Aldridge", 12, 0, spurs)
        savePlayer(saveUser(userRole, "paugasol", "paugasol", "Coming Soon!!!",
                Date.parse("MM-dd-yyyy", "1-01-1900"), "Unknown Birthplace", "5'0\"", 100, "Unknown College", "http://www.elterrat.com/media/fotografiasxii/paugasol.jpg"), "Pau", "Gasol", 16, 0, spurs)
        savePlayer(saveUser(userRole, "manuginobili", "manuginobili", "Coming Soon!!!",
                Date.parse("MM-dd-yyyy", "1-01-1900"), "Unknown Birthplace", "5'0\"", 100, "Unknown College", "https://pbs.twimg.com/media/CK-DqzhW8AAeh55.jpg"), "Manu", "Ginobili", 20, 0, spurs)
        savePlayer(saveUser(userRole, "dannygreen", "dannygreen", "Coming Soon!!!",
                Date.parse("MM-dd-yyyy", "1-01-1900"), "Unknown Birthplace", "5'0\"", 100, "Unknown College", "http://i.cdn.turner.com/nba/nba/video/teams/spurs/2015/04/19/DannyGreenShootaround42015mp4-3576842-4.600x336.jpg"), "Danny", "Green", 12, 0, spurs)
        /* ___  ~cscie56.ps3.Person~ [coach]  ___ */
        saveCoach(saveUser(userRole, "gregpopovich", "gregpopovich", "Coming Soon!!!",
                Date.parse("MM-dd-yyyy", "1-01-1900"), "Unknown Birthplace", "5'0\"", 100, "Unknown College", "http://www.tacticmusic.com/wp-content/uploads/2015/11/coming-soon.jpg"), "Gregg", "Popovich", spurs)









        /*  ____________________________                            ___________________________  */
        /*  ========================= !!! ---*** SIM SEASON ***--- !!! ========================  */
        simSeason('2017', 2)
    }








    def destroy = {
    }
    /*  -------------------------------   ( server init )   -------------------------------  */
    /*  -----------------------------------   ~ END ~    ----------------------------------  */


    /*                          ==============  ***  ==============                          *
     #  ---------------------                Functions                ---------------------  #
     *                          ===================================                          */


    /*  ____________________________                            ___________________________  */
    /*  ======================= !!! ---*** SAVE FUNCTIONS ***--- !!! ======================  */


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
    def savePlayer(user, firstName, lastName, number, pointsScored, team) {
        /* ___  create player  ___ */
        Person newPlayer = new Person(user: user, firstName: firstName, lastName: lastName, number: number, role: "player",
                pointsScored: pointsScored, team: team)
        /* ___  save player  ___ */
        saveObject(newPlayer)
        /* ___  return player object  ___ */
        newPlayer
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ CREATE USERS ~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def saveUser(userRole, username, password, bio, birthDate, birthPlace, height, weight, universityAttended, pictureURL) {
        /* ___  create player  ___ */
        User newUser = new User(username: username, password: password, bio: bio, birthDate: birthDate, birthPlace: birthPlace, height: height,
                weight: weight, universityAttended: universityAttended, pictureURL: pictureURL)
        /* ___  save user  ___ */
        saveObject(newUser)

        //Role usrRole = new Role(authority: Role.ROLE_USER)
        //UserRole.create(admin, adminRole)
        UserRole.create(newUser, userRole)
        /* ___  return player object  ___ */
        newUser
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ BLOG POSTS ~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def saveBlog(title, description, text, dateCreated, datePublished, published, pictureURL, author) {
        /* ___  create player  ___ */
        BlogEntry newBlog = new BlogEntry(title: title, description: description, text: text, dateCreated: dateCreated, datePublished: datePublished,
                published: published, pictureURL: pictureURL, author: author )
        /* ___  save user  ___ */
        saveObject(newBlog)
        /* ___  return player object  ___ */
        newBlog
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ BLOG COMMENTS ~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def saveComment(text, dateCreated, author) {
        /* ___  create player  ___ */
        Comment newComment= new Comment(text: text, dateCreated: dateCreated, author: author )
        /* ___  save user  ___ */
        saveObject(newComment)
        /* ___  return player object  ___ */
        newComment
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ CREATE COACHES ~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def saveCoach(user, firstName, lastName, team) {
        /* ___  create coach  ___ */
        Person newCoach = new Person(user: user, firstName: firstName, lastName: lastName, number: "C", role: "coach",
                pointsScored: 0, team: team)
        /* ___  save team  ___ */
        saveObject(newCoach)
        /* ___  return player object  ___ */
        newCoach
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~ SAVE OBJECTS ~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def saveObject(object) {
        if (!object.save(flush:true)) {
            object.errors.allErrors.each { println it }
        }
    }
    /*  -----------------------------   ( save functions )   ------------------------------  */
    /*  -----------------------------------   ~ END ~    ----------------------------------  */

    /*  _____________________                                         _____________________  */
    /*  ===================== !!! ---*** SIMULATION HELPERS ***--- !!!=====================  */


    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~~~~ PLAY GAME ~~~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def playGame(String homeTeamName, String awayTeamName, Date date) {
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

        /*  ---------------               *** Play Game ***             ---------------  */
        /* ___  generate random score  ___ */
        Random random = new Random()
        /* ___  w/ home court advantage  ___ */
        Integer ptsHome = random.nextInt(70) + 70
        Integer ptsAway = random.nextInt(65) + 65

        /*  ---------------           *** Determine Winner ***          ---------------  */
        Map result = determineResult(homeTeam, ptsHome, awayTeam, ptsAway)

        /*  --------------                *** Save Game ***             ---------------  */
        /* ___  create game instance ___ */
        Game newGame = new Game(homeTeam: homeTeamName, awayTeam: awayTeamName, winner: result["winner"], loser: result["loser"], homePoints: ptsHome,
                awayPoints: ptsAway, gameDate: date, location: homeTeam.location, hostTeam: homeTeam, guestTeam: awayTeam).save(flush:true)

        /*  ---------------     *** Determine Player Performances ***    ---------------  */
        playerPerformance(homeTeam, ptsHome, awayTeam, ptsAway, newGame)

    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~ DETERMINE RESULT ~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def determineResult(Team homeTeam, ptsHome, Team awayTeam, ptsAway) {
        /*  ---------------              *** Game Result ***           ---------------  */
        Map result
        /* ___  home team gets tie break  ___ */
        if (ptsHome == ptsAway) { ptsHome += 1 }
        /* ___  determine result  ___ */
        if (ptsHome > ptsAway) {
            /* ___  update team stats  ___ */
            homeTeam.storeResults(ptsHome, ptsAway, homeTeam.location)
            awayTeam.storeResults(ptsAway, ptsHome, homeTeam.location)
            /* ___  home team wins  ___ */
            result = [winner: homeTeam.name, loser:awayTeam.name]

        }
        else {
            /* ___  update team stats  ___ */
            homeTeam.storeResults(ptsHome, ptsAway, homeTeam.location)
            awayTeam.storeResults(ptsAway, ptsHome, homeTeam.location)
            /* ___  visiting team wins  ___ */
            result = [winner: awayTeam.name, loser:homeTeam.name]
        }


        result
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~ DETERMINE RESULT ~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def playerPerformance(Team homeTeam, ptsHome, Team awayTeam, ptsAway, newGame) {
        /*  ---------------          *** Get List of Players ***       ---------------  */
        /* ___  get home team players  ___ */
        def homePlayers = Person.findAllByTeam(homeTeam)
        homePlayers.removeIf { it.role != "player" }
        /* ___  get away team players  ___ */
        def roadPlayers = Person.findAllByTeam(awayTeam)
        roadPlayers.removeIf { it.role != "player" }
        /* ___  randomize participation order  ___ */
        Collections.shuffle(homePlayers)
        Collections.shuffle(roadPlayers)

        /*  ---------------           *** Get Stat Breakdown ***       ---------------  */
        def statSplits = [0.35, 0.3, 0.2, 0.1, 0.05]
        Collections.shuffle(statSplits)

        /*  ---------------           *** Store Player Stats ***       ---------------  */
        /* ___  HOME | split stats among 5 players  ___ */
        homePlayers.take(5).eachWithIndex { player, idx ->
            player.storeResults(ptsHome, statSplits[idx], newGame)
        }

        roadPlayers.take(5).eachWithIndex { player, idx ->
            player.storeResults(ptsAway, statSplits[idx], newGame)
        }
    }
    /*  ---------------------------   ( simulate functions )   ----------------------------  */
    /*  -----------------------------------   ~ END ~    ----------------------------------  */

    /*  ______________________                                       ______________________  */
    /*  ====================== !!! ---*** PRIMARY FUNCTION ***--- !!!======================  */


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
            /* ___  set calendar  ___ */
            Date date = season.startDate - 7
            def seasonDates = []
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
                /* ___  set calender week  ___ */
                date += 7
                seasonDates << date
                /* ___  each team plays once  ___ */
                for(int j=0; j<gmsPerNight; j+=2) {
                    playGame(teamList[j].name, teamList[j+1].name, seasonDates[i])
                }
                print("Week ${i} Simulated ")
            }
        }
        /*  -------------------------------   ( sim season )   --------------------------------  */
        /*  -----------------------------------   ~ END ~    ----------------------------------  */
    }
    /*  ----------------------------   ( helper functions )   -----------------------------  */
    /*  -----------------------------------   ~ END ~    ----------------------------------  */
}

