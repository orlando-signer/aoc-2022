package aoc._2025

import aoc.Day

class Day2 : Day(2) {

    override fun partOne(): Any {
        var total = 0L
        inputString.split(",").forEach {
            val low = it.split("-")[0].toLong()
            val high = it.split("-")[1].toLong()

            val start = if (low < 10) low else low.toString().take(low.toString().length / 2).toLong()
            val end = high.toString().take(high.toString().length / 2 + 1).toLong()

            for (i in start..end) {
                val value = (i.toString() + i.toString()).toLong()
                if (value < low) {
                    continue
                }
                if (value > high) {
                    break
                }
                total += value
            }

            // bruteforce-solution 38310256125
//            for (i in low..high){
//                val iStr = i.toString()
//                if (iStr.take(iStr.length / 2) == iStr.substring(iStr.length/2)){
//                    total += i
//                }
//            }
        }
        return total
    }

    override fun partTwo(): Any {
        val invalidIds = mutableSetOf<Long>()
        inputString.split(",").forEach {
            val low = it.split("-")[0].toLong()
            val high = it.split("-")[1].toLong()

            val maxRepeats = high.toString().length
            for (repeats in 2..maxRepeats) {
                for (i in 0..Long.MAX_VALUE) {
                    val value = i.toString().repeat(repeats).toLong()
                    if (value < low) {
                        continue
                    }
                    if (value > high) {
                        break
                    }
                    invalidIds += value
                }

            }
        }
        return invalidIds.sum()
    }
}
