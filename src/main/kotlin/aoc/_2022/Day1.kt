package aoc._2022

import aoc.Day

class Day1 : Day(1) {

    override fun partOne(): Any {
        return inputString.split("\n\n")
            .maxOf { it -> it.split("\n").sumOf { it.toInt() } }
    }

    override fun partTwo(): Any {
        return inputString.split("\n\n")
            .map { it -> it.split("\n").sumOf { it.toInt() } }
            .sortedDescending()
            .take(3)
            .sum()
    }
}
