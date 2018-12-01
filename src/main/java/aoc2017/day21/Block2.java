package day21;

public class Block2 {
    int nrDots;
    int nrHashes;
    char[][] m;
    char[][] pattern;

    public Block2(String[] parts) {
        nrDots = countDots(parts[0]);
        nrHashes = countHashes(parts[0]);
        String[] p = parts[0].split("/");
        m = new char[p.length][];
        for (int i = 0; i < p.length; i++) {
            m[i] = p[i].toCharArray();
        }
        p = parts[1].split("/");
        pattern = new char[p.length][];
        for (int i = 0; i < p.length; i++) {
            pattern[i] = p[i].toCharArray();
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
        if (dots != nrDots || hashes != nrHashes) {
            return false;
        }
        if (m[0][0] == p[0][0] && m[1][0] == p[1][0] && m[0][1] == p[0][1] && m[1][1] == p[1][1]) {
            return true;
        }
        if (m[0][0] == p[1][0] && m[1][0] == p[0][0] && m[0][1] == p[1][1] && m[1][1] == p[0][1]) {
            return true;
        }

        if (m[0][0] == p[0][1] && m[1][0] == p[1][1] && m[0][1] == p[0][0] && m[1][1] == p[1][0]) {
            return true;
        }
        if (m[0][0] == p[1][1] && m[1][0] == p[0][0] && m[0][1] == p[1][0] && m[1][1] == p[0][1]) {
            return true;
        }
        if (m[0][0] == p[1][0] && m[1][0] == p[1][1] && m[0][1] == p[0][0] && m[1][1] == p[0][1]) {
            return true;
        }
        if (m[0][0] == p[1][1] && m[1][0] == p[1][0] && m[0][1] == p[0][1] && m[1][1] == p[0][0]) {
            return true;
        }
        if (m[0][0] == p[0][0] && m[1][0] == p[0][1] && m[0][1] == p[1][0] && m[1][1] == p[1][1]) {
            return true;
        }
        if (m[0][0] == p[0][1] && m[1][0] == p[0][0] && m[0][1] == p[1][1] && m[1][1] == p[1][0]) {
            return true;
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
