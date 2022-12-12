package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day11Test : FunSpec({

    val day = Day11()

    test("testPartOne") {
        day.partOne().shouldBe(10605)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(2713310158)
    }
})
