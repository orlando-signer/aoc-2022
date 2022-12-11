package aoc._2022

import aoc.Day

class Day8 : Day(8) {

    override fun partOne(): Any {
        val forest = inputList.map { it -> it.map { it.digitToInt() }.toList() }
        val visibleTrees = List(forest.size) { MutableList(forest[0].size) { false } }

        updateVisibility(forest.indices, forest[0].indices, forest, visibleTrees)
        updateVisibility(forest.indices.reversed(), forest[0].indices.reversed(), forest, visibleTrees)

        return visibleTrees.sumOf { it -> it.count { it } }
    }

    private fun updateVisibility(
        xIndices: IntProgression,
        yIndices: IntProgression,
        forrest: List<List<Int>>,
        visibleTrees: List<MutableList<Boolean>>
    ) {
        for (x in xIndices) {
            var currentMax = -1
            for (y in yIndices) {
                val tree = forrest[x][y]
                if (tree > currentMax) {
                    currentMax = tree
                    visibleTrees[x][y] = true
                }
            }
        }
        for (y in yIndices) {
            var currentMax = -1
            for (x in xIndices) {

                val tree = forrest[x][y]
                if (tree > currentMax) {
                    currentMax = tree
                    visibleTrees[x][y] = true
                }
            }
        }
    }

    private fun prettyPrintMatrix(matrix: List<List<Any>>) {
        println()
        matrix.forEach { println(it) }
    }

    override fun partTwo(): Any {
        val forest = inputList.map { it -> it.map { it.digitToInt() }.toList() }
        var maxScenicScore = 0
        forest.forEachIndexed { x, line ->
            line.forEachIndexed { y, tree ->
                run {
                    var scenicScore = viewDistance(tree, line.subList(0, y).reversed())
                    scenicScore *= viewDistance(tree, line.subList(y + 1, line.size))
                    scenicScore *= viewDistance(tree, forest.subList(0, x).map { it[y] }.reversed())
                    scenicScore *= viewDistance(tree, forest.subList(x + 1, forest.size).map { it[y] })

                    maxScenicScore = if (scenicScore > maxScenicScore) scenicScore else maxScenicScore

                }
            }
        }
        return maxScenicScore
    }

    fun viewDistance(tree: Int, line: List<Int>): Int {
        var distance = 0
        for (treeDistance in line) {
            distance++
            if (treeDistance >= tree) {
                break
            }
        }
        return distance
    }
}
