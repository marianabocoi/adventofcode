package aoc2016;


public class Day19 {
    public static void main(String[] args) {
        int dwarves = 3014603;
        Dwarf start = new Dwarf(1);
        Dwarf runner1 = start;
        Dwarf runner2;
        for(int i = 2; i <= dwarves; i++){
            runner2 = new Dwarf(i);
            runner1.setNext(runner2);
            System.out.println(runner1);
            runner1 = runner2;
        }
        runner2 = start;
        runner1.setNext(runner2);
        runner1 = start;
        System.out.println(runner1);
        while (!runner1.next.equals(runner1)){
            runner1.setNext(runner1.next.next);
            runner1 = runner1.next;
            System.out.println("-> " + runner1);
        }
        System.out.println("last" + runner1);
    }
}
class Dwarf{
    Dwarf next;
    int id;

    Dwarf(int i){
        id = i;
    }

    public void setNext(Dwarf next) {
        this.next = next;
    }

    public String toString(){
        return id + "(" + next.id + ")";
    }
}
