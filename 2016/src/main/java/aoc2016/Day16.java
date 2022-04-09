package aoc2016;


public class Day16 {
    static String input = "01111001100111011";
    static String test = "111100001010";

    public static void main(String[] args) {
        StringBuilder testB = getB(new StringBuilder(test));
        System.out.println("101011110000".equals(testB.toString()));

        //up to checksum
        StringBuilder data = new StringBuilder(input);
        int size = 35651584;
        while (data.length() < size) {
            process(data);
        }
        String d = data.substring(0, size);
        //System.out.println(d.length() + " " + d.toString());
        String checksum = checksum(d);
        System.out.println(checksum);
    }

    private static String checksum(String d) {
        StringBuilder temp = new StringBuilder(d);
        StringBuilder checksum = new StringBuilder(d.length() / 2);
        while (checksum.length() % 2 == 0) {
            checksum = new StringBuilder(temp.length() / 2);
            for (int i = 0; i < temp.length(); i += 2) {
                if (temp.charAt(i) == temp.charAt(i + 1)) {
                    checksum.append('1');
                } else {
                    checksum.append('0');
                }
            }
            temp = checksum;
        }
        return checksum.toString();
    }

    private static void process(StringBuilder data) {
        StringBuilder tmp = getB(data);
        data.append('0');
        data.append(tmp);
    }

    public static StringBuilder getB(StringBuilder s) {
        StringBuilder processed = new StringBuilder(s);
        processed.reverse();
        for (int i = 0; i < processed.length(); i++) {
            if (processed.charAt(i) == '0') {
                processed.setCharAt(i, '1');
            } else {
                processed.setCharAt(i, '0');
            }
        }
        return processed;
    }
}
