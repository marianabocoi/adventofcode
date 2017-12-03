import javax.swing.SwingUtilities;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        Sqware s = new Sqware(5);
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
        int count = 2;
        s[width/2][width/2] = 1;
        for(int j = width/2 - 1; j >= 0; j--) {
            System.out.println(getStringBuilder(s));
            int i1 = j;
            int j1 = width - 1 - j;
            for (int i = j1 - 1; i >= i1; i--) {
                s[i][j1] = count;
                count++;
            }
            for (int i = j1 - 1; i >= i1; i--) {
                s[i1][i] = count;
                count++;
            }
            for (int i = i1 + 1; i <= j1; i++) {
                s[i][i1] = count;
                count++;
            }
            for (int i = i1 + 1; i <= j1; i++) {
                s[j1][i] = count;
                count++;
            }
        }
        String sb = getStringBuilder(s);
        return sb;
    }

    private String getStringBuilder(int[][] s) {
        StringBuilder sb = new StringBuilder( width * width);
        for (int[] m : s) {
            sb.append(Arrays.toString(m) + "\n");
        }
        return sb.toString();
    }
}
