package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day5Test : FunSpec({

    val day = Day5()

    test("testPartOne") {
        day.partOne().shouldBe(143)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(123)
    }
})
