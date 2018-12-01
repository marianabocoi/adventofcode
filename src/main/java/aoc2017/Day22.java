import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day22 {

    public static long part1(String[] in) {
        Maze ini = getMaze(in);
        Maze maze = getMaze(in);
        maze.addInitial(ini.m);
        Carrier carrier = new Carrier(maze);
        System.out.println(carrier.m.toString());
        for (int i = 0; i < 10000; i++) {
            carrier.burst();
            if (i % 1000 == 0) {
                System.out.println(carrier.m.toString());
                System.out.println("count = " + carrier.m.count);
                System.out.println("dir = " + carrier.direction);
                System.out.println("current = " + carrier.location);
                System.out.println("\n");
            }
        }
        return carrier.m.count;
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


    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "day22_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
//        String input = "..#\n" +
//                "#..\n" +
//                "...";
        String[] in = input.split("\n");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

        System.out.println(part1(in));
    }

    static class Maze {
        private ArrayList<Point> m;
        private ArrayList<Point> initial;
        long max;
        long count;
        Point current;

        Maze() {
            this.m = new ArrayList<>();
            initial = new ArrayList<>();
            current = new Point(0, 0);
        }

        void addInfected(Point p) {
            long tmp = Math.max(Math.abs(p.x), Math.abs(p.y));
            if (tmp > max) {
                max = tmp;
            }
//            if (!initial.contains(p)) {
                count++;
//            }
            m.add(p.copy());
        }

        void addClean(Point p) {
            m.remove(p);
        }

        boolean isInfected(Point p) {
            return m.contains(p);
        }

        void addInitial(ArrayList<Point> i) {
            initial = i;
            count -= initial.size();
        }

        public String toString() {
            long tmp = Math.max(Math.abs(current.x), Math.abs(current.y));
            if (tmp > max) {
                max = tmp;
            }
            StringBuilder sb = new StringBuilder();
            for (long i = -max; i <= max; i++) {
                for (long j = -max; j <= max; j++) {
                    char infected = isInfected(new Point(j, i)) ? '#' : '.';
                    sb.append(' ');
                    sb.append(infected);
                    sb.append(' ');
                    if (i == 0 && j == 0) {
                        sb.setLength(sb.length() - 3);
                        sb.append('[');

                        sb.append(infected);
                        sb.append(']');
                    }
                    if (j == current.x && i == current.y) {
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
            if (m.isInfected(location)) {
                turnRight();
                m.addClean(location);
                location.add(direction);
                return;
            } else {
                turnLeft();
                m.addInfected(location);
                location.add(direction);
                return;
            }
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

