package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day15Test : FunSpec({

    val day = Day15()

    test("testPartOne") {
        day.partOne().shouldBe(26)
    }

    test("testPartTwo") .config(enabled = false) {
        day.partTwo().shouldBe(56000011)
    }
})
