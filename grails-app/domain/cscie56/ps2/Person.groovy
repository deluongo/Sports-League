package cscie56.ps2

class Person {

    String firstName
    String lastName
    Integer number
    Boolean player
    Boolean coach
    Integer pointsScored

    static belongsTo = [teams:Team]

    static constraints = {
    }
}
