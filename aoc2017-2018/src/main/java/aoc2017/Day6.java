package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Day6 {

    public static long part1(String input) {
        int[] in = Arrays.stream(input.split("\t")).mapToInt(Integer::parseInt).toArray();
        int count = -1;
        ArrayList<String> memo = new ArrayList<>();
        String repr = "";
        do {
            memo.add(repr);
            repr = String.join(",", Arrays.stream(in).mapToObj(String::valueOf).toArray(String[]::new));
            redistribute(in);
            count ++;
        } while (!memo.contains(repr));
        return count;
    }

    private static void redistribute(int[] in) {
        int max = in[0];
        int maxIndex = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] > max) {
                max = in[i];
                maxIndex = i;
            }
        }
        in[maxIndex] = 0;
        int l = in.length;
        int index = (maxIndex + 1) % l;
        while (max != 0) {
            in[index] = in[index] + 1;
            index = (index + 1) % l;
            max--;
        }
    }

    public static long part2(String input) {
        int[] in = Arrays.stream(input.split("\t")).mapToInt(Integer::parseInt).toArray();
        int count = -1;
        ArrayList<String> memo = new ArrayList<>();
        String repr = "";
        do {
            memo.add(repr);
            repr = String.join(",", Arrays.stream(in).mapToObj(String::valueOf).toArray(String[]::new));
            redistribute(in);
            count ++;
        } while (!memo.contains(repr));
        int count2 = 0;
        String repr2 = "";
        while (!repr.equals(repr2)){
            repr2 = String.join(",", Arrays.stream(in).mapToObj(String::valueOf).toArray(String[]::new));
            redistribute(in);
            count2 ++;
        }
        return count2;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "aoc2018/day6_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
