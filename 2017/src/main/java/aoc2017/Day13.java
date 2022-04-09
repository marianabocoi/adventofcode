package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day13 {

    public static long part1(String input) {
        String[] lines = input.split("\n");
        int[] d = new int[lines.length];
        int[] r = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] a = lines[i].split(": ");
            int depth = Integer.parseInt(a[0]);
            d[i] = depth;
            int range = Integer.parseInt(a[1]);
            r[i] = range;
        }
        int severity = getSeverity(d, r, 0);
        return severity;
    }

    private static int getSeverity(int[] d, int[] r, int o) {
        int severity = 0;
        for (int i = 0; i < r.length; i++) {
            int position = (d[i] + o) % (r[i] * 2 - 2);
            if (position == 0) {
                severity += d[i] * r[i];
            }

        }
        return severity;
    }
    private static boolean caught(int[] d, int[] r, int o) {
        for (int i = 0; i < r.length; i++) {
            int position = (d[i] + o) % (r[i] * 2 - 2);
            if (position == 0) {
                return true;
            }

        }
        return false;
    }

    public static int part2(String input) {
        String[] lines = input.split("\n");
        int[] d = new int[lines.length];
        int[] r = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] a = lines[i].split(": ");
            int depth = Integer.parseInt(a[0]);
            d[i] = depth;
            int range = Integer.parseInt(a[1]);
            r[i] = range;
        }
        int count = 0;
        while (caught(d, r, count)) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "aoc2018/day13_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        System.out.println(part1(input));
        System.out.println(part2(input));// w 8752
    }
}

