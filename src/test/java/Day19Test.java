import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Day19Test {
    @Test
    public void testPart1() {
        assertEquals("ABCDEF", Day19.part1(("     |          \n" +
                "     |  +--+    \n" +
                "     A  |  C    \n" +
                " F---|----E|--+ \n" +
                "     |  |  |  D \n" +
                "     +B-+  +--+ \n").split("\n")));
    }

    @Test
    public void testPart2() throws IOException, InterruptedException {
        assertEquals(38, Day19.part2(("     |          \n" +
                "     |  +--+    \n" +
                "     A  |  C    \n" +
                " F---|----E|--+ \n" +
                "     |  |  |  D \n" +
                "     +B-+  +--+ \n").split("\n")));

    }


}