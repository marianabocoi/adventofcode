import org.junit.Test;

import static org.junit.Assert.*;

public class Day9Test {
    @Test
    public void testPart1() throws Exception {
        assertEquals(9, Day9.part1("{{<!!>},{<!!>},{<!!>},{<!!>}}"));
        assertEquals(3, Day9.part1("{{<a!>},{<a!>},{<a!>},{<ab>}}"));
        assertEquals(1, Day9.part1("{}"));
        assertEquals(6, Day9.part1("{{{}}}"));
        assertEquals(5, Day9.part1("{{},{}}"));
        assertEquals(16, Day9.part1("{{{},{},{{}}}}"));
        assertEquals(1, Day9.part1("{<a>,<a>,<a>,<a>}"));
        assertEquals(9, Day9.part1("{{<ab>},{<ab>},{<ab>},{<ab>}}"));
    }

    @Test
    public void testPart2() throws Exception {
        assertEquals(0, Day9.part2("<>"));
        assertEquals(17, Day9.part2("<random characters>"));
        assertEquals(3, Day9.part2("<<<<>"));
        assertEquals(2, Day9.part2("<{!>}>"));
        assertEquals(0, Day9.part2("<!!>"));
        assertEquals(0, Day9.part2("<!!!>>"));
        assertEquals(10, Day9.part2("<{o\"i!a,<{i<a>"));
    }
}