package cscie56.ps2

import grails.test.mixin.*
import spock.lang.*
import cscie56.ps5.BlogEntry
@TestFor(LeagueController)
@Mock([League, BlogEntry])
class LeagueControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params << [name:'NBA']

    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.leagueList
            model.leagueCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.league!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def league = new League()
            league.validate()
            controller.save(league)

        then:"The create view is rendered again with the correct model"
            model.league!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            league = new League(params)

            controller.save(league)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/league/show/1'
            controller.flash.message != null
            League.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def league = new League(params)
            controller.show(league)

        then:"A model is populated containing the domain instance"
            model.league == league
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def league = new League(params)
            controller.edit(league)

        then:"A model is populated containing the domain instance"
            model.league == league
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/league/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def league = new League()
            league.validate()
            controller.update(league)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.league == league

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            league = new League(params).save(flush: true)
            controller.update(league)

        then:"A redirect is issued to the show action"
            league != null
            response.redirectedUrl == "/league/show/$league.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/league/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def league = new League(params).save(flush: true)

        then:"It exists"
            League.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(league)

        then:"The instance is deleted"
            League.count() == 0
            response.redirectedUrl == '/league/index'
            flash.message != null
    }



    void "test that newPost method saves a post"() {


        when:"A domain instance is created"
        response.reset()
        populateValidParams(params)
        def post = new BlogEntry(params).save(flush: true)

        then:"It exists"
        true

\
    }
}
