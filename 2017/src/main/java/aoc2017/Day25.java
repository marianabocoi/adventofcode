package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day25 {

    public static long part1(String[] in) {
        char state = 'a';
        Node list = new Node();
        for (int i = 0; i < 12425180; i++) {
            switch (state){
                case 'a':
                    list = runA(list);
                    state = list.nextState;
                    break;
                case 'b':
                    list = runB(list);
                    state = list.nextState;
                    break;
                case 'c':
                    list = runC(list);
                    state = list.nextState;
                    break;
                case 'd':
                    list = runD(list);
                    state = list.nextState;
                    break;
                case 'e':
                    list = runE(list);
                    state = list.nextState;
                    break;
                case 'f':
                    list = runF(list);
                    state = list.nextState;
                    break;
            }
        }
        return list.count;
    }

    private static Node runA(Node list) {
        if (list.val == 0) {
            list.setVal(1);
            Node r = list.getRight();
            r.count = list.count;
            r.nextState = 'b';
            return r;
        }else{
            list.setVal(0);
            Node r = list.getRight();
            r.count = list.count;
            r.nextState = 'f';
            return r;
        }
    }

    private static Node runB(Node list) {
        if (list.val == 0) {
            list.setVal(0);
            Node l = list.getLeft();
            l.count = list.count;
            l.nextState = 'b';
            return l;
        }else{
            list.setVal(1);
            Node l = list.getLeft();
            l.count = list.count;
            l.nextState = 'c';
            return l;
        }
    }

    private static Node runC(Node list) {
        if (list.val == 0) {
            list.setVal(1);
            Node l = list.getLeft();
            l.count = list.count;
            l.nextState = 'd';
            return l;
        }else{
            list.setVal(0);
            Node r = list.getRight();
            r.count = list.count;
            r.nextState = 'c';
            return r;
        }
    }

    private static Node runD(Node list) {
        if (list.val == 0) {
            list.setVal(1);
            Node l = list.getLeft();
            l.count = list.count;
            l.nextState = 'e';
            return l;
        }else{
            list.setVal(1);
            Node r = list.getRight();
            r.count = list.count;
            r.nextState = 'a';
            return r;
        }
    }

    private static Node runE(Node list) {
        if (list.val == 0) {
            list.setVal(1);
            Node l = list.getLeft();
            l.count = list.count;
            l.nextState = 'f';
            return l;
        }else{
            list.setVal(0);
            Node l = list.getLeft();
            l.count = list.count;
            l.nextState = 'd';
            return l;
        }
    }

    private static Node runF(Node list) {
        if (list.val == 0) {
            list.setVal(1);
            Node r = list.getRight();
            r.count = list.count;
            r.nextState = 'a';
            return r;
        }else{
            list.setVal(0);
            Node l = list.getLeft();
            l.count = list.count;
            l.nextState = 'e';
            return l;
        }
    }

    public static long part2(String[] in) {
        return 2;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "aoc2018/day25_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));

        String[] in = input.split("\n");
//        int[] in = Arrays.stream(input.split("\n")).mapToInt(Integer::parseInt).toArray();

        System.out.println(part1(in));
        System.out.println(part2(in));
    }

    static class Node{
        int val;
        Node left;
        Node right;
        char nextState;
        long count;
        Node(){
            this.val = 0;
        }

        public void setVal(int v) {
            if (v == 0 && val == 1){
                count--;
            }
            if(v==1&& val ==0){
                count++;
            }
            this.val = v;
        }

        public Node getRight() {
            if(right == null){
                right = new Node();
                right.left = this;
            }
            return right;
        }
        public Node getLeft() {
            if(left == null){
                left = new Node();
                left.right = this;
            }
            return left;
        }
    }
}


