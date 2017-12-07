import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class Day7Test {
    @Test
    public void testPart1() throws Exception {
//        assertEquals("tknk", Day7.part1("pbga (66)\n" +
//                "xhth (57)\n" +
//                "ebii (61)\n" +
//                "havc (66)\n" +
//                "ktlj (57)\n" +
//                "fwft (72) -> ktlj, cntj, xhth\n" +
//                "qoyq (66)\n" +
//                "padx (45) -> pbga, havc, qoyq\n" +
//                "tknk (41) -> ugml, padx, fwft\n" +
//                "jptl (61)\n" +
//                "ugml (68) -> gyxo, ebii, jptl\n" +
//                "gyxo (61)\n" +
//                "cntj (57)"));
    }

    @Test
    public void testPart2() throws Exception {
        String input = "pbga (66)\n" +
                "xhth (57)\n" +
                "ebii (61)\n" +
                "havc (66)\n" +
                "ktlj (57)\n" +
                "fwft (72) -> ktlj, cntj, xhth\n" +
                "qoyq (66)\n" +
                "padx (45) -> pbga, havc, qoyq\n" +
                "tknk (41) -> ugml, padx, fwft\n" +
                "jptl (61)\n" +
                "ugml (68) -> gyxo, ebii, jptl\n" +
                "gyxo (61)\n" +
                "cntj (57)";
        String[] in = input.split("\n");
        HashMap<String, ArrayList<String>> tower = new HashMap<>();
        HashMap<String, Integer> weights = new HashMap<>();
        for (String e : in) {
            String[] t = e.split(" -> ");
            String[] parts = t[0].split(" ");
            String r = parts[0];
            int w = Integer.parseInt(parts[1].substring(1, parts[0].length() - 1));
            weights.put(r, w);
            ArrayList<String> childrean = new ArrayList<>();
            if (t.length == 2) {
                childrean.addAll(Arrays.asList(t[1].split(", ")));
            }
            tower.put(r, childrean);

        }
        String root = Day7.part1(tower);
        assertEquals(1, Day7.part2(in, tower, root, weights));
    }
}