package aoc2017.day21;

import java.util.ArrayList;

public class RuleBook {
    ArrayList<Block2> rulebook2 = new ArrayList<>();
    ArrayList<Block3> rulebook3 = new ArrayList<>();

    public RuleBook(String[] in) {
        for (String s : in) {
            String[] parts = s.split(" => ");
            if (parts[0].length() == 5) {
                Block2 b2 = new Block2(parts);
                rulebook2.add(b2);
            } else {
                Block3 b3 = new Block3(parts);
                rulebook3.add(b3);
            }
        }
    }

    public char[][] findMatching(char[][] c) {
        if (c.length%2 == 0) {
            return findMatching2(c);
        } else {
            return findMatching3(c);
        }
    }

    private char[][] findMatching3(char[][] c) {
        for (Block3 b3 : rulebook3) {
            if (b3.matches(c)) {
                return b3.pattern;
            }
        }
        return null;
    }

    private char[][] findMatching2(char[][] c) {
        for (Block2 b2 : rulebook2) {
            if (b2.matches(c)) {
                return b2.pattern;
            }
        }
        return null;
    }

    public static String printBlock(char[][] yay) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < yay.length; i++) {
            for (int j = 0; j < yay[0].length; j++) {
                sb.append(yay[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
