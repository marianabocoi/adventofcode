package aoc2017;

import aoc2017.Day7;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class Day7Test {
    @Test
    public void testPart1() throws Exception {
//        assertEquals("tknk", aoc2017.Day7.part1("pbga (66)\n" +
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
        HashMap<String, Day7.Node> nodes = new HashMap<>();
        for (String e : in) {
            String[] t = e.split(" -> ");
            Day7.Node element = new Day7.Node(t[0]);
            nodes.put(element.name, element);
        }
        for (String e : in) {
            String[] t = e.split(" -> ");
            if (t.length == 2) {
                String name = t[0].split(" ")[0];
                Day7.Node base = nodes.get(name);
                for (String c : t[1].split(", ")) {
                    Day7.Node node = nodes.get(c);
                    base.children.add(node);
                    node.depth = base.depth + 1;
                }
            }
        }
        Day7.Node root = Day7.part1(nodes);
        System.out.println(Day7.part1(nodes));
        System.out.println();
        //assertEquals(1, aoc2017.Day7.part2(nodes, root));
    }
}