import java.util.Scanner;

public class P2CafeteriaOrder {
    // TODO: static int priceOf(String menu)
    static int priceOf(String menu) {
        if (menu.equals("ramen")) return 3500;
        if (menu.equals("bibimbap")) return 5000;
        if (menu.equals("donkatsu")) return 6000;
        if (menu.equals("coffee")) return 2000;
        return -1;

    }

    // TODO: static int calcDiscount(int subtotal, boolean isStudent)
    static int calcDiscount(int subtotal, boolean isStudent) {
        if(isStudent && subtotal >= 10000){
            return 0;
        }
        return 0;
    }
    public static void main(String[] args) {
        // TODO: 손님 유형, n 입력
        // TODO: 주문 n줄 처리
        // TODO: Subtotal / Discount / Total 출력
    }
}
