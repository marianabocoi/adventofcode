package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Day20 {
    public static long part1(String[] in) {
        Particle[] particles = new Particle[in.length];
        for (int i = 0; i < in.length; i++) {
            particles[i] = new Particle(in[i], i);
            //System.out.println(particles[i]);
        }

        long min = Long.MAX_VALUE;
        int index = 0;
        for (int j = 0; j < in.length; j++) {
            long increase = particles[j].distance(100000000l) - particles[j].distance(0l);
            if (increase < min) {
                min = increase;
                index = j;
            }
        }
        return index; //67 no // 401 no //279 no // try 272 //797 not// try 308
    }

    public static long part2(String[] in) {
        ArrayList<Particle> particles = new ArrayList<>();
        for (int i = 0; i < in.length; i++) {
            particles.add(new Particle(in[i], i));
            //System.out.println(particles[i]);
        }
        for (int i = 0; i < 1000; i++) {
            for (Particle p :
                    particles) {
                p.update();
            }
            ArrayList<Particle> tmp = new ArrayList<>();
            for (Particle p :
                    particles) {
                if (particles.indexOf(p) != particles.lastIndexOf(p)) {
                    tmp.add(p);
                }
            }
            particles.removeAll(tmp);

        }
        return particles.size();
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "aoc2018/day20_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        String[] in = input.split("\n");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

//        System.out.println(part1(in));
        System.out.println(part2(in));
    }

    static class Particle {
        long p[];
        long v[];
        long a[];
        int id;
        long d;

        Particle(String s, int id) {
            this.id = id;
            String[] parts = s.split(", ");
            p = parseCoords(parts[0]);
            v = parseCoords(parts[1]);
            a = parseCoords(parts[2]);

        }

        private long[] parseCoords(String part) {
            String[] l = part.substring(3, part.length() - 1).split(",");
            return Arrays.stream(l).mapToLong(Long::parseLong).toArray();
        }

        long getIncrease() {
            long in = 0;
            for (int i = 0; i < 3; i++) {
                in += Math.abs(a[i]);
            }
            return in;
        }

        public void update() {
            for (int i = 0; i < 3; i++) {
                v[i] += a[i];
                p[i] += v[i];
            }
            for (int i = 0; i < 3; i++) {
                d += Math.abs(p[i]);
            }
        }

        public long distance() {
            return d;
        }

        public long distance(long n) {
            long di = 0;
            for (int i = 0; i < 3; i++) {
                long tmp = p[i] + n * v[i] + ((n * (n - 1) / 2) + n) * a[i];
                di += Math.abs(tmp);
            }
            return di;
        }

        public String toString() {
            return "p= " + Arrays.toString(p) +
                    "v= " + Arrays.toString(v) +
                    "a= " + Arrays.toString(a);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Particle)) return false;
            Particle tmp = (Particle) obj;
            for (int i = 0; i < p.length; i++) {
                if (tmp.p[i] != p[i]) return false;
            }
            return true;
        }
    }
}

