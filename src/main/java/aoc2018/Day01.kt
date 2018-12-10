package aoc2018

import java.io.File

object Day01 {
    fun sumInput(input: List<Long>): Long {
        return input.sum()
    }

    fun findRepeating(input: List<Long>, mm: MutableMap<Long, Boolean> = mutableMapOf<Long, Boolean>(), sum: Long = 0): Long {
        mm[sum] = true
        return findRepeating(input, mm, input.fold(sum) { accumulator, frequency ->
            val sumSoFar = accumulator + frequency
            if (mm[sumSoFar] == true) {
                return sumSoFar
            }
            mm[sumSoFar] = true
            sumSoFar
        })
    }
}


fun main(args: Array<String>) {
    val inputFile = Day01::class.java.classLoader.getResource("aoc2018/day01")
    val input = File(inputFile.path).readLines().map { s -> s.toLong() }
    println("Part1: " + Day01.sumInput(input))
    println("Part2: " + Day01.findRepeating(input))
}