import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Day23a {
    static int count;

    public static long part1(String[] step) {
        HashMap<String, Long> r = new HashMap<>();
        r.put("a", 1l);
        for (int i = 0; i < step.length && i >= 0; i++) {
            System.out.println(i);
            Long tmp = process(step[i], r);
            if (tmp != null) {
                i += tmp - 1;
            }
        }
        return r.get("h");
    }

    private static Long process(String s, HashMap<String, Long> r) {
        String[] p = s.split(" ");
        switch (p[0]) {
            case "set":
                set(p[1], p[2], r);
                break;
            case "mul":
                mul(p[1], p[2], r);
                break;
            case "sub":
                mul(p[1], p[2], r);
                break;
            case "mod":
                mod(p[1], p[2], r);
                break;
            case "jnz":
                return jnz(p[1], p[2], r);
        }
        return null;
    }

    private static Long jnz(String x, String y, HashMap<String, Long> r) {
        if (getFromMap(r, x) != 0) {
            Long fromMap = getFromMap(r, y);
            return fromMap;
        }
        return null;
    }

    private static void mod(String x, String y, HashMap<String, Long> r) {
        long tmp = getFromMap(r, x) % getFromMap(r, y);
        r.put(x, tmp);
    }

    private static void mul(String x, String y, HashMap<String, Long> r) {
        count++;
        long tmp = getFromMap(r, x) * getFromMap(r, y);
        r.put(x, tmp);
    }

    private static void sub(String x, String y, HashMap<String, Long> r) {
        long tmp = getFromMap(r, x) - getFromMap(r, y);
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

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "day23_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        String[] in = input.split("\n");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

//        System.out.println(part1(in));//"b" -> "9423"
        System.out.println(part1(in));// not 127 not +-1 not 190 // not 51 // not156 // not 3365 // not 988 //not 15180
    }
}

