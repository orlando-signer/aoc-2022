package aoc._2024

import aoc.Day

class Day6 : Day(6) {

    override fun partOne(): Any {
        val position = getStartingPosition()

        val visitedFields = findVisitedFields(inputList, position)
        return visitedFields.size
    }

    private fun findVisitedFields(
        inputList: List<String>,
        start: Point,
    ): Set<Point> {
        var next = start
        val visitedFields = mutableMapOf<Point, Int>()

        var direction = Direction.NORTH
        var position = start
        while (!isOutOfBound(next)) {
            if (inputList[next.y][next.x] == '#') {
                direction = direction.rotate()
            } else {
                position = next
            }
            visitedFields[position] = visitedFields.getOrDefault(position, 0) + 1
            if (visitedFields[position]!! > 4) {
                throw LazyLoopDetectionException()
            }
            next = position.move(direction)
        }
        return visitedFields.keys
    }

    private fun getStartingPosition(): Point {
        inputList.forEachIndexed { y, s ->
            val x = s.indexOf('^')
            if (x > -1) {
                return Point(x, y)
            }
        }
        throw IllegalStateException("No starting point :(")
    }

    private fun isOutOfBound(point: Point): Boolean {
        return point.x < 0 || point.x > (inputList.first().length - 1) || point.y < 0 || point.y > (inputList.size - 1)
    }

    // 1718 to high
    override fun partTwo(): Any {
        val position = getStartingPosition()
        var loopCount = 0
        // den ganzen Pfad des Guardian rausfinden
        val visitedFields = findVisitedFields(inputList, position).minus(position)
        // auf jedem Feld ein Obstacle platzieren und gucken obs explodiert
        for (visitedField in visitedFields) {
            val obstacledMap = inputList.mapIndexed { y, s ->
                if (y == visitedField.y) s.replaceRange(
                    visitedField.x,
                    visitedField.x + 1,
                    "#"
                ) else s
            }
            try {
                findVisitedFields(obstacledMap, position)
            } catch (e: LazyLoopDetectionException) {
                loopCount++
            }
        }
        return loopCount
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

    class LazyLoopDetectionException : Exception("guardian is getting tired")
}
