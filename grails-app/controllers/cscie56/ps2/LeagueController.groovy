package cscie56.ps2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LeagueController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond League.list(params), model:[leagueCount: League.count()]
    }

    def show(League league) {
        respond league
    }

    def create() {
        respond new League(params)
    }

    @Transactional
    def save(League league) {
        if (league == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (league.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond league.errors, view:'create'
            return
        }

        league.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'league.label', default: 'League'), league.id])
                redirect league
            }
            '*' { respond league, [status: CREATED] }
        }
    }

    def edit(League league) {
        respond league
    }

    @Transactional
    def update(League league) {
        if (league == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (league.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond league.errors, view:'edit'
            return
        }

        league.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'league.label', default: 'League'), league.id])
                redirect league
            }
            '*'{ respond league, [status: OK] }
        }
    }

    @Transactional
    def delete(League league) {

        if (league == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        league.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'league.label', default: 'League'), league.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'league.label', default: 'League'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /*                          ==============  ***  ==============                          *
     #  ---------------------             CUSTOM RENDERING            ---------------------  #
     *                          ===================================                          */

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~~~ SHOW STANDINGS ~~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def season(String conferenceIndex) {

        /*  --------------               *** Sim Season  ***            ---------------  */
        //simSeason('2017', 82)

        /*  --------------            *** Load All Teams ***            ---------------  */
        /* ___  Load Conference Objects  ___ */
        def westernConference = Conference.findAllByName("Western Conference")
        def easternConference = Conference.findAllByName("Eastern Conference")
        /* ___  Teams List by Conference  ___ */
        def westTeams = Team.findAllByConference(westernConference)
        def eastTeams = Team.findAllByConference(easternConference)

        /*  --------------          *** Create Conference Map  ***      ---------------  */
        /* ___  Ensure Page Load for /season/showStandings  ___ */
        if (conferenceIndex == null) {conferenceIndex = 1}
        def team
        if (conferenceIndex == '1') { team = westTeams }
        else { team = eastTeams}

        /*  --------------            *** Display Standings ***         ---------------  */
        /* ___  open standings view ___ */
        render(view: "season/showStandings/${conferenceIndex}", model: [seasonName: westernConference.seasons.name, teamList: team])
    }

    /*  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *   ~~ !!! FUNCTION !!! ~~~  | ~~~~~~~~~~ SHOW PLAYER STATS ~~~~~~~~~~~
     *  ========================= | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    def person(String personIndex) {
        /*------------------------------------------*
        * ===========================================
        * FUNCTION -> SHOW PLAYER STATS!
        * ===========================================
        * INPUTS:
        *     -
        * FUNCTIONS:
        *     - playGame(homeTeam, awayTeam)
        * DESCRIPTION:
        *     - Displays league standings in the browser
        * OUTPUT:
        *     -
        /*---------------------------------------------------------------------------------------------*/

        /*  --------------              *** Select Player ***           ---------------  */
        personIndex = personIndex ?: "1"
        def person = Person.get(personIndex)

        /*  --------------              *** Display Stats ***           ---------------  */

        render(view: "person/stats", model: [person: person])
	    //respond(person, view: "person/stats/${personIndex}")
    }
}
