package cscie56.ps3

import cscie56.ps2.Conference
import cscie56.ps2.League
import cscie56.ps2.Season
import cscie56.ps2.Team
import spock.lang.Specification
import grails.test.mixin.*

@TestFor(GameStatsTagLib)
class GameStatsTagLibSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    /*  ---------------             *** League~ ***            ---------------  */
    League nba = new League(name: 'NBA')

    /*  ---------------             *** 2Season~ ***           ---------------  */
    Season season = new Season(name: '2017', startDate: new Date(), endDate: new Date(), league: nba)

    /*  ---------------          *** Western ~Conference~ ***       ---------------  */
    Conference west = new Conference(name: 'Western Conference', seasons: season)

    /*  ---------------          *** Western ~Conference~ ***       ---------------  */
    Team testTeam = new Team(name: "Team Name", wins: 0, losses: 0, ties: 0,
            scored: 0, allowed: 0, location: "Golden State", conference: west,
            seed: 0, gamesPlayed: 0,   lastResult: "-", result: "-" )

    void gameResultsShouldRenderCorrectSumOfGameStats() {

        def testPlayer = new Person(firstName: "name", lastName: "name", number: 9, role: "player",
                pointsScored: 0, team: testTeam, bio: "string", birthDate: new Date(), birthPlace: "Carolina", height: "6'3\"3",
                weight: 180, universityAttended: "Davidson", pictureURL: "https://pbs.twimg.com/media/Ce55dhKUYAAVEzF.jpg")

        GameStats game1 = new GameStats(player: testPlayer, minutesPlayed: 48,
                   points: 30, assists: 6,
                   rebounds: 9, steals: 3,
                   shotsAttempted: 30,
                   shotsMade: 14,
                   shootingPercentage: 56.5,
                   threePointersAttempted: 2,
                   threePointersMade: 2,
                   threePointPercentage: 100.0,
                   personalFouls: 2)

        new GameStats(player: testPlayer, minutesPlayed: 48,
                points: 30, assists: 6,
                rebounds: 9, steals: 3,
                shotsAttempted: 30,
                shotsMade: 14,
                shootingPercentage: 56.5,
                threePointersAttempted: 2,
                threePointersMade: 2,
                threePointPercentage: 100.0,
                personalFouls: 2)

        //GameStats game2 = GameStats.find(2)

        String result = applyTemplate('<stats:seasonStats player="${person}" ></stats:seasonStats>',
                [person : testPlayer]).trim()

        assert result.contains("<tbody>")
        assert result.contains("<tr>")
        assert result.contains("<td>")
        assert result.contains("<td><yield>96</yield></td>")
        assert result.contains(60)
        assert result.contains("<td><yield>96</yield></td>")
        assert result.contains("<td><yield>12</yield></td>")
        assert result.contains("<td><yield>6</yield></td>")
        assert result.contains("<td><yield>18</yield></td>")
        assert result.contains("<td><yield>100.0</yield></td>")
        assert result.contains("<td><yield>123298</yield></td>")

    }
}

