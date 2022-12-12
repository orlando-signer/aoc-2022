package aoc._2022

import aoc.Day
import java.lang.RuntimeException

class Day11 : Day(11) {

    override fun partOne(): Any {
        val monkeys = inputString.split("\n\n").map { Monkey.from(it) }
        for (i in 0 until 20) {
            monkeys.forEach { it.inspectAndThrow1(monkeys) }
        }
        return monkeys.sortedByDescending { it.getInsepctions() }.take(2).map { it.getInsepctions() }
            .reduce { acc, value -> acc * value }
    }

    override fun partTwo(): Any {
        val monkeys = inputString.split("\n\n").map { Monkey.from(it) }
        val worryLevelMod = inputList.filter { it.contains("divisible by") }.map { it.substringAfterLast(" ").toLong() }
            .reduce { acc, value -> acc * value }
        for (i in 0 until 10000) {
            monkeys.forEach { it.inspectAndThrow2(monkeys, worryLevelMod) }
        }
        return monkeys.sortedByDescending { it.getInsepctions() }.take(2).map { it.getInsepctions() }
            .reduce { acc, value -> acc * value }
    }

    class Monkey(op: (input: Long) -> Long, test: (input: Long) -> Int, startingItems: List<Long>) {
        private val op = op
        private val test = test
        internal val items = startingItems.toMutableList()
        private var inspections = 0.toLong()

        companion object {
            fun from(string: String): Monkey {
                val lines = string.lines()
                val startingItems = lines[1].split(":")[1].split(",").map { it.trim().toLong() }
                val operation = parseOperation(lines[2])
                val test = parseTest(lines)

                return Monkey(operation, test, startingItems)
            }

            private fun parseOperation(string: String): (input: Long) -> Long {
                return when {
                    string.contains("old * old") -> { i: Long -> i * i }
                    string.contains("+") -> { i: Long -> i + string.substringAfterLast(" ").toLong() }
                    string.contains("*") -> { i: Long -> i * string.substringAfterLast(" ").toLong() }
                    else -> throw RuntimeException("i fucked up: $string")
                }
            }

            private fun parseTest(lines: List<String>): (test: Long) -> Int {
                val divisibleBy = lines[3].substringAfterLast(" ").toLong()
                val throwToPositive = lines[4].substringAfterLast(" ").toInt()
                val throwToNegative = lines[5].substringAfterLast(" ").toInt()
                return { test: Long -> if (test % divisibleBy == 0.toLong()) throwToPositive else throwToNegative }
            }
        }

        fun inspectAndThrow1(toMonkeys: List<Monkey>) {
            inspections += items.size
            items.map { op(it) }.map { it / 3 }.forEach {
                run {
                    val toMonkey = test(it)
                    toMonkeys[toMonkey].catchItem(it)
                }
            }
            items.clear()
        }

        fun inspectAndThrow2(toMonkeys: List<Monkey>, worryLevelMod: Long) {
            inspections += items.size
            items.map { op(it) }.map { it % worryLevelMod }.forEach {
                run {
                    val toMonkey = test(it)
                    toMonkeys[toMonkey].catchItem(it)
                }
            }
            items.clear()
        }

        private fun catchItem(item: Long) {
            items.add(item)
        }

        fun getInsepctions(): Long {
            return inspections
        }
    }
}
