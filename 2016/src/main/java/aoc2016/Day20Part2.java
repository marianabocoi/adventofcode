package aoc2016;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day20Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "in20.txt";
        Set<Rang> list = new TreeSet<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream
                    .map(Rang::new)
                    .collect(Collectors.toSet());

        } catch (IOException e) {
            e.printStackTrace();
        }
        PriorityQueue<Rang> theSet = new PriorityQueue<>(list);

        Stack<Rang> queue = new Stack<>();
        Rang range = theSet.poll();
        while (theSet.size() != 0) {
            if (queue.empty()) {
                queue.push(range);
                range = theSet.poll();
                System.out.println("was empty \t" + queue.peek() + " - " + queue.size());
            } else {
                if (queue.peek().overlapping(range)) {
                    System.out.println("merging \t" + range + " with " + queue.peek() + " - " + queue.size());
                    range = range.merge(queue.pop());
//                    System.out.println("merged \t" + range + " - " + queue.size());
                } else {
                    queue.push(range);
                    range = theSet.poll();
                    System.out.println("pushed \t" + queue.peek() + " - " + queue.size());
                }
            }

        }
        long sum = 4294967296L;
        while (!queue.empty()) {
            sum -= queue.pop().getNumber();
        }
        System.out.println(sum);
    }
}

class Rang implements Comparable<Rang> {
    long lower;
    long upper;

    Rang(String s) {
        String[] split = s.split("-");
        lower = Long.parseLong(split[0].trim());
        upper = Long.parseLong(split[1].trim());
    }

    Rang(long l, long u) {
        lower = l;
        upper = u;
    }

    @Override
    public int compareTo(Rang r) {
        return lower == r.lower ? 0 : (lower - r.lower < 0 ? -1 : 1);
    }

    long getNumber() {
        return upper - lower +1 ;
    }

    public String toString() {
        return lower + "-" + upper ;
    }

    boolean overlapping(Rang r) {
        return r.lower <= upper + 1 || r.upper + 1 <= lower;
    }

    Rang merge(Rang r) {
        return new Rang(Math.min(r.lower, lower), Math.max(r.upper, upper));
    }
    //4294967295
}
