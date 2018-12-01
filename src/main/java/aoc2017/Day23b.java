
public class Day23b {
    public static void main(String[] args) {
        int h = 0;

        int b = 107900;
        int c = b + 17000;

        for (int i = 107900; i <= c; i += 17) {
            if (!isPrime(i)) {
                h++;
            }
        }
        System.out.println(h);
    }

    static boolean isPrime(long nr) {
        if (nr % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(nr); i += 2) {
            if (nr % i == 0) {
                return false;
            }
        }
        return true;
    }

}
