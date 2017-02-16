package cscie56.ps2

class Conference {

    String name
    Integer id

    static hasMany = [teams:Team]

    static constraints = {
    }
}
