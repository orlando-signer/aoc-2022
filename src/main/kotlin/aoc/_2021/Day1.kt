package aoc._2021

import aoc.Day

class Day1 : Day(1) {

    override fun partOne(): Any {
        return inputList.zipWithNext()
            .count { (a, b) -> a.toInt() < b.toInt() }
    }

    override fun partTwo(): Any {
        return inputList.windowed(3)
            .map { it -> it.sumOf { it.toInt() } }
            .zipWithNext()
            .count { (a, b) -> a < b }
    }
}
