import java.util.ArrayList;

public class solution {


    public static void main(String[] args) {

        System.out.println("23571".equals(answer(0)));
        System.out.println("71113".equals(answer(3)));
    }


    public static String prep() {
        int n = 10000;
        StringBuilder primeStr = new StringBuilder(n + 10);
        ArrayList<Integer> primes = new ArrayList(n);
        primes.add(2);
        primeStr.append(2);
        int count = 3;
        while (primeStr.length() < n + 5) {
            boolean isPrime = true;
            for (int nr : primes) {
                if (count % nr == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime == true) {
                primes.add(count);
                primeStr.append(count);
            }
            count += 2;
        }
        return primeStr.toString().substring(0, 10005);
    }

    public static String answer(int n) {
        StringBuilder primeStr = new StringBuilder(n + 10);
        ArrayList<Integer> primes = new ArrayList(n);
        primes.add(2);
        primeStr.append(2);
        int count = 3;
        while (primeStr.length() < n + 5) {
            boolean isPrime = true;
            for (int nr : primes) {
                if (count % nr == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime == true) {
                primes.add(count);
                primeStr.append(count);
            }
            count += 2;
        }
        return primeStr.toString().substring(n, n + 5);
    }
}
