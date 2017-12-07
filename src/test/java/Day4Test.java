import org.junit.Test;

import static org.junit.Assert.*;

public class Day4Test {
    @Test
    public void testPart1() throws Exception {
        assertEquals(1, Day4.part1("aa bb cc dd ee"));
        assertEquals(0, Day4.part1("aa bb cc dd aa"));
        assertEquals(1, Day4.part1("aa bb cc dd aaa"));
        assertEquals(2, Day4.part1("aa bb cc dd ee\naa bb cc dd aa\naa bb cc dd aaa"));
    }

    @Test
    public void testPart2() throws Exception {
        System.out.println(new Anagram("abcde").equals(new Anagram("ecdab")));
        assertEquals(1, Day4.part2("abcde fghij"));
        assertEquals(0, Day4.part2("abcde xyz ecdab"));
        assertEquals(1, Day4.part2("a ab abc abd abf abj"));
        assertEquals(1, Day4.part2("iiii oiii ooii oooi oooo"));
        assertEquals(0, Day4.part2("oiii ioii iioi iiio"));
    }

}