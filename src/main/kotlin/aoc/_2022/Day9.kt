package aoc._2022

import aoc.Day
import java.lang.IllegalStateException
import kotlin.math.abs
import kotlin.math.sign

class Day9 : Day(9) {

    override fun partOne(): Any {
        return runWithRopeLength(2)
    }

    override fun partTwo(): Any {
        return runWithRopeLength(10)
    }

    private fun runWithRopeLength(n: Int): Int {
        val tailVisits = mutableSetOf<Point>()

        val rope = MutableList(n) { Point(0, 0) }
        inputList.forEach {
            val (direction, amount) = it.split(" ")

            repeat(amount.toInt()) {
                rope[0] = rope[0].move(direction)
                for (idx in 1 until rope.size) {
                    rope[idx] = rope[idx].follow(rope[idx - 1])
                }

                tailVisits.add(rope.last())
            }
        }
        return tailVisits.size
    }

    data class Point(val x: Int, val y: Int) {
        fun move(direction: String): Point {
            return when (direction) {
                "U" -> Point(x, y + 1)
                "D" -> Point(x, y - 1)
                "L" -> Point(x - 1, y)
                "R" -> Point(x + 1, y)
                else -> throw IllegalStateException("somethings wrong: $direction")
            }
        }

        fun follow(point: Point): Point {
            val xDiff = point.x - x
            val yDiff = point.y - y

            if (abs(xDiff) <= 1 && abs(yDiff) <= 1) {
                return this // diff zu klein, nix moven
            }
            return Point(x + xDiff.sign, y + yDiff.sign)
        }
    }
}
