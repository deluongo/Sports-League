package sports.league

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/season/leaderboard/$conferenceName?" {
            controller = 'season'
            action = 'showLeaderboard'
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
