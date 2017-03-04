package cscie56.ps2

class GameStatsTagLib {
    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [allStats: [taglib:'html'], statTotalsTR: [taglib:'none']]

    static namespace = "stats"

    def seasonStats = {attrs ->
        Person player = attrs.player

        def mb = new groovy.xml.MarkupBuilder(out)
        def allGameStats = player.gameStats

        print("${allGameStats}")

        mb.table{
            tr{
                th{ mb.yield "Minutes" }
                th{ mb.yield "Points" }
                th{ mb.yield "Assists" }
                th{ mb.yield "Rebounds" }
                th{ mb.yield "FGA" }
                th{ mb.yield "FGM" }
                th{ mb.yield "FG%" }
                th{ mb.yield "3PA" }
                th{ mb.yield "3PM" }
                th{ mb.yield "3P%" }
                th{ mb.yield "Fouls" }
            }
            tr{
                td{mb.yield "${allGameStats.sum().minutesPlayed}"}
                td{mb.yield "${allGameStats.sum().points}"}
                td{mb.yield "${allGameStats.sum().assists}"}
                td{mb.yield "${allGameStats.sum().rebounds}"}
                td{mb.yield "${allGameStats.sum().steals}"}
                td{mb.yield "${allGameStats.sum().shotsAttempted}"}
                td{mb.yield "${allGameStats.sum().shotsMade}"}
                td{mb.yield "${allGameStats.sum().shootingPercentage}"}
                td{mb.yield "${allGameStats.sum().threePointersAttempted}"}
                td{mb.yield "${allGameStats.sum().threePointersMade}"}
                td{mb.yield "${allGameStats.sum().threePointPercentage}"}
                td{mb.yield "${allGameStats.sum().personalFouls}"}
            }
        }
    }

    def perGameStats = {attrs ->
        Person player = attrs.player
        def mb = new groovy.xml.MarkupBuilder(out)

        def allGameStats = player.gameStats


        mb.table{
            tr{
                th{ mb.yield "Minutes" }
                th{ mb.yield "Points" }
                th{ mb.yield "Assists" }
                th{ mb.yield "Rebounds" }
                th{ mb.yield "FGA" }
                th{ mb.yield "FGM" }
                th{ mb.yield "FG%" }
                th{ mb.yield "3PA" }
                th{ mb.yield "3PM" }
                th{ mb.yield "3P%" }
                th{ mb.yield "Fouls" }
            }
            allGameStats.each{ gm ->
                tr{
                    td{mb.yield "${gm.minutesPlayed}"}
                    td{mb.yield "${gm.points}"}
                    td{mb.yield "${gm.assists}"}
                    td{mb.yield "${gm.rebounds}"}
                    td{mb.yield "${gm.steals}"}
                    td{mb.yield "${gm.shotsAttempted}"}
                    td{mb.yield "${gm.shotsMade}"}
                    td{mb.yield "${gm.shootingPercentage}"}
                    td{mb.yield "${gm.threePointersAttempted}"}
                    td{mb.yield "${gm.threePointersMade}"}
                    td{mb.yield "${gm.threePointPercentage}"}
                }
            }
        }
    }


}
