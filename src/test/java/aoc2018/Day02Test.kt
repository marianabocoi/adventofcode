package aoc2018

import org.junit.Assert.assertEquals
import org.junit.Test

class Day02Test {
    @Test
    fun part1() {
        assertEquals(Day02.stringCount("abcdef"), mutableMapOf<Int, Int>())
        assertEquals(Day02.stringCount("bababc"), mutableMapOf(2 to 1, 3 to 1))
        assertEquals(Day02.stringCount("abbcde"), mutableMapOf(2 to 1))
        assertEquals(Day02.stringCount("abcccd"), mutableMapOf(3 to 1))
        assertEquals(Day02.stringCount("aabcdd"), mutableMapOf(2 to 1))
        assertEquals(Day02.stringCount("abcdee"), mutableMapOf(2 to 1))
        assertEquals(Day02.stringCount("ababab"), mutableMapOf(3 to 1))
        assertEquals(
            Day02.countStuff(
                listOf(
                    "ababab",
                    "abcdee",
                    "aabcdd",
                    "abcccd",
                    "abbcde",
                    "bababc",
                    "abcdef"
                )
            ), 12
        )
    }

    @Test
    fun part2() {
        assertEquals(
            Day02.part2(
                listOf(
                    "abcde",
                    "fghij",
                    "klmno",
                    "pqrst",
                    "fguij",
                    "axcye",
                    "wvxyz"
                )
            ), "fgij"
        )
    }
}
