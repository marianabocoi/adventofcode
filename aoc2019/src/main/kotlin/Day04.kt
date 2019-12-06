object Day04 {
    fun part1(rangeStart: Int, rangeEnd: Int): Int {
        val matches = (rangeStart..rangeEnd).filter { it ->
            hasDouble(it.toString()) &&
                decreasing(it.toString())
        }
        return matches.size
    }

    fun decreasing(it: String): Boolean = it.toCharArray().sorted().joinToString("") == it

    fun hasDouble(it: String): Boolean = Regex("""(.)\1""").containsMatchIn(it)

    fun hasExactDouble(it: String): Boolean = Regex("""(.)\1{2}""").containsMatchIn(it)

    fun part2(rangeStart: Int, rangeEnd: Int): Int {
        val matches = (rangeStart..rangeEnd).filter { it ->
            hasExactDouble(it.toString()) &&
                decreasing(it.toString())
        }
        return matches.size
    }
}

fun main() {

    val rangeStart = 147981
    val rangeEnd = 691423
    val part1 = Day04.part1(rangeStart, rangeEnd)
    println("Day 1 part 1: $part1")  // 1790
    val part2 = Day04.part2(rangeStart, rangeEnd)
    println("Day 1 part 2: $part2") // 984 too low
}