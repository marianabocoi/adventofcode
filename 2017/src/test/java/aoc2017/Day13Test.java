package aoc2017;

import aoc2017.Day13;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day13Test {
    @Test
    public void testPart1() {
        assertEquals(24, Day13.part1("0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4"));
    }

    @Test
    public void testPart2() {
        assertEquals(10, Day13.part2("0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4"));
    }
}