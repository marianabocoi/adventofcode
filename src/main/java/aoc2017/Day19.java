package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day19 {
    public static String part1(String[] in) {
        StringBuilder l = new StringBuilder();
        Position start = new Position(in[0].indexOf("|"), 0, '|', 0, 1);
        Position current = start;
        while (current != null) {
            if (current.letter != null) {
                l.append(current.letter);
            }
            current = current.getNext(in);
        }
        return l.toString();
    }

    public static long part2(String[] in) {

        Position start = new Position(in[0].indexOf("|"), 0, '|', 0, 1);
        Position current = start;
        long count = 0;
        while (current != null) {
//            if (current.wasTurn) {
//                count++;
//            }
            current = current.getNext(in);
            count++;
        }
        return count;
    }


    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "aoc2018/day19_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        String[] in = input.split("\n");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

//        System.out.println(part1(in));
        System.out.println(part2(in));
    }

    static class Position {
        int row;
        int col;
        char val;
        int drow;
        int dcol;
        Position prev;
        Position next;
        String letter;
        boolean wasTurn;

        Position(int row, int col, char val, int drow, int dcol) {
            this.row = row;
            this.col = col;
            this.val = val;
            this.drow = drow;
            this.dcol = dcol;
            wasTurn = false;
        }

        Position getNext(String[] maze) {
            int nrow = row + drow;
            int ncol = col + dcol;
            char nextc = getAChar(maze, nrow, ncol);
            int ndrow = drow;
            int ndcol = dcol;
            String l = null;
            boolean t = false;
            if (val == '|' && nextc == '-') {
                nextc = '|';
            } else if (val == '-' && nextc == '|') {
                nextc = '-';
            } else if (nextc == '+') {
                nextc = getReverse(val);
                t=true;
                if (val == '|') {
                    ndcol = 0;
                    if (nrow + 1 < maze[ncol].length()) {
                        char tmp = getAChar(maze, nrow + 1, ncol);
                        if (tmp != '|' && tmp != ' ') {
                            ndrow = 1;
                        }
                    }
                    if (nrow - 1 >= 0) {
                        char tmp = getAChar(maze, nrow - 1, ncol);
                        if (tmp != '|' && tmp != ' ') {
                            ndrow = -1;
                        }
                    }
                } else {
                    ndrow = 0;
                    if (ncol + 1 < maze.length && nrow < maze[ncol + 1].length()) {
                        char tmp = getAChar(maze, nrow, ncol + 1);
                        if (tmp != '-' && tmp != ' ') {
                            ndcol = 1;
                        }
                    }
                    if (ncol - 1 >= 0 && nrow < maze[ncol - 1].length()) {
                        char tmp = getAChar(maze, nrow, ncol - 1);
                        if (tmp != '-' && tmp != ' ') {
                            ndcol = -1;
                        }
                    }
                }
            } else if (nextc != '-' && nextc != '|' && nextc != ' ') {
                l = String.valueOf(nextc);
                nextc = val;
            } else if (nextc == ' ') {
                return null;
            }
            Position position = new Position(nrow, ncol, nextc, ndrow, ndcol);
            position.letter = l;
            position.prev = this;
            this.next = position;
            position.wasTurn = t;
            return position;

        }

        private char getAChar(String[] maze, int row, int col) {
            return maze[col].charAt(row);
        }

        private char getReverse(char val) {
            if (val == '|') {
                return '-';
            } else {
                return '|';
            }
        }

    }
}

