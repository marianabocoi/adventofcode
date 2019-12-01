package aoc2018

import java.io.File
import java.io.FileOutputStream

object Day10 {

    val guardRegex = """position=<\s*(\S*),\s*(\S*)> velocity=<\s*(\S*),\s*(\S*)>""".toRegex()
    fun parsePixels(line: String): Pixel? {
        guardRegex.matchEntire(line)?.groups?.let { input ->
            val details = input.drop(1).mapNotNull { i -> i?.value?.toInt() }
            if (details.isNotEmpty()) {
                return Pixel(details[0], details[1], details[2], details[3])
            }
        }
        return null
    }

    fun part1(input: List<String>, seconds: Int = 5): Int {
        val pixels = input.mapNotNull { parsePixels(it) }

        for (s in 0..seconds) {
            val movedPixels = pixels.map { (x, y, vx, vy) -> Pixel(x + s * vx, y + s * vy, vx, vy) }
            val maxX = movedPixels.maxBy { p -> p.x }!!.x
            val maxY = movedPixels.maxBy { p -> p.y }!!.y
            val minX = movedPixels.minBy { p -> p.x }!!.x
            val minY = movedPixels.minBy { p -> p.y }!!.y
            if (maxX - minX < 100 && maxY - minY < 100) {
                val sorted = movedPixels.groupBy { it.y }.toSortedMap()
                val line = StringBuffer()
                line.append("Text for second: $s\n")
                for (i in minY..maxY) {
                    val xValues = sorted[i]?.associate { p -> p.x to p.y } ?: emptyMap()
                    for (j in minX..maxX) {
                        if (xValues.containsKey(j)) {
                            line.append("#")
                        } else {
                            line.append(".")
                        }
                    }
                    line.append("\n")
                }
                FileOutputStream("yay.txt", true).bufferedWriter().use { writer ->
                    writer.append(line.toString())
                }
            }
        }
        return 1
    }

    fun part2(input: List<String>): Int {
        return 2
    }
}

fun main(args: Array<String>) {

    val inputFile = Day10::class.java.classLoader.getResource("aoc2018/day10")
    val input = File(inputFile.path).readLines()
    println("Part1: " + Day10.part1(input, 1000000))
    println("Part2: " + Day10.part2(input))
}

data class Pixel(val x: Int, val y: Int, val vx: Int, val vy: Int)