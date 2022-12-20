package aoc._2022

import aoc.Day
import kotlinx.serialization.json.*
import java.util.LinkedList
import java.util.stream.IntStream
import kotlin.math.max
import kotlin.math.min

class Day20 : Day(20) {

    override fun partOne(): Any {
        val initalList = inputList.map { it.toInt() }.zip(IntRange(0, inputList.size))
        val mutableList = initalList.toMutableList()
        initalList.forEach { value ->
            run {
                val oldIdx = mutableList.indexOf(value)
                var newIdx = (oldIdx + value.first) % (initalList.size - 1)
                newIdx = if (newIdx <= 0) initalList.size + newIdx - 1 else newIdx
                mutableList.removeAt(oldIdx)
                mutableList.add(newIdx, value)
            }
        }
        val zeroIdx = mutableList.indexOfFirst { it.first == 0 }
        return listOf(1000, 2000, 3000).sumOf {
            mutableList[(zeroIdx + it) %
                    (initalList.size)].first
        }
    }

    override fun partTwo(): Any {
        val initalList = inputList.map { it.toLong() }.map { it * 811589153 }.zip(IntRange(0, inputList.size))
        val mutableList = initalList.toMutableList()
        repeat(10) {
            initalList.forEach { value ->
                run {
                    val oldIdx = mutableList.indexOf(value)
                    var newIdx = (oldIdx + value.first) % (initalList.size - 1)
                    newIdx = if (newIdx <= 0) initalList.size + newIdx - 1 else newIdx
                    mutableList.removeAt(oldIdx)
                    mutableList.add(newIdx.toInt(), value)
                }
            }
        }
        val zeroIdx = mutableList.indexOfFirst { it.first == 0L }
        return listOf(1000, 2000, 3000).sumOf {
            mutableList[(zeroIdx + it) %
                    (initalList.size)].first
        }
    }

}
