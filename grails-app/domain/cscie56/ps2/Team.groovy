package cscie56.ps2

class Team {

	/* ~~~~~~~~~~~~~~ PROPERTIES ~~~~~~~~~~~~~ */
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	String name, streak
	Integer wins, losses, ties, scored, allowed, delta
	def winPercent
	Character lastResult, result
    //def coachingStaff
    //def roster

    static hasMany = [persons:Person]


    static constraints = {

    }
}
