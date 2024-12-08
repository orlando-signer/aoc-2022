package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class Day8Test : FunSpec({

    val day = Day8()

    test("testPartOne") {
        day.partOne().shouldBe(14)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(34)
    }

    test("findAntinodes") {
        val a = Day8.Antenna('a', Day8.Point(4, 3))
        val b = Day8.Antenna('a', Day8.Point(5, 5))

        a.findAntinodesTo(b).shouldContainExactlyInAnyOrder(Day8.Point(3, 1), Day8.Point(6, 7))
        b.findAntinodesTo(a).shouldContainExactlyInAnyOrder(Day8.Point(3, 1), Day8.Point(6, 7))
    }
})
