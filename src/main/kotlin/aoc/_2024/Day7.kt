package aoc._2024

import aoc.Day
import kotlin.math.exp

class Day7 : Day(7) {

    override fun partOne(): Any {
        return evaluate(this::reduce)
    }

    override fun partTwo(): Any {
        return evaluate(this::reducePart2)

    }

    private fun evaluate(reduceOp: (List<Long>, Long) -> Boolean): Long {
        var result = 0L
        inputList.forEach { // Berni Tribute Code, zwischendurch ists auch mit ner Loop einfacher ;)
            val expectedValue = it.split(":").first().toLong()
            val numbers = it.split(":")[1].trim().split(" ").map { it.toLong() }
            if (reduceOp.invoke(numbers, expectedValue)) {
                result += expectedValue
            }
        }
        return result
    }

    private fun reduce(numbers: List<Long>, expectedValue: Long): Boolean {
        if (numbers.size == 1) {
            return numbers.first() == expectedValue
        } else if (numbers.first() > expectedValue) {
            return false
        }
        return reduce(listOf(numbers[0] * numbers[1]) + numbers.drop(2), expectedValue)
                || reduce(listOf(numbers[0] + numbers[1]) + numbers.drop(2), expectedValue)
    }

    private fun reducePart2(numbers: List<Long>, expectedValue: Long): Boolean {
        if (numbers.size == 1) {
            return numbers.first() == expectedValue
        } else if (numbers.first() > expectedValue) {
            return false
        }
        return reducePart2(listOf(numbers[0] * numbers[1]) + numbers.drop(2), expectedValue)
                || reducePart2(listOf(numbers[0] + numbers[1]) + numbers.drop(2), expectedValue)
                || reducePart2(
            listOf((numbers[0].toString() + numbers[1].toString()).toLong()) + numbers.drop(2),
            expectedValue
        )
    }
}
