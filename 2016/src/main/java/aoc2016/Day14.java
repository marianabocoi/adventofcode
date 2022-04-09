package aoc2016;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Day14 {

    static HashMap<String, String>  strechedHashes = new HashMap<>();
//    static String salt = "abc";
    static String salt = "cuanljph";
    public static void main(String[] args) {
        int[] ints = IntStream.iterate(0, i -> i + 1)
                .filter(nr -> {

                    String digest = getMd5(salt + nr);
                    char c = isThreeInARow(digest);
                    return c != ' ' && nextFiveInARow(nr, c);
                })
                .limit(64)
                .peek(x -> System.out.println(x))
                .toArray();
        System.out.println("yay " + ints[63]);
    }
    //23769

    private static boolean nextFiveInARow(int nr, char c) {
        return IntStream.iterate(nr+1, i -> i + 1).limit(1000)
                .filter(n -> {
                    String digest = getMd5(salt + n);
                    return isFiveInARow(digest, c, nr);
                })
                .peek(x -> System.out.println("\t " + x ))
                .count() > 0;
    }

    private static String getMd5(String s) {
        if(!strechedHashes.containsKey(s)) {
            String theDigest = s;
            for (int i = 0; i < 2017; i++) {
                theDigest = md5(theDigest);
            }
            strechedHashes.put(s, theDigest);
        }
        return strechedHashes.get(s);
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

    public static char isThreeInARow(String digest) {
        if (digest.length() < 3) return ' ';
        for (int i = 0; i < digest.length() - 3; i++) {
            if (digest.charAt(i) == digest.charAt(i + 1) &&
                    digest.charAt(i) == digest.charAt(i + 2) // &&
                    //digest.charAt(i) != digest.charAt(i + 3) &&
                    //(i == 0 || digest.charAt(i) != digest.charAt(i - 1))
                    ) {
                return digest.charAt(i);
            }
        }
        return ' ';
    }

    public static boolean isFiveInARow(String digest, char c, int nr) {
        if (digest.length() < 5) return false;
        for (int i = 0; i < digest.length() - 5; i++) {
            if (digest.charAt(i) == c &&
                    digest.charAt(i) == digest.charAt(i + 1) &&
                    digest.charAt(i) == digest.charAt(i + 2) &&
                    digest.charAt(i) == digest.charAt(i + 3) &&
                    digest.charAt(i) == digest.charAt(i + 4)) {
                return true;
            }
        }
        return false;
    }
}
