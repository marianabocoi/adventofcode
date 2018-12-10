package aoc2018

import java.io.File

object Day06 {
    fun part1(input: List<Point>, padding: Int): Int {
        val leftMost = input.maxBy { -1 * it.x }!!
        val rightMost = input.maxBy { it.x }!!
        val upMost = input.maxBy { -1 * it.y }!!
        val downMost = input.maxBy { it.y }!!

//        val padding = 100
        val width = rightMost.x + 2 * padding
        val height = downMost.y + 2 * padding
        val xOffset = -1 * leftMost.x + padding
        val yOffset = -1 * upMost.y + padding
        val points = input.mapIndexed { i, p -> Point(i, p.x, p.y, xOffset, yOffset) }
        val f = Array(width) { arrayOfNulls<Int>(height) }
        for (i in 0 until width) {
            for (j in 0 until height) {
                val distances = points.map { p -> p.id to p.omDistance(i, j) }
                val max = distances.minBy { (_, count) -> count }!!
                if (distances.filter { it.second == max.second }.size == 1) {
                    f[i][j] = max.first
                } else {
                    f[i][j] = -1
                }
            }
        }
        val topEdge = f[0].toSet()
        val downEdge = f[width - 1].toSet()
        val leftEdge = f.map { x -> x[0] }.toSet()
        val rightEdge = f.map { x -> x[height - 1] }.toSet()
        val allEdges = topEdge.union(downEdge).union(leftEdge).union(rightEdge)

        val pointsNotOnEdge = points.filterNot { p -> allEdges.contains(p.id) }

        val winner = pointsNotOnEdge.maxBy { p -> f.sumBy { h -> h.count { i -> p.id == i } } }!!

        println("Edges:")
        allEdges.forEach { print(idRep(it!!)) }
        println()
//        println("Representation:")
//        for (i in 0 until width) {
//            for (j in 0 until height) {
//                print(idRep(f[i][j]!!))
//            }
//            println()
//        }
        println("Winner:")
        println(idRep(winner.id))

        val count = f.sumBy { h -> h.count { i -> winner.id == i } }
        println("Count: $count")
        return count
    }

    private fun idRep(i: Int): Char {
        if (i == -1) return '.'
        return (i + 65).toChar()
    }

    fun part2(input: List<Point>, padding: Int, safeZone: Int): Int? {
        val leftMost = input.maxBy { -1 * it.x }!!
        val rightMost = input.maxBy { it.x }!!
        val upMost = input.maxBy { -1 * it.y }!!
        val downMost = input.maxBy { it.y }!!

        val width = rightMost.x + 2 * padding
        val height = downMost.y + 2 * padding
        val xOffset = -1 * leftMost.x + padding
        val yOffset = -1 * upMost.y + padding
        val points = input.mapIndexed { i, p -> Point(i, p.x, p.y, xOffset, yOffset) }
//        val f = Array(width) { arrayOfNulls<Boolean>(height) }
        var safeCount = 0
        for (i in 0 until width) {
            for (j in 0 until height) {
                val distances = points.map { p -> p.omDistance(i, j) }.sum()
//                f[i][j] = distances < safeZone
                if (distances < safeZone) {
                    safeCount++
                }
            }
        }
//        println("Representation:")
//        for (i in 0 until width) {
//            for (j in 0 until height) {
//                print(safeRep(f[i][j]!!))
//            }
//            println()
//        }
        println("Count:")
        println(safeCount)
        return safeCount
    }

    private fun safeRep(b: Boolean): Any? {
        if (b) {
            return '#'
        }
        return '.'
    }
}

fun main(args: Array<String>) {
    val inputFile = Day01::class.java.classLoader.getResource("aoc2018/day06")
    val input = File(inputFile.path).readLines().mapIndexed { i, p -> Point.fromString(i, p) }
    println("Part1: " + Day06.part1(input, 100))
    println("Part2: " + Day06.part2(input, 10001, 10000))
}

data class Point(
    val id: Int,
    val x: Int,
    val y: Int,
    val xOffset: Int = 0,
    val yOffset: Int = 0
) {
    fun mDistance(p: Point) = Math.abs(this.x - p.x) + Math.abs(this.y - p.y)
    fun omDistance(x: Int, y: Int) = Math.abs(this.ox - x) + Math.abs(this.oy - y)
    val ox = x + xOffset
    val oy = y + yOffset

    companion object {
        fun fromString(i: Int, s: String): Point {
            val coords = s.trim().split(", ").map { n -> n.toInt() }
            return Point(i, coords[0], coords[1])
        }
    }
}