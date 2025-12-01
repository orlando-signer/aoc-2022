package aoc._2025

import aoc.Day
import kotlin.math.absoluteValue

class Day1 : Day(1) {

    override fun partOne(): Any {
        var pos = 50
        var zeroes = 0
        inputList.forEach {
            val direction = it[0]
            val amount = it.substring(1).toInt()
            if (direction == 'R') {
                pos = (pos + amount) % 100
            } else {
                pos = (pos - amount) % 100
                if (pos < 0) {
                    pos = 100 - pos.absoluteValue
                }
            }
            if (pos == 0) {
                zeroes++
            }
        }
        return zeroes
    }

    // wrong 7203
    // wrong 6321
    // wrong 6665
    // correct 6689
    override fun partTwo(): Any {
        var pos = 50
        var zeroes = 0
        inputList.forEach {
            val direction = it[0]
            val amount = it.substring(1).toInt()

            val subtract = direction == 'L'

            for (i in 0..<amount) {
                pos += if (subtract) -1 else 1
                if (pos >= 100) {
                    pos = 0
                    zeroes++
                } else if (pos == 0) {
                    zeroes++
                } else if (pos < 0) {
                    pos = 99
                }
            }
        }
        return zeroes
    }
}
