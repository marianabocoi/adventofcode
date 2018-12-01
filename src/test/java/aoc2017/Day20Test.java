package aoc2017;

import aoc2017.Day20;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Day20Test {
    @Test
    public void testPart1() {
        assertEquals(0, Day20.part1(("p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>\n" +
                "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>").split("\n")));

        assertEquals(0, Day20.part1(("p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>\n" +
                "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>").split("\n")));
    }

    @Test
    public void mm() {


        String[] in = ("p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>\n" +
                "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>").split("\n");
        Day20.Particle[] particles1 = new Day20.Particle[in.length];
        Day20.Particle[] particles2 = new Day20.Particle[in.length];
        for (int i = 0; i < in.length; i++) {
            particles1[i] = new Day20.Particle(in[i], i);
            particles2[i] = new Day20.Particle(in[i], i);
            //System.out.println(particles[i]);
        }
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < particles1.length; i++) {
                particles1[i].update();
                //System.out.println(particles[i]);
            }
        }
        for (int i = 0; i < particles1.length; i++) {
            assertEquals(particles1[i].distance(), particles2[i].distance(10));
            //System.out.println(particles[i]);
        }
    }

    @Test
    public void testPart2() throws IOException, InterruptedException {
        assertEquals(1, Day20.part2(("p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>\n" +
                "p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>\n" +
                "p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>\n" +
                "p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>").split("\n")));

    }
/*
p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>
p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>
p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>
p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>
 */

}