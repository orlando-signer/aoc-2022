package days

class Day4 : Day(4) {


    override fun partOne(): Any {
        return inputList.map { it.split(",") }
            .map { Pair(buildIntRange(it[0]), buildIntRange(it[1])) }
            .count { (a, b) -> a.fullyContains(b) || b.fullyContains(a) }
    }

    fun buildIntRange(input: String): IntRange {
        val splitted = input.split("-")
        return IntRange(splitted[0].toInt(), splitted[1].toInt())
    }

    fun IntRange.fullyContains(other: IntRange): Boolean {
        return first <= other.first && last >= other.last
    }

    override fun partTwo(): Any {
        return inputList.map { it.split(",") }
            .map { Pair(buildIntRange(it[0]), buildIntRange(it[1])) }
            .count { (a, b) -> a.intersect(b).isNotEmpty() }
    }
}
