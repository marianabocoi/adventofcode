import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 {


    public static String part1(HashMap<String, ArrayList<String>> tower) {

        String root = "";
        List<ArrayList<String>> values = tower.values().stream().filter(x -> x.size() > 0).collect(Collectors.toList());
        for (String prog : tower.keySet()) {
            boolean isRoot = true;
            for (ArrayList<String> v : values) {
                isRoot = isRoot && (!v.contains(prog));
            }
            if (isRoot) {
                return prog;
            }
        }
        return root;
    }

    public static long part2(String[] input, HashMap<String, ArrayList<String>> tower, String root, HashMap<String, Integer> weights) {
        HashMap<Integer, ArrayList<String>> height = new HashMap<>();
        ArrayList<String> x = new ArrayList<>();
        x.add(root);
        height.put(0, x);
        calcWeightsAndHeights(root, tower, weights, height, 0);
        ArrayList<String>[] heights = new ArrayList[height.size()];
        for (Map.Entry<Integer, ArrayList<String>> mm : height.entrySet()) {
            heights[mm.getKey()] = mm.getValue();
        }
        String unProgr = "";
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i].size() > 0) {
                int w = weights.get(heights[i].get(0));
                int uw = 0;
                boolean unbalanced = false;
                int count = 0;
                for (String y : heights[i]) {
                    if (weights.get(y) != w) {
                        unbalanced = true;
                        uw = weights.get(y);
                        count++;
                        unProgr  = y;
                    }
                }
                if(unbalanced){
                    if(count > 1){
                        unProgr = heights[i].get(0);
                        break;
                    }
                }

            }

        }
        return 1;
    }

    private static void calcWeightsAndHeights(String element, HashMap<String, ArrayList<String>> tower, HashMap<String, Integer> weights, HashMap<Integer, ArrayList<String>> height, int depth) {
        if (height.containsKey(depth)) {
            height.get(depth).addAll(tower.get(element));
        } else {
            height.put(depth, tower.get(element));
        }
        for (String c : tower.get(element)) {
            calcWeightsAndHeights(c, tower, weights, height, depth + 1);
        }
        int totalWeight = weights.get(element);
        for (String c : tower.get(element)) {
            totalWeight += weights.get(c);
        }
        weights.put(element, totalWeight);
    }

    public static void main(String[] args) throws URISyntaxException, IOException {

        String resource = "day7_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        String[] in = input.split("\n");
        HashMap<String, ArrayList<String>> tower = new HashMap<>();
        HashMap<String, Integer> weights = new HashMap<>();
        for (String e : in) {
            String[] t = e.split(" -> ");
            String[] parts = t[0].split(" ");
            String r = parts[0];
            int w = Integer.parseInt(parts[0].substring(1, parts[0].length() - 1));
            weights.put(r, w);
            ArrayList<String> childrean = new ArrayList<>();
            if (t.length == 2) {
                childrean.addAll(Arrays.asList(t[1].split(", ")));
            }
            tower.put(r, childrean);

        }
        String root = part1(tower);
        System.out.println();
        System.out.println(part2(in, tower, root, weights));
    }
}
