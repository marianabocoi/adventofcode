
public class Day23b {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        int c = 1;
        int d = 1;
        int e = 1;
        int f = 1;
        int g = 1;
        int h = 1;

        b = 79;
        b *= 100;
        b -= -100000;
        c = b;
        c -= -17000;
        f = 1;
        do {
            d = 2;
            do {
                e = 2;
                do {
                    g = d;
                    g *= e;
                    g -= b;
                    if (g == 0) {
                        f = 0;
                    }
                    e -= -1;
                    g = e;
                    g -= b;
                } while (g != 0);
                d -= -1;
                g = d;
                g -= b;
            } while (g != 0);
            if (f == 0) {
                h -= -1;
            }
            g = b;
            g -= c;
            if (g == 0) {
                System.out.println("h=" + h);
                return;
            }
            b -= -17;
        } while (true);
    }
}
