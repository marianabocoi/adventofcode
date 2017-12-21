import org.junit.Test;

import static org.junit.Assert.*;

public class Day11Test {
    @Test
    public void testPart1() throws Exception {
        assertEquals(3, Day11.part1("ne,ne,ne"));
        assertEquals(0, Day11.part1("ne,ne,sw,sw"));
        assertEquals(2, Day11.part1("ne,ne,s,s"));
        assertEquals(3, Day11.part1("se,sw,se,sw,sw"));
    }

    @Test
    public void testPart2() throws Exception {
        assertEquals(0, Day11.part2("ne,ne,sw,sw"));
    }
}