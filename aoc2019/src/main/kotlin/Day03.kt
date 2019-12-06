import java.lang.Math.abs

data class Point(val x: Int, val y: Int)

object Day03 {
    fun part1(firstWire: List<String>, secondWire: List<String>): Int {

        val firstWirePoints = toPoints(firstWire)
        val secondWirePoints = toPoints(secondWire)

        val intersections = mutableSetOf<Point>()
        firstWirePoints.forEach {
            if (secondWirePoints.contains(it)) intersections.add(it)
        }
        intersections.remove(Point(0, 0))

        return intersections.map { abs(it.x) + abs(it.y) }.min()!!
    }

    private fun toPoints(wire: List<String>): Set<Point> {
        var position = Point(0, 0)
        val points = mutableSetOf<Point>()
        for (direction in wire) {
            val destination = applyDirection(direction, position)
            points.addAll(pointsBetween(position, destination))
            position = destination
        }
        return points
    }

    private fun pointsBetween(a: Point, b: Point) =
        if (a.x == b.x) rangeFrom(a.y, b.y).map { Point(a.x, it) }
        else rangeFrom(a.x, b.x).map { Point(it, a.y) }

    private fun rangeFrom(a: Int, b: Int): Set<Int> =
        if (a < b) (a..b).toSet() else (b..a).toSet()

    private fun applyDirection(direction: String, position: Point): Point {
        val heading = direction.first()
        val steps = direction.drop(1).toInt()
        return when (heading) {
            'R' -> Point(position.x + steps - 1, position.y)
            'U' -> Point(position.x, position.y + steps - 1)
            'L' -> Point(position.x - steps + 1, position.y)
            'D' -> Point(position.x, position.y - steps + 1)
            else -> throw IllegalArgumentException("Heading $heading not recognised for $direction")
        }
    }

    fun part2(firstWire: List<String>, secondWire: List<String>) = 2
}

fun main() {
    val (firstWire, secondWire) = Utils.getInputAsStringList("input_day03.txt", ",")

    val part1 = Day03.part1(firstWire, secondWire)
    println("Day 1 part 1: $part1") //367 low -- 766 low off by 2 O.o 
    val part2 = Day03.part2(firstWire, secondWire)
    println("Day 1 part 2: $part2")
}