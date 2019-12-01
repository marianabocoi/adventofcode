package aoc2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day11 {

    public static long part1(String input) {
        String[]  lengths = input.split(",");
        int ne = 0;
        int n = 0;
        int nw =0;
        int se = 0;
        int s = 0;
        int sw =0;
        for (String m :  lengths){
            switch (m){
                case "ne":
                    ne++;
                    break;

                case "sw":
                    sw++;
                    break;

                case "n":
                    n++;
                    break;

                case "s":
                    s++;
                    break;

                case "nw":
                    nw++;
                    break;

                case "se":
                    se++;
                    break;
            }
        }

        if(n>s){
            n -= s;
            s=0;
        }else {
            s-= n;
            n=0;
        }
        if(nw>se){
            nw -= se;
            se=0;
        }else {
            se-= nw;
            nw=0;
        }
        if(ne>sw){
            ne -= sw;
            sw=0;
        }else {
            sw-= ne;
            ne=0;
        }
        int diff;
        //
        if(nw>0 && ne>0){
            diff= Math.min(ne,nw);
            n+=diff;
            ne-=diff;
            nw-=diff;
        }
        //
        if(n>0 && se>0){
            diff= Math.min(n,se);
            ne+=diff;
            n-=diff;
            se-=diff;
        }
//
        if(ne>0 && s>0){
            diff= Math.min(ne,s);
            se+=diff;
            ne-=diff;
            s-=diff;
        }
//
        if(sw>0 && se>0){
            diff= Math.min(se,sw);
            s+=diff;
            se-=diff;
            sw-=diff;
        }
//
        if(s>0 && nw>0){
            diff= Math.min(s,nw);
            sw+=diff;
            s-=diff;
            nw-=diff;
        }
//
        if(n>0 && sw>0){
            diff= Math.min(n,sw);
            nw+=diff;
            n-=diff;
            sw-=diff;
        }
        return n+ne+se+s+sw+nw;
    }

    public static int part2(String input) {
        String[]  lengths = input.split(",");
        int ne = 0;
        int n = 0;
        int nw =0;
        int se = 0;
        int s = 0;
        int sw =0;
        int max = 0;
        int distance = 0;
        for (String m :  lengths){
            switch (m){
                case "ne":
                    ne++;
                    distance = distance(ne,n,nw,se,s,sw);
                    if(distance > max){
                        max=distance;
                    }
                    break;

                case "sw":
                    sw++;
                    distance = distance(ne,n,nw,se,s,sw);
                    if(distance > max){
                        max=distance;
                    }
                    break;

                case "n":
                    n++;
                    distance = distance(ne,n,nw,se,s,sw);
                    if(distance > max){
                        max=distance;
                    }
                    break;

                case "s":
                    s++;
                    distance = distance(ne,n,nw,se,s,sw);
                    if(distance > max){
                        max=distance;
                    }
                    break;

                case "nw":
                    nw++;
                    distance = distance(ne,n,nw,se,s,sw);
                    if(distance > max){
                        max=distance;
                    }
                    break;

                case "se":
                    se++;
                    distance = distance(ne,n,nw,se,s,sw);
                    if(distance > max){
                        max=distance;
                    }
                    break;
            }
        }
        return max;
    }

    private static int distance(int ne, int n, int nw, int se, int s, int sw) {

        if(n>s){
            n -= s;
            s=0;
        }else {
            s-= n;
            n=0;
        }
        if(nw>se){
            nw -= se;
            se=0;
        }else {
            se-= nw;
            nw=0;
        }
        if(ne>sw){
            ne -= sw;
            sw=0;
        }else {
            sw-= ne;
            ne=0;
        }
        int diff;
        //
        if(nw>0 && ne>0){
            diff= Math.min(ne,nw);
            n+=diff;
            ne-=diff;
            nw-=diff;
        }
        //
        if(n>0 && se>0){
            diff= Math.min(n,se);
            ne+=diff;
            n-=diff;
            se-=diff;
        }
//
        if(ne>0 && s>0){
            diff= Math.min(ne,s);
            se+=diff;
            ne-=diff;
            s-=diff;
        }
//
        if(sw>0 && se>0){
            diff= Math.min(se,sw);
            s+=diff;
            se-=diff;
            sw-=diff;
        }
//
        if(s>0 && nw>0){
            diff= Math.min(s,nw);
            sw+=diff;
            s-=diff;
            nw-=diff;
        }
//
        if(n>0 && sw>0){
            diff= Math.min(n,sw);
            nw+=diff;
            n-=diff;
            sw-=diff;
        }
        return n+ne+se+s+sw+nw;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String resource = "aoc2018/day11_in.txt";
        String input = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI())));
        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}

