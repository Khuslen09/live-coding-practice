import java.util.Scanner;

public class P1TempConvert {
    // TODO: static double cToF(double c)
    static double CtoF(double c){
        return c * 9 / 5 + 32;
    } 
    // TODO: static double fToC(double f)
    static double FtoC(double f){
        return (f - 32) * 5 / 9;
    }

    public static void main(String[] args) {
        // TODO: n 입력 후 n번 변환
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i = 0; i < n; i++){
            String unit = sc.next();
            double v = sc.nextDouble();
            if(unit.equals("C")) {
                System.out.printf("%.1fC = %.1fF%n", v, CtoF(v));
            }else if(unit.equals("F")) {
                System.out.printf("%.1fF = %.1fC%n", v, FtoC(v));
            }else {
                System.out.println("Invalid unit");
            }
        }
        sc.close();
    }
}
