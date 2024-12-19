package aoc.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import java.math.BigDecimal

class MatrixTest : FunSpec({

    test("solve linear equation 1") {
        val matrix = Matrix.ofString(
            listOf(
                "2  5   7",
                "1  1   1",
                "2  1  -1"
            )
        )
        val b = listOf(52, 9, 0).map { BigDecimal(it) }

        val x = matrix.solveLinearEquation(b)
        x.shouldContainExactly(listOf(1, 3, 5).map { BigDecimal(it) })
    }

    test("solve linear equation 2") {
        val matrix = Matrix.ofString(
            listOf(
                "1  0   0",
                "4  1   0",
                "-6 5   1"
            )
        )
        val b = listOf(3, 14, -7).map { BigDecimal(it) }

        val x = matrix.solveLinearEquation(b)
        x.shouldContainExactly(listOf(3, 2, 1).map { BigDecimal(it) })
    }

    test("solve linear equation 3") {
        val matrix = Matrix.ofString(
            listOf(
                "4    1    2    3",
                "3   -6    5    4",
                "9    7   -7    8",
                "1    1    1   -1"
            )
        )
        val b = listOf(6, 1, 4, 1).map { BigDecimal(it) }

        val x = matrix.solveLinearEquation(b)
        x.shouldContainExactly(listOf("-1.01875", "1.8375", "1.75625", "1.575").map { BigDecimal(it) })
    }
})