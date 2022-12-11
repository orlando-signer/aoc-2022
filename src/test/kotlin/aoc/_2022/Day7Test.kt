package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day7Test : FunSpec({

    val day = Day7()

    test("testPartOne") {
        day.partOne().shouldBe(95437)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(24933642)
    }
})
