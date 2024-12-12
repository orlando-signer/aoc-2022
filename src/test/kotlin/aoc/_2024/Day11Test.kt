package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day11Test : FunSpec({

    val day = Day11()

    test("testPartOne") {
        day.partOne().shouldBe(55312)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(65601038650482L)
    }
})
