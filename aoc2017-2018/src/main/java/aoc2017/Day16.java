package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class Day16 {
    public static char[] apply(String c, char[] s) {
        switch (c.charAt(0)) {
            case 's':
                return swap(c.substring(1), s);
            case 'x':
                return exchange(c.substring(1), s);
            case 'p':
                return partner(c.substring(1), s);
        }
        return s;
    }

    private static char[] swap(String substring, char[] s) {
        char[] r = new char[s.length];
        int o = Integer.parseInt(substring);
        int l = r.length;
        for (int i = 0; i < s.length; i++) {
            r[(i + o) % l] = s[i];
        }
        return r;
    }

    private static char[] exchange(String substring, char[] s) {
        String[] n = substring.split("/");
        int a = Integer.parseInt(n[0]);
        int b = Integer.parseInt(n[1]);
        char tmp = s[b];
        s[b] = s[a];
        s[a] = tmp;
        return s;
    }

    private static char[] partner(String substring, char[] s) {
        String[] n = substring.split("/");
        char a = n[0].charAt(0);
        char b = n[1].charAt(0);
        for (int i = 0; i < s.length; i++) {
            if (s[i] == b) {
                s[i] = a;
            } else if (s[i] == a) {
                s[i] = b;
            }
        }
        return s;
    }

    public static char[] part1(String[] in, char[] si) {
        char[] s = Arrays.copyOf(si, si.length);
        for (int i = 0; i < in.length; i++) {
            s = apply(in[i], s);
        }
        return s;
    }

    public static char[] part2(String[] input, char[] s) {
        HashMap<String, char[]> mem = new HashMap<>();
        String key;
        for (long i = 0; i < 1000000000%60; i++) {
            key = new String(s);
            if (!mem.containsKey(key)) {
                mem.put(key, part1(input, s));
            }
            s = mem.get(key);
        }
        return s;
    }


    public static void main(String[] args) throws URISyntaxException, IOException {
        char[] s = new char[16];
        char start = 'a';
        for (int i = 0; i < 16; i++) {
            s[i] = (char) (start + i);
        }

        String resource = "aoc2018/day16_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        String[] in = input.split(",");

        System.out.println(part2(in, s));
    }


}

