package aoc._2022

import aoc.Day

class Day0 : Day(0) {

    override fun partOne(): Any {
        return inputList.take(2)
            .map { it.uppercase() }
            .joinToString(" ")
    }

    override fun partTwo(): Any {
        return inputString.split("\n")
            .filterNot { it.isEmpty() }
            .map { it.uppercase() }
            .last()
    }
}
