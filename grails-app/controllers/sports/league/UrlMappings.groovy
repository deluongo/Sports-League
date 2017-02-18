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

        "/league/season/showStandings/$conferenceIndex?" {
            controller = 'league'
            action = 'season'
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }

}
