package cscie56.ps2

class Season {

    String name
    Date startDate
    Date endDate

    static belongsTo = [league:League]
    static hasMany = [conferences:Conference]

    static constraints = {
    }
}
