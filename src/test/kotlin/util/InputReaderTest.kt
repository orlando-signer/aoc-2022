package util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class InputReaderTest : FunSpec({

    test("testReadInputAsString") {
        val testInputAsString = InputReader.getInputAsString(1)
        testInputAsString.shouldBe("this\nis\na\ntest input\nfile\n")
    }

    test("testReadInputAsList") {
        val testInputAsList = InputReader.getInputAsList(1)
        testInputAsList.shouldContainExactly("this", "is", "a", "test input", "file")
    }
})
