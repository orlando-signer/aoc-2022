package days

class Day3 : Day(3) {


    override fun partOne(): Any {
        return inputList.map { findMisplacedChar(it) }
            .sumOf { mapCharToIndex(it) }

    }

    override fun partTwo(): Any {
        return inputList.chunked(3)
            .map { findIntersectingChar(it) }
            .sumOf { mapCharToIndex(it) }
    }

    private fun findMisplacedChar(input: String): Char {
        val firstCompartment = input.subSequence(0, input.length / 2).toSet()
        val secondCompartment = input.subSequence(input.length / 2, input.length).toSet()
        return firstCompartment.intersect(secondCompartment).first()

    }

    private fun mapCharToIndex(input: Char): Int {
        return if (input.isUpperCase()) input.code - 38 else input.code - 96
    }

    private fun findIntersectingChar(input: List<String>): Char {
        return input.map { it.toSet() }
            .reduce { acc, set -> acc.intersect(set) }
            .first()
    }
}
