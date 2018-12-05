package aoc2018

import org.junit.Assert.assertEquals
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class Day05Test {
    @Test
    fun part1() {
        assertEquals(Day05.react("dabAcCaCBAcCcaDA"), "dabAaCBAcCcaDA")
//        assertEquals(Day05.part1(input.split("\n")), 240)
    }

    @Test
    fun part2() {
        assertEquals(Day05.part2("dabAcCaCBAcCcaDA"), 4)

    }
}
