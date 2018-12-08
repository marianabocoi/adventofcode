package aoc2018

import java.io.File

object Day07 {
    fun part1(input: List<String>): String {
        val nodes = mutableMapOf<String, Node>()

        for (l in input) {
            val parent = Node.fromString(l)!!
            mergeNode(nodes, parent)
        }
        val nodeParents = mutableMapOf<String, MutableSet<Node>>()
        nodes.values.forEach { node ->
            node.children.forEach { child ->
                if (nodeParents.containsKey(child.value)) {
                    nodeParents[child.value]!!.add(node)
                } else {
                    nodeParents[child.value] = mutableSetOf(node)
                }
            }
        }
        val allTopParents: Set<Node> =
            nodes.values.filterNot { nodeParents.containsKey(it.value) }.toSet()

        var tmpSet: MutableSet<Node> = mutableSetOf()
        tmpSet.addAll(allTopParents)
        val result: MutableSet<Node> = mutableSetOf()
        while (!tmpSet.isEmpty()) {
            val theNode = tmpSet.filter {
                (!nodeParents.containsKey(it.value)) || result.containsAll(nodeParents[it.value]!!)
            }
                .sortedBy { it.value }
                .first()
            result.add(theNode)
            tmpSet.remove(theNode)
            tmpSet.addAll(theNode.children)
        }
        return result.map { it.value }.joinToString("")
    }

    private fun mergeNode(nodes: MutableMap<String, Node>, parent: Node) {
        val child = parent.children.first()
        if (nodes.containsKey(child.value)) {
            parent.children = mutableSetOf(nodes[child.value]!!)
        } else {
            nodes[child.value] = child
        }
        if (nodes.containsKey(parent.value)) {
            nodes[parent.value]!!.children.add(nodes[child.value]!!)
        } else {
            nodes[parent.value] = parent
        }
    }

    fun part2(input: List<String>, workers: Int, secondsOffset: Int = 0): Int {

        val nodes = mutableMapOf<String, Node>()
        input.forEach { mergeNode(nodes, Node.fromString(it)!!) }
        nodes.values.forEach { it.secondsLeft = it.secondsLeft + secondsOffset }
        val nodeParents = nodeParents(nodes)
        val allTopParents: Set<Node> =
            nodes.values.filterNot { nodeParents.containsKey(it.value) }.toSet()

        var tmpSet: MutableSet<Node> = mutableSetOf()
        var theNodes: MutableSet<Node> = mutableSetOf()
        tmpSet.addAll(allTopParents)
        theNodes.addAll(tmpSet.take(workers))
        val result: MutableSet<Node> = mutableSetOf()
        var duration = 0

        while (!(tmpSet.isEmpty() || theNodes.isEmpty())) {

            theNodes.forEach { it.secondsLeft = it.secondsLeft - 1 }
            val finishedParts = theNodes.filter { it.secondsLeft == 0 }
            result.addAll(finishedParts.sortedBy { it.value })
            for (n in theNodes) {
                print("${n.value}(${n.secondsLeft})\t")
            }
            println()
            tmpSet.addAll(theNodes.flatMap { it.children })
            tmpSet = tmpSet.filterNot { finishedParts.contains(it) }.toMutableSet()
            theNodes = theNodes.filterNot { finishedParts.contains(it) }.toMutableSet()
            duration += 1
            val i = workers - theNodes.size
            if (!tmpSet.isEmpty()) {
                val newParts = tmpSet.filter {
                    (!nodeParents.containsKey(it.value)) || result.containsAll(nodeParents[it.value]!!)
                }
                    .sortedBy { it.value }
                    .take(i)
                theNodes.addAll(newParts)
            }
        }
        println("time it took $duration")
        return duration
    }

    private fun nodeParents(nodes: MutableMap<String, Node>): MutableMap<String, MutableSet<Node>> {
        val nodeParents = mutableMapOf<String, MutableSet<Node>>()
        nodes.values.forEach { node ->
            node.children.forEach { child ->
                if (nodeParents.containsKey(child.value)) {
                    nodeParents[child.value]!!.add(node)
                } else {
                    nodeParents[child.value] = mutableSetOf(node)
                }
            }
        }
        return nodeParents
    }
}

data class Node(
    val value: String,
    var children: MutableSet<Node> = mutableSetOf(),
    var secondsLeft: Int = value[0].minus('A').plus(1)
) {
    companion object {
        val nodeRegex = """Step (\w) must be finished before step (\w) can begin.""".toRegex()
        fun fromString(l: String): Node? {
            nodeRegex.matchEntire(l)?.groups?.let { input ->
                val details = input.map { i -> i?.value }.filterNotNull()
                if (details.isNotEmpty()) {
                    val childNode = Node(details[2])
                    val parentNode = Node(details[1], mutableSetOf(childNode))
                    return parentNode
                }
            }
            return null
        }
    }
}

fun main(args: Array<String>) {

    val imputFile = Day01::class.java.classLoader.getResource("aoc2018/day07")
    val input = File(imputFile.path).readLines()
    //println("Part1: " + Day07.part1(input))
    println("Part2: " + Day07.part2(input, 5, 60))
}