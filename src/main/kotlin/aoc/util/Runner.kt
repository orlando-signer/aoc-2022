package aoc.util

import aoc.Day
import org.reflections.Reflections
import kotlin.math.max
import kotlin.time.ExperimentalTime
import kotlin.time.TimedValue
import kotlin.time.measureTimedValue

@ExperimentalTime
object Runner {

    private val reflections = Reflections("aoc")

    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isNotEmpty()) {
            val day = try {
                args[0].toInt()
            } catch (e: NumberFormatException) {
                printError("Day argument must be an integer")
                return
            }

            val dayClass = getAllDayClasses()?.find { it.simpleName.replace("Day", "").toInt() == day }
            if (dayClass != null) {
                println(DayRunner(dayClass).result)
            } else {
                printError("Day $day not found")
            }
        } else {
            val allDayClasses = getAllDayClasses()
            if (allDayClasses != null) {
                allDayClasses.parallelStream()
                    .map { DayRunner(it) }
                    .toList()
                    .sortedWith(dayComparator)
                    .forEach { println(it.result) }
            } else {
                printError("Couldn't find day classes - make sure you're in the right directory and try building again")
            }
        }
    }

    private val dayComparator = compareBy<DayRunner> { it.dayClass.packageName.takeLast(4).toInt() }
        .thenBy { it.dayNumber() }

    class DayRunner(val dayClass: Class<out Day>) {

        val result: String

        init {
            result = getResultString()
        }

        private fun getResultString(): String {
            val day = dayClass.constructors[0].newInstance() as Day

            val (partOne, partTwo) = run(day)
            val result = getPartsString(partOne, partTwo)
            return "\n=== DAY ${dayClass.name} ===\n" + result
        }


        private fun run(day: Day): Pair<TimedValue<Any>, TimedValue<Any>> {
            return Pair(
                measureTimedValue { safeExecute { day.partOne() } },
                measureTimedValue { safeExecute { day.partTwo() } }
            )
        }

        private fun safeExecute(block: () -> Any): Any {
            return try {
                block()
            } catch (e: Exception) {
                "Failed: $e"
            }
        }

        private fun getPartsString(partOne: TimedValue<Any>, partTwo: TimedValue<Any>): String {
            val padding = max(
                partOne.value.toString().length,
                partTwo.value.toString().length
            ) + 14        // 14 is 8 (length of 'Part 1: ') + 6 more
            return "Part 1: ${partOne.value}".padEnd(padding, ' ') + "(${partOne.duration})\n" +
                    "Part 2: ${partTwo.value}".padEnd(padding, ' ') + "(${partTwo.duration})"
        }

        fun dayNumber(): Int = dayClass.simpleName.replace("Day", "").toInt()
    }

    private fun getAllDayClasses(): MutableSet<Class<out Day>>? {
        return reflections.getSubTypesOf(Day::class.java)
    }

    private fun printError(message: String) {
        System.err.println("\n=== ERROR ===\n$message")
    }
}
