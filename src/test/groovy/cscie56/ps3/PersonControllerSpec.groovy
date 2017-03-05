package cscie56.ps3

import cscie56.ps2.Conference
import cscie56.ps2.League
import cscie56.ps2.Season
import cscie56.ps2.Team
import cscie56.ps2.Game

import grails.test.mixin.*
import spock.lang.*

@TestFor(PersonController)
@Mock(Person)
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

        params << [firstName: "Steph", lastName: "Curry", number: 30, role: "player",
        pointsScored: 0, team: testTeam, bio: "Some Text", birthDate: new Date(), birthPlace: "Carolina", height: "6'3\"",
        weight: 180, universityAttended: "Davidson", pictureURL: "https://static01.nyt.com/images/2016/02/29/sports/basketball/STEPHCURRY/STEPHCURRY-articleLarge.jpg",
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

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def person = new Person()
            person.validate()
            controller.save(person)

        then:"The create view is rendered again with the correct model"
            model.person!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            person = new Person(params)

            controller.save(person)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/person/show/1'
            controller.flash.message != null
            Person.count() == 1
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

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/person/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def person = new Person()
            person.validate()
            controller.update(person)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.person == person

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            person = new Person(params).save(flush: true)
            controller.update(person)

        then:"A redirect is issued to the show action"
            person != null
            response.redirectedUrl == "/person/show/$person.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/person/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def person = new Person(params).save(flush: true)

        then:"It exists"
            Person.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(person)

        then:"The instance is deleted"
            Person.count() == 0
            response.redirectedUrl == '/person/index'
            flash.message != null
    }
}
