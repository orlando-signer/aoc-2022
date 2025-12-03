package aoc._2025

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day3Test : FunSpec({

    val day = Day3()

    test("testPartOne") {
        day.partOne().shouldBe(357)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(3121910778619)
    }
})
