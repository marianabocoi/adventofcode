import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Day18c {
    public static long part2(String[] in) {
        WorldState[] state = new WorldState[2];
        state[0] = new WorldState(0);
        state[1] = new WorldState(1);
        int i = 0;
        do {
//            System.out.println(state[i]);
            run(in, state[i]);
            int newi = (i + 1) % 2;
            state[i].count += state[i].output.size();
            state[newi].input = state[i].output;
            System.out.println(state[i]);
            i = newi;
        } while ((state[0].input.size() != 0 || state[1].input.size() != 0) &&
                (state[0].notExited || state[1].notExited));
        return state[1].count;
    }

    static class WorldState {
        private int id;
        String waitingRcv;
        HashMap<String, Long> state;
        LinkedList<Long> input;
        LinkedList<Long> output;
        long count;
        int index;
        boolean notExited;
        Integer jmp;

        WorldState(int id) {
            this.id = id;
            state = new HashMap<>();
            state.put("p", (long) id);
            notExited = true;
            input = new LinkedList<>();
            output = new LinkedList<>();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("id = ");
            sb.append(id);
            sb.append("\t ");

            sb.append("index = ");
            sb.append(index);
            sb.append("\t ");

            sb.append("count = ");
            sb.append(count);
            sb.append("\t ");

            sb.append("notExited = ");
            sb.append(notExited);
            sb.append("\t ");


            for (Map.Entry<String, Long> e : state.entrySet()) {
                if (!"id".equals(e.getKey())) {
                    sb.append(e.getKey());
                    sb.append("= ");
                    sb.append(e.getValue());
                    sb.append("  ");
                }
            }
            sb.append("\n\t output[" + output.size() + "] = ");

            for (Long l : output) {
                sb.append(l);
                sb.append(" ");
            }

            sb.append("\n\t input[" + input.size() + "] = ");

            for (Long l : input) {
                sb.append(l);
                sb.append(" ");
            }
            return sb.toString();
        }

        public Long get(String id) {
            if (id.matches("^[a-z]+$")) {
                if (!state.containsKey(id)) {
                    state.put(id, 0l);
                }
                return state.get(id);
            }
            return Long.parseLong(id);
        }

        public boolean containsKey(String index) {
            return state.containsKey(index);
        }

        public void put(String index, long i) {
            state.put(index, i);
        }

        public int getNextIndex(int i) {
            int offset = jmp != null ? jmp : 1;
            jmp = null;
            index = i + offset;
            return index;
        }

        public boolean notExited(int length) {
            notExited = ((index < length) && (index >= 0));
            return notExited;
        }
    }


    static void run(String[] in, WorldState r) {

        int i = r.index;
        if (r.waitingRcv != null) {
            rcv(r.waitingRcv, r);
        }
        while (r.notExited(in.length) && r.waitingRcv == null) {
            process(in[i], r);
            i = r.getNextIndex(i);
        }
    }

    private static void process(String s, WorldState r) {
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
                rcv(p[1], r);
                break;
            case "jgz":
                jgz(p[1], p[2], r);
        }
    }

    private static void jgz(String x, String y, WorldState r) {
        if (r.get(x) > 0) {
            int i = r.get(y).intValue();
            r.jmp = i;
        }
    }

    private static void mod(String x, String y, WorldState r) {
        long tmp = r.get(x) % r.get(y);
        r.put(x, tmp);
    }

    private static void mul(String x, String y, WorldState r) {
        long tmp = r.get(x) * r.get(y);
        r.put(x, tmp);
    }//"snd" -> "7481" // -2147483648

    private static void add(String x, String y, WorldState r) {
        long tmp = r.get(x) + r.get(y);
        r.put(x, tmp);
    }

    private static void set(String x, String y, WorldState r) {
        r.put(x, r.get(y));
    }

    private static void rcv(String x, WorldState r) {
        if (r.input.size() != 0) {
            r.waitingRcv = null;
            Long remove = r.input.remove();
            r.put(x, remove);
        } else {
            r.waitingRcv = x;
        }
    }

    private static void snd(String x, WorldState r) {
        r.output.add(r.get(x));
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

