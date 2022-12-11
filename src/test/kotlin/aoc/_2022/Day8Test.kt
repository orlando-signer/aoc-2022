package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day8Test : FunSpec({

    val day = Day8()

    test("testPartOne") {
        day.partOne().shouldBe(21)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(8)
    }
})
