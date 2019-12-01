package aoc2018

import org.junit.Assert.assertEquals
import org.junit.Test

class Day01Test {
    @Test
    fun part1() {
        assertEquals(Day01.sumInput(listOf(+1, +1, +1)), 3)
        assertEquals(Day01.sumInput(listOf(+1, +1, -2)), 0)
        assertEquals(Day01.sumInput(listOf(-1, -2, -3)), -6)
    }

    @Test
    fun part2() {
        assertEquals(Day01.findRepeating(listOf(+1, -1)), 0)
        assertEquals(Day01.findRepeating(listOf(+3, +3, +4, -2, -4)), 10)
        assertEquals(Day01.findRepeating(listOf(-6, +3, +8, +5, -6)), 5)
        assertEquals(Day01.findRepeating(listOf(+7, +7, -2, -7, -4)), 14)
    }
}
