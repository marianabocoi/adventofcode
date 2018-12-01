package aoc2017.day21;

public class Block3 {
    int nrDots;
    int nrHashes;
    char center;
    char[][] m;
    char[][] pattern;

    public Block3(String[] parts) {
        nrDots = countDots(parts[0]);
        nrHashes = countHashes(parts[0]);
        center = parts[0].charAt(5);
        String[] pp = parts[0].split("/");
        m = new char[pp.length][];
        for (int i = 0; i < pp.length; i++) {
            m[i] = pp[i].toCharArray();
        }
        pp = parts[1].split("/");
        pattern = new char[pp.length][];
        for (int i = 0; i < pp.length; i++) {
            pattern[i] = pp[i].toCharArray();
        }
    }

    public boolean matches(char[][] p) {
        int dots = 0;
        int hashes = 0;
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                if (p[i][j] == '.') {
                    dots++;
                } else {
                    hashes++;
                }
            }
        }
        if (dots != nrDots || hashes != nrHashes || m[1][1] != center) {
            return false;
        }
        if (p[1][0] == m[1][0] && p[1][2] == m[1][2]) {
            if (p[0][0] == m[0][0] && p[0][1] == m[0][1] && p[0][2] == m[0][2] &&
                    p[2][0] == m[2][0] && p[2][1] == m[2][1] && p[2][2] == m[2][2]) {
                return true;
            }

            if (p[0][0] == m[2][0] && p[0][1] == m[2][1] && p[0][2] == m[2][2] &&
                    p[2][0] == m[0][0] && p[2][1] == m[0][1] && p[2][2] == m[0][2]) {
                return true;
            }
        }

        if (p[1][0] == m[1][2] && p[1][2] == m[1][0]) {
            if (p[0][0] == m[0][2] && p[0][1] == m[0][1] && p[0][2] == m[0][0] &&
                    p[2][0] == m[2][2] && p[2][1] == m[2][1] && p[2][2] == m[2][0]) {
                return true;
            }

            if (p[0][0] == m[2][2] && p[0][1] == m[2][1] && p[0][2] == m[2][0] &&
                    p[2][2] == m[0][0] && p[2][1] == m[0][1] && p[2][2] == m[0][0]) {
                return true;
            }
        }

        if (p[1][0] == m[2][1] && p[1][2] == m[0][1]) {
            if (p[0][0] == m[2][0] && p[0][1] == m[1][0] && p[0][2] == m[0][0] &&
                    p[2][0] == m[2][2] && p[2][1] == m[1][2] && p[2][2] == m[0][2]) {
                return true;
            }

            if (p[0][0] == m[2][2] && p[0][1] == m[1][2] && p[0][2] == m[0][2] &&
                    p[2][0] == m[2][0] && p[2][1] == m[1][0] && p[2][2] == m[0][0]) {
                return true;
            }
        }


        if (p[1][0] == m[0][1] && p[1][2] == m[2][1]) {
            if (p[0][0] == m[0][0] && p[0][1] == m[1][0] && p[0][2] == m[2][0] &&
                    p[2][0] == m[0][2] && p[2][1] == m[1][2] && p[2][2] == m[2][1]) {
                return true;
            }

            if (p[0][0] == m[0][2] && p[0][1] == m[1][2] && p[0][2] == m[2][1] &&
                    p[2][0] == m[0][0] && p[2][1] == m[1][0] && p[2][2] == m[2][0]) {
                return true;
            }
        }

        return false;
    }

    private int countChar(String parts, String s) {
        return parts.length() - parts.replace(s, "").length();
    }

    private int countHashes(String parts) {
        return countChar(parts, "#");
    }

    private int countDots(String p) {
        return countChar(p, ".");
    }

}
/*
. # .
. # .
. . #

. . #
# # .
. . .

#.#./..#./##../..#.

found: #.##/####/#.##/#..#
from .##/##./...

should find: .###/#.#./...#/##..
from .#./#../...

. . .
. # #
# # .

# . # # # # . . # # . .
. # # # # . . . # # . .

# # # # . # # . . . # .
# . # . . # # . . . # .

# . # # . . # . # # # #
. . . # . . . . # # # #

# . . # . # . . # # . #
# # . . # . # . # # . #

# # . . # # . . . . . .
# # . . # # . . # # # #

. . # . . . # . # . . .
. . # . . . # . . . . #

# # # # # # # # . . # .
# # # # # # # # . . . #

# # . # # # . # . . # #
# # . # # # . # # . # #

# # . . # # . . # # . .
# # . . # # . . # # . .

. . # . . . # . . . # .
. . # . . . # . . . # .

# # # # # # # # # # # #
# # # # # # # # # # # #

# # . # # # . # # # . #
# # . # # # . # # # . #


. # # # # . . . # # . .
# . # . . # # . . . # .
. . . # . . . . # # # #
# # . . # . # . # # . #
# # . . # # . . # # # #
. . # . . . # . . . . #
# # # # # # # # . . . #
# # . # # # . # # . # #
# # . . # # . . # # . .
. . # . . . # . . . # .
# # # # # # # # # # # #
# # . # # # . # # # . #

 */