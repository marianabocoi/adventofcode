import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class Day18 {
    public static long part1(String[] step) {
        HashMap<String, Long> r = new HashMap<>();
        long lastrcv = 0;
        for (int i = 0; i < step.length && i >= 0; i++) {
            Long tmp = process(step[i], r);
            if (r.containsKey("rcv") && r.get("rcv") != lastrcv) {
                System.out.println(i + "\t" + r.get("rcv") + "/t " + r);
                return getFromMap(r, "rcv");
            }
            if (tmp != null) {
                i += tmp - 1;
                if (tmp > 0) {
                    i -= 1;
                }
            }
        }
        return getFromMap(r, "rcv");
    }

    private static Long process(String s, HashMap<String, Long> r) {
        String[] p = s.split(" ");
        switch (p[0]) {
            case "snd":
                snd(p[1], r);
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
                return rcv(p[1], r);
//                break;
            case "jgz":
                return jgz(p[1], p[2], r);
        }
        return null;
    }

    private static Long jgz(String x, String y, HashMap<String, Long> r) {
        if (getFromMap(r, x) != 0) {
            return getFromMap(r, y);
        }
        return null;
    }

    private static Long rcv(String x, HashMap<String, Long> r) {
        if (getFromMap(r, x) != 0) {
            r.put("rcv", getFromMap(r, "snd"));
            //return Integer.MAX_VALUE;
        }
        return null;
    }//"b" -> "9423"

    private static void mod(String x, String y, HashMap<String, Long> r) {
        long tmp = getFromMap(r, x) % getFromMap(r, y);
        r.put(x, tmp);
    }

    private static void mul(String x, String y, HashMap<String, Long> r) {
        long tmp = getFromMap(r, x) * getFromMap(r, y);
        r.put(x, tmp);
    }//"snd" -> "7481" // -2147483648

    private static void add(String x, String y, HashMap<String, Long> r) {
        long tmp = getFromMap(r, x) + getFromMap(r, y);
        r.put(x, tmp);
    }

    private static void set(String x, String y, HashMap<String, Long> r) {
        r.put(x, getFromMap(r, y));
    }

    private static void snd(String x, HashMap<String, Long> r) {
        r.put("snd", getFromMap(r, x));
    }

    private static Long getFromMap(HashMap<String, Long> r, String x) {
        if (x.matches("^[a-z]+$")) {
            if (r.containsKey(x)) {
                return r.get(x);
            }
            return 0l;
        }
        return Long.parseLong(x);
    }

    static volatile LinkedList<Long> p0 = new LinkedList<>();
    static volatile LinkedList<Long> p1 = new LinkedList<>();

    static  boolean waiting0 = false;
    static  boolean waiting1 = false;

    static long count1 = 0;
    static long count0 = 0;

    public static class Prog extends Thread {
        private int id;
        private String[] in;

        Prog(int id, String[] in) {
            super();

            this.id = id;
            this.in = in;
        }

        public void run() {
            System.out.println("hello from p" + id);
            HashMap<String, Long> r = new HashMap<>();
            r.put("p", (long) id);
            for (int i = 0; i < in.length && i >= 0; i++) {
                Long tmp = process2(in[i], r);
                if (tmp != null) {
                    i += tmp - 1;
                    if (tmp > 0) {
                        i -= 1;
                    }
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
            if (getFromMap(r, x) != 0) {
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
            if (id == 0) {
                while (p0.isEmpty()) {
                    waiting0 = true;
                    if (waiting1) {
                        System.out.println("count0=" + count0);
//                        currentThread().interrupt();
                    }
                    try {
                        currentThread().sleep(20);
                    } catch (InterruptedException e) {
                    }
                }
                waiting0 = false;
                r.put(x, p0.remove());
            }
            if (id == 1) {
                while (p1.isEmpty()) {
                    waiting1 = true;
                    if (waiting0) {
                        System.out.println("count1=" + count0);
//                        currentThread().interrupt();
                    }
                    try {
                        currentThread().sleep(20);
                    } catch (InterruptedException e) {
                    }
                }
                waiting1 = false;

                r.put(x, p1.remove());
            }
        }//"b" -> "9423"

        private void snd2(String x, HashMap<String, Long> r) {
            if (id == 0) {
                count1++;
                p1.add(getFromMap(r, x));
            }
            if (id == 1) {
                count0++;
                p0.add(getFromMap(r, x));
            }
        }

    }

    public static long part2(String[] in) {
        Prog p0 = new Prog(0, in);
        Prog p1 = new Prog(1, in);
        p0.start();
        p1.start();

        return count1;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "day18_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        String[] in = input.split("\n");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

//        System.out.println(part1(in));//"b" -> "9423"
        System.out.println(part2(in));// not 127 not +-1 not 190 // not 51 // not156 // not 3365 // not 988 //not 15180
    }
}

