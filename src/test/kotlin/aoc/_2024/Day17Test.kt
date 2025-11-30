package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day17Test : FunSpec({

    val day = Day17()

    test("testPartOne") {
        day.partOne().shouldBe("4,6,3,5,6,3,5,2,1,0")
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(TODO("Set value from example 2"))
    }
})
