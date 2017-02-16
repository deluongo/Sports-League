package cscie56.ps2

class Game {

    String homeTeam
    String awayTeam
    String winner
    String looser
    Integer homePoints
    Integer awayPoints
    Date gameDate
    String location

    Integer id

    static hasMany = [teams:Team]

    static constraints = {
    }
}
