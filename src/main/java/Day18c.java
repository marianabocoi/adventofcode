import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;

public class Day18c {
    public static long part2(String[] in) {
        HashMap<String, Long>[] state = new HashMap[2];
        state[0] = new HashMap();
        state[0].put("id", 0l);
        state[1] = new HashMap();
        state[1].put("id", 1l);
        long count[] = new long[2];
        int i = 0;
        LinkedList<Long> input = new LinkedList<>();
        LinkedList<Long> output = new LinkedList<>();
        do {
            output = run(in, state[i], input);
            i = (i + 1) % 2;
        } while (output.size() != 0 && state[i].get("rcv") != null);


        return count[1];
    }

    public static LinkedList<Long> run(String[] in, HashMap<String, Long> r, LinkedList<Long> input) {
        System.out.println("hello from p" + r.get("id"));
        LinkedList<Long> output = new LinkedList<>();
        int i = r.containsKey("index") ? r.get("index").intValue() : 0;
        while (i < in.length && i >= 0 && r.get("rcv") != null) {
            process(in[i], r, input, output);
            i = getNextIndex(r, i);
            r.put("index", (long) i);
        }
        if (i < in.length && i >= 0) r.put("endState", 0l);
        return output;
    }

    private static int getNextIndex(HashMap<String, Long> r, int i) {
        Long tmp = r.get("jgz");
        int increment = tmp != null ? tmp.intValue() : 1;
        i += increment;
        return i;
    }


    private static void process(String s, HashMap<String, Long> r, LinkedList<Long> input, LinkedList<Long> output) {
        String[] p = s.split(" ");
        switch (p[0]) {
            case "snd":
                snd(p[1], r, output);
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
                rcv(p[1], r, input);
                break;
            case "jgz":
                jgz(p[1], p[2], r);
        }
    }

    private static Long jgz(String x, String y, HashMap<String, Long> r) {
        if (getFromMap(r, x) > 0) {
            Long j = getFromMap(r, y);
            if (j > 0) {
                j -= 1;
            }
            r.put("jgz", j);
        }
        return null;
    }

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

    private static Long getFromMap(HashMap<String, Long> r, String x) {
        if (x.matches("^[a-z]+$")) {
            if (r.containsKey(x)) {
                return r.get(x);
            }
            return 0l;
        }
        return Long.parseLong(x);
    }


    private static void rcv(String x, HashMap<String, Long> r, LinkedList<Long> input) {
        if (input.size() != 0) {
            Long remove = input.remove();
            r.put(x, remove);
            r.put("rcv", remove);
        } else {
            r.put("rcv", null);
        }
    }

    private static void snd(String x, HashMap<String, Long> r, LinkedList<Long> output) {
        output.add(getFromMap(r, x));
    }

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String resource = "day18_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        String[] in = input.split("\n");

        Instant start = Instant.now();
        System.out.println(part2(in));
        Instant end = Instant.now();
        System.out.println("time= " + Duration.between(start, end)); // prints PT1M3.553S

    }
}

