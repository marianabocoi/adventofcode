package aoc2018

import java.io.File

object Day01 {
    fun sumInput(input: List<Long>): Long {
        return input.sum()
    }

    fun findRepeating(input: List<Long>): Long {
        var mm: MutableMap<Long, Boolean> = mutableMapOf<Long, Boolean>()
        var sum: Long = 0
        mm[sum] = true
        while (true) {
            input.forEach { frequency ->
                sum += frequency
                if (mm[sum] == true) {
                    return sum
                } else {
                    mm[sum] = true
                }
            }
        }
    }

}


fun main(args: Array<String>) {
    val mmma = Day01::class.java.classLoader.getResource("aoc2018/day01")
    val input = File(mmma.path).readLines().map { s -> s.toLong() }
    println("Part1: " + Day01.sumInput(input))
    println("Part2: " + Day01.findRepeating(input))
}