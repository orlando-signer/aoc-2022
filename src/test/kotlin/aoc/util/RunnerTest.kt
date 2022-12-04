package aoc.util

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemErr
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import kotlin.time.ExperimentalTime

@Ignored
@OptIn(ExperimentalTime::class)
class RunnerTest : FunSpec({

    test("testPrintDay") {
        val day1 = tapSystemOut { Runner.main(arrayOf("0")) }
        day1.shouldMatch(
            """
            
            === DAY 0 ===
            Part 1: THIS IS      \(.*\)
            Part 2: FILE         \(.*\)
            
            """.trimIndent()
        )
    }


    test("testPrintErrors") {
        val dayNotAString = tapSystemErr { Runner.main(arrayOf("one")) }
        dayNotAString.shouldBe("\n=== ERROR ===\nDay argument must be an integer\n")

        val dayNotExists = tapSystemErr { Runner.main(arrayOf("99")) }
        dayNotExists.shouldBe("\n=== ERROR ===\nDay 99 not found\n")
    }
})