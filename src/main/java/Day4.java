import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Day4 {
    public static long part1(String pos) {
        int count = 0;
        String[] in = pos.split("\n");
        for (String i : in) {
            String[] words = i.split(" ");
            boolean valud = true;
            HashMap<String, Boolean> m = new HashMap<>();
            for (String w : words) {
                if (m.containsKey(w)) {
                    valud = false;
                    break;
                } else {
                    m.put(w, true);
                }
            }
            if (valud) {
                count++;
            }
        }
        return count;
    }

    public static long part2(String pos) {
        int count = 0;
        String[] in = pos.split("\n");
        for (String i : in) {
            String[] words = i.split(" ");
            boolean valud = true;
            ArrayList<Anagram> m = new ArrayList<Anagram>();
            for (String w : words) {
                if (m.contains(new Anagram(w))) {
                    valud = false;
                    break;
                } else {
                    m.add(new Anagram(w));
                }
            }
            if (valud) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        String resource = "day4_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
