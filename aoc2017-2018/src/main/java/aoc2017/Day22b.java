package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Day22b {

    public static long part1(String[] in) {
        Maze maze = getMaze(in);
        Carrier carrier = new Carrier(maze);
        System.out.println(carrier.m.toString());
        for (int i = 1; i <= 10000000; i++) {
            carrier.burst();
//            if (i % 1000000 == 0) {
//            System.out.println(carrier.m.toString());
//            System.out.println("iteration = " + i);
//            System.out.println("count = " + carrier.m.count);
//            System.out.println("dir = " + carrier.direction);
//            System.out.println("current = " + carrier.location);
//            System.out.println("\n");
//            }
        }//exp 26 got 24 for 100
        //tried 2511226 out 2511224
        // too low 2499952
        //         2511527
        return carrier.m.count;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "aoc2018/day22_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
//        String input = "..#\n" +
//                "#..\n" +
//                "...";
        String[] in = input.split("\n");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

        System.out.println(part1(in));
    }


    private static Maze getMaze(String[] in) {
        Maze maze = new Maze();
        int offset = (int) Math.floor(in.length / 2);
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[0].length(); j++) {
                if (in[i].charAt(j) == '#') {
                    maze.addInfected(new Point(j - offset, i - offset));
                } else {
//                    System.out.print(in[i].charAt(j));
                }
            }
//            System.out.println("\n");
        }
        return maze;
    }

    public static long part2(String[] in) {

        return 1;
    }

    static class Maze {
        HashMap<Point, Character> m;
        long max;
        long count;
        Point current;

        Maze() {
            this.m = new HashMap<>();
            current = new Point(0, 0);
        }

        void visit(Point p) {
            maybeUpdateMax(p);
            Character state = getNextState(p);
            if (state == '.') {
                m.remove(p);
            } else {
                if (state == '#') {
                    count++;
                }
                m.put(p.copy(), getNextState(p));
            }
        }

        private Character getNextState(Point p) {
            switch (m.getOrDefault(p, '.')) {
                case '.':
                    return 'w';
                case 'w':
                    return '#';
                case '#':
                    return 'f';
                case 'f':
                    return '.';
            }
            return null;
        }


        public String toString() {
            maybeUpdateMax(current);
            StringBuilder sb = new StringBuilder();
            for (long i = -max; i <= max; i++) {
                for (long j = -max; j <= max; j++) {
                    Point key = new Point(j, i);
                    char infected = m.getOrDefault(key, '.');
                    sb.append(' ');
                    sb.append(infected);
                    sb.append(' ');
                    if (i == 0 && j == 0) {
                        sb.setLength(sb.length() - 3);
                        sb.append('[');

                        sb.append(infected);
                        sb.append(']');
                    }
                    if (j == this.current.x && i == this.current.y) {
                        sb.setLength(sb.length() - 3);
                        sb.append('(');

                        sb.append(infected);
                        sb.append(')');
                    }

                }
                sb.append("\n");
            }
            return sb.toString();
        }

        private void maybeUpdateMax(Point cc) {
            long tmp = Math.max(Math.abs(cc.x), Math.abs(cc.y));
            if (tmp > max) {
                max = tmp;
            }
        }

        public char getState(Point point) {
            if (!m.containsKey(point)) {
                return '.';
            }
            return m.get(point);
        }

        public void addInfected(Point point) {
            maybeUpdateMax(point);
            m.put(point, '#');
        }
    }

    static class Point {
        long x;
        long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) return false;
            Point p = (Point) obj;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return (int) ((Math.abs(x) + Math.abs(y)) % 100);
        }


        public void add(Point direction) {
            x += direction.x;
            y += direction.y;
        }

        public void remove(Point direction) {
            x -= direction.x;
            y -= direction.y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public Point copy() {
            return new Point(x, y);
        }
    }

    static class Carrier {
        Point location;
        Point direction;
        long count;
        Maze m;

        Carrier(Maze m) {
            location = new Point(0, 0);
            direction = new Point(0, -1);
            count = 0;
            this.m = m;
            location = m.current;
        }

        void burst() {
            boolean stop = false;
            switch (m.getState(location)) {
                case '.':
                    turnLeft();
                    break;
                case 'w':
//                    stop = true;
                    break;
                case '#':
                    turnRight();
                    break;
                case 'f':
                    flipDirection();
                    break;
            }
            m.visit(location);
            if (!stop) {
                location.add(direction);
            }
        }

        private void flipDirection() {
            direction.x = direction.x * (-1);
            direction.y = direction.y * (-1);
        }

        private void turnLeft() {
//            System.out.println("turn left");
            if (direction.x == 0) {
                if (direction.y > 0) {
                    direction.x = 1;
                } else {
                    direction.x = -1;
                }
                direction.y = 0;
            } else {
                if (direction.x > 0) {
                    direction.y = -1;
                } else {
                    direction.y = 1;
                }
                direction.x = 0;
            }
        }

        private void turnRight() {
//            System.out.println("turn right");
            if (direction.x == 0) {
                if (direction.y > 0) {
                    direction.x = -1;
                } else {
                    direction.x = 1;
                }
                direction.y = 0;
            } else {
                if (direction.x > 0) {
                    direction.y = 1;
                } else {
                    direction.y = -1;
                }
                direction.x = 0;
            }
        }

    }
}

