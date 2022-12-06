package aoc._2021

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day3Test : FunSpec({

    val day = Day3()

    test("testPartOne") {
        day.partOne().shouldBe(198)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(900)
    }
})
