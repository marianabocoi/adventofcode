package aoc2018

import java.awt.Point
import java.awt.Rectangle
import java.io.File

object Day03 {
    fun part1(input: List<String>): Int {
        val patches = input.map { s -> Patch.fromString(s) }
        var fabric: MutableMap<Point, Boolean> = mutableMapOf()
        var count = 0
        patches.forEach { patch ->
            val m: MutableMap<Point, Int> = patch.getInches()

            m.forEach { patchInch ->
                if (fabric.containsKey(patchInch.key)) {
                    if (fabric[patchInch.key] == false) {
                        count++
                        fabric[patchInch.key] = true
                    }
                } else {
                    fabric[patchInch.key] = false
                }
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val patches = input.map { s -> Patch.fromString(s) }
        patches.forEach { patch ->
            if (patches.filterNot { p -> p == patch }.map { p -> patch.overlaps(p) }.none { x -> x }) {
                return patch.id
            }
        }
        return -1
    }
}

fun main(args: Array<String>) {

    val inputFile = Day03::class.java.classLoader.getResource("aoc2018/day03")
    val input = File(inputFile.path).readLines()
    println("Part1: " + Day03.part1(input))
    println("Part2: " + Day03.part2(input))
}

data class Patch(val id: Int, val x: Int, val y: Int, val width: Int, val height: Int) {
    fun getInches(): MutableMap<Point, Int> {
        val patchInches: MutableMap<Point, Int> = mutableMapOf()
        val endInchesX = x + width - 1
        for (i in x..endInchesX) {
            val endInchesY = y + height - 1
            for (j in y..endInchesY) {
                patchInches[Point(i, j)] = id
            }
        }
        return patchInches
    }

    private fun toRectangle() = Rectangle(x, y, width, height)

    fun overlaps(patch: Patch): Boolean = toRectangle().intersects(patch.toRectangle())

    companion object {
        fun fromString(s: String): Patch {
            val patchInput = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()
            patchInput.matchEntire(s)?.groups?.let { input ->
                val details = input.filterNot { x -> x?.value == s }
                    .map { x -> x?.value?.toInt() }
                    .filterNotNull()
                return Patch(details[0], details[1], details[2], details[3], details[4])
            }
            throw IllegalArgumentException("Huh?: $s")
        }
    }
}