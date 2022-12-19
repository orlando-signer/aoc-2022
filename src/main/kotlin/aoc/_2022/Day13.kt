package aoc._2022

import aoc.Day
import kotlinx.serialization.json.*

class Day13 : Day(13) {

    override fun partOne(): Any {
        val correctIndices = mutableListOf<Int>()
        inputString.split("\n\n")
            .map { it.split("\n") }
            .forEachIndexed { i, input ->
                if (compare(input[0], input[1]) <= 0) {
                    correctIndices.add(i + 1)
                }
            }
        return correctIndices.sum()
    }

    override fun partTwo(): Any {
        val divider1 = "[[2]]"
        val divider2 = "[[6]]"
        val list = inputList.filter { it.isNotBlank() }.toMutableList()
        list.add(divider1)
        list.add(divider2)

        list.sortWith { s1, s2 -> compare(s1, s2) }
        return (list.indexOf(divider1) + 1) * (list.indexOf(divider2) + 1)
    }

    private fun compare(s1: String, s2: String): Int {
        val json1 = Json.parseToJsonElement(s1)
        val json2 = Json.parseToJsonElement(s2)
        return inOrder(json1, json2)
    }

    // Return negative or 0 value if in Order
    private fun inOrder(j1: JsonElement, j2: JsonElement): Int {
        if (j1 is JsonPrimitive && j2 is JsonPrimitive) {
            return j1.content.toInt() - j2.content.toInt()
        } else {
            val jArray1 = if (j1 is JsonPrimitive) JsonArray(listOf(j1)) else j1.jsonArray
            val jArray2 = if (j2 is JsonPrimitive) JsonArray(listOf(j2)) else j2.jsonArray

            for ((first, second) in jArray1.zip(jArray2)) {
                val result = inOrder(first, second)
                if (result != 0) {
                    return result
                }
            }
            return jArray1.size - jArray2.size
        }
    }
}
