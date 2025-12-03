package aoc._2025

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day2Test : FunSpec({

    val day = Day2()

    test("testPartOne") {
        day.partOne().shouldBe(1227775554L)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(4174379265L)
    }
})
