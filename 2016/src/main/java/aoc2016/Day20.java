package aoc2016;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day20 {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "in20.txt";
        Set<Range> list = new TreeSet<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream
                    .map(Range::new)
                    .collect(Collectors.toSet());

        } catch (IOException e) {
            e.printStackTrace();
        }
        TreeSet<Range> theSet = new TreeSet<>(list);
        Iterator<Range> it = theSet.iterator();
        long n = 0;
        Range range;
        while (it.hasNext()) {
            range = it.next();
            if (range.inRange(n)) {
                n = range.upper + 1;
            }
            System.out.println(range);
        }
        System.out.println(n);
    }
}

class Range implements Comparable<Range> {
    long lower;
    long upper;

    Range(String s) {
        String[] split = s.split("-");
        lower = Long.parseLong(split[0].trim());
        upper = Long.parseLong(split[1].trim());
    }

    @Override
    public int compareTo(Range r) {
        return upper == r.upper ? 0 : (upper - r.upper < 0 ? -1 : 1);
    }

    boolean inRange(long n) {
        return n <= upper && n >= lower;
    }

    public String toString() {
        return lower + "-" + upper;
    }
}
