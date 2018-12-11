package aoc2018

import java.io.File

object Day21 {
    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 2
    }
}

fun main(args: Array<String>) {

    val inputFile = Day21::class.java.classLoader.getResource("aoc2018/day21")
    val input = File(inputFile.path).readLines()
    println("Part1: " + Day21.part1(input))
    println("Part2: " + Day21.part2(input))
}