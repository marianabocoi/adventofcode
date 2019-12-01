package aoc2018

import java.io.File

object Day08 {
    fun part1(input: List<Int>): Int {
        return getMetadataSum(input).second
    }

    private fun getMetadataSum(input: List<Int>, index: Int = 0): Pair<Int, Int> {

        val childrenNumber = input[index]
        val metadataNumber = input[index + 1]
        var offset = index + 2
        var sum = 0
        for (i in 0 until childrenNumber) {
            val (childOffset, partialSum) = getMetadataSum(input, offset)
            offset = childOffset
            sum += partialSum
        }
        for (i in offset until offset + metadataNumber)
            sum += input[i]
        offset += metadataNumber
        return (offset to sum)
    }

    fun part2(input: List<Int>): Int {
        return getChildrenSum(input).second
    }

    private fun getChildrenSum(input: List<Int>, index: Int = 0): Pair<Int, Int> {

        val childrenNumber = input[index]
        val metadataNumber = input[index + 1]
        var offset = index + 2
        var sum = 0
        val childrenValues = arrayOfNulls<Int>(childrenNumber)
        for (i in 0 until childrenNumber) {
            val (childOffset, partialSum) = getChildrenSum(input, offset)
            offset = childOffset
            childrenValues[i] = partialSum
        }
        if (childrenNumber == 0) {
            for (i in offset until offset + metadataNumber)
                sum += input[i]
        } else {
            val meradata = IntArray(metadataNumber) { input[it + offset] - 1 }
            val childSum = meradata.filter { it < childrenValues.size }
                .map { childrenValues[it] }.filterNotNull().sum()
            sum = childSum
        }
        offset += metadataNumber
        return (offset to sum)
    }
}

/*
0 1 2 3 4  5  6  7 8 9 0  1 2 3 4 5
2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2
A----------------------------------
    B----------- C-----------
                     D-----
 */

fun main(args: Array<String>) {

    val inputFile = Day08::class.java.classLoader.getResource("aoc2018/day08")
    val input = File(inputFile.path).readLines()[0].split("""\s""".toRegex()).map { it.toInt() }
    println("Part1: " + Day08.part1(input))
    println("Part2: " + Day08.part2(input))
}

data class Node08(val id: Int, val children: List<Node08>, val metadata: List<Int>)