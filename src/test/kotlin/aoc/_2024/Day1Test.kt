package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day1Test : FunSpec({

    val day = Day1()

    test("testPartOne") {
        day.partOne().shouldBe(11)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(31)
    }
})
