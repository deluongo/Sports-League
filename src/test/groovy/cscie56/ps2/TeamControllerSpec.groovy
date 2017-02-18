package cscie56.ps2

import grails.test.mixin.*
import spock.lang.*

@TestFor(TeamController)
@Mock(Team)
class TeamControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null


        /*  ---------------             *** NBA ~League~ ***            ---------------  */
        League nba = new League(name: 'NBA')


        /*  ---------------             *** 2017 ~Season~ ***           ---------------  */
        def startDate = Date.parse("MM-dd-yyyy", "10-01-2016")
        def endDate = Date.parse("MM-dd-yyyy", "10-01-2017")
        Season season = new Season(name: '2017', startDate: startDate, endDate: endDate, league: nba)
        Conference west = new Conference(name: 'Western Conference', seasons: season)

        // TODO: Populate valid properties like...
        params << [name: "teamName", streak: 'W2', wins: 0, losses: 0, ties: 0,
                scored: 0, allowed: 0, delta: 0, winPercent: 0,
                lastResult: "-", result: "-", location: "Bay Area", conference: west,
                seed: 1, gamesBack: 0, l10: "0-0", homeRecord: "0-0", roadRecord: "0-0", gamesPlayed: 0]

        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }


}
