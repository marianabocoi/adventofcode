import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Day18Test {
    @Test
    public void testPart1() {
        assertEquals(4, Day18.part1("set a 1\nadd a 2\nmul a a\nmod a 5\nsnd a\nset a 0\nrcv a\njgz a -1\nset a 1\njgz a -2".split("\n")));
    }

    @Test
    public void testPart2() throws IOException, InterruptedException {
        assertEquals(3, Day18b.part2(("snd 1\n" +
                "snd 2\n" +
                "snd p\n" +
                "rcv a\n" +
                "rcv b\n" +
                "rcv c\n" +
                "rcv d").split("\n")));

        assertEquals(3, Day18b.part2(("set a 1\nadd a 2\nmul a a\nmod a 5\nsnd a\nset a 0\nrcv a\njgz a - 1\nset a 1\njgz a - 2").split("\n")));

    }


}