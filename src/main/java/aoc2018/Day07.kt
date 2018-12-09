package aoc2018

import java.io.File

object Day07 {
    fun part1(input: List<String>): String {
        val dependencies = input.mapNotNull { parseLine(it) }
        val children = dependencies.groupBy { it.first }
            .mapValues { (k, v) -> v.map { it.second } }
        val parents = dependencies.groupBy { it.second }
            .mapValues { (k, v) -> v.map { it.first } }

        val result = mutableListOf<String>()
        val queue = children.keys.filterNot { parents.containsKey(it) }.toMutableList()

        while (!queue.isEmpty()) {
            val element = queue.sorted().first()
            result.add(element)
            queue.remove(element)
            val newOnes = children[element]?.filter {
                !parents.keys.contains(it) || result.containsAll(parents[it]!!)
            }
            newOnes?.let { queue.addAll(it) }
            println(result)
        }
        return result.joinToString("")
    }

    fun part2(input: List<String>, workers: Int, secondsOffset: Int = 0): Int {
        val dependencies = input.mapNotNull { parseLine(it) }

        val children = dependencies.groupBy { it.first }
            .mapValues { (k, v) -> v.map { it.second } }
        val parents = dependencies.groupBy { it.second }
            .mapValues { (k, v) -> v.map { it.first } }

        val allValues = (children.keys + parents.keys).toSet()

        // TODO add letter offset
        val progress = allValues
            .associate { k -> k to (secondsOffset + k[0].minus('A').plus(1)) }.toMutableMap()

        val result = mutableSetOf<String>()
        val queue = allValues.filterNot { parents.containsKey(it) }.toMutableSet()
        val inProgress = queue.take(workers).toMutableSet()
        queue.removeAll(inProgress)
        var count = 0
        while (result.size != allValues.size) {
            inProgress.map { progress[it] = progress[it]!! - 1 }
            val finished = inProgress.filter { progress[it] == 0 }
            result.addAll(finished)
            inProgress.removeAll(finished)

            val newOnes = finished.mapNotNull { element -> children[element] }
                .flatten()
                .filter { !parents.keys.contains(it) || result.containsAll(parents[it]!!) }
            queue.addAll(newOnes)
            inProgress.addAll(queue.sorted().take(workers - inProgress.size))
            queue.removeAll(inProgress)
            println(result)
            count++
        }
        return count
    }

    private fun parseLine(line: String): Pair<String, String>? {
        val nodeRegex = """Step (\w) must be finished before step (\w) can begin.""".toRegex()
        nodeRegex.matchEntire(line)?.groups?.let { input ->
            val details = input.mapNotNull { i -> i?.value }
            if (details.isNotEmpty()) {
                return details[1] to details[2]
            }
        }
        return null
    }
}

fun main(args: Array<String>) {

    val imputFile = Day01::class.java.classLoader.getResource("aoc2018/day07")
    val input = File(imputFile.path).readLines()
    println("Part1: " + Day07.part1(input))
    println("Part2: " + Day07.part2(input, 5, 60))
}