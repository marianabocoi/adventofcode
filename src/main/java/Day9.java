import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

import static java.util.stream.Collectors.joining;

public class Day9 {

    public static long part1(String input) {
        StringBuilder in = new StringBuilder();
        boolean ignore = false;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '!') {
                i++;
                continue;
            }
            if (input.charAt(i) == '<') {
                ignore = true;
            }
            if (input.charAt(i) == '>') {
                ignore = false;
                continue;
            }
            if (!ignore) {
                if (input.charAt(i) == ',' || input.charAt(i) == '{' || input.charAt(i) == '}') {
                    in.append(input.charAt(i));
                }


            }
        }
        Stack<Integer> p = new Stack<>();
        String f = in.toString();
        String[] st = new String[f.length()];
        for (int i = 0; i < f.length(); i++) {
            if (f.charAt(i) == '{') {
                p.push(i);
            }
            if (f.charAt(i) == '}') {
                if (!p.empty()) {
                    st[p.pop()] = "{";
                    st[i] = "}";
                }
            }
        }
        String the = Arrays.stream(st).filter(Objects::nonNull)
                .collect(joining(""));
        int depth = 0;
        int sum = 0;
        for (int i = 0; i < the.length(); i++) {
            if (the.charAt(i) == '{') {
                depth++;
//                System.out.println("d++");
            }
            if (the.charAt(i) == '}') {
                sum += depth;
                depth--;
//                System.out.println("d--");
            }
        }
        return sum;
    }

    public static long part2(String input) {
        StringBuilder in = new StringBuilder();
        boolean ignore = true;
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '!') {
                i++;
                continue;
            }
            if (input.charAt(i) == '<' && ignore) {
                ignore = false;
                continue;
            }
            if (input.charAt(i) == '>') {
                ignore = true;
            }
            if (!ignore) {
                sum ++;
            }
        }
        return sum;
        // 6281 - low
        // 7834 - high
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "day9_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}

