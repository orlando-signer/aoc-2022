package days

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day4Test : FunSpec({

    val day = Day4()

    test("testPartOne") {
        day.partOne().shouldBe(2)
    }

    test("testPartTwo") {
        day.partTwo().shouldBe(4)
    }
})
