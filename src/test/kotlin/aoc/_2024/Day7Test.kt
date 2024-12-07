package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day7Test : FunSpec({

    val day = Day7()

    test("testPartOne") {
        day.partOne().shouldBe(3749)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(11387)
    }
})
