package aoc._2024

import aoc.Day

class Day7 : Day(7) {

    override fun partOne(): Any {
        var result = 0L
        inputList.forEach {
            val expectedValue = it.split(":").first().toLong()
            val numbers = it.split(":")[1].trim().split(" ").map { it.toLong() }
            val evaluated = reduce(numbers)
            if (evaluated.contains(expectedValue)) {
                result += expectedValue
            }

        }
        return result
    }

    private fun reduce(numbers: List<Long>): List<Long> {
        if (numbers.size == 1) {
            return numbers
        }
        return reduce(listOf(numbers[0] * numbers[1]) + numbers.drop(2))
            .plus(reduce(listOf(numbers[0] + numbers[1]) + numbers.drop(2)))
    }

    override fun partTwo(): Any {
        return -1
    }
}
