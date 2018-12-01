import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Day10 {

    public static long part1(String input, int nr) {
        int[] lengths = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
        int current_position = 0;
        int skip_size = 0;
        int[] numbers = IntStream.range(0, nr).toArray();

        //Reverse the order by that length, starting with the element at the current position
        for (int length : lengths) {
            if (length < nr) {
                int[] tmp = numbers.clone();
                for (int i = current_position; i < current_position + length; i++) {
                    int idx1 = i % nr;
                    int idx2 = (current_position + length - 1 - i + current_position) % nr;
                    numbers[idx1] = tmp[idx2];
                }
                current_position += (length + skip_size) % nr;
                skip_size += 1;
            }
        }
        return numbers[0] * numbers[1];
    }

    //[2, 1, 0, 3, 4]
    public static String part2(String input, int nr) {
        int[] in = input.chars().toArray();
        int current_position = 0;
        int skip_size = 0;
        int[] numbers = IntStream.range(0, nr).toArray();
        int[] lengths = new int[in.length + 5];
        for (int i = 0; i < in.length; i++) {
            lengths[i] = in[i];
        }
        lengths[in.length] = 17;
        lengths[in.length + 1] = 31;
        lengths[in.length + 2] = 73;
        lengths[in.length + 3] = 47;
        lengths[in.length + 4] = 23;
        //Reverse the order by that length, starting with the element at the current position
        for (int j = 0; j < 64; j++) {
            for (int length : lengths) {
                if (length < nr) {
                    int[] tmp = numbers.clone();
                    for (int i = current_position; i < current_position + length; i++) {
                        int idx1 = i % nr;
                        int idx2 = (current_position + length - 1 - i + current_position) % nr;
                        numbers[idx1] = tmp[idx2];
                    }
                    current_position += (length + skip_size) % nr;
                    skip_size += 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int from = i * 16;
            int to = (i + 1) * 16;
            int h = hash(Arrays.copyOfRange(numbers, from, to));
            sb.append(gethexHash(h));
        }
        return sb.toString();
    }

    private static String gethexHash(int h) {
        String s = Integer.toHexString(h);
        return s.length() == 1 ? "0" + s : s;
    }

    static int hash(int[] x) {
        return IntStream.of(x).reduce((m, n) -> m ^ n).getAsInt();
    }


    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "day10_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        System.out.println(part1(input, 256));
        System.out.println(part2(input, 256));
    }
}

