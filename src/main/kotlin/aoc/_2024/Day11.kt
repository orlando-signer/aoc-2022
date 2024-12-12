package aoc._2024

import aoc.Day

class Day11 : Day(11) {

    override fun partOne(): Any {
        var stones = inputString.split(" ").map { it.toLong() }
        repeat(25) {
            stones = mapStones(stones)
        }
        return stones.size
    }

    override fun partTwo(): Any {
        var stones = inputString.split(" ").map { it.toLong() }
        repeat(75) {
            stones = mapStones(stones)
            println(it)
        }
        return stones.size
    }

    private fun mapStones(stones: List<Long>): List<Long> {
        val newStones = mutableListOf<Long>()
        stones.forEach { stone ->
            run {
                val stoneStr = stone.toString()
                if (stone == 0L) {
                    newStones.add(1)
                } else if (stoneStr.length % 2 == 0) {
                    newStones += stoneStr.take(stoneStr.length / 2).toLong()
                    newStones += stoneStr.drop(stoneStr.length / 2).toLong()
                } else {
                    newStones += stone * 2024
                }
            }
        }
        return newStones
    }

}
