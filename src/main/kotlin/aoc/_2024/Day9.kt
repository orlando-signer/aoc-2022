package aoc._2024

import aoc.Day

class Day9 : Day(9) {

    override fun partOne(): Any {
        val disk = mutableListOf<Int>()
        for (i in inputString.indices) {
            val number = inputString[i].digitToInt()
            val value = if (i % 2 == 0) i / 2 else -1
            repeat(number) { disk.add(value) }
        }

        for (i in disk.indices) {
            if (disk[i] == -1) {
                for (j in disk.size - 1 downTo i) {
                    if (disk[j] != -1) {
                        disk[i] = disk[j]
                        disk[j] = -1
                        break
                    }
                }
            }
        }
        return disk.filter { it > -1 }.map { it.toLong() }.reduceIndexed { index, acc, i -> acc + (index * i) }
    }

    override fun partTwo(): Any {
        return -1
    }
}
