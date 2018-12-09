package aoc2018

import java.io.File

object Day22 {
    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 2
    }
}

fun main(args: Array<String>) {

    val imputFile = Day01::class.java.classLoader.getResource("aoc2018/day22")
    val input = File(imputFile.path).readLines()
    println("Part1: " + Day22.part1(input))
    println("Part2: " + Day22.part2(input))
}