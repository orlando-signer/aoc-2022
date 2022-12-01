package days

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class Day0Test : FunSpec({

    val dayOne = Day0()

    test("testPartOne") {
        dayOne.partOne().shouldBe("THIS IS")
    }

    test("testPartTwo") {
        val partTwo = dayOne.partTwo()
        partTwo.shouldNotBeNull()
            .shouldBeInstanceOf<String>()
            .shouldBe("FILE")
    }
})
