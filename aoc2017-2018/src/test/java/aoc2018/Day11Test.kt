package aoc2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day11Test {

    @Test
    fun part1() {
        assertThat(Day11.getFuelLevel(3, 5, 8)).isEqualTo(4)
        assertThat(Day11.getFuelLevel(122, 79, 57)).isEqualTo(-5)
        assertThat(Day11.getFuelLevel(217, 196, 39)).isEqualTo(0)
        assertThat(Day11.getFuelLevel(101, 153, 71)).isEqualTo(4)

        assertThat(Day11.part1(18L).toString()).isEqualTo("33,45,3,29")
        assertThat(Day11.part1(42).toString()).isEqualTo("21,61,3,30")
    }

    @Test
    fun part2() {
        assertThat(Day11.part2(18L).toString()).isEqualTo("90,269,16,113")
        assertThat(Day11.part2(42L).toString()).isEqualTo("232,251,12,119")
    }

    @Test
    fun cacheTest() {
        var cached = Day11.cachedSums(18L, 1)
        var notCached = Day11.naiveSums(18L, 1)
        println("pass 1")
        printSelection(cached)
        printSelection(notCached)
        assertThat(cached).isEqualTo(Day11.naiveSums(18L, 1))

        cached = Day11.cachedSums(18L, 2)
        notCached = Day11.naiveSums(18L, 2)
        println("pass 2")
        printSelection(cached)
        printSelection(notCached)
        assertThat(cached).isEqualTo(notCached)

        cached = Day11.cachedSums(18L, 3)
        notCached = Day11.naiveSums(18L, 3)
        println("pass 2")
        printSelection(cached)
        printSelection(notCached)
        assertThat(cached).isEqualTo(notCached)
    }

    private fun printSelection(cached: Array<Array<Long?>>) {
        for (i in 0 until 10) {
            for (j in 0 until 10) {
                print(cached[i][j]!!.toString() + "\t\t")
            }
            println()
        }
        println()
    }

}
