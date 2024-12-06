package aoc._2024

import aoc.Day

class Day5 : Day(5) {

    override fun partOne(): Any {
        val rules = inputList.takeWhile { it.isNotBlank() }
            .map { it.split("|") }
            .map { it.map { it.toInt() } }
            .groupBy({ it.first() }, { it[1] })
        val pagesToProduce = inputList.filter { it.contains(",") }
            .map { it.split(",").map { it.toInt() } }

        var count = 0
        for (pages in pagesToProduce) {
            var isBreaking = false
            for (i in pages.indices) {
                val rules = rules.getOrDefault(pages[i], emptyList())
                isBreaking = isBreaking || pages.filterIndexed { idx, _ -> idx < i }.any { rules.contains(it) }
            }
            if (!isBreaking) {
                count += pages[pages.size / 2]
            }
        }

        return count
    }

    override fun partTwo(): Any {
        return -1
    }
}
