import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Day16Test {
    @Test
    public void testPart1() {
        assertEquals("cdeab", new String(Day16.apply("s3", "abcde".toCharArray())));
        assertEquals("abdce", new String(Day16.apply("x2/3", "abcde".toCharArray())));
        assertEquals("cbade", new String(Day16.apply("pa/c", "abcde".toCharArray())));
        assertEquals("baedc", new String(Day16.part1("s1,x3/4,pe/b".split(","), "abcde".toCharArray())));
    }

    @Test
    public void testPart2() throws IOException {
//        Day16.part0(in, s);
    }
//
//    @Test
//    public void testPart0() throws IOException {
//        String[] elements = Day16.part0("s1,pa/b,pa/b,x3/4".split(","), "abcde".toCharArray());
//        String res = String.join(
//                ",",
//                elements
//        );
//        assertEquals("s1,x3/4", res);
//    }
//
//    @Test
//    public void testPart02() throws IOException {
//        String[] elements = Day16.part0("pa/b,pa/b,s1,x3/4".split(","), "abcde".toCharArray());
//        String res = String.join(
//                ",",
//                elements
//        );
//        assertEquals("s1,x3/4", res);
//    }
//
//    @Test
//    public void testPart03() throws IOException {
//        String[] elements = Day16.part0("s1,x3/4,pa/b,pa/b".split(","), "abcde".toCharArray());
//        String res = String.join(
//                ",",
//                elements
//        );
//        assertEquals("s1,x3/4", res);
//    }
//
//    @Test
//    public void testPart04() throws IOException {
//        char[] s = new char[16];
//        char start = 'a';
//        for (int i = 0; i < 16; i++) {
//            s[i] = (char) (start + i);
//        }
//
//        String resource = "day16_in.txt";
//        String input = new String(Files.readAllBytes(Paths.get(resource)));
//        String[] in = input.split(",");
//        int[] sw = Day16.part0(in, s);
//        String base = new String(Day16.part1(in,  Arrays.copyOf(s, s.length)));
//        String t = new String(Day16.part3(sw,  Arrays.copyOf(s, s.length)));
//        assertEquals(base,t);
//    }
//
//    @Test
//    public void dask(){
//        assertEquals("ceadb", new String(Day16.part4("s1,x3/4,pe/b".split(","), "abcde".toCharArray())));
//    }


}