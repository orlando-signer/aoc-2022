package aoc._2024

import aoc.Day

class Day2 : Day(2) {

    override fun partOne(): Any {
        return inputList.filter { isReportSafe(it) }.count()
    }

    private fun isReportSafe(report: String): Boolean {
        val deltas = report.split(" ").map { it.toInt() }
            .zipWithNext()
            .map { (a, b) -> a - b }
        return deltas.all { it in 1..3 } || deltas.all { it in -3..-1 }
    }

    override fun partTwo(): Any {
        return -1
    }
}
