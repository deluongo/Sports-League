package sports.league

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/season/showStandings/$conferenceIndex?" {
            controller = 'season'
            action = 'showStandings'
        }

        "/season/leaderboard/$conferenceName?" {
            controller = 'season'
            action = 'showLeaderboard'
        }


        "/league/season/showStandings/$conferenceIndex?" {
            controller = 'league'
            action = 'season'
        }

        "/league/person/stats/$personIndex?" {
            controller = 'league'
            action = 'person'
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }

}
