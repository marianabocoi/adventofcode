package aoc2016;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class Day17 {
    static String getPath(String seed) {
        Node[][] room = new Node[4][4];
        room[0][0] = new Node(seed, 0, 0);
        PriorityQueue<Node> stack = new PriorityQueue<>(Comparator.comparingInt(Node::getWeight));
        stack.add(room[0][0]);
        while (!stack.isEmpty() && stack.peek().path.length() < 50) {
            Node n = stack.remove();
            System.out.println(n.path + " (" + n.x + "," + n.y + ")");
            if (room[n.x][n.y] == null || room[n.x][n.y].getWeight() > n.getWeight()) {
                room[n.x][n.y] = n;
            }
            stack.addAll(n.getDirections());
        }
        System.out.println("yay" + room[3][3].path);
        return room[3][3].path.replace(seed, "");
    }

    static String getLongPath(String seed) {
        Node[][] room = new Node[4][4];
        room[0][0] = new Node(seed, 0, 0);
        Stack<Node> stack = new Stack<>();
        stack.add(room[0][0]);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            //System.out.println(n.path + " (" + n.x + "," + n.y + ")");
            if (room[n.x][n.y] == null || room[n.x][n.y].getWeight() < n.getWeight()) {
                room[n.x][n.y] = n;
                if (n.x == 3 && n.y == 3) {
                    System.out.println(n.path + " (" + n.x + "," + n.y + ")");
                }
            }
            if (n.x != 3 || n.y != 3) {
                stack.addAll(n.getDirections());
            }
            System.out.println(stack.size());
        }
        System.out.println("longest" + room[3][3].path);
        return room[3][3].path.replace(seed, "");
    }


}

class Node {
    int x;
    int y;
    String path;

    Node(String s, int xx, int yy) {
        path = s;
        x = xx;
        y = yy;
    }

    ArrayList<Node> getDirections() {
        //up, down, left, and right
        ArrayList<Node> directions = new ArrayList<>();
        String hash = md5(path);
        if (hash.matches("^[a-f].*") && y !=  0 && (x!=0 || y!=1))
            directions.add(new Node(path + 'U', x, y - 1));
        if (hash.matches("^.[a-f].*") && y != 3) directions.add(new Node(path + 'D', x, y + 1));
        if (hash.matches("^..[a-f].*") && x != 0  && (x!=1 || y!=0))
            directions.add(new Node(path + 'L', x - 1, y));
        if (hash.matches("^...[a-f].*") && x != 3) directions.add(new Node(path + 'R', x + 1, y));
        return directions;
    }

    private static String md5(String nr) {
        String theDigest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(nr.getBytes("UTF-8"));
            theDigest = DatatypeConverter
                    .printHexBinary(digest).toLowerCase();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return theDigest;
    }

    public int getWeight() {
        return path.length();
    }
}
