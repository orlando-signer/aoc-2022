package aoc._2024

import aoc.Day
import aoc.util.Matrix
import java.math.BigDecimal
import java.math.BigInteger

class Day13 : Day(13) {

    override fun partOne(): Any {
        return inputString.split("\n\n")
            .sumOf { Game.ofPart1(it.split("\n")).calcualteTokenCost() }
    }

    override fun partTwo(): Any {
        return inputString.split("\n\n")
            .sumOf { Game.ofPart2(it.split("\n")).calcualteTokenCost() }
    }

    class Game(private val buttonPresses: Matrix, private val prizePosition: List<BigDecimal>) {

        companion object {
            fun ofPart1(input: List<String>): Game {
                val (matrix, prizePosition) = extract(input)
                return Game(matrix, prizePosition)
            }

            fun ofPart2(input: List<String>): Game {
                val (matrix, prizePosition) = extract(input)
                return Game(matrix, prizePosition.map { it + BigDecimal("10000000000000") })
            }

            private fun extract(input: List<String>): Pair<Matrix, List<BigDecimal>> {
                val digitsRegex = Regex("\\d+")
                val buttonValues =
                    input.take(2).map { digitsRegex.findAll(it).map { it.value }.joinToString(separator = " ") }
                val matrix = Matrix.ofString(buttonValues).transpose()
                val prizePosition = digitsRegex.findAll(input.last())
                    .map { BigDecimal(it.value) }.toList()
                return Pair(matrix, prizePosition)
            }
        }

        fun calcualteTokenCost(): BigInteger {
            try {
                val buttonPresses = buttonPresses.solveLinearEquation(prizePosition).map { it.toBigIntegerExact() }
                return BigInteger("3") * buttonPresses.first() + buttonPresses.last()
            } catch (e: Exception) {
                return BigInteger.ZERO
            }
        }
    }
}
