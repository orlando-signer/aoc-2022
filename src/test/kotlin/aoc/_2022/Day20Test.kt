package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day20Test : FunSpec({

    val day = Day20()

    test("testPartOne") {
        day.partOne().shouldBe(3)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(1623178306)
    }
})
