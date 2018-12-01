package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Day12 {

    public static long part1(String input) {
        String[] iii = input.split("\n");
        HashMap<Integer, ArrayList<Integer>> children = new HashMap<>();
        for (String m : iii) {
            String[] in = m.split(" <-> ");
            int[] l = Arrays.stream(in[1].split(", ")).mapToInt(Integer::parseInt).toArray();
            int root = Integer.parseInt(in[0]);
            if (!children.keySet().contains(root)) {
                children.put(root, new ArrayList<>());
            }
            for (int i = 0; i < l.length; i++) {
                if (!children.keySet().contains(l[i])) {
                    children.put(l[i], new ArrayList<>());
                }
                children.get(root).add(l[i]);
                children.get(l[i]).add(root);
            }
        }
        Stack<Integer> s = new Stack<>();
        s.add(0);
        ArrayList<Integer> res = new ArrayList<>();
        while (!s.empty()) {
            Integer element = s.pop();
            for (Integer n : children.get(element)) {
                if (!res.contains(n)) {
                    s.push(n);
                    res.add(n);
                }
            }
        }
        return res.size();
    }

    public static int part2(String input) {
        String[] iii = input.split("\n");
        ArrayList<Integer> numbers = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> children = new HashMap<>();
        for (String m : iii) {
            String[] in = m.split(" <-> ");
            int[] l = Arrays.stream(in[1].split(", ")).mapToInt(Integer::parseInt).toArray();
            int root = Integer.parseInt(in[0]);
            if (!children.keySet().contains(root)) {
                children.put(root, new ArrayList<>());
            }
            if (!numbers.contains(root)) {
                numbers.add(root);
            }
            for (int i = 0; i < l.length; i++) {
                if (!children.keySet().contains(l[i])) {
                    children.put(l[i], new ArrayList<>());
                }
                children.get(root).add(l[i]);
                children.get(l[i]).add(root);
                if (!numbers.contains(l[i])) {
                    numbers.add(l[i]);
                }
            }
        }
        ArrayList<Integer> tmp = new ArrayList<>(numbers);
        HashMap<Integer,ArrayList<Integer>> groups= new HashMap<>();
       for (int i=0;i<numbers.size();i++){
           if(tmp.contains(numbers.get(i))) {
               Integer nr = numbers.get(i);
               Stack<Integer> s = new Stack<>();
               s.add(nr);
               tmp.remove(nr);
               ArrayList<Integer> res = new ArrayList<>();
               while (!s.empty()) {
                   Integer element = s.pop();
                   for (Integer n : children.get(element)) {
                       if (!res.contains(n)) {
                           s.push(n);
                           res.add(n);
                           tmp.remove(n);
                       }
                   }
               }
               groups.put(nr, res);
           }
        }
        return groups.keySet().size();
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "aoc2018/day12_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}

