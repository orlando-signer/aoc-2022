package aoc._2021

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day2Test : FunSpec({

    val day = Day2()

    test("testPartOne") {
        day.partOne().shouldBe(150)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(900)
    }
})
