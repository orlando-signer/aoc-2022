package days

import java.lang.IllegalArgumentException

class Day2 : Day(2) {

    private val magicLookupMap: Map<String, Int> = mapOf(
        "A" to 7,
        "B" to 4,
        "C" to 1,
        "X" to 5,
        "Y" to 0,
        "Z" to 4
    )

    override fun partOne(): Any {
        return inputList.map { it.split(" ") }
            .sumOf { (magicLookupMap.getValue(it[0]) + magicLookupMap.getValue(it[1])) % 9 +1}
    }

    override fun partTwo(): Any {
        return ""
    }

}
