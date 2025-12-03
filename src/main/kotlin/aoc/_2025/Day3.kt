package aoc._2025

import aoc.Day

class Day3 : Day(3) {

    override fun partOne(): Any {
        var sum = 0
        inputList.forEach { bank ->
            val first = max(bank.dropLast(1))
            val second = max(bank.substring(bank.indexOf(first.toString()) + 1, bank.length))
            sum += (first.toString() + second.toString()).toInt()
        }
        return sum
    }

    fun max(bank: String): Int {
        if (bank.isEmpty()) {
            return 0
        }
        return bank.first().digitToInt().coerceAtLeast(max(bank.substring(1)))
    }

    override fun partTwo(): Any {
        var sum = 0L
        inputList.forEach {
            val batteries = mutableListOf<Int>()
            var bank = it
            for (i in 11 downTo 0) {
                val max = max(bank.dropLast(i))
                batteries += max
                bank = bank.substring(bank.indexOf(max.toString()) + 1, bank.length)
            }
            sum += batteries.map { it.toString() }.reduce { acc, i -> acc + i }.toLong()
        }
        return sum
    }
}
