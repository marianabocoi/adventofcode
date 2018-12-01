package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1 {

    public static long part1(String input) {
        long sum = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt((i + 1) % input.length())) {
                sum += input.charAt(i) - '0';
            }
        }
        return sum;
    }

    public static long part2(String input) {
        long sum = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt((i + (input.length() / 2)) % input.length())) {
                sum += input.charAt(i) - '0';
            }
        }
        return sum;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "aoc2018/day1_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
