package aoc._2024

import aoc.Day
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day2 : Day(2) {

    override fun partOne(): Any {
        return inputList.count { isReportSafe(it.split(" ").map { it.toInt() }) }
    }

    private fun isReportSafe(report: List<Int>): Boolean {
        val deltas = report
            .zipWithNext()
            .map { (a, b) -> a - b }
        return deltas.all { it in 1..3 } || deltas.all { it in -3..-1 }
    }

    override fun partTwo(): Any {
        inputList.forEach {
            val safe = isDampenedReportSafe(it.split(" ").map { it.toInt() })
            println("$it -> $safe")
        }
        return 0
    }

    private fun isDampenedReportSafe(report: List<Int>): Boolean {
        // zuerst normaler Report prüfen
        val deltas = report
            .zipWithNext()
            .map { (a, b) -> a - b }
        if (deltas.all { it in 1..3 } || deltas.all { it in -3..-1 }) {
            return true
        }

        // Es gibt was zu entfernen --> Index der Diff rausfinden
        val idxOfDroppable = deltas.zipWithNext()
            .map {  pair -> if (pair.first.sign != pair.second.sign || pair.first.absoluteValue !in 1..3 || pair.second.absoluteValue !in 1..3) 1 else 0 }
        val idxToDrop = idxOfDroppable.indexOfFirst { it != 0 }

        // bitzli random um den Index dinger weglöschen
        val a = isReportSafe(report.filterIndexed { idx, _ -> idx != idxToDrop })
        val b = isReportSafe(report.filterIndexed { idx, _ -> idx != (idxToDrop + 1) })
        val c = isReportSafe(report.filterIndexed { idx, _ -> idx != (idxToDrop + 2) })

        return a || b || c
    }
}