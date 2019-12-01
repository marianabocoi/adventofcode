package aoc2018

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day09Test {

    @Test
    fun part101() {
        assertThat(Day09.part1(9, 25)).isEqualTo(32)
    }

    @Test
    fun part102() {
        assertThat(Day09.part1(10, 1618)).isEqualTo(8317)
    }

    @Test
    fun part103() {
        assertThat(Day09.part1(13, 7999)).isEqualTo(146373)
    }

    @Test
    fun part104() {
        assertThat(Day09.part1(17, 1104)).isEqualTo(2764)
    }

    @Test
    fun part105() {
        assertThat(Day09.part1(21, 6111)).isEqualTo(54718)
    }

    @Test
    fun part106() {
        assertThat(Day09.part1(30, 5807)).isEqualTo(37305)
    }


    @Test
    fun part201() {
        assertThat(Day09.part2(9, 25)).isEqualTo(32)
    }

    @Test
    fun part202() {
        assertThat(Day09.part2(10, 1618)).isEqualTo(8317)
    }

    @Test
    fun part203() {
        assertThat(Day09.part2(13, 7999)).isEqualTo(146373)
    }

    @Test
    fun part204() {
        assertThat(Day09.part2(17, 1104)).isEqualTo(2764)
    }

    @Test
    fun part205() {
        assertThat(Day09.part2(21, 6111)).isEqualTo(54718)
    }

    @Test
    fun part206() {
        assertThat(Day09.part2(30, 5807)).isEqualTo(37305)
    }
}
