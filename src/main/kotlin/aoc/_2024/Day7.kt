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

    private fun evaluate(reduceOp: (List<Long>, Int, Long, Long) -> Boolean): Long {
        var result = 0L
        inputList.forEach { // Berni Tribute Code, zwischendurch ists auch mit ner Loop einfacher ;)
            val expectedValue = it.split(":").first().toLong()
            val numbers = it.split(":")[1].trim().split(" ").map { it.toLong() }
            if (reduceOp.invoke(numbers, 1, numbers.first(), expectedValue)) {
                result += expectedValue
            }
        }
        return result
    }

    private fun reduce(numbers: List<Long>, index: Int, currentValue: Long, expectedValue: Long): Boolean {
        if (currentValue == expectedValue) {
            return numbers.size == index
        } else if (currentValue > expectedValue || index >= numbers.size ) {
            return false
        }
        return reduce(numbers, index + 1, currentValue * numbers[index], expectedValue)
                || reduce(numbers, index + 1, currentValue + numbers[index], expectedValue)
    }

    private fun reducePart2(numbers: List<Long>, index: Int, currentValue: Long, expectedValue: Long): Boolean {
        if (currentValue == expectedValue) {
            return numbers.size == index
        } else if (currentValue > expectedValue || index >= numbers.size ) {
            return false
        }
        return reducePart2(numbers, index + 1, currentValue * numbers[index], expectedValue)
                || reducePart2(numbers, index + 1, currentValue + numbers[index], expectedValue)
                || reducePart2(
            numbers, index + 1, (currentValue.toString() + numbers[index].toString()).toLong(), expectedValue
        )
    }
}
