package aoc2018

import java.io.File

object Day11 {
    private const val width = 300
    private const val height = 300
    private val cache = mutableMapOf<Long, Array<Array<Long?>>>()
    private val sumCache = mutableMapOf<Pair<Int, Long>, Array<Array<Long?>>>()

    fun part1(input: Long, gridSize: Int = 3): Cell {
        val batterySum: Array<Array<Long?>> = cachedSums(input, gridSize)

        var maxX = -1
        var maxY = -1
        var fuel = -10000L
        for (i in 0 until width - gridSize) {
            for (j in 0 until height - gridSize) {
                if (batterySum[i][j]!! > fuel) {
                    maxX = i + 1
                    maxY = j + 1
                    fuel = batterySum[i][j]!!
                }
            }
        }
        return Cell(maxX, maxY, gridSize, fuel)
    }

    fun cachedSums(
        input: Long,
        gridSize: Int
    ): Array<Array<Long?>> {
        val battery = getFuelCellValues(input)
        val batterySum: Array<Array<Long?>>
        if (gridSize == 1) {
            batterySum = getFuelCellValues(input).clone()
        } else {
            if (!sumCache.containsKey(gridSize - 1 to input)) {
                cachedSums(input, gridSize - 1)
            }
            batterySum = Array(width) { arrayOfNulls<Long>(height) }
            val previousSum = sumCache[gridSize - 1 to input]!!.clone()
            for (i in 0..width - gridSize) {
                for (j in 0..height - gridSize) {
                    val sumVerticals = IntArray(gridSize - 1) { it }
                        .map { battery[i + gridSize - 1][j + it]!! + battery[i + it][j + gridSize - 1]!! }
                        .sum()
                    batterySum[i][j] = previousSum[i][j]!! +
                        battery[i + gridSize - 1][j + gridSize - 1]!! + sumVerticals
                }
            }
        }
        sumCache[gridSize to input] = batterySum
        return batterySum.clone()
    }

    fun naiveSums(
        input: Long,
        gridSize: Int
    ): Array<Array<Long?>> {
        val battery = getFuelCellValues(input)
        val batterySum: Array<Array<Long?>> = Array(width) { arrayOfNulls<Long>(height) }
        for (i in 0..width - gridSize) {
            for (j in 0..height - gridSize) {
                batterySum[i][j] = 0
                for (k in 0 until gridSize) {
                    for (l in 0 until gridSize) {
                        batterySum[i][j] = batterySum[i][j]!! + battery[i + k][j + l]!!
                    }
                }
            }
        }
        return batterySum
    }

    private fun getFuelCellValues(
        gridSerialNumber: Long
    ): Array<Array<Long?>> {
        if (cache.containsKey(gridSerialNumber)) {
            return cache[gridSerialNumber]!!
        }

        val battery = Array(width) { arrayOfNulls<Long>(height) }
        for (i in 0 until width) {
            for (j in 0 until height) {
                battery[i][j] = getFuelLevel((i + 1).toLong(), (j + 1).toLong(), gridSerialNumber)
            }
        }
        cache[gridSerialNumber] = battery.clone()
        return battery
    }

    fun getFuelLevel(x: Long, y: Long, gridSerialNumber: Long): Long {
        if (x <= 300 && y <= 300) {
            val rackId = x + 10
            val initialPowerLevel = rackId * y
            val plusGridSerialNumber = initialPowerLevel + gridSerialNumber
            val multipliedWithRackId = plusGridSerialNumber * rackId
            if (multipliedWithRackId < 100) {
                return 0
            }
            return (multipliedWithRackId % 1000) / 100 - 5
        }
        return -1000
    }

    fun part2(input: Long): Cell {
        return IntArray(300) { it + 1 }.map { gridSize ->
            part1(
                input,
                gridSize
            )
        }.maxBy { it.value }!!
    }
}

data class Cell(val x: Int, val y: Int, val gridSize: Int, val value: Long) {
    override fun toString(): String {
        return "$x,$y,$gridSize,$value"
    }
}

fun main(args: Array<String>) {

    val inputFile = Day11::class.java.classLoader.getResource("aoc2018/day11")
    val input = File(inputFile.path).readLines()[0].toLong()
    println("Part1: " + Day11.part1(input))
    println("Part2: " + Day11.part2(input))
}