package aoc2017;

import aoc2017.Day6;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day6Test {
    @Test
    public void testPart1() throws Exception {
        assertEquals(5, Day6.part1("0\t2\t7\t0"));
    }

    @Test
    public void testPart2() throws Exception {
        assertEquals(4, Day6.part2("0\t2\t7\t0"));
    }
}