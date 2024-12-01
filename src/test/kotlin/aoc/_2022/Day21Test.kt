package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day21Test : FunSpec({

    val day = Day21()

    test("testPartOne") {
        day.partOne().shouldBe(152)
    }


    test("testPartTwo").config(enabled = false) {
        day.partTwo().shouldBe(301)
    }
})
