import java.util.ArrayList;

public class Answer {
    public static void main(String[] args) {
//        System.out.println(answer(0));
//        System.out.println(answer2(new int[]{},new int[]{}));
        System.out.println(answer2(new int[]{13, 5, 6, 2, 5},new int[]{5, 2, 5, 13}));
    }
    public static String answer(int n) {
        StringBuilder primeStr = new StringBuilder(n + 10);
        int count = 3;
        while (primeStr.length() < n + 5) {
            if (isPrime(count) == true) {
                primeStr.append(count);
            }
            count += 2;
        }
        return primeStr.toString().substring(n, n + 5);
    }
    private static boolean isPrime(int n){
        for(int i = 3; i <= Math.sqrt(n) ; i++){
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }
    public static int answer2(int[] x, int[] y) {
        int number = 0;
        for(int i=0; i < Math.min(x.length, y.length); i++){
            number += x[i];
            number -= y[i];
        }
        if(x.length > y.length){
            number += x[x.length-1];
        } else {
            number = y[y.length-1] - number;
        }
        return number;
    }
}