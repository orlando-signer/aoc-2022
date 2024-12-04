package aoc._2024

import aoc.util.InputReader
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day4Test : FunSpec({

    val day = Day4()

    test("testPartOne") {
        day.partOne().shouldBe(18)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(TODO("Set value from example 2"))
    }

    test("rotate4TimesIsSame") {
        val matrix = InputReader.getInputAsList("aoc/_2024", 4)
        var rotated = day.rotate90degrees(matrix)
        rotated = day.rotate90degrees(rotated)
        rotated = day.rotate90degrees(rotated)
        rotated = day.rotate90degrees(rotated)

        rotated.shouldBe(matrix)
    }
})
