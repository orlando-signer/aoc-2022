package aoc._2022

import aoc.Day
import java.util.*

class Day5 : Day(5) {

    override fun partOne(): Any {
        val stacks = buildStack()

        inputList.dropWhile { it.isNotBlank() }
            .filter { it.isNotBlank() }
            .map { MoveAction.fromString(it) }
            .forEach { it.applyOn(stacks) }

        return stacks.map { it.peek() }
            .joinToString(separator = "") { it.toString() }
    }


    override fun partTwo(): Any {
        val stacks = buildStack()

        inputList.dropWhile { it.isNotBlank() }
            .filter { it.isNotBlank() }
            .map { MoveAction.fromString(it) }
            .forEach { it.applyOn2(stacks) }

        return stacks.map { it.peek() }
            .joinToString(separator = "") { it.toString() }
    }

    data class MoveAction(private val amount: Int, private val fromStack: Int, private val toStack: Int) {
        companion object {
            fun fromString(input: String): MoveAction {
                val actionList = Regex("[0-9]+").findAll(input)
                    .map(MatchResult::value).map { it.toInt() }.toList()
                return MoveAction(actionList[0], actionList[1] - 1, actionList[2] - 1)
            }
        }

        fun applyOn(stacks: List<Stack<Char>>) {
            repeat(amount) { stacks[toStack].push(stacks[fromStack].pop()) }
        }

        fun applyOn2(stacks: List<Stack<Char>>) {
            stacks[toStack].addAll(stacks[fromStack].takeLast(amount))
            repeat(amount) { stacks[fromStack].removeLast() }
        }
    }

    private fun buildStack(): List<Stack<Char>> {
        val dataList = inputList.takeWhile { it.isNotEmpty() }
            .asReversed()
        val numberOfStacks = dataList[0].trim().last().digitToInt()
        val stacks = List<Stack<Char>>(numberOfStacks) { Stack() }
        dataList.drop(1)
            .map {
                it.chunked(4).onEachIndexed() { index, crate ->
                    if (crate.isNotBlank()) {
                        stacks[index].push(crate.trim().replace("[", "").replace("]", "")[0])
                    }
                }
            }
        return stacks
    }
}
