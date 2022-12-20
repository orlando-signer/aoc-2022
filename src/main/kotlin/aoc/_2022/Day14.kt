package aoc._2022

import aoc.Day
import kotlinx.serialization.json.*
import java.util.stream.IntStream
import kotlin.math.max
import kotlin.math.min

class Day14 : Day(14) {

    override fun partOne(): Any {
        val cave = List(1000) { MutableList(1000) { false } }
        inputList.forEach { buildStructure(it, cave) }
        val dropPoint = Point(500, 0)

        return generateSequence(0) { it }.takeWhile { leiseRieseltDerSand(dropPoint, cave) }.count()
    }

    override fun partTwo(): Any {
        val cave = List(1000) { MutableList(1000) { false } }
        inputList.forEach { buildStructure(it, cave) }
        val dropPoint = Point(500, 0)
        val maxDepth = cave.maxOf { it -> it.indexOfLast { it } }
        cave.forEach { it[maxDepth + 2] = true }
        return generateSequence(0) { it }.takeWhile { leiseRieseltDerSand(dropPoint, cave) }.count()
    }

    private fun printCave(cave: List<List<Boolean>>) {
        cave.map { it -> (it.joinToString(separator = "") { if (it) "*" else " " }) }.dropWhile { it.isBlank() }
            .reversed().dropWhile { it.isBlank() }.reversed()
            .forEach { println(it) }
    }

    fun leiseRieseltDerSand(sand: Point, cave: List<MutableList<Boolean>>): Boolean {
        if (sand.y >= 999) { // abbrechen wenn wir zu tief fallen
            return false
        }
        val down = sand.copy(y = sand.y + 1)
        if (!cave[down.x][down.y]) { // keine Kollision
            return leiseRieseltDerSand(down, cave)
        }
        val left = down.copy(x = down.x - 1)
        if (!cave[left.x][left.y]) { // keine Kollision auf der linken seite
            return leiseRieseltDerSand(left, cave)
        }
        val right = down.copy(x = down.x + 1)
        if (!cave[right.x][right.y]) { // keine Kollision auf der rechten seite
            return leiseRieseltDerSand(right, cave)
        }
        // wir können nicht mehr runterfallen, der Eingang ist blockiert
        if (cave[sand.x][sand.y]) {
            return false
        }

        // Wir fallen nicht weiter -> fertig
        cave[sand.x][sand.y] = true
        return true
    }

    private fun buildStructure(inputLine: String, cave: List<MutableList<Boolean>>) {
        inputLine.split("->").map { Point.from(it) }.zipWithNext().forEach { (p1, p2) ->
            run {
                if (p1.x == p2.x) { // move vertically
                    val min = min(p1.y, p2.y)
                    val max = max(p1.y, p2.y)
                    IntRange(min, max).forEach { cave[p1.x][it] = true }
                } else {
                    val min = min(p1.x, p2.x)
                    val max = max(p1.x, p2.x)
                    cave.subList(min, max + 1).forEach { it[p1.y] = true }
                }
            }
        }
    }

    data class Point(val x: Int, val y: Int) {
        companion object {
            fun from(s: String): Point {
                val splitted = s.trim().split(",")
                return Point(splitted[0].toInt(), splitted[1].toInt())
            }
        }
    }
}
