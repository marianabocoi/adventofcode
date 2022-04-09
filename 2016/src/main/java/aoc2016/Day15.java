package aoc2016;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day15 {
    public static void main(String[] args) {
        String input = "Disc #1 has 13 positions; at time=0, it is at position 11.\n" +
                "Disc #2 has 5 positions; at time=0, it is at position 0.\n" +
                "Disc #3 has 17 positions; at time=0, it is at position 11.\n" +
                "Disc #4 has 3 positions; at time=0, it is at position 0.\n" +
                "Disc #5 has 7 positions; at time=0, it is at position 2.\n" +
                "Disc #6 has 19 positions; at time=0, it is at position 17.";
        for (int t =0; t<100000000 ; t++) {
            if ((11 + (t + 1))%13 == 0)
                if ((0 + (t + 2))%5 == 0)
                    if ((11 + (t + 3))%17 == 0)
                        if ((0 + (t + 4))%3 == 0)
                            if ((2 + (t + 5))%7 == 0)
                                if ((17 + t + 6)%19 == 0)
                                    if ((0 + t + 7)%11 == 0){
                                        System.out.println(t);
                                        break;
                                    }
        }
//        String[] a = input.split("\n");
//        Plate[] in = new Plate[a.length];
//        int maxPosIndex = 0;
//        for (int i = 0; i < a.length; i++) {
//            in[i] = new Plate(a[i]);
//            if (in[i].positions > in[maxPosIndex].positions) maxPosIndex = i;
//        }
//        System.out.println(Arrays.toString(in));
//        int finalMaxPosIndex = maxPosIndex;
//        long[] times = IntStream.iterate(0, i -> i + 1)
//                .mapToLong(x -> in[finalMaxPosIndex].positions * x + in[finalMaxPosIndex].initialPosition)
//                .peek(System.out::println)
//                .filter(x -> isAlighned(in, x))
//                .limit(1)
//                .toArray();
//        System.out.println(Arrays.toString(times));
    }

    private static void advanceOne(Plate[] in) {
        for (int i = 0; i < in.length; i++) {
            in[i].advance();
        }
    }

    private static boolean isAlighned(Plate[] in, long time) {
        for (int i = 0; i < in.length; i++) {
            if (!in[i].isGood(time))
                return false;
        }
        return true;
    }

}

class Plate {
    int id;
    int positions;
    int time;
    int initialPosition;
    int currentPosition;

    Plate(String s) {
        String[] tmp = s.split("Disc #| has |positions; at time=|, it is at position |\\.");
        id = Integer.parseInt(tmp[1].trim());
        positions = Integer.parseInt(tmp[2].trim());
        time = Integer.parseInt(tmp[3].trim());
        currentPosition = Integer.parseInt(tmp[4].trim());
        initialPosition = Integer.parseInt(tmp[4].trim());
    }

    void advance() {
        currentPosition = (currentPosition + 1) % positions;
        time++;
    }

    boolean isGood(long x) {
        return (x + initialPosition) % positions == id;
    }

    boolean atZero() {
        return currentPosition == id;
    }

    @Override
    public String toString() {
        return id + " " + positions + " " + time + " " + currentPosition;
    }
}