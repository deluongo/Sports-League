package cscie56.ps3


class GameStatsTagLib {
    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [seasonStats: [taglib:'none'], perGameStats: [taglib:'none']]

    static namespace = "stats"

    def seasonStats = {attrs ->
        Person player = attrs.player

        def mb = new groovy.xml.MarkupBuilder(out)

        mb.table(class: "w3-table w3-bordered w3-striped w3-border test w3-hoverable w3-centered"){
            tr(class: "w3-cyan"){
                th{ mb.yield "" }
                th{ mb.yield "MP" }
                th{ mb.yield "P" }
                th{ mb.yield "A" }
                th{ mb.yield "R" }
                th{ mb.yield "ST" }
                th(class: 'hidden-xs hidden-sm'){ mb.yield "FGM" }
                th(class: 'hidden-xs hidden-sm'){ mb.yield "FGA" }
                th(class: 'hidden-xs'){ mb.yield "FG%" }
                th(class: 'hidden-xs hidden-sm'){ mb.yield "3PM" }
                th(class: 'hidden-xs hidden-sm'){ mb.yield "3PA" }
                th(class: 'hidden-xs'){ mb.yield "3P%" }
                th(class: 'hidden-xs'){ mb.yield "PF" }
            }
            tr{
                th{ mb.yield "Totals" }
                td{mb.yield "${player.minutesPlayed}"}
                td{mb.yield "${player.points}"}
                td{mb.yield "${player.assists}"}
                td{mb.yield "${player.rebounds}"}
                td{mb.yield "${player.steals}"}
                td(class: 'hidden-xs hidden-sm'){mb.yield "${player.shotsAttempted}"}
                td(class: 'hidden-xs hidden-sm'){mb.yield "${player.shotsMade}"}
                td(class: 'hidden-xs '){mb.yield "${player.shootingPercentage}%"}
                td(class: 'hidden-xs hidden-sm'){mb.yield "${player.threePointersAttempted}"}
                td(class: 'hidden-xs hidden-sm'){mb.yield "${player.threePointersMade}"}
                td(class: 'hidden-xs '){mb.yield "${player.threePointPercentage}%"}
                td(class: 'hidden-xs '){mb.yield "${player.personalFouls}"}
            }
            tr{
                th{ mb.yield "Per Game" }
                td{mb.yield "${(player.minutesPerGame  as double).round(1)}"}
                td{mb.yield "${(player.pointsPerGame  as double).round(1)}"}
                td{mb.yield "${(player.assistsPerGame  as double).round(1)}"}
                td{mb.yield "${(player.reboundsPerGame  as double).round(1)}"}
                td{mb.yield "${(player.stealsPerGame  as double).round(1)}"}
                td(class: 'hidden-xs hidden-sm'){mb.yield "${(player.shotsAttemptedPerGame  as double).round(1)}"}
                td(class: 'hidden-xs hidden-sm'){mb.yield "${(player.shotsMadePerGame  as double).round(1)}"}
                td(class: 'hidden-xs '){mb.yield "${(player.shootingPercentage  as double).round(1)}%"}
                td(class: 'hidden-xs hidden-sm'){mb.yield "${(player.threePointersAttemptedPerGame  as double).round(1)}"}
                td(class: 'hidden-xs hidden-sm'){mb.yield "${(player.threePointersMadePerGame  as double).round(1)}"}
                td(class: 'hidden-xs '){mb.yield "${(player.threePointPercentage  as double).round(1)}%"}
                td(class: 'hidden-xs '){mb.yield "${(player.personalFoulsPerGame  as double).round(1)}"}
            }
        }
    }

    def perGameStats = {attrs ->
        Person player = attrs.player
        def mb = new groovy.xml.MarkupBuilder(out)

        def allGameStats = player.gameStats

        mb.table(class: "w3-table w3-bordered w3-striped w3-border test w3-hoverable w3-centered"){
            tr(class: "w3-cyan"){
                th{ mb.yield "Game" }
                th{ mb.yield "MP" }
                th{ mb.yield "P" }
                th{ mb.yield "A" }
                th{ mb.yield "R" }
                th{ mb.yield "ST" }
                th(class: 'hidden-xs hidden-sm hidden-md'){ mb.yield "FGM" }
                th(class: 'hidden-xs hidden-sm hidden-md'){ mb.yield "FGA" }
                th(class: 'hidden-xs hidden-sm'){ mb.yield "FG%" }
                th(class: 'hidden-xs hidden-sm hidden-md'){ mb.yield "3PM" }
                th(class: 'hidden-xs hidden-sm hidden-md'){ mb.yield "3PA" }
                th(class: 'hidden-xs hidden-sm'){ mb.yield "3P%" }
                th(class: 'hidden-xs hidden-sm'){ mb.yield "PF" }
            }
            allGameStats.each{ gm ->
                tr{
                    td{mb.yield "${gm.game.homeTeam} (${gm.game.homePoints}) v. ${gm.game.guestTeam.name} (${gm.game.awayPoints})"}
                    td{mb.yield "${gm.minutesPlayed}"}
                    td{mb.yield "${gm.points}"}
                    td{mb.yield "${gm.assists}"}
                    td{mb.yield "${gm.rebounds}"}
                    td{mb.yield "${gm.steals}"}
                    td(class: 'hidden-xs hidden-sm hidden-md'){mb.yield "${gm.shotsAttempted}"}
                    td(class: 'hidden-xs hidden-sm hidden-md'){mb.yield "${gm.shotsMade}"}
                    td(class: 'hidden-xs hidden-sm'){mb.yield "${gm.shootingPercentage}%"}
                    td(class: 'hidden-xs hidden-sm hidden-md'){mb.yield "${gm.threePointersAttempted}"}
                    td(class: 'hidden-xs hidden-sm hidden-md'){mb.yield "${gm.threePointersMade}"}
                    td(class: 'hidden-xs hidden-sm'){mb.yield "${gm.threePointPercentage}%"}
                    td(class: 'hidden-xs hidden-sm'){mb.yield "${gm.personalFouls}"}

                }
            }
        }
    }


}