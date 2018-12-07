package aoc2018

import java.io.File

object Day08 {
    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 2
    }
}

fun main(args: Array<String>) {

    val imputFile = Day01::class.java.classLoader.getResource("aoc2018/day08")
    val input = File(imputFile.path).readLines()
    println("Part1: " + Day08.part1(input))
    println("Part2: " + Day08.part2(input))
}