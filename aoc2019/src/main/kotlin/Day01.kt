import kotlin.math.floor

object Day01 {
    fun calculateFuel(mass: Double) = (floor(mass.toDouble() / 3) - 2)

    fun calculateFuelRecursively(mass: Double): Double {
        val fuel = calculateFuel(mass)
        return if (fuel > 0) fuel + calculateFuelRecursively(fuel) else 0.0
    }

    fun part1(input: List<Double>) = input.map { calculateFuel(it) }.sum()

    fun part2(input: List<Double>) = input.map { calculateFuelRecursively(it) }.sum()
}

fun main() {
    val input = Utils.getInputAsDoubleList("input_day01.txt")
    val part1 = Day01.part1(input)
    println("Day 1 part 1: $part1")
    val part2 = Day01.part2(input)
    println("Day 1 part 2: $part2")
}
