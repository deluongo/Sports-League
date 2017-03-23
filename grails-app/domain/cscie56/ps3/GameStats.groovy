package cscie56.ps3

/* ___  domain classes  ___ */
import cscie56.ps2.League
import cscie56.ps2.Conference
import cscie56.ps2.Season
import cscie56.ps2.Team
import cscie56.ps2.Game

class GameStats {

    Integer minutesPlayed
    Integer points
    Integer assists
    Integer rebounds
    Integer steals
    Integer shotsAttempted
    Integer shotsMade
    Double shootingPercentage
    Integer threePointersAttempted
    Integer threePointersMade
    Double threePointPercentage
    Integer personalFouls

    static belongsTo = [player:Person]

    static constraints = {
    }

    GameStats plus(GameStats other) {
        GameStats stats =  new GameStats(minutesPlayed: this.minutesPlayed + other.minutesPlayed,
                points: this.points + other.points, assists: this.assists + other.assists,
                rebounds: this.rebounds + other.rebounds, steals: this.steals + other.steals,
                shotsAttempted: this.shotsAttempted + other.shotsAttempted,
                shotsMade: this.shotsMade + other.shotsMade,
                threePointersAttempted: this.threePointersAttempted + other.threePointersAttempted,
                threePointersMade: this.threePointersMade + other.threePointersMade,
                personalFouls: this.personalFouls + other.personalFouls)

        stats
    }

}
