package cscie56.ps2

class Conference {

    String name

    static belongsTo = [seasons:Season]
    static hasMany = [teams:Team]

    static constraints = {
    }
}
