package cscie56.ps3

import cscie56.ps2.Conference
import cscie56.ps2.Game
import cscie56.ps2.League
import cscie56.ps2.Season
import cscie56.ps2.Team
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Person)
class PersonSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Test that name is not empty"() {

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


        Team testTeam2 = new Team(name: "Team Name", wins: 0, losses: 0, ties: 0,
                scored: 0, allowed: 0, location: "Golden State", conference: west,
                seed: 0, gamesPlayed: 0,   lastResult: "-", result: "-" )


        Game randGame = new Game(homeTeam: testTeam.name, guestTeam: testTeam2, awayTeam: testTeam.name,
                hostTeam: testTeam, roadTeam: testTeam2, winner: testTeam.name, loser: testTeam2.name, homePoints: 100, awayPoints: 80,
                gameDate: new Date(), location: testTeam.location)

        when: 'player has stats'
        def p = new Person(firstName: "Steph", lastName: "Curry", number: 30, role: "player",
                team: testTeam, bio: "Some Text", birthDate: new Date(), birthPlace: "Carolina", height: "6'3\"",
                weight: 180, universityAttended: "Davidson", pictureURL: "https://static01.nyt.com/images/2016/02/29/sports/basketball/STEPHCURRY/STEPHCURRY-articleLarge.jpg")

        then: 'first name is not empty'
        assert(p.firstName)

    }
}
