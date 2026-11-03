import java.util.Scanner;

public class P1CreditCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int k = sc.nextInt();
        int total = 0;
        for (int i = 0; i < k; i++) {
            total += sc.nextInt();
        }

        System.out.println(name + ": " + total + " credits");
        if (total > 21) {
            System.out.println("Over limit");
        } else if (total < 12) {
            System.out.println("Under minimum");
        } else {
            System.out.println("OK");
        }
        sc.close();
    }
}
