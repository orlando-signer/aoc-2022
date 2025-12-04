package aoc._2025

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day4Test : FunSpec({

    val day = Day4()

    test("testPartOne") {
        day.partOne().shouldBe(13)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(43)
    }
})
