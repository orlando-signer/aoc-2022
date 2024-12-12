package aoc.util

import java.io.File

object PrepareDay {
    private val year = "_2024"
    private val day: Int = 11

    @JvmStatic
    fun main(args: Array<String>) {
        initDay()
        initDayTest()
        initInput()
    }

    private fun initDay(){
       val content =  """
            package aoc.$year

            import aoc.Day

            class Day$day : Day($day) {

                override fun partOne(): Any {
                    return TODO("Implement Part One")
                }

                override fun partTwo(): Any {
                   return TODO("Implement Part Two")
                }
            }

        """.trimIndent()
        File("src/main/kotlin/aoc/$year/Day$day.kt").writeText(content)
    }

    private fun initDayTest(){
        val content =  """
            package aoc.$year
            
            import io.kotest.core.spec.style.FunSpec
            import io.kotest.matchers.shouldBe
            
            class Day${day}Test : FunSpec({
            
                val day = Day$day()
            
                test("testPartOne") {
                    day.partOne().shouldBe(TODO("Set value from example 1"))
                }
            
                test("testPartTwo") {
                    day.partTwo().shouldBe(TODO("Set value from example 2"))
                }
            })

        """.trimIndent()
        File("src/test/kotlin/aoc/$year/Day${day}Test.kt").writeText(content)
    }

    private fun initInput(){
        File("src/main/resources/aoc/$year/input_day_${day}.txt").createNewFile()
        File("src/test/resources/aoc/$year/input_day_${day}.txt").createNewFile()
    }
}