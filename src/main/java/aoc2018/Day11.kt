package aoc2018

import java.io.File

object Day11 {
    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 2
    }
}

fun main(args: Array<String>) {

    val inputFile = Day01::class.java.classLoader.getResource("aoc2018/day11")
    val input = File(inputFile.path).readLines()
    println("Part1: " + Day11.part1(input))
    println("Part2: " + Day11.part2(input))
}