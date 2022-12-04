package aoc._2021

import aoc.Day

class Day2 : Day(2) {

    override fun partOne(): Any {
        val position = PositionOne()
        inputList.forEach { position.apply(it) }
        return position.multiply()
    }

    class PositionOne(private var x: Int, private var y: Int) {

        constructor() : this(0, 0)

        fun apply(input: String) {
            val (action, value) = input.split(" ")
            when (action) {
                "forward" -> x += value.toInt()
                "up" -> y -= value.toInt()
                "down" -> y += value.toInt()
            }
        }

        fun multiply(): Int {
            return x * y
        }
    }

    override fun partTwo(): Any {
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
