package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day2Test : FunSpec({

    val day = Day2()

    test("testPartOne") {
        day.partOne().shouldBe(2)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(TODO("Set value from example 2"))
    }
})
