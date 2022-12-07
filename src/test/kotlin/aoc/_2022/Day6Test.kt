package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day6Test : FunSpec({

    val day = Day6()

    test("testPartOne") {
        day.partOne().shouldBe(7)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(19)
    }
})
