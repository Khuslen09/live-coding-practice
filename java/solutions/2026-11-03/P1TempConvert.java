import java.util.Scanner;

public class P1TempConvert {
    static double cToF(double c) {
        return c * 9 / 5 + 32;
    }

    static double fToC(double f) {
        return (f - 32) * 5 / 9;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String unit = sc.next();
            double v = sc.nextDouble();
            if (unit.equals("C")) {
                System.out.printf("%.1fC = %.1fF%n", v, cToF(v));
            } else if (unit.equals("F")) {
                System.out.printf("%.1fF = %.1fC%n", v, fToC(v));
            } else {
                System.out.println("Unknown unit");
            }
        }
        sc.close();
    }
}
