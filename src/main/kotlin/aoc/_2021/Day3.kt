package aoc._2021

import aoc.Day
import kotlin.streams.toList

class Day3 : Day(3) {

    override fun partOne(): Any {
        val valueMap = mutableMapOf<Int, Int>()
        inputList.forEach {
            it.chars().toList().forEachIndexed { index, value -> valueMap.merge(index, value - 48, Int::plus) }
        }
        val totalLength = inputList.size
        val gamma = valueMap.values.joinToString(separator = "") { if (it > totalLength / 2) "1" else "0" }.toInt(2)
        val epsilon = valueMap.values.joinToString(separator = "") { if (it < totalLength / 2) "1" else "0" }.toInt(2)

        return gamma * epsilon
    }


    override fun partTwo(): Any {
        val numberOfOnes = inputList.sumOf { it[0].digitToInt() }
        val totalLength = inputList.size
        val keep = if (numberOfOnes > totalLength / 2) '1' else '0'
        inputList.filter { it[0] == keep }

        val position = PositionTwo()
        inputList.forEach { position.apply(it) }
        return position.multiply()
    }

    class PositionTwo(private var x: Int, private var y: Int, private var aim: Int) {

        constructor() : this(0, 0, 0)

        fun apply(input: String) {
            val (action, value) = input.split(" ")
            when (action) {
                "forward" -> {
                    y += aim * value.toInt();
                    x += value.toInt();
                }

                "up" -> aim -= value.toInt()
                "down" -> aim += value.toInt()
            }
        }

        fun multiply(): Int {
            return x * y
        }
    }
}
