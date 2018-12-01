package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day2 {
    public static long part1(String pos) {
        String[] in = pos.split("\n");
        int sum = 0;
        for (String nrs : in) {
            String[] nr = nrs.split("\t");
            int max = 0;
            int min = Integer.MAX_VALUE;
            for (String x : nr) {
                int y = Integer.parseInt(x);
                if (y > max) {
                    max = y;
                }
                if (y < min) {
                    min = y;
                }
            }
            sum += max - min;
        }
        return sum;
    }

    public static long part2(String s) {
        String[] in = s.split("\n");
        int sum = 0;
        for (String nrs : in) {
            String[] nr = nrs.split("\t");
            int first = 0;
            int second = 0;
            for (int i = 0; i < nr.length; i++) {
                int tmp1 = Integer.parseInt(nr[i]);
                for (int j = 0; j < nr.length; j++) {
                    if (i != j) {
                        int tmp2 = Integer.parseInt(nr[j]);
                        if (tmp1 % tmp2 == 0) {
                            first = tmp1;
                            second = tmp2;
                            break;
                        }
                    }
                }
            }
            sum += first / second;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        String resource = "aoc2018/day2_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
