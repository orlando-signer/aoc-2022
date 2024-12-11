package aoc._2024

import aoc.Day

class Day10 : Day(10) {

    override fun partOne(): Any {
        var trailheadCount = 0
        for (y in inputList.indices) {
            for (x in 0..<inputList[0].length) {
                if (inputList[y][x] == '0') {
                    trailheadCount += countTrailhead(x, y).size

                }
            }
        }
        return trailheadCount
    }

    override fun partTwo(): Any {
        var trailheadCount = 0
        for (y in inputList.indices) {
            for (x in 0..<inputList[0].length) {
                if (inputList[y][x] == '0') {
                    trailheadCount += countTrailheadPaths(x, y)

                }
            }
        }
        return trailheadCount
    }

    private fun countTrailhead(x: Int, y: Int): Set<Pair<Int, Int>> {
        val currentHeight = inputList[y][x]
        if (currentHeight == '9') {
            return setOf(Pair(x,y))
        }
        val sum = mutableSetOf<Pair<Int, Int>>()
        if (x > 0 && inputList[y][x - 1] == (currentHeight + 1)) {
            sum += countTrailhead(x - 1, y, )
        }
        if (x < inputList[0].length - 1 && inputList[y][x + 1] == (currentHeight + 1)) {
            sum += countTrailhead(x + 1, y)
        }
        if (y > 0 && inputList[y - 1][x] == (currentHeight + 1)) {
            sum += countTrailhead(x, y - 1)
        }
        if (y < inputList.size - 1 && inputList[y + 1][x] == (currentHeight + 1)) {
            sum += countTrailhead(x, y + 1)
        }
        return sum
    }

    private fun countTrailheadPaths(x: Int, y: Int): Int {
        val currentHeight = inputList[y][x]
        if (currentHeight == '9') {
            return 1
        }
        var sum = 0
        if (x > 0 && inputList[y][x - 1] == (currentHeight + 1)) {
            sum += countTrailheadPaths(x - 1, y)
        }
        if (x < inputList[0].length - 1 && inputList[y][x + 1] == (currentHeight + 1)) {
            sum += countTrailheadPaths(x + 1, y)
        }
        if (y > 0 && inputList[y - 1][x] == (currentHeight + 1)) {
            sum += countTrailheadPaths(x, y - 1)
        }
        if (y < inputList.size - 1 && inputList[y + 1][x] == (currentHeight + 1)) {
            sum += countTrailheadPaths(x, y + 1)
        }
        return sum
    }
}
