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
        val mulMatches = Regex("mul\\(\\d{1,3},\\d{1,3}\\)").findAll(inputString)
        val doMatches = Regex("do\\(\\)").findAll(inputString)
        val dontMatches = Regex("don't\\(\\)").findAll(inputString)

        val enabledAt = doMatches.map { it.range.first }
        val disabledAt = dontMatches.map { it.range.first }

        return mulMatches.filter { mulMatch ->
            (enabledAt.findLast { it < mulMatch.range.first } ?: -1) >
                    (disabledAt.findLast { it < mulMatch.range.first } ?: -2)
        }.sumOf { match ->
            match.value.split(Regex("\\D+"))
                .filter { it.isNotBlank() }
                .map { it.toInt() }
                .reduce { a, b -> a * b }

        }
    }
}