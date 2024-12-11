package aoc._2024

import aoc.Day

fun main() {
    println(Day9().partTwo())
}

class Day9 : Day(9) {

    override fun partOne(): Any {
        val disk = mutableListOf<Int>()
        for (i in inputString.indices) {
            val number = inputString[i].digitToInt()
            val value = if (i % 2 == 0) i / 2 else -1
            repeat(number) { disk.add(value) }
        }

        var lastOccupiedSpaced = disk.size - 1
        for (i in disk.indices) {
            if (disk[i] == -1) {
                for (j in lastOccupiedSpaced downTo i) {
                    if (disk[j] != -1) {
                        lastOccupiedSpaced = j
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
        val disk = mutableListOf<File>()
        for (i in inputString.indices) {
            val number = inputString[i].digitToInt()
            if (number > 0) {
                val value = if (i % 2 == 0) i / 2 else -1
                disk.add(File(value, number))
            }
        }
        val filesToMove = disk.filter { !it.isEmpty() }.reversed()
        for (fileToMove in filesToMove) {
            val fileToMoveIndex = disk.indexOf(fileToMove)
            for (index in disk.indices) {
                val file = disk[index]
                if (file.isEmpty() && index < fileToMoveIndex) {
                    if (file.length == fileToMove.length) {
                        disk[fileToMoveIndex] = File(-1, fileToMove.length)
                        disk[index] = fileToMove
                        break
                    } else if (file.length > fileToMove.length) {
                        disk[fileToMoveIndex] = File(-1, fileToMove.length)
                        disk[index] = fileToMove
                        disk.add(index + 1, File(-1, file.length - fileToMove.length))
                        break
                    }
                }
            }
            //println(disk.joinToString("") { it.toString() })
        }
        var result = 0L
        var index = 0L
        for (file in disk) {
            if (file.isEmpty()) {
                index += file.length
            } else {
                for (i in 0 until file.length) {
                    result += (index * file.value)
                    index++
                }
            }

        }
        return result
    }

    data class File(val value: Int, val length: Int) {
        fun isEmpty() = value == -1

        override fun toString() = if (value == -1) ".".repeat(length) else value.toString().repeat(length)
    }
}
