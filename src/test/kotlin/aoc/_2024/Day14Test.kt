package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day14Test : FunSpec({

    val day = Day14()

    test("testPartOne") {
        day.partOne().shouldBe(12)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(6) // its not a tree, its a monster!
    }

    test("simple robot") {
        val gridSize = Day14.GridSize(11, 7)
        val robot = Day14.Robot(2, 4, 2, -3)

        robot.afterIterations(1, gridSize).x.shouldBe(4)
        robot.afterIterations(1, gridSize).y.shouldBe(1)

        robot.afterIterations(2, gridSize).x.shouldBe(6)
        robot.afterIterations(2, gridSize).y.shouldBe(5)

        robot.afterIterations(3, gridSize).x.shouldBe(8)
        robot.afterIterations(3, gridSize).y.shouldBe(2)

        robot.afterIterations(4, gridSize).x.shouldBe(10)
        robot.afterIterations(4, gridSize).y.shouldBe(6)

        robot.afterIterations(5, gridSize).x.shouldBe(1)
        robot.afterIterations(5, gridSize).y.shouldBe(3)

        (1..100).forEach { println(robot.afterIterations(it, gridSize)) }
    }
})
