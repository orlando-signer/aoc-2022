package aoc._2024

import aoc.Day

class Day6 : Day(6) {

    override fun partOne(): Any {
        var position = Point(-1, -1)
        var direction = Direction.NORTH
        val visitedFields = mutableSetOf<Point>()
        inputList.forEachIndexed { y, s ->
            val x = s.indexOf('^')
            if (x > -1) {
                position = Point(x, y)
            }
        }
        visitedFields += position

        var next = position

        while (!isOutOfBound(next)) {
            if (inputList[next.y][next.x] == '#') {
                direction = direction.rotate()
            } else {
                position = next
            }
            visitedFields += position
            next = position.move(direction)
        }
        return visitedFields.size
    }

    private fun isOutOfBound(point: Point): Boolean {
        return point.x < 0 || point.x > (inputList.first().length - 1) || point.y < 0 || point.y > (inputList.size - 1)
    }

    override fun partTwo(): Any {
        return -1
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

        fun rotate(): Direction {
            return entries[(ordinal + 1) % 4]
        }
    }
}
