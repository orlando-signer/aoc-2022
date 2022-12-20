package aoc._2022

import aoc.Day
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day15 : Day(15) {

    override fun partOne(): Any {
        val signals = inputList.map { Signal.from(it) }
        val y = 2000000 // change to 10 for test
        val signalOverlaps = mutableSetOf<Int>()

        for (signal in signals) {
            val manhattenDistance = signal.manhattenDistance()
            val horizontalOverlap = manhattenDistance - abs(signal.y - y)
            if (horizontalOverlap > 0) {
                val overlaps = IntRange(signal.x - horizontalOverlap, signal.x + horizontalOverlap)
                signalOverlaps.addAll(overlaps)
            }
        }
        val beaconsInRowY = signals.filter { it.bY == y }.map { it.bX }.distinct().size
        return signalOverlaps.size - beaconsInRowY
    }

    override fun partTwo(): Any {
        return ""
    }

    data class Signal(val x: Int, val y: Int, val bX: Int, val bY: Int) {
        fun manhattenDistance(): Int {
            return abs(x - bX) + abs(y - bY)
        }

        companion object {
            fun from(s: String): Signal {
                val splitted = s.trim().split("[^\\-0-9]".toRegex()).filter { it.isNotBlank() }.map { it.toInt() }
                return Signal(splitted[0], splitted[1], splitted[2], splitted[3])
            }
        }
    }
}
