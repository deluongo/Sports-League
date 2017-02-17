package cscie56.ps2

class Game {

    String homeTeam
    String awayTeam
    String winner
    String loser
    Integer homePoints
    Integer awayPoints
    Date gameDate
    String location

    static hasMany = [teams:Team]

    static constraints = {
    }
}
