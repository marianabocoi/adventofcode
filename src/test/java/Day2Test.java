import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {
    @Test
    void testPart1() {
        assertEquals(18, Day2.part1("5\t1\t9\t5\n" +
                "7\t5\t3\n" +
                "2\t4\t6\t8"));
    }

    @Test
    void testPart2() {
        assertEquals(9, Day2.part2("5\t9\t2\t8\n" +
                "9\t4\t7\t3\n" +
                "3\t8\t6\t5"));
    }
}