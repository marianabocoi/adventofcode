package aoc2018

import org.junit.Assert.assertEquals
import org.junit.Test

class Day04Test {
    @Test
    fun part1() {
        val input = "[1518-11-01 00:00] Guard #10 begins shift\n" +
            "[1518-11-01 00:05] falls asleep\n" +
            "[1518-11-01 00:25] wakes up\n" +
            "[1518-11-01 00:30] falls asleep\n" +
            "[1518-11-01 00:55] wakes up\n" +
            "[1518-11-01 23:58] Guard #99 begins shift\n" +
            "[1518-11-02 00:40] falls asleep\n" +
            "[1518-11-02 00:50] wakes up\n" +
            "[1518-11-03 00:05] Guard #10 begins shift\n" +
            "[1518-11-03 00:24] falls asleep\n" +
            "[1518-11-03 00:29] wakes up\n" +
            "[1518-11-04 00:02] Guard #99 begins shift\n" +
            "[1518-11-04 00:36] falls asleep\n" +
            "[1518-11-04 00:46] wakes up\n" +
            "[1518-11-05 00:03] Guard #99 begins shift\n" +
            "[1518-11-05 00:45] falls asleep\n" +
            "[1518-11-05 00:55] wakes up"
        assertEquals(Day04.part1(input.split("\n")), 240)
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
