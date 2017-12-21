package day04;

public class Anagram implements Comparable<Anagram> {
    private String s;

    Anagram(String s) {

        this.s = s;
    }

    @Override
    public int compareTo(Anagram o) {
        int[] word1 = new int[100];
        int[] word2 = new int[100];
        if (o.getS().length() != s.length()) {
            return s.compareTo(o.getS());
        }
        for (int i = 0; i < s.length(); i++) {
            word1[s.charAt(i) - 'a'] = word1[s.charAt(i) - 'a'] + 1;
            word2[o.getS().charAt(i) - 'a'] = word2[o.getS().charAt(i) - 'a'] + 1;
        }
        for (int i = 0; i < word1.length; i++) {
            if (word1[i] != word2[i]) {
                return s.compareTo(o.getS());
            }
        }
        return 0;
    }


    public boolean equals(Object oo) {
        if (!(oo instanceof Anagram)) {
            return false;
        }
        Anagram o = (Anagram) oo;
        int[] word1 = new int[100];
        int[] word2 = new int[100];
        if (o.getS().length() != s.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            word1[s.charAt(i) - 'a'] = word1[s.charAt(i) - 'a'] + 1;
            word2[o.getS().charAt(i) - 'a'] = word2[o.getS().charAt(i) - 'a'] + 1;
        }
        for (int i = 0; i < word1.length; i++) {
            if (word1[i] != word2[i]) {
                return false;
            }
        }
        return true;
    }

    public String getS() {
        return s;
    }
}
