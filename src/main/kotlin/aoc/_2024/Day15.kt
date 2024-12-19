package aoc._2024

import aoc.Day

class Day15 : Day(15) {

    override fun partOne(): Any {
        val splitted = inputString.split("\n\n")
        val grid = splitted[0].split("\n").map { it.toList().toMutableList() }
        val moves = splitted[1].replace("\n", "").map(Direction.Companion::from)
        var robotPosition = Point(-1, -1)
        for (y in grid.indices) {
            val x = grid[y].indexOf('@')
            if (x >= 0) {
                robotPosition = Point(x, y)
                break
            }
        }

        moves.forEach { move ->
            val newRobotPosition = robotPosition.move(move)
            val item = grid[newRobotPosition.y][newRobotPosition.x]
            when (item) {
                '#' -> {
                    // we hit a wall -> noop
                }

                '.' -> {
                    grid[robotPosition.y][robotPosition.x] = '.'
                    grid[newRobotPosition.y][newRobotPosition.x] = '@'
                    robotPosition = newRobotPosition
                }

                'O' -> {
                    var pointAfterBox = newRobotPosition
                    var valueAfterBox: Char
                    do {
                        pointAfterBox = pointAfterBox.move(move)
                        valueAfterBox = grid[pointAfterBox.y][pointAfterBox.x]
                    } while (valueAfterBox == 'O')
                    if (valueAfterBox == '#') {
                        // we hit a wall and cant move the boxes --> noop
                    } else {
                        grid[pointAfterBox.y][pointAfterBox.x] = 'O'
                        grid[robotPosition.y][robotPosition.x] = '.'
                        grid[newRobotPosition.y][newRobotPosition.x] = '@'
                        robotPosition = newRobotPosition
                    }
                }
            }
           // println("Grid after $move")
            //printGrid(grid)
        }

        var sum = 0
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (grid[y][x] == 'O') {
                    sum += x + 100 * y
                }
            }
        }

        return sum
    }

    override fun partTwo(): Any {
        return -1
    }

    private fun printGrid(grid: List<List<Char>>) {
        grid.forEach {
            println(it.joinToString(separator = ""))
        }
    }

    data class Point(val x: Int, val y: Int) {
        fun move(direction: Direction): Point {
            return Point(x + direction.moveTo.x, y + direction.moveTo.y)
        }
    }

    enum class Direction(val moveTo: Point) {
        NORTH(Point(0, -1)),
        EAST(Point(1, 0)),
        SOUTH(Point(0, 1)),
        WEST(Point(-1, 0));

        companion object {
            fun from(char: Char): Direction {
                return when (char) {
                    '^' -> NORTH
                    '>' -> EAST
                    'v' -> SOUTH
                    '<' -> WEST
                    else -> throw IllegalArgumentException("Unknown directhion: $char")
                }
            }
        }
    }
}