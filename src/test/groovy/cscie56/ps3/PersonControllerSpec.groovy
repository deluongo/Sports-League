package cscie56.ps3


import cscie56.ps2.Conference
import cscie56.ps2.League
import cscie56.ps2.Season
import cscie56.ps2.Team
import cscie56.ps2.Game
import cscie56.ps5.User

import grails.test.mixin.*
import spock.lang.*
import grails.test.mixin.Mock

@TestFor(PersonController)
@Mock([cscie56.ps5.User, Person])
class PersonControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null


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

        User u = new User()

        params << [user: u, firstName: "Steph", lastName: "Curry", role: "player",
        pointsScored: 0, team: testTeam, gameStats: randGame
        ]


    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.personList
            model.personCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.person!= null
    }


    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def person = new Person(params)
            controller.show(person)

        then:"A model is populated containing the domain instance"
            model.person == person
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def person = new Person(params)
            controller.edit(person)

        then:"A model is populated containing the domain instance"
            model.person == person
    }

}
