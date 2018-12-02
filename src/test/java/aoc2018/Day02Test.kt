package aoc2018

import org.junit.Assert.*
import org.junit.Test

class Day02Test {
    @Test
    fun part1() {
        assertEquals(Day02.stringCount("abcdef"), mutableMapOf<Int, Int>())
        assertEquals(Day02.stringCount("bababc"), mutableMapOf<Int, Int>(2 to 1, 3 to 1))
        assertEquals(Day02.stringCount("abbcde"), mutableMapOf<Int, Int>(2 to 1))
        assertEquals(Day02.stringCount("abcccd"), mutableMapOf<Int, Int>(3 to 1))
        assertEquals(Day02.stringCount("aabcdd"), mutableMapOf<Int, Int>(2 to 1))
        assertEquals(Day02.stringCount("abcdee"), mutableMapOf<Int, Int>(2 to 1))
        assertEquals(Day02.stringCount("ababab"), mutableMapOf<Int, Int>(3 to 1))
        assertEquals(Day02.countStuff(listOf<String>("ababab", "abcdee", "aabcdd", "abcccd", "abbcde", "bababc", "abcdef")), 12)
    }

    @Test
    fun part2() {
        assertEquals(Day02.part2(listOf<String>("abcde",
                "fghij",
                "klmno",
                "pqrst",
                "fguij",
                "axcye",
                "wvxyz")), "fgij")
    }
}
