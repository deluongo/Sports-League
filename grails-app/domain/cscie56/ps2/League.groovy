package cscie56.ps2

class League {

    String name

    Integer id

    static hasMany = [conferences:Conference]

    static constraints = {
    }
}
