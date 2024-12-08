package aoc._2024

import aoc.Day

class Day8 : Day(8) {

    override fun partOne(): Any {
        val groupedByFrequency = findAntennaGroups()

        val antinodes = mutableSetOf<Point>()
        for (freqAntennas in groupedByFrequency.values) {
            for (i in freqAntennas.indices) {
                for (j in i + 1 until freqAntennas.size) {
                    antinodes += freqAntennas[i].findAntinodesTo(freqAntennas[j])
                }
            }
        }
        return antinodes.filter { isInBound(it) }
            .size
    }


    override fun partTwo(): Any {
        val groupedByFrequency = findAntennaGroups()

        val antinodes = mutableSetOf<Point>()
        for (freqAntennas in groupedByFrequency.values) {
            for (i in freqAntennas.indices) {
                for (j in i + 1 until freqAntennas.size) {
                    antinodes += freqAntennas[i].findHarmonicAntinodesTo(freqAntennas[j])
                }
            }
        }
        return antinodes.filter { isInBound(it) }
            .size
    }

    private fun findAntennaGroups(): Map<Char, List<Antenna>> {
        val antennas = mutableListOf<Antenna>()
        inputList.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char.isLetterOrDigit()) {
                    antennas += Antenna(char, Point(x, y))
                }
            }
        }
        return antennas.groupBy { it.frequency }
    }

    fun isInBound(it: Point) = it.x >= 0 && it.x < inputList[0].length && it.y >= 0 && it.y < inputList.size

    data class Antenna(val frequency: Char, val point: Point) {
        fun findAntinodesTo(other: Antenna): List<Point> {
            val distance = point.distanceTo(other.point)
            val node1 = point.plus(distance).plus(distance)
            val node2 = point.minus(distance)
            return listOf(node1, node2)
        }

        fun findHarmonicAntinodesTo(other: Antenna): List<Point> {
            val distance = point.distanceTo(other.point)
            val antinodes = mutableListOf(point)
            var node1 = point.plus(distance)
            // wäre hübscher mit OutOfBoundsCheck hier, aber ausser Role kümmerts ja niemand ;)
            repeat(100) {
                antinodes += node1
                node1 = node1.plus(distance)
            }
            var node2 = point.minus(distance)
            repeat(100) {
                antinodes += node2
                node2 = node2.minus(distance)
            }

            return antinodes
        }
    }

    data class Point(val x: Int, val y: Int) {
        fun distanceTo(other: Point): Point {
            return Point(other.x - this.x, other.y - this.y)
        }

        fun plus(other: Point): Point {
            return Point(x + other.x, y + other.y)
        }

        fun minus(other: Point): Point {
            return Point(x - other.x, y - other.y)
        }
    }
}
