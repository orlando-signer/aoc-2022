package aoc._2022

import aoc.Day
import java.util.*
import kotlin.math.abs

class Day10 : Day(10) {

    override fun partOne(): Any {
        val cyclesMap = buildCyclesMap()
        return listOf(20, 60, 100, 140, 180, 220).sumOf { cyclesMap.lowerEntry(it).value * it }
    }

    override fun partTwo(): Any {
        val cyclesMap = buildCyclesMap()

        val crt = MutableList((cyclesMap.lastKey() / 40 + 1) * 40) { " " }
        for (i in 0 until crt.size) {
            val register = cyclesMap.floorEntry(i)?.value ?: 0
            if (abs(register - (i % 40)) <= 1) {
                crt[i] = "#"
            }
        }
        crt.chunked(40).forEach { it -> println(it.joinToString(separator = "") { it }) }
        return ""
    }

    private fun buildCyclesMap(): NavigableMap<Int, Int> {
        val cycles = mutableMapOf<Int, Int>()
        var register = 1
        var cycle = 0
        inputList.forEach {
            when (it) {
                "noop" -> cycle++
                else -> {
                    val op = it.split(" ")[1].toInt()
                    cycle += 2
                    register += op
                    cycles[cycle] = register
                }
            }
        }
        return TreeMap(cycles)
    }


}
