import day21.RuleBook;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class Day21 {
    static char[][] pattern;

    static {
        pattern = new char[3][];
        String[] p = (".#.\n" +
                "..#\n" +
                "###").split("\n");

//          String[] p = (".#.\n.#.\n..#").split("\n");
        for (int i = 0; i < p.length; i++) {
            pattern[i] = p[i].toCharArray();
        }
    }

    public static long part1(String[] in) {
        RuleBook rb = new RuleBook(in);
        char[][] result = pattern;
        for (int i = 0; i < 18; i++) {
            result = increase(result, rb);
            System.out.println(RuleBook.printBlock(result) +
                    "\nsize: " + result.length + "x" + result[0].length +
                    "\ncount: " + countOn(result) + "\n");
        }

        int i = countOn(result);
        return i;
    }//104 too low // 292 wrong // 160 right for someone else
    /*

Iter 1
# # # . 
. . # # 
. # . . 
. # # .

###./..##/.#../.##.

Iter 2
 ..#  .#.
 ...  .#.
 ###  ..#

 ..#  .##
 ...  .##
 ###  #..

. . # . # .
. . . . # .
# # # . . #
. . # . # #
. . . . # #
# # # # . .

.#./.#./..#
..#/.#./.#.


Iter 3

# # #
. . .
# . .

# # . .
. . # .
# # # #
# # . #



     */

    private static int countOn(char[][] result) {
        int count = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if (result[i][j] == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private static char[][] increase(int nr, char[][] pattern, RuleBook rb) {
        return null;
    }

    private static char[][] increase(char[][] pattern, RuleBook rb) {
        int chunks = 3;
        int transformTo = 4;
        if (pattern.length % 2 == 0) {
            chunks = 2;
            transformTo = 3;
        }
        int l = pattern.length / chunks;

        char[][] newPattern = new char[l * transformTo][];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                char[][] m = new char[chunks][];
                for (int x = 0; x < chunks; x++) {
                    int i1 = i * chunks + x;
                    int from = j * chunks;
                    int to = j * chunks + chunks;
                    m[x] = Arrays.copyOfRange(pattern[i1], from, to);
                }
                char[][] newm = rb.findMatching(m);
                newm = rb.findMatching(m);
                for (int k = 0; k < newm.length; k++) {
                    int x = i * transformTo + k;
                    if (newPattern[x] == null) {
                        newPattern[x] = new char[l * transformTo];
                    }
                    for (int n = 0; n < newm[0].length; n++) {
                        int y = j * transformTo + n;
                        newPattern[x][y] = newm[k][n];
                    }
                }
//                System.out.println("yay");
            }
        }

        return newPattern;
    }

    public static long part2(String[] in) {

        return 1;
    }


    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "day21_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
//        String input = "../.# => ##./#../...\n" +
//                ".#./..#/### => #..#/..../..../#..#";

        String[] in = input.split("\n");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

        Instant start = Instant.now();
        System.out.println(part1(in));
        Instant end = Instant.now();
        System.out.println("time= " + Duration.between(start, end));
    }
}

