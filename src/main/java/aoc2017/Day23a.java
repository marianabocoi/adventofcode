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
            getMapStr(r);
            Long tmp = process(step[i], r);
//            if (i == 25) {
                System.out.println((i + 1) + "\t " + step[i] + "\t " + getMapStr(r));
//            }
            if (tmp != null) {
                i += tmp - 1;
            }
        }
        return r.get("h");
    }
    /*
     a=1 b=107900 c=124900 d=2 e=5 f=1 g=-107892
     a=1 b=107900 c=124900 d=3 e=3 f=1 g=3


     25	 jnz f   a=1 b=107900 c=124900 d=107900 e=107900 f=1 g=0
9	 set f 1	 a=1 b=107917 c=124900 d=107900 e=107900 f=1 g=-17000

	 a=1 b=108478 c=124900 d=108478 e=108478 f=0 g=0 h=18



26	 sub h -1	 a=1 b=109090 c=124900 d=109090 e=109090 f=0 g=0 h=36
26	 sub h -1	 a=1 b=109124 c=124900 d=109124 e=109124 f=0 g=0 h=37
34


110484  124900  110484  110484  77
110518  124900  110518  110518  78
110552  124900  110552  110552  79
110586  124900  110586  110586  80
110620  124900  110620  110620  81
110654  124900  110654  110654  82
110688  124900  110688  110688  83
110722  124900  110722  110722  84
110756  124900  110756  110756  85
110790  124900  110790  110790  86
110824  124900  110824  110824  87
110858  124900  110858  110858  88


not 501
     */

    public static String getMapStr(HashMap<String, Long> r) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Long> e : r.entrySet()) {
            sb.append(e.getKey() + "=" + e.getValue() + " ");
        }
        return sb.toString();
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
                sub(p[1], p[2], r);
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

