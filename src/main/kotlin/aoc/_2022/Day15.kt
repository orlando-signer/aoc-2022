package aoc._2022

import aoc.Day
import java.lang.IllegalStateException
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day15 : Day(15) {

    override fun partOne(): Any {
        val signals = inputList.map { Signal.from(it) }
        val y = 10 // change to 2000000 for real result
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
        // idea: the beacon is either on an edge, or in between 2 parallel lines (with a gap of 1) intersected by
        // another pair of parallel lines (with a gap of 1)
        // steps:
        // 1. normalize lines (find intersection with y=0)

        val lim = 20 // change to 4_000_000 for real result
        val signals = inputList.map { Signal.from(it) }
       // drawIt(signals)

        val zeroIntersectsFromUpRight = signals.map { it.x + abs(it.y - it.manhattenDistance()) }.toSet()
        val zeroIntersectsFromUpLeft = signals.map { it.x - abs(it.y - it.manhattenDistance()) }.toSet()

        // all lines that contain a neighbour with a gap of 1 (to the right)
        val upRightPairs = zeroIntersectsFromUpRight.filter { zeroIntersectsFromUpRight.contains(it - 2) }
        val upLeftPairs = zeroIntersectsFromUpLeft.filter { zeroIntersectsFromUpLeft.contains(it - 2) }

        for (upRight in upRightPairs) {
            for (upLeft in upLeftPairs)
                if ((upRight + upLeft) / 2 < lim) {
                    println("candidate with $upRight -> $upLeft = ${(upRight + upLeft) / 2}")
                }
        }
        return -1
    }

    private fun drawIt(signals: List<Signal>) {

        for (s in signals) {
            println("Signal $s")
            for (y in -20..30) {
                for (x in -20..30) {
                    print(if (abs(s.x - x) + abs(s.y - y) <= s.manhattenDistance()) "#" else ".")
                }
                println("  $y")
            }
        }
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
