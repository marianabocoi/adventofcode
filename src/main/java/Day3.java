import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Day3 {
    public static int part1(int pos) {
        int corner = corner(pos); // n-1
        System.out.println(corner);
        return 0;
    }

    static int corner(int pos) {
        int count = 1;
        while (pos > Math.pow(count, 2)) {
            count += 2;
        }
        return count;
    }

    private static Sqware lower_right(int pos) {
        int size = 1;
        int width = 1;
        while (size < pos) {
            width += 2;
            size += width * 4 - 4;
        }
        return new Sqware(width);
    }//551 // 550 // 552

    public static int part2(int pos) {
        return pos;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
//        int input = 325489;
//
//        System.out.println(part1(input));
//        System.out.println(part2(input));
        Sqware s = new Sqware(9);
//        Files.write(Paths.get("./fileName.txt"), s.toString().getBytes());
        System.out.println(s.toString());
    }
}

class Sqware {
    int width;

    Sqware(int width) {

        this.width = width;
    }

    public String toString() {

        int[][] s = new int[(int) width][(int) width];
        s[width / 2][width / 2] = 1;
        for (int j = width / 2 - 1; j >= 0; j--) {
//            System.out.println(getStringBuilder(s));
            int i1 = j;
            int j1 = width - 1 - j;
            for (int i = j1 - 1; i >= i1; i--) {
                s[i][j1] = sumNeighBours(s, i, j1);
            }
            for (int i = j1 - 1; i >= i1; i--) {
                s[i1][i] = sumNeighBours(s, i1, i);
            }
            for (int i = i1 + 1; i <= j1; i++) {
                s[i][i1] = sumNeighBours(s, i, i1);
            }
            for (int i = i1 + 1; i <= j1; i++) {
                s[j1][i] = sumNeighBours(s, j1, i);
            }
        }
        String sb = getStringBuilder(s);
        return sb;
    }

    private int sumNeighBours(int[][] s, int x, int y) {
        int top = x == 0 ? 0 : s[x - 1][y];
        int bottom = x == s.length - 1 ? 0 : s[x + 1][y];
        int left = y == 0 ? 0 : s[x][y - 1];
        int right = y == s[0].length - 1 ? 0 : s[x][y + 1];
        int topright = x == 0 || y == s[0].length - 1 ? 0 : s[x - 1][y + 1];
        int topleft = x == 0 || y == 0 ? 0 : s[x - 1][y - 1];
        int bottomright = x == s.length - 1 || y == s[0].length - 1 ? 0 : s[x + 1][y + 1];
        int bottomleft = x == s.length - 1 || y == 0 ? 0 : s[x + 1][y - 1];
        int nr = top + bottom + left + right + topleft + topright + bottomleft + bottomright;
        if (nr > 325489) {
            System.out.println(nr);
        }
        return nr;//330785
    }

    private String getStringBuilder(int[][] s) {
        StringBuilder sb = new StringBuilder(width * width);
        for (int[] m : s) {
            sb.append(Arrays.toString(m) + "\n");
        }
        return sb.toString();
    }
}
