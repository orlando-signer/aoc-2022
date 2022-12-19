package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day13Test : FunSpec({

    val day = Day13()

    test("testPartOne") {
        day.partOne().shouldBe(13)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(140)
    }
})
