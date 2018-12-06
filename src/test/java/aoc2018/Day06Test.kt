package aoc2018

import org.junit.Assert.assertEquals
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class Day06Test {
    @Test
    fun part1() {
        val input = "1, 1\n" +
            "1, 6\n" +
            "8, 3\n" +
            "3, 4\n" +
            "5, 5\n" +
            "8, 9"
        val points = input.split("\n").mapIndexed { i, p -> Point.fromString(i, p) }
        assertEquals(
            Day06.part1(points, 1),
            17
        )
    }

    @Test
    fun part2() {
        val input = "1, 1\n" +
            "1, 6\n" +
            "8, 3\n" +
            "3, 4\n" +
            "5, 5\n" +
            "8, 9"
        val points = input.split("\n").mapIndexed { i, p -> Point.fromString(i, p) }
        assertEquals(
            Day06.part2(points, 1, 32),
            16
        )
    }
}
