package cscie56.ps2

import grails.test.mixin.*
import spock.lang.*

@TestFor(TeamController)
@Mock(Team)

class TeamControllerSpec extends Specification {


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def populateValidParams(params) {
        assert params != null


        /*  ---------------             *** League~ ***            ---------------  */
        League nba = new League(name: 'NBA')

        /*  ---------------             *** 2Season~ ***           ---------------  */
        Season season = new Season(name: '2017', startDate: new Date(), endDate: new Date(), league: nba)

        /*  ---------------          *** Western ~Conference~ ***       ---------------  */
        Conference west = new Conference(name: 'Western Conference', seasons: season)

        params << [name: "Team Name", streak: 'W0', wins: 1, losses: 0, ties: 0,
                   scored: 10, allowed: 9, delta: 1, winPercent: 1.0,
                   lastResult: "-", result: "-", location: "Golden State", conference: west,
                   seed: 1, gamesBack: 1, l10: "0-0", homeRecord: "0-0", roadRecord: "0-0", gamesPlayed: 1]


    }



    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.teamList
            model.teamCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.team!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def team = new Team()
            team.validate()
            controller.save(team)

        then:"The create view is rendered again with the correct model"
            model.team != null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            team = new Team(params).save(flush: true)
            controller.save(team)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/team/show/1'
            controller.flash.message != null
            Team.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def team = new Team(params)
            controller.show(team)

        then:"A model is populated containing the domain instance"
            model.team == team
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def team = new Team(params)
            controller.edit(team)

        then:"A model is populated containing the domain instance"
            model.team == team
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/team/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def team = new Team()
            team.validate()
            controller.update(team)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.team == team

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            team = new Team(params).save(flush: true)
            controller.update(team)

        then:"A redirect is issued to the show action"
            team != null
            response.redirectedUrl == "/team/show/$team.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/team/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def team = new Team(params).save(flush: true)

        then:"It exists"
            Team.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(team)

        then:"The instance is deleted"
            Team.count() == 0
            response.redirectedUrl == '/team/index'
            flash.message != null
    }
}
