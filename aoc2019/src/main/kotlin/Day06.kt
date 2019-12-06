object Day06 {
    private const val rootName = "COM"
    fun part1(input: List<String>): Int {
        val grouping = input.map { it.split(")") }.groupBy { it[0] }
        val root = Node(rootName)
        constructTree(root, grouping)
        return sumOfDepths(root)
    }

    private fun sumOfDepths(root: Node): Int {
        return root.depth + root.getChildren().map { sumOfDepths(it) }.sum()
    }

    private fun constructTree(
        root: Node,
        grouping: Map<String, List<List<String>>>
    ) {
        val children = grouping[root.name]?.map { Node(it[1], root.depth + 1) }
        root.addAll(children)
        for (child in root.getChildren()) {
            constructTree(child, grouping)
        }
    }

    fun part2(rangeEnd: Int): Int = 2


    class Node(val name: String, val depth: Int = 0) {
        private val children: MutableList<Node> = mutableListOf()
        fun addAll(nodes: List<Node>?) = nodes?.let { children.addAll(nodes) }
        fun getChildren() = children.toList()
    }
}

fun main() {
    val input = Utils.getInputAsList("input_day06.txt")
    val part1 = Day06.part1(input)
    println("Day 1 part 1: $part1")
    val part2 = Day06.part2(42)
    println("Day 1 part 2: $part2")
}

