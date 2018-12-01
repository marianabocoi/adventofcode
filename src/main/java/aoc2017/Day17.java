import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class Day17 {
    public static long part1(long step) {
        Node c = new Node(0);
        c.setPrev(c);
        c.setNext(c);
        Node zero = c;
        for (int i = 1; i < 50000001; i++) {
            for (int j = 0; j < step; j++) {
                c = c.getNext();
            }
            Node n = new Node(i);
            c.getNext().setPrev(n);
            n.setNext(c.getNext());
            n.setPrev(c);
            c.setNext(n);
            c = n;
            if (i % 10000 == 0) System.out.println(zero);
        }
        System.out.println(0);
        return 1;
    }

    public static long part2(String[] in) {
        return 2;
    }


    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "day16_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        String[] in = input.split(",");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

        System.out.println(part1(337));
    }

    static class Node {
        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            int p = prev == null ? -1 : prev.val;
            int n = next == null ? -1 : next.val;
            return "Node{" +
                    "val=" + val +
                    ", prev=" + p +
                    ", next=" + n + "}"
                    ;
        }

        int val;

        public int getVal() {
            return val;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        Node prev;
        Node next;
    }

}

