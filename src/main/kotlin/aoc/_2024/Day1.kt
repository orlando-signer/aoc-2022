package aoc._2024

import aoc.Day
import kotlin.math.absoluteValue

class Day1 : Day(1) {

    override fun partOne(): Any {
        val (firstList, secondList) = getLists()
        return firstList.zip(secondList).sumOf { (first, second) -> (first - second).absoluteValue }
    }

    private fun getLists(): Pair<List<Int>, List<Int>> {
        val numbers = inputString.split("\n").flatMap { it.split("\\s+".toRegex()) }.map { it.toInt() }
        val firstList = numbers.filterIndexed { index, i -> index % 2 == 0 }.sorted()
        val secondList = numbers.filterIndexed { index, i -> index % 2 == 1 }.sorted()
        return Pair(firstList, secondList)
    }

    override fun partTwo(): Any {
        val (firstList, secondList) = getLists()
        val counted = secondList.groupingBy { it }.eachCount()
        return firstList.sumOf { counted.getOrDefault(it, 0) * it }
    }
}
