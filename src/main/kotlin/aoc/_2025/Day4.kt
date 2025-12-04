package aoc._2025

import aoc.Day

class Day4 : Day(4) {

    fun isPaper(x: Int, y: Int, map: List<CharArray>): Int {
        if (x < 0 || y < 0 || x >= map.size || y >= map[x].size) {
            return 0
        }
        return if (map[x][y] == '@') 1 else 0
    }

    fun countPaperRolls(x: Int, y: Int, map: List<CharArray>): Int = listOf(
        isPaper(x - 1, y - 1, map),
        isPaper(x, y - 1, map),
        isPaper(x + 1, y - 1, map),
        isPaper(x - 1, y, map),
        isPaper(x + 1, y, map),
        isPaper(x - 1, y + 1, map),
        isPaper(x, y + 1, map),
        isPaper(x + 1, y + 1, map)
    ).sum()

    override fun partOne(): Any {
        val map = inputList.map { it.toCharArray() }
        var total = 0
        for (x in 0 until map.size) {
            for (y in 0 until map[x].size) {
                if (map[x][y] != '@') {
                    continue
                }
                val paperRolls = countPaperRolls(x, y, map)
                if (paperRolls < 4) {
                    total++
                }
            }
        }
        return total
    }


    override fun partTwo(): Any {
        val map = inputList.map { it.toCharArray() }
        var prevTotal = -1
        var total = 0
        while (prevTotal != total) {
            prevTotal = total
            for (x in 0 until map.size) {
                for (y in 0 until map[x].size) {
                    if (map[x][y] != '@') {
                        continue
                    }
                    val paperRolls = countPaperRolls(x, y, map)
                    if (paperRolls < 4) {
                        total++
                        map[x][y] = '.'
                    }
                }
            }
        }
        return total
    }
}
