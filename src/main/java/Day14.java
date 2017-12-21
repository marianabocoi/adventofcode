
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;

public class Day14 {

    public static long part1(String input) {
        int occurrences = 0;
        for (int i = 0; i < 128; i++) {
            String s = Day10.part2(input + "-" + i, 256);
            StringBuilder sb = new StringBuilder();
            System.out.println(s);
            for (int j = 0; j < s.length(); j++) {
                sb.append(hexToBin(String.valueOf(s.charAt(j))));
            }
            s = sb.toString();
//            System.out.println(s);
            int count = s.length() - s.replace("1", "").length();
            occurrences += count;
        }
        return occurrences;
    }

    public static int part2(String input) {
        int count = 0;
        char[][] grid = new char[128][];
        for (int i = 0; i < 128; i++) {
            String s = Day10.part2(input + "-" + i, 256);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < s.length(); j++) {
                sb.append(hexToBin(String.valueOf(s.charAt(j))));
            }
            s = sb.toString();
            s = s.replace("1", "#");
            s = s.replace("0", ".");
            grid[i]=s.toCharArray();
            System.out.println(s);
        }
        String[][] g = new String[128][];
        for (int i = 0; i <grid.length; i++) {
            g[i] = new String[128];
            for (int j = 0; j <grid[0].length ; j++) {
                g[i][j] = String.valueOf(grid[i][j]);
            }
        }
        for (int i = 0; i <grid.length; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                if(grid[i][j]=='#'){
                    recursiveset(g, count, i,j);
                    count++;
                }
            }
        }
        showGrid(g);

        return count;
    }

    private static void showGrid(String[][] grid) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i <grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private static void recursiveset(String[][] grid, int count, int i, int j) {
        try {
            if ("#".equals(grid[i][j])){
                grid[i][j] = String.valueOf(count);
                //showGrid(grid);
                if(validpoint(i+1)) recursiveset(grid, count, i+1,j);
                if(validpoint(j+1))recursiveset(grid, count, i,j+1);
                if(validpoint(i-1))recursiveset(grid, count, i-1,j);
                if(validpoint(j-1))recursiveset(grid, count, i,j-1);
            }
        }catch (Exception e){

        }
    }

    private static boolean validpoint(int i) {
        return i>=0 && i<128;
    }

    static String hexToBin(String s) {
        StringBuilder sb = new StringBuilder();
        String m = new BigInteger(s, 16).toString(2);
        if (m.length() < 4) {
            for (int i = 0; i < 4 - m.length(); i++) {
                sb.append('0');
            }
        }
        sb.append(m);
        String s1 = sb.toString();
        return s1;
    }


    public static void main(String[] args) throws URISyntaxException, IOException {
        gentest();
        String input = "hxtvlmkl";
        System.out.println(part1(input));
        System.out.println(part2(input));// w 8279 - too high 8012 - too low // 8199 wrong

    }

    public static void gentest() {
        System.out.println(Integer.toHexString(Integer.parseInt("1101", 2)) + Integer.toHexString(Integer.parseInt("0100", 2)));
        System.out.println(Integer.toHexString(Integer.parseInt("0101", 2)) + Integer.toHexString(Integer.parseInt("0101", 2)));
        System.out.println(Integer.toHexString(Integer.parseInt("0000", 2)) + Integer.toHexString(Integer.parseInt("1010", 2)));
        System.out.println(Integer.toHexString(Integer.parseInt("1010", 2)) + Integer.toHexString(Integer.parseInt("1101", 2)));
        System.out.println(Integer.toHexString(Integer.parseInt("0110", 2)) + Integer.toHexString(Integer.parseInt("1000", 2)));
        System.out.println(Integer.toHexString(Integer.parseInt("1100", 2)) + Integer.toHexString(Integer.parseInt("1001", 2)));
        System.out.println(Integer.toHexString(Integer.parseInt("0100", 2)) + Integer.toHexString(Integer.parseInt("0100", 2)));
        System.out.println(Integer.toHexString(Integer.parseInt("1101", 2)) + Integer.toHexString(Integer.parseInt("0110", 2)));
    }

    public static String herify(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
//            System.out.println(s.charAt(i));
            String str = hexToBin(String.valueOf(s.charAt(i)));
//            System.out.println(str);
            sb.append(str);
        }
        return sb.toString();
    }
}

