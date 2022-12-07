package aoc._2022

import aoc.Day
import java.util.*

class Day6 : Day(6) {

    override fun partOne(): Any {
        val first = inputString.windowed(4).find { it.toSet().size == 4 }
        return inputString.indexOf(first!!) + 4
    }


    override fun partTwo(): Any {
        val first = inputString.windowed(14).find { it.toSet().size == 14 }
        return inputString.indexOf(first!!) + 14
    }

}
