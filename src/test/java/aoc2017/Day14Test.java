import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day14Test {
    @Test
    public void testPart1() {
        Day14.gentest();

        assertEquals("1010000011000010000000010111", Day14.herify("a0c2017"));
        assertEquals(8108, Day14.part1("flqrgnkx"));
    }

    @Test
    public void testPart2() {
        assertEquals(1242, Day14.part2("flqrgnkx"));
    }
}