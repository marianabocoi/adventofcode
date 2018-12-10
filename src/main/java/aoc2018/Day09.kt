package aoc2018

import java.io.File

object Day09 {
    fun part1(players: Int, points: Int): Int {
        val circle = mutableListOf<Int>(0, 1)
        val scores = IntArray(players) { 0 }
        var currentPosition = 1
        //   printCircle(circle, currentPosition, 2, players)
        for (i in 2..points) {
            if (i % 23 == 0) {
                currentPosition = (currentPosition - 7) % circle.size
                if (currentPosition < 0) currentPosition += circle.size
                scores[i % players] += i + circle.removeAt(currentPosition)
            } else {
                currentPosition = (currentPosition + 2) % (circle.size)
                circle.add(currentPosition, i)
            }
            //         printCircle(circle, currentPosition, i, players)
        }
        scores.forEachIndexed { i, s -> println("[$i] $s") }
        var player = points % players - 2
        if (player < 0) player += players
        println("Current player: $player")
        return scores.max()!!
    }

    private fun printCircle(
        circle: MutableList<Int>,
        current: Int,
        index: Int,
        players: Int
    ) {
        val id = index % players + 1
        print("[$id]\t")
        for (i in 0 until circle.size) {
            if (i == current) {
                print("(${circle[i]})\t")
            } else {
                print("${circle[i]}\t")
            }
        }
        println()
    }

    fun part2(players: Int, points: Long): Long {
        var root = Node09(0)
        root.left = root
        root.right = root

        val scores = LongArray(players) { 0L }
        for (i in 1L..points) {
            if (i % 23 == 0L) {
                val oldNode = root.removeSpecial()
                scores[(i % players).toInt()] += i + oldNode.value
                root = oldNode.right!!
            } else {
                root = root.add(i)
            }
        }
        scores.forEachIndexed { i, s -> println("[$i] $s") }
        var player = points % players - 2
        if (player < 0) player += players
        println("Current player: $player")
        return scores.max()!!
    }
}

fun main(args: Array<String>) {

    val inputFile = Day01::class.java.classLoader.getResource("aoc2018/day09")
    val input = File(inputFile.path).readLines()
    println("Part1: " + Day09.part1(403, 71920))
    println("Part2: " + Day09.part2(403, 7192000))// not 439089
}

data class Node09(val value: Long, var left: Node09? = null, var right: Node09? = null) {
    fun add(v: Long): Node09 {
        val newNode = Node09(v)
        newNode.left = this.right
        newNode.right = this.right!!.right
        this.right!!.right!!.left = newNode
        this.right!!.right = newNode
        return newNode
    }

    fun removeSpecial(): Node09 {
        val replaceMe = this.left!!.left!!.left!!.left!!.left!!.left!!.left!!
        replaceMe.left!!.right = replaceMe.right
        replaceMe.right!!.left = replaceMe.left
        return replaceMe
    }
}