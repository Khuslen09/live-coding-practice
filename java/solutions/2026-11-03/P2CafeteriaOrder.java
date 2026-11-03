import java.util.Scanner;

public class P2CafeteriaOrder {
    static int priceOf(String menu) {
        if (menu.equals("ramen")) return 3500;
        if (menu.equals("bibimbap")) return 5000;
        if (menu.equals("donkatsu")) return 6000;
        if (menu.equals("coffee")) return 2000;
        return -1;
    }

    static int calcDiscount(int subtotal, boolean isStudent) {
        if (isStudent && subtotal >= 10000) {
            return subtotal / 10;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        int n = sc.nextInt();
        boolean isStudent = type.equals("STUDENT");
        int subtotal = 0;

        for (int i = 0; i < n; i++) {
            String menu = sc.next();
            int qty = sc.nextInt();
            int price = priceOf(menu);
            if (price == -1) {
                System.out.println("Unknown menu: " + menu);
            } else if (qty <= 0) {
                System.out.println("Invalid qty: " + menu);
            } else {
                int amount = price * qty;
                subtotal += amount;
                System.out.println(menu + " x" + qty + " = " + amount);
            }
        }

        int discount = calcDiscount(subtotal, isStudent);
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Discount: " + discount);
        System.out.println("Total: " + (subtotal - discount));
        sc.close();
    }
}
