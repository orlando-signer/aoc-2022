package aoc._2022

import aoc.Day
import kotlinx.serialization.json.*
import java.lang.IllegalStateException
import java.util.LinkedList
import java.util.stream.IntStream
import kotlin.math.max
import kotlin.math.min

class Day21 : Day(21) {

    override fun partOne(): Any {
        val commandMap = inputList.map { it.split(":") }.map { Pair(it[0].trim(), it[1].trim()) }.toMap().toMutableMap()
        return getValue("root", commandMap)
    }

    fun getValue(monkeyName: String, commandMap: MutableMap<String, String>): Long {
        val command = commandMap.getValue(monkeyName)
        if (command.toIntOrNull() != null) {
            return command.toLong()
        }
        val op = command[5]
        val monkeyA = command.subSequence(0, 4)
        val monkeyB = command.subSequence(7, 11)
        val valueA = getValue(monkeyA.toString(), commandMap)
        val valueB = getValue(monkeyB.toString(), commandMap)
        val result = when (op) {
            '+' -> valueA + valueB
            '-' -> valueA - valueB
            '*' -> valueA * valueB
            '/' -> valueA / valueB
            else -> throw IllegalStateException("unknown op: $op")
        }
        commandMap[monkeyName] = result.toString()
        return result
    }

    override fun partTwo(): Any {
        val initalCommandMap = inputList.map { it.split(":") }.map { Pair(it[0].trim(), it[1].trim()) }.toMap()
        val rootCmd = initalCommandMap.getValue("root")

        // first monkey from root leads tu 'humn', so get the value of the second monkey
        var commandMap = initalCommandMap.toMutableMap()
        val targetValue = getValue(rootCmd.subSequence(7, 11).toString(), commandMap)
        var currentValue = -1L
        var humnValue = -100000000

//        while(currentValue != targetValue){
//            humnValue++
//            commandMap = initalCommandMap.toMutableMap()
//            commandMap["humn"] = humnValue.toString()
//            currentValue = getValue(rootCmd.subSequence(0,4).toString(), commandMap)
//            println("humn = $humnValue -> $currentValue != $targetValue")
//        }

        return humnValue
    }

}
