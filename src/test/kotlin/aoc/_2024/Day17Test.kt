package aoc._2024

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.config.enabledOrReasonIf
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled

class Day17Test : FunSpec({

    val day = Day17()

    xtest("testPartOne") {
        day.partOne().shouldBe("4,6,3,5,6,3,5,2,1,0")
    }
    xtest("testPartTwo") {
        day.partTwo().shouldBe(TODO("Set value from example 2"))
    }
})
