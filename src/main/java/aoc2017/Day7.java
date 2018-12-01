package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Day7 {


    public static Node part1(HashMap<String, Node> nodes) {
        for (Node n : nodes.values()) {
            if (n.depth == 0) {
                return n;
            }
        }
        return null;
    }


    public static Integer part2(HashMap<String, Node> nodes, Node root) {
        calcWeightsAndHeights(root);
        return findUnbalancedParent(root);
    }

    private static Integer findUnbalancedParent(Node node) {
        Node unbalanced = null;
        Integer ub = null;
        for (Node c : node.children) {
            ub = findUnbalancedParent(c);
            if (ub != null) {
                return ub;
            }
        }
        if (node.children.size() > 0) {
            int w = node.children.get(0).coumpoundWeight;
            int ow = 0;
            Node u = node.children.get(0);
            boolean found = false;
            int count = 0;
            for (Node c : node.children) {
                if (c.coumpoundWeight != w) {
                    found = true;
                    unbalanced = c;
                    count++;
                    ow = c.coumpoundWeight;
                }
            }
            if (found) {
                if (count > 1) {

                    return u.weight + ow - w;
                } else {
                    return unbalanced.weight + w - ow;
                }
            }
        }
        return null;
    }

    private static void calcWeightsAndHeights(Node node) {
        int weight = node.weight;
        for (Node c : node.children) {
            c.depth = node.depth + 1;
            calcWeightsAndHeights(c);
            weight += c.coumpoundWeight;
        }
        node.coumpoundWeight = weight;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {

        String resource = "aoc2018/day7_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        String[] in = input.split("\n");
        HashMap<String, Node> nodes = new HashMap<>();
        for (String e : in) {
            String[] t = e.split(" -> ");
            Node element = new Node(t[0]);
            nodes.put(element.name, element);
        }
        for (String e : in) {
            String[] t = e.split(" -> ");
            if (t.length == 2) {
                String name = t[0].split(" ")[0];
                Node base = nodes.get(name);
                for (String c : t[1].split(", ")) {
                    Node node = nodes.get(c);
                    base.children.add(node);
                    node.depth = base.depth + 1;
                }
            }
        }
        Node root = part1(nodes);
        System.out.println(part1(nodes));
        System.out.println();
        System.out.println(part2(nodes, root));
    }

    static class Node {
        final String name;
        int weight;
        int coumpoundWeight;
        int depth;
        ArrayList<Node> children;

        Node(String e) {
            String[] t = e.split(" -> ");
            String[] parts = t[0].split(" ");
            name = parts[0];
            weight = Integer.parseInt(parts[1].substring(1, parts[1].length() - 1));
            children = new ArrayList<>();
        }
    }
}

