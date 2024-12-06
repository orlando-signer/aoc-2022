package aoc._2024

import aoc.Day

class Day5 : Day(5) {

    private val rules = inputList.takeWhile { it.isNotBlank() }
        .map { it.split("|") }
        .map { it.map { it.toInt() } }
        .groupBy({ it.first() }, { it[1] })
    private val pagesToProduce = inputList.filter { it.contains(",") }
        .map { it.split(",").map { it.toInt() } }

    override fun partOne(): Any {
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
        var count = 0
        val comp = object : Comparator<Int> {
            override fun compare(in1: Int, in2: Int): Int {
                if (rules.getOrDefault(in1, emptyList()).contains(in2)) {
                    return -1
                } else if (rules.getOrDefault(in2, emptyList()).contains(in1)) {
                    return 1
                }
                return 0;
            }
        }
        for (pages in pagesToProduce) {
            val sorted = pages.sortedWith(comp)
            if (pages != sorted) {
                count += sorted[sorted.size / 2]
            }
        }

        return count
    }
}
