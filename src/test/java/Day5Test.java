import org.junit.Test;

import static org.junit.Assert.*;

public class Day5Test {
    @Test
    public void testPart1() throws Exception {
        assertEquals(5, Day5.part1("0\n" +
                "3\n" +
                "0\n" +
                "1\n" +
                "-3"));
    }

    @Test
    public void testPart2() throws Exception {
        assertEquals(10, Day5.part2("0\n" +
                "3\n" +
                "0\n" +
                "1\n" +
                "-3"));
    }
}