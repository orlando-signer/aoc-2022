package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day15Test : FunSpec({

    val day = Day15()

    test("testPartOne") {
        day.partOne().shouldBe(26)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(93)
    }
})
