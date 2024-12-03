package aoc._2024

import aoc.Day

class Day3 : Day(3) {

    override fun partOne(): Any {
        val mulMatches = Regex("mul\\(\\d{1,3},\\d{1,3}\\)").findAll(inputString)
        return mulMatches.sumOf { match ->
            match.value.split(Regex("\\D+"))
                .filter { it.isNotBlank() }
                .map { it.toInt() }
                .reduce { a, b -> a * b }
        }
    }

    override fun partTwo(): Any {
        return -1
    }
}
