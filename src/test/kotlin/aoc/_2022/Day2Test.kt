package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day2Test : FunSpec({

    val day = Day2()

    test("testPartOne") {
        day.partOne().shouldBe(15)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(12)
    }
})
