package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.math.BigInteger

class Day13Test : FunSpec({

    val day = Day13()

    test("testPartOne") {
        day.partOne().shouldBe(BigInteger("480"))
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(BigInteger("1"))
    }
})
