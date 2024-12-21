package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day15Test : FunSpec({

    val day = Day15()

    test("testPartOne") {
        day.partOne().shouldBe(10092)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(9021)
    }
})
