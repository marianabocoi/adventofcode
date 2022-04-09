package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Day24 {

    public static long part1(String[] in) {
        ArrayList<Component> l = new ArrayList<>();
        ArrayList<Component> first = new ArrayList<>();
        for (String s : in) {
            Component c = new Component(s);
            l.add(c);
            if (c.contains(0)) first.add(c);
        }
        long maxPath = 0;
        HashMap<Long, Long> memo = new HashMap<>();
        for (Component n : first) {
            ArrayList<Component> v = new ArrayList<>();
            Integer pos0 = (n.pos(0) + 1) % 2;
            long tmp = findMax(0, n, pos0, l, v, memo);
            if (maxPath < tmp) {
                maxPath = tmp;
            }
        }
        long maxLength = memo.keySet().stream().mapToLong(Long::longValue).max().orElse(0l);

        return memo.get(maxLength); // 2030 too high
    }

    private static long findMax(long sum, Component n, int s, ArrayList<Component> l, ArrayList<Component> v, HashMap<Long, Long> memo) {
        v.add(n);
        ArrayList<Component> c = new ArrayList<>();
        sum += n.strength;
        long max = sum;
        boolean endNode = true;
        for (Component m : l) {
            if (!v.contains(m) && m.contains(n.ports[s])) {
                Integer pos = (m.pos(n.ports[s]) + 1) % 2;
                long tmp = findMax(sum, m, pos, l, v, memo);
                endNode = false;
                if (max < tmp) {
                    max = tmp;
                }
            }
        }
        if (endNode) {
            System.out.println(max + "\t- " + v);
            if (memo.keySet().contains(v.size())) {
                if (memo.get((long) v.size()) < sum) {
                    memo.put((long) v.size(), sum);
                }
            } else {
                memo.put((long) v.size(), sum);
            }
        }
        v.remove(n);
        return max;
    }

    public static long part2(String[] in) {
        return 2;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "aoc2018/day24_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
//        String input = "0/2\n" +
//                "2/2\n" +
//                "2/3\n" +
//                "3/4\n" +
//                "3/5\n" +
//                "0/1\n" +
//                "10/1\n" +
//                "9/10";

        String[] in = input.split("\n");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

        System.out.println(part1(in));
        System.out.println(part2(in));
    }

    static class Component {
        int[] ports;
        boolean[] u;
        boolean first;
        int strength;

        Component(String m) {
            ports = Arrays.stream(m.split("/")).mapToInt(Integer::parseInt).toArray();
            u = new boolean[ports.length];
            for (int i = 0; i < ports.length; i++) {
                strength += ports[i];
                if (ports[i] == 0) {
                    first = true;
                }
                u[i] = false;
            }
        }

        boolean contains(Integer i) {
            return IntStream.of(ports).anyMatch(x -> x == i);
        }

        public Integer pos(int i) {
            if (ports[0] == i) return 0;
            if (ports[1] == i) return 1;
            return null;
        }

        @Override
        public String toString() {
            return Arrays.toString(ports);
        }
    }
}


