object Day02 {
    private fun applyCommand(opcode: Int, firstNumber: Int, secondNumber: Int): Int =
        when (opcode) {
            1 -> firstNumber + secondNumber
            2 -> firstNumber * secondNumber
            else -> {
                throw IllegalArgumentException("The opcode $opcode is not recognised")
            }
        }

    fun processProgram(input: List<Int>, noun: Int = input[1], verb: Int = input[2]): List<Int> {
        val resultList = input.toMutableList()
        resultList[1] = noun
        resultList[2] = verb
        for (address in 0 until resultList.size step 4) {
            val opcode = resultList[address]
            if (opcode == 99) break

            val firstNumber = resultList[resultList[address + 1]]
            val secondNumber = resultList[resultList[address + 2]]
            val resultPosition = resultList[address + 3]

            val result: Int = applyCommand(opcode, firstNumber, secondNumber)

            resultList[resultPosition] = result
        }
        return resultList
    }

    fun part1(input: List<Int>) = processProgram(input, 12, 2).first()
    fun part2(input: List<Int>): Int? {
        for (noun in 0..99) {
            for (verb in 0..99) {
                val output = processProgram(input, noun, verb)
                if (output.first() == 19690720) {
                    return 100 * noun + verb
                }
            }
        }
        return null
    }
}

fun main() {
    val input = Utils.getInputAsIntList("input_day02.txt", ",")
    val part1 = Day02.part1(input)
    println("Day 1 part 1: $part1") //4484226
    val part2 = Day02.part2(input)
    println("Day 1 part 2: $part2")
}