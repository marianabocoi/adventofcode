package aoc2017;

import aoc2017.Day25;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Day25Test {
    @Test
    public void testPart1() {
        assertEquals(0, Day25.part1(("").split("\n")));
    }

    @Test
    public void testPart2() throws IOException, InterruptedException {
        assertEquals(1, Day25.part2(("").split("\n")));

    }
}