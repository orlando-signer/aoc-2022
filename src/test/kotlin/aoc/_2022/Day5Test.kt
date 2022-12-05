package aoc._2022

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day5Test : FunSpec({

    val day = Day5()

    test("testPartOne") {
        day.partOne().shouldBe("CMZ")
    }

    test("testPartTwo") {
        day.partTwo().shouldBe("MCD")
    }
})
