package aoc2018

import org.junit.Assert.assertEquals
import org.junit.Test

class Day03Test {
    @Test
    fun part1() {
        assertEquals(Patch.fromString("#1379 @ 437,273: 20x16"), Patch(1379, 437, 273, 20, 16))
        assertEquals(
            Day03.part1(
                listOf(
                    "#1 @ 1,3: 4x4",
                    "#2 @ 3,1: 4x4",
                    "#3 @ 5,5: 2x2"
                )
            ), 4
        )
    }

    @Test
    fun part2() {
        assertEquals(
            Day03.part2(
                listOf(
                    "#1 @ 1,3: 4x4",
                    "#2 @ 3,1: 4x4",
                    "#3 @ 5,5: 2x2"
                )
            ), 3
        )
    }
}
