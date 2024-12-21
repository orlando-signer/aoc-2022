package aoc._2024

import aoc.Day
import kotlin.math.sqrt

class Day14 : Day(14) {
    private val gridSize = if (isInTest) GridSize(11, 7) else GridSize(101, 103)

    override fun partOne(): Any {
        val robots = inputList.map { Robot.of(it) }

        val groupedByQuadrant = robots.map { it.afterIterations(100, gridSize) }.groupBy { it.getQuadrant(gridSize) }
        return groupedByQuadrant.filter { it.key > 0 }.map { it.value.size }.reduce(Int::times)
    }

    override fun partTwo(): Any {
        val robots = inputList.map { Robot.of(it) }

        // idea 1: find symmetry along x-axis, didnt work because the tree is not centered AND not all
        // robots are part of the tree
        // for (i in 1..1000000) {
        //     val newRobots = robots.map { it.afterIterations(i, gridSize) }
        //     val isSymmetricalAlongYAxis = newRobots.groupBy { it.y }
        //         .all {
        //             val robotsInLine = it.value
        //             robotsInLine.all { robot ->
        //                 robot.x == (gridSize.x / 2) || robotsInLine.map { it.x }.contains(gridSize.x - robot.x)
        //             }
        //         }
        //     if(isSymmetricalAlongYAxis) {
        //         return i
        //     }
        // }

        // idea 2: find low entropy -> robots in Christmas tree should be relativly close together
        var lowestEntropy = Double.MAX_VALUE
        var mostBeautifulTreeAfterSeconds = Int.MAX_VALUE
        for (i in 1..10000) {
            val newRobots = robots.map { it.afterIterations(i, gridSize) }
            var entropy = 0.0
            for(j in newRobots.indices){
                for(k in j+1 until newRobots.size){
                    entropy +=  sqrt((newRobots[k].x * newRobots[k].x + newRobots[k].y * newRobots[k].y).toDouble())
                }
            }
            if (entropy < lowestEntropy) {
                lowestEntropy = entropy
                mostBeautifulTreeAfterSeconds = i
                // print tree
                // println("Entropy: $entropy producing beautiful tree after $i seconds")
                // val canvas = List(gridSize.y) { MutableList(gridSize.x) { ' ' } }
                // newRobots.forEach {
                // canvas[it.y][it.x] = 'X'
                // }
                // canvas.forEach {
                // println(it.joinToString(separator = ""))
                // }
            }
        }


        return mostBeautifulTreeAfterSeconds
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
