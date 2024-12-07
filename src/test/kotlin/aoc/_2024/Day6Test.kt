package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day6Test : FunSpec({

    val day = Day6()

    test("testPartOne") {
        day.partOne().shouldBe(41)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(6)
    }
})
