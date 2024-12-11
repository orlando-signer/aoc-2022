package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day9Test : FunSpec({

    val day = Day9()

    test("testPartOne") {
        day.partOne().shouldBe(1928)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(2858)
    }
})
