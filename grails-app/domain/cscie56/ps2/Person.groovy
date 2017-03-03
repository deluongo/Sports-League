package cscie56.ps2

class Person {

    //PS2
    String firstName
    String lastName
    def number
    String role
    Integer pointsScored

    //PS3
    String bio
    Date birthDate
    String birthPlace
    String height // must be displayed in feet and inches (e.g., 6’1”)
    Integer weight
    String universityAttended

    static belongsTo = [team:Team]

    static constraints = {
    }

    static mapping = {
        bio type: 'longtext'
    }
}
