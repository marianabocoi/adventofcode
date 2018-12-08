package aoc2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day08Test {

    @Test
    fun part1() {
        val input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2".split(" ").map { it.toInt() }
        assertThat(Day08.part1(input)).isEqualTo(138)
    }

    @Test
    fun part2() {
        val input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2".split(" ").map { it.toInt() }
        assertThat(Day08.part2(input)).isEqualTo(2)
    }
}
