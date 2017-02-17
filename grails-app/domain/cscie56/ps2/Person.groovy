package cscie56.ps2

class Person {

    String firstName
    String lastName
    def number
    String role
    Integer pointsScored

    static belongsTo = [team:Team]

    static constraints = {
    }
}
