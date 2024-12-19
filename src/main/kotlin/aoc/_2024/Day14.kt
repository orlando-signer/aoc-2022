package aoc._2024

import aoc.Day

class Day14 : Day(14) {

    override fun partOne(): Any {
        val gridSize = if (isInTest) GridSize(11, 7) else GridSize(101, 103)
        val robots = inputList.map { Robot.of(it) }

        val groupedByQuadrant = robots.map { it.afterIterations(100, gridSize) }
            .groupBy { it.getQuadrant(gridSize) }
        return groupedByQuadrant.filter { it.key > 0 }.map { it.value.size }.reduce(Int::times)
    }

    override fun partTwo(): Any {
        return -1
    }

    data class Robot(val x: Int, val y: Int, val vx: Int, val vy: Int) {
        companion object {
            fun of(input: String): Robot {
                val digitsRegex = Regex("-?\\d+")
                val values = digitsRegex.findAll(input).map { Integer.parseInt(it.value) }.toList()
                return Robot(values[0], values[1], values[2], values[3])
            }
        }

        fun afterIterations(n: Int, gridSize: GridSize): Robot {
            val newX = (x + n * vx).mod(gridSize.x)
            val newY = (y + n * vy).mod(gridSize.y)
            return this.copy(x = newX, y = newY)
        }

        /**
         * Returns the Quadrant the robot is in, or Zero if exactly in the middle lines
         */
        fun getQuadrant(gridSize: GridSize): Int {
            val middleX = gridSize.x / 2
            val middleY = gridSize.y / 2
            if (x == middleX || y == middleY) {
                return 0
            }
            if (x < middleX) {
                return if (y < middleY) 1 else 3
            }
            return if (y < middleY) 2 else 4
        }
    }

    data class GridSize(val x: Int, val y: Int) {}
}
