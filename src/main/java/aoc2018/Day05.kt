package aoc2018

import java.io.File

object Day05 {
    fun part1(input: String): Int {
        var lastChange = ""
        var currentValue = input
        while (lastChange != currentValue) {
            lastChange = currentValue
            currentValue = react(currentValue)
        }
        return currentValue.length
    }

    fun part2(input: String): Int? {
        val letters = input.toLowerCase().toSet()
        val values = mutableMapOf<Char, Int>()
        for (l in letters) {
            val inputWithoutLetter = input.replace("[$l${switchCase(l)}]".toRegex(), "")
            values[l] = part1(inputWithoutLetter)
        }
        val winner = values.minBy { (_, count) -> count }
        return winner?.value
    }

    fun react(s: String): String {
        for (i in 0..s.length - 1) {
            val letter = s[i]
            if (i + 1 < s.length && s[i + 1] == switchCase(letter)) {
                return s.replaceFirst("$letter${s[i + 1]}", "")
            } else if (i - 1 > -1 && s[i - 1] == switchCase(letter)) {
                return s.replaceFirst("${s[i - 1]}$letter", "")
            }
        }
        return s
    }

    private fun switchCase(letter: Char): Any? {
        if (letter.isUpperCase()) {
            return letter.toLowerCase()
        } else {
            return letter.toUpperCase()
        }
    }
}

fun main(args: Array<String>) {
    val inputFile = Day01::class.java.classLoader.getResource("aoc2018/day05")
    val input = File(inputFile.path).readLines()[0]
    println("Part1: " + Day05.part1(input))
    println("Part2: " + Day05.part2(input))
}