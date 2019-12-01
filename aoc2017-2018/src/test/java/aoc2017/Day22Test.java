package aoc2017;

import aoc2017.Day22;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Day22Test {
    @Test
    public void testPart1() {
        assertEquals(0, Day22.part1(("").split("\n")));
    }

    @Test
    public void testPart2() throws IOException, InterruptedException {
        assertEquals(1, Day22.part2(("").split("\n")));

    }
}