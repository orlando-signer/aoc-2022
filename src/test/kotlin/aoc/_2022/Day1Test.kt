package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day1Test : FunSpec({

    val day = Day1()

    test("testPartOne") {
        day.partOne().shouldBe(24000)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(45000)
    }
})
