package aoc._2025

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day5Test : FunSpec({

    val day = Day5()

    test("testPartOne") {
        day.partOne().shouldBe(3)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(14)
    }
})
