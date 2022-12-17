package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day12Test : FunSpec({

    val day = Day12()

    test("testPartOne") {
        day.partOne().shouldBe(31)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(29)
    }
})
