package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Day18b {

    public static class Prog extends Thread {
        private volatile int id;
        private volatile String[] in;
        volatile LinkedBlockingQueue<Long> input;
        volatile LinkedBlockingQueue<Long> output;

        long count = 0;

        Prog(int id, String[] in, LinkedBlockingQueue<Long> input, LinkedBlockingQueue<Long> output) {
            super();

            this.id = id;
            this.in = in;
            this.input = input;
            this.output = output;
        }

        public void run() {
            System.out.println("hello from p" + id);
            HashMap<String, Long> r = new HashMap<>();
            r.put("p", (long) id);
            int i = 0;
            while (i < in.length && i >= 0) {
                Long tmp = process2(in[i], r);
                if (tmp != null) {
                    i += tmp;
                } else {
                    i++;
                }
            }
        }


        private Long process2(String s, HashMap<String, Long> r) {
            String[] p = s.split(" ");
            switch (p[0]) {
                case "snd":
                    snd2(p[1], r);
                    break;
                case "set":
                    set(p[1], p[2], r);
                    break;
                case "add":
                    add(p[1], p[2], r);
                    break;
                case "mul":
                    mul(p[1], p[2], r);
                    break;
                case "mod":
                    mod(p[1], p[2], r);
                    break;
                case "rcv":
                    rcv2(p[1], r);
                    break;
                case "jgz":
                    return jgz(p[1], p[2], r);
            }
            return null;
        }

        private Long jgz(String x, String y, HashMap<String, Long> r) {
            if (getFromMap(r, x) > 0) {
                return getFromMap(r, y);
            }
            return null;
        }

        private void mod(String x, String y, HashMap<String, Long> r) {
            long tmp = getFromMap(r, x) % getFromMap(r, y);
            r.put(x, tmp);
        }

        private void mul(String x, String y, HashMap<String, Long> r) {
            long tmp = getFromMap(r, x) * getFromMap(r, y);
            r.put(x, tmp);
        }//"snd" -> "7481" // -2147483648

        private void add(String x, String y, HashMap<String, Long> r) {
            long tmp = getFromMap(r, x) + getFromMap(r, y);
            r.put(x, tmp);
        }

        private void set(String x, String y, HashMap<String, Long> r) {
            r.put(x, getFromMap(r, y));
        }

        private Long getFromMap(HashMap<String, Long> r, String x) {
            if (x.matches("^[a-z]+$")) {
                if (r.containsKey(x)) {
                    return r.get(x);
                }
                return 0l;
            }
            return Long.parseLong(x);
        }


        private void rcv2(String x, HashMap<String, Long> r) {
            try {
                r.put(x, input.take());
//                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("send" + id);
            }
        }//"b" -> "9423"

        private void snd2(String x, HashMap<String, Long> r) {
            count++;
            output.add(getFromMap(r, x));
//            try {
////                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                throw new RuntimeException("send" + id);
//            }
        }

    }

    public static long part2(String[] in) throws InterruptedException {
        LinkedBlockingQueue<Long> input0 = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Long> input1 = new LinkedBlockingQueue<>();
        Prog p0 = new Prog(0, in, input0, input1);
        Prog p1 = new Prog(1, in, input1, input0);

        p0.start();
        p1.start();
        while (!(p0.getState() == Thread.State.WAITING
                && p0.getState() == Thread.State.WAITING
                && p1.input.size() == 0
                && p0.input.size() == 0)) {
            TimeUnit.SECONDS.sleep(5);
        }
        p0.interrupt();
        p1.interrupt();
        System.out.println("0 = " + p0.count);
        System.out.println("1 = " + p1.count);
        return p1.count;// not 94593
    }

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String resource = "aoc2018/day18_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        String[] in = input.split("\n");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

//        System.out.println(part1(in));//"b" -> "9423"
        System.out.println(part2(in));// not 127 not +-1 not 190 // not 51 // not156 // not 3365 // not 988 //not 15180
    }//181504484 - 181504581
}

