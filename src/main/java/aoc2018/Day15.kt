package aoc2018

import java.io.File

object Day15 {
    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 2
    }
}

fun main(args: Array<String>) {

    val inputFile = Day15::class.java.classLoader.getResource("aoc2018/day15")
    val input = File(inputFile.path).readLines()
    println("Part1: " + Day15.part1(input))
    println("Part2: " + Day15.part2(input))
}