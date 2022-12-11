package aoc._2022

import aoc.Day
import java.util.*

class Day7 : Day(7) {

    override fun partOne(): Any {
        val root = buildTree()
        return root.find { it is Folder && it.size() <= 100000 }.sumOf { it.size() }
    }

    override fun partTwo(): Any {
        val root = buildTree()
        val minSize = 30000000 - (70000000 - root.size())
        return root.find { it is Folder && it.size() >= minSize }.minBy { it.size() }.size()
    }

    private fun buildTree(): Node {
        val root = Folder("/", null)
        var currentNode = root
        inputList.drop(1).forEach {
            when {
                it.startsWith("dir") -> currentNode.addItem(Folder(it.split(" ")[1], currentNode))
                it.contains("^\\d".toRegex()) -> currentNode.addItem(
                    File(
                        it.split(" ")[1],
                        it.split(" ")[0].toInt(),
                        currentNode
                    )
                )

                it.startsWith("$ cd ..") -> currentNode = currentNode.parent as Folder
                it.startsWith("$ cd") -> currentNode = currentNode.getFolder(it.split(" ")[2])

            }
        }
        return root
    }

    abstract class Node(val name: String, val parent: Node?) {
        abstract fun size(): Int

        abstract fun find(matcher: (node: Node) -> Boolean): List<Node>
    }

    class Folder(name: String, parent: Node?) : Node(name, parent) {
        private val items = mutableListOf<Node>()

        override fun size(): Int {
            return items.sumOf { it.size() }
        }

        fun addItem(item: Node) {
            items.add(item)
        }

        fun getFolder(name: String): Folder {
            return items.first { name == it.name && it is Folder } as Folder
        }

        override fun find(matcher: (node: Node) -> Boolean): List<Node> {
            val start = if (matcher(this)) mutableListOf(this) else mutableListOf()
            return start + items.flatMap { it.find(matcher) }
        }
    }

    class File(name: String, private val size: Int, parent: Node) : Node(name, parent) {
        override fun size(): Int {
            return size
        }

        override fun find(matcher: (node: Node) -> Boolean): List<Node> {
            return if (matcher(this)) mutableListOf(this) else mutableListOf()
        }

    }
}
