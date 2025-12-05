package aoc._2025

import aoc.Day
import java.math.BigInteger
import kotlin.math.max

class Day5 : Day(5) {

    override fun partOne(): Any {
        val ranges = mutableListOf<LongRange>()
        val ingredients = mutableListOf<Long>()

        inputList.forEach {
            if (it.contains('-')) {
                ranges += LongRange(it.split('-')[0].toLong(), it.split('-')[1].toLong())
            } else if (!it.isBlank()) {
                ingredients += it.toLong()
            }
        }
        return ingredients.filter { i -> ranges.find { r -> r.contains(i) } != null }.size
    }

    override fun partTwo(): Any {
        val ranges = inputList.filter { it.contains('-') }
            .map { LongRange(it.split('-')[0].toLong(), it.split('-')[1].toLong()) }
            .sortedBy { it.first }


        // 352509891817900 your answer is too high.
        // 352509891817881 correct
        return merge(ranges).sumOf { it.last - it.first + 1 }
    }

    fun merge(ranges: List<LongRange>): List<LongRange> {
        if (ranges.size <= 1) return ranges
        val first = ranges.first()
        val overlappingRange = ranges.drop(1).find { overlaps(first, it) }

        if (overlappingRange == null) {
            val newRanges = merge(ranges.drop(1)).toMutableList()
            newRanges.addFirst(first)
            return newRanges
        }

        val mergedRange = mergeRange(first, overlappingRange)
        val newRanges = ranges.filter { it != first && it != overlappingRange }.toMutableList()
        newRanges.addFirst(mergedRange)
        return merge(newRanges)
    }

    fun overlaps(a: LongRange, b: LongRange): Boolean {
        if (a.first > b.first) {
            throw IllegalArgumentException("Ranges not sorted: $a > $b")
        }
        return a.last >= b.first - 1
    }

    fun mergeRange(a: LongRange, b: LongRange): LongRange {
        if (a.first > b.first) {
            throw IllegalArgumentException("Ranges not sorted: $a > $b")
        }
        return LongRange(a.first, max(a.last, b.last))
    }
}
