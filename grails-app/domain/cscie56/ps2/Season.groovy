package cscie56.ps2

class Season {

    String name
    Date startDate
    Date endDate


    static hasMany = [teams:Team]

    static constraints = {
    }
}
