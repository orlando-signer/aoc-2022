package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day14Test : FunSpec({

    val day = Day14()

    test("testPartOne") {
        day.partOne().shouldBe(24)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(93)
    }
})
