package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day10Test : FunSpec({

    val day = Day10()

    test("testPartOne") {
        day.partOne().shouldBe(13140)
    }

    test("testPartTwo") {
        // outputs to println
        day.partTwo().shouldBe("")
    }
})
