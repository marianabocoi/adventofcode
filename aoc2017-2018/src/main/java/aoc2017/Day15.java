package aoc2017;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day15 {
    static long genA = 16807;
    static long genB = 48271;
    static long ast = 516;
    static long bst = 190;
    static long divby = 2147483647;
    static long multA = 4;
    static long multB = 8;


    public static long part1(long ast, long bst) {
        long currA = ast;
        long currB = bst;
        long count = 0;
        for (long i = 0; i < 5000000; i++) {
            if (getlast16bits(currA).equals(getlast16bits(currB))) {
                count++;
            }
            currA = genNext(currA, genA, multA);
            currB = genNext(currB, genB, multB);
        }
        return count;
    }

    public static long part2(String input) {

        return 2;
    }

    static long genNext(long nr, long coef, long mult) {
        long number = (nr * coef) % divby;
        while (number % mult != 0) {
            number = (number * coef) % divby;
        }
        return number;
    }

    public static String getlast16bits(long nr) {
        String m = Long.toBinaryString(nr);
        StringBuilder sb = new StringBuilder();
        if (m.length() < 32) {
            for (long i = 0; i < 32 - m.length(); i++) {
                sb.append('0');
            }
        }
        sb.append(m);
        m = sb.toString();
        return m.substring(m.length() - 16, m.length());
    }

    public static void main(String[] args) throws URISyntaxException, IOException {

        System.out.println(part1(ast, bst));// 2441 bad
        System.out.println(part2(""));

    }
}