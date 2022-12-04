package aoc.util

import java.io.File

object InputReader {

    fun getInputAsString(year: String, day: Int): String {
        return fromResources(year, day).readText()
    }

    fun getInputAsList(year: String, day: Int): List<String> {
        return fromResources(year, day).readLines()
    }

    private fun fromResources(year: String, day: Int): File {
        return File(javaClass.classLoader.getResource("$year/input_day_$day.txt").toURI())
    }
}
