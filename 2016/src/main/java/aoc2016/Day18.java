package aoc2016;

public class Day18 {
    public static void main(String[] args) {
        String input = "^^^^......^...^..^....^^^.^^^.^.^^^^^^..^...^^...^^^.^^....^..^^^.^.^^...^.^...^^.^^^.^^^^.^^.^..^.^";
//        String input = ".^^.^.^^^^";
        StringBuilder nextRow = new StringBuilder(input);
        String row = input;
        long count = 0;
        for (int i = 1; i <= 400000; i++) {
            row = nextRow.toString();
            nextRow = calculateRow(row);
            System.out.println(i + " " + row);
            count += row.replace("^", "").length();
        }
        System.out.println(count);
    }

    private static StringBuilder calculateRow(String row) {
        StringBuilder newRow = new StringBuilder(row.length());
        newRow.append(applyRules('.' + row.substring(0, 2)));
        for (int i = 1; i < row.length() - 1; i++) {
            newRow.append(applyRules(row.substring(i - 1, i + 2)));
        }
        newRow.append(applyRules(row.substring(row.length() - 2) + '.'));
        return newRow;
    }

    private static char applyRules(String s) {
        /*
         * Its left and center tiles are traps, but its right tile is not.
         * Its center and right tiles are traps, but its left tile is not.
         * Only its left tile is a trap.
         * Only its right tile is a trap.
         */
        return s.matches("\\^\\^\\.|\\.\\^\\^|\\.\\.\\^|\\^\\.\\.") ? '^' : '.';
    }

}


/*
.^^.^.^^^^
^^^...^..^
^.^^.^.^^.
..^^...^^^
.^^^^.^^.^
^^..^.^^..
^^^^..^^^.
^..^^^^.^^
.^^^..^.^^
^^.^^^..^^
1 .^^.^.^^^^
2 ^^^...^..^
3 ^.^^.^.^^.
4 ..^^...^^^
5 .^^^^.^^.^
6 ^^..^.^^..
7 ^^^^..^^^.
8 ^..^^^^.^^
9 .^^^..^.^^
10 ^^.^^^..^^


 */