package aoc2016;

import java.util.ArrayList;

public class Day19Part2 {
    public static void main(String[] args) {
        ArrayList<Integer> dwarfCircle = new ArrayList();
        int n = 3014603;
//        int n = 5;
        for (int i = 1; i <= n; i++) {
            dwarfCircle.add(i);
        }
        int current = 0;
        while (dwarfCircle.size() != 1) {
            int opposite = (current + (int) Math.floor(dwarfCircle.size() / 2)) % dwarfCircle.size();
            //System.out.println(current + " - " + opposite + " - " + Arrays.toString(dwarfCircle.toArray()));
            if (current > opposite) {
                current--;
            }

            dwarfCircle.remove(opposite);
            current = (current + 1) % dwarfCircle.size();
        }
        System.out.println(dwarfCircle.get(0));
        //1420280
    }
}
