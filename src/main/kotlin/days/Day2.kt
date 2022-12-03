package days

import java.lang.IllegalArgumentException

class Day2 : Day(2) {

    private val pointsMatrix = arrayOf(
        arrayOf(4, 8, 3),
        arrayOf(1, 5, 9),
        arrayOf(7, 2, 6)
    )

    private val pointsMatrix2 = arrayOf(
        arrayOf(3, 4, 8),
        arrayOf(1, 5, 9),
        arrayOf(2, 6, 7)
    )

    override fun partOne(): Any {
        return inputList.map { inputToIndices(it) }
            .sumOf { (a, b) -> pointsMatrix[a][b] }
    }

    override fun partTwo(): Any {
        return inputList.map { inputToIndices(it) }
            .sumOf { (a, b) -> pointsMatrix2[a][b] }
    }

    private fun inputToIndices(input: String): Pair<Int, Int> {
        val splitted = input.split(" ")
        // Subtracts the Charcode of A (65) resp X (88) to get a zero-based index
        return  Pair((splitted[0][0] - 65).code, (splitted[1][0] - 88).code)
    }
}
