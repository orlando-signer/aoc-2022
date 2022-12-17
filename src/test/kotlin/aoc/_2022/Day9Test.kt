package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day9Test : FunSpec({

    val day = Day9()

    test("testPartOne") {
        day.partOne().shouldBe(13)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(1)
    }

    // Muss testinput geswitcht werden
    test("testPartTwoWithInput2").config(enabled = false) {
        day.partTwo().shouldBe(36)
    }
})
