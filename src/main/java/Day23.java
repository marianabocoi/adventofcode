import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Day23 {

    public static long part1(String[] in) {
        return 1;
    }

    public static long part2(String[] in) {
        return 2;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "day23_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        String[] in = input.split("\n");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

        System.out.println(part1(in));
        System.out.println(part2(in));
    }
}

