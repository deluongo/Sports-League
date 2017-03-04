package cscie56.ps3


class GameStatsTagLib {
    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [seasonStats: [taglib:'none'], perGameStats: [taglib:'none']]

    static namespace = "stats"

    def seasonStats = {attrs ->
        Person player = attrs.player

        def mb = new groovy.xml.MarkupBuilder(out)

        mb.table(class: "w3-table w3-bordered w3-striped w3-border test w3-hoverable w3-centered"){
            tr(class: "w3-green"){
                th{ mb.yield "" }
                th{ mb.yield "Minutes" }
                th{ mb.yield "Points" }
                th{ mb.yield "Assists" }
                th{ mb.yield "Rebounds" }
                th{ mb.yield "Steals" }
                th{ mb.yield "FGA" }
                th{ mb.yield "FGM" }
                th{ mb.yield "FG%" }
                th{ mb.yield "3PA" }
                th{ mb.yield "3PM" }
                th{ mb.yield "3P%" }
                th{ mb.yield "Fouls" }
            }
            tr{
                th{ mb.yield "Totals" }
                td{mb.yield "${player.minutesPlayed}"}
                td{mb.yield "${player.points}"}
                td{mb.yield "${player.assists}"}
                td{mb.yield "${player.rebounds}"}
                td{mb.yield "${player.steals}"}
                td{mb.yield "${player.shotsAttempted}"}
                td{mb.yield "${player.shotsMade}"}
                td{mb.yield "${player.shootingPercentage}%"}
                td{mb.yield "${player.threePointersAttempted}"}
                td{mb.yield "${player.threePointersMade}"}
                td{mb.yield "${player.threePointPercentage}%"}
                td{mb.yield "${player.personalFouls}"}
            }
            tr{
                th{ mb.yield "Per Game" }
                td{mb.yield "${player.minutesPerGame}"}
                td{mb.yield "${player.pointsPerGame}"}
                td{mb.yield "${player.assistsPerGame}"}
                td{mb.yield "${player.reboundsPerGame}"}
                td{mb.yield "${player.stealsPerGame}"}
                td{mb.yield "${player.shotsAttemptedPerGame}"}
                td{mb.yield "${player.shotsMadePerGame}"}
                td{mb.yield "${player.shootingPercentage}%"}
                td{mb.yield "${player.threePointersAttemptedPerGame}"}
                td{mb.yield "${player.threePointersMadePerGame}"}
                td{mb.yield "${player.threePointPercentage}%"}
                td{mb.yield "${player.personalFoulsPerGame}"}
            }
        }
    }

    def perGameStats = {attrs ->
        Person player = attrs.player
        def mb = new groovy.xml.MarkupBuilder(out)

        def allGameStats = player.gameStats

        mb.table(class: "w3-table w3-bordered w3-striped w3-border test w3-hoverable w3-centered"){
            tr(class: "w3-green"){
                th{ mb.yield "Minutes" }
                th{ mb.yield "Points" }
                th{ mb.yield "Assists" }
                th{ mb.yield "Rebounds" }
                th{ mb.yield "Steals" }
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
                    td{mb.yield "${gm.shootingPercentage}%"}
                    td{mb.yield "${gm.threePointersAttempted}"}
                    td{mb.yield "${gm.threePointersMade}"}
                    td{mb.yield "${gm.threePointPercentage}%"}
                    td{mb.yield "${gm.personalFouls}"}

                }
            }
        }
    }


}