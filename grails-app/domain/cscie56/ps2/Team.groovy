package cscie56.ps2

class Team {

    Integer wins
    Integer losses
    Integer ties
    Double winPercent
    Integer pointsScored
    Integer pointsAgainst
    Integer streak
    def coachingStaff
    def roster

	String name

    static hasMany = [persons:Person]


    static constraints = {

    }
}
