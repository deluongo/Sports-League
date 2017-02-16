package cscie56.ps2

class LeagueController {

    static scaffold = League

    /*---------------------------------------------------------------------------------------------*
    * ===========================================
    * FUNCTION -> CREATE LEAGUE!
    * ===========================================
    * INPUTS:
    *     - Number of Teams (Integer numTeams)
    * FUNCTIONS:
    *     - Team Constructor
    * DESCRIPTION:
    *     - Creates a league of Teams
    * OUTPUT:
    *     - List of Team Class Instances (List leagueOfTeams)
    /*---------------------------------------------------------------------------------------------*/
    def createLeague(Integer numTeams) {
        List leagueOfTeams = []
        (1..numTeams).eachWithIndex{ team, idx ->
            leagueOfTeams.add(new Team("Team ${idx + 1}", 0, 0, 0, 0.0, 0, 0, 0, "-", "-", "-"))
        }
        leagueOfTeams
    }

}
