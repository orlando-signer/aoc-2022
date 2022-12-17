package aoc._2022

import aoc.Day
import kotlin.math.max
import kotlin.math.min

class Day12 : Day(12) {

    override fun partOne(): Any {
        val elevationMap = inputList.map { it.toCharArray() }
        val stepMap = List(inputList.size) { MutableList(inputList[0].length) { Int.MAX_VALUE } }
        var signalPositionX = 0;
        var signalPositionY = 0
        // find start- and end-points
        elevationMap.forEachIndexed { x, list ->
            list.forEachIndexed { y, char ->
                if (char == 'S') {
                    stepMap[x][y] = 0
                    elevationMap[x][y] = 'a'
                } else if (char == 'E') {
                    signalPositionX = x
                    signalPositionY = y
                    elevationMap[x][y] = 'z'
                }
            }
        }

        return findShortestPath(elevationMap, stepMap, Pair(signalPositionX, signalPositionY))
    }

    override fun partTwo(): Any {
        val elevationMap = inputList.map { it.toCharArray() }
        val stepMap = List(inputList.size) { MutableList(inputList[0].length) { Int.MAX_VALUE } }
        var signalPositionX = 0;
        var signalPositionY = 0
        // find start- and end-points
        elevationMap.forEachIndexed { x, list ->
            list.forEachIndexed { y, char ->
                if (char == 'S' || char == 'a') {
                    stepMap[x][y] = 0
                    elevationMap[x][y] = 'a'
                } else if (char == 'E') {
                    signalPositionX = x
                    signalPositionY = y
                    elevationMap[x][y] = 'z'
                }
            }
        }

        return findShortestPath(elevationMap, stepMap, Pair(signalPositionX, signalPositionY))    }

    private fun findShortestPath(
        elevationMap: List<CharArray>, stepMap: List<MutableList<Int>>, signalPosition: Pair<Int, Int>
    ): Int {
        var stepCount = 0
        while (stepMap[signalPosition.first][signalPosition.second] == Int.MAX_VALUE) {
            for (x in elevationMap.indices) {
                for (y in 0 until elevationMap[0].size) {
                    if (stepMap[x][y] == stepCount) {

                        getPointsToCheck(x, y, elevationMap).forEach { (tmpX, tmpY) ->
                            if (stepMap[tmpX][tmpY] > stepMap[x][y] && (elevationMap[tmpX][tmpY] - elevationMap[x][y]) <= 1) {
                                stepMap[tmpX][tmpY] = stepCount + 1
                            }
                        }

                    }
                }
            }
            stepCount++
        }
//        stepMap.forEach {
//            println(it.map { it.toString().replace(Int.MAX_VALUE.toString(), "∞").padStart(3, ' ') }
//                .joinToString(separator = ""))
//        }
        return stepMap[signalPosition.first][signalPosition.second]
    }

    private fun getPointsToCheck(x: Int, y: Int, elevationMap: List<CharArray>): Set<Pair<Int, Int>> {
        return setOf(
            Pair(max(x - 1, 0), y),
            Pair(min(x + 1, elevationMap.size - 1), y),
            Pair(x, max(y - 1, 0)),
            Pair(x, min(y + 1, elevationMap[0].size - 1))
        )
    }
}
