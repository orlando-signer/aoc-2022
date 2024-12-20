package aoc._2024

import aoc.Day

class Day11 : Day(11) {

    override fun partOne(): Any {
        return countStones(25)
    }

    override fun partTwo(): Any {
        return countStones(75)
    }

    private fun countStones(iterations: Int): Long {
        var stonesCount = inputString.split(" ")
            .map { it.toLong() }.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        val stoneCache = mutableMapOf<Long, List<Long>>()
        repeat(iterations) {
            val nextStonesCount = mutableMapOf<Long, Long>()
            for ((stone, count) in stonesCount) {
                stoneCache.getOrPut(stone) { mapStone(stone) }.forEach {
                    nextStonesCount.merge(it, count, Long::plus)
                }
            }
            stonesCount = nextStonesCount
        }
        return stonesCount.values.sum()
    }

    private fun mapStone(stone: Long): List<Long> {
        val stoneStr = stone.toString()
        if (stone == 0L) {
            return listOf(1)
        } else if (stoneStr.length % 2 == 0) {
            return listOf(
                stoneStr.take(stoneStr.length / 2).toLong(),
                stoneStr.drop(stoneStr.length / 2).toLong()
            )
        }
        return listOf(stone * 2024)
    }
}

