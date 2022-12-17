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
            val splitted = it.split(" ")
            val direction = directionFrom(splitted[0])
            val amount = splitted[1].toInt()

            repeat(amount) {
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
        fun move(direction: Direction): Point {
            return when (direction) {
                Direction.UP -> Point(x, y + 1)
                Direction.DOWN -> Point(x, y - 1)
                Direction.LEFT -> Point(x - 1, y)
                Direction.RIGHT -> Point(x + 1, y)
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

    enum class Direction() {
        UP, DOWN, LEFT, RIGHT
    }

    private fun directionFrom(string: String): Direction {
        return when (string) {
            "U" -> Direction.UP
            "D" -> Direction.DOWN
            "L" -> Direction.LEFT
            "R" -> Direction.RIGHT
            else -> throw IllegalStateException("fucked up: $string")
        }
    }

}
