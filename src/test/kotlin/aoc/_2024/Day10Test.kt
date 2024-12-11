package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day10Test : FunSpec({

    val day = Day10()

    test("testPartOne") {
        day.partOne().shouldBe(36)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(81)
    }
})
