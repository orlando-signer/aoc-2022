package aoc._2024

import aoc.Day

class Day4 : Day(4) {

    override fun partOne(): Any {
        var count = 0
        var rotated = rotate90degrees(inputList)
        repeat(4) {
            rotated = rotate90degrees(rotated)
            count += countXmas(rotated)
        }
        return count
    }

    private fun countXmas(matrix: List<String>): Int {
        var count = 0
        for (y in matrix.indices) {
            for (x in 0..matrix[0].length - 4) {
                if (matrix[y][x] == 'X') {
                    // check horizontal left to right
                    if (matrix[y][x + 1] == 'M' && matrix[y][x + 2] == 'A' && matrix[y][x + 3] == 'S') {
                        count++
                    }
                    // check diagonal NE to SW
                    if (y <= matrix.size - 4 && matrix[y + 1][x + 1] == 'M' && matrix[y + 2][x + 2] == 'A' && matrix[y + 3][x + 3] == 'S') {
                        count++
                    }
                }
            }
        }
        return count
    }

    fun rotate90degrees(matrix: List<String>): List<String> {
        // rotation -> transponieren und zeilen reversen
        return matrix.indices.map { idx -> matrix.map { it[idx] }.joinToString(separator = "") }
            .map { it.reversed() }
    }

    override fun partTwo(): Any {
        return -1
    }
}
