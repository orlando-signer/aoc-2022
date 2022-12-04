package aoc.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class InputReaderTest : FunSpec({

    test("testReadInputAsString") {
        val testInputAsString = InputReader.getInputAsString("aoc/_2022", 0)
        testInputAsString.shouldBe("this\nis\na\ntest input\nfile\n")
    }

    test("testReadInputAsList") {
        val testInputAsList = InputReader.getInputAsList("aoc/_2022", 0)
        testInputAsList.shouldContainExactly("this", "is", "a", "test input", "file")
    }
})
