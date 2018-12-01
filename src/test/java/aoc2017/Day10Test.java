package aoc2017;

import aoc2017.Day10;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day10Test {
    @Test
    public void testPart1() throws Exception {
        assertEquals(12, Day10.part1("3,4,1,5", 5));
    }

    @Test
    public void testPart2() throws Exception {
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", Day10.part2("", 256));
    }
    @Test
    public void testHash() throws Exception {
        assertEquals(64, Day10.hash(new int[]{65 , 27 , 9 , 1 , 4 , 3 , 40 , 50 , 91 , 7 , 6 , 0 , 2 , 5 , 68 , 22}));
    }
}