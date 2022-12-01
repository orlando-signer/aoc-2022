package days

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class Day1Test : FunSpec({

    val dayOne = Day1()

    test("testPartOne") {
        dayOne.partOne().shouldBe(24000)
    }

    test("testPartTwo") {
        dayOne.partTwo().shouldBe(45000)
    }
})
