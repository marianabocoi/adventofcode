import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day5 {

    public static long part1(String input) {
        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();
        int count = 0;
        int location;
        int nextLocation = 0;
        while (nextLocation < in.length && nextLocation >= 0) {
            location = nextLocation;
            nextLocation = location + in[location];
            in[location] = in[location] + 1;
            count++;
        }
        return count;
    }

    public static long part2(String input) {
        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();
        int count = 0;
        int location;
        int nextLocation = 0;
        while (nextLocation < in.length && nextLocation >= 0) {
            location = nextLocation;
            nextLocation = location + in[location];
            int increment = in[location] >= 3 ? -1 : 1;
            in[location] = in[location] + increment;
            count++;
        }
        return count;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "day5_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
