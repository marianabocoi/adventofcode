package aoc2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day07Test {

    @Test
    fun part1() {
        val input = "Step C must be finished before step A can begin.\n" +
            "Step C must be finished before step F can begin.\n" +
            "Step A must be finished before step B can begin.\n" +
            "Step A must be finished before step D can begin.\n" +
            "Step B must be finished before step E can begin.\n" +
            "Step D must be finished before step E can begin.\n" +
            "Step F must be finished before step E can begin."
        assertThat(Day07.part1(input.split("\n"))).isEqualTo(1)
    }

    @Test
    fun part2() {
        val input = "Step C must be finished before step A can begin.\n" +
            "Step C must be finished before step F can begin.\n" +
            "Step A must be finished before step B can begin.\n" +
            "Step A must be finished before step D can begin.\n" +
            "Step B must be finished before step E can begin.\n" +
            "Step D must be finished before step E can begin.\n" +
            "Step F must be finished before step E can begin."
        assertThat(Day07.part2(input.split("\n"),2)).isEqualTo(15)
    }
}
