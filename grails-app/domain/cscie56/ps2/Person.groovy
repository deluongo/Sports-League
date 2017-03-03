package cscie56.ps2

class Person {

    //PS2
    String firstName
    String lastName
    def number
    String role
    Integer pointsScored

    static belongsTo = [team:Team]

    static hasMany = [gameStats:GameStats]

    static constraints = {
    }

    //PS3
    String bio
    Date birthDate
    String birthPlace
    String height // must be displayed in feet and inches (e.g., 6’1”)
    Integer weight
    String universityAttended

    static mapping = {
        bio type: 'text'
    }
}
