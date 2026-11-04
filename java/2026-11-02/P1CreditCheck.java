import java.util.Scanner;

public class P1CreditCheck {
    public static void main(String[] args) {
        // TODO: Scanner로 이름, k, 학점 k개 입력
        Scanner sc = new Scanner(System.in);
        String  name = sc.next();
        int k = sc.nextInt();
        int total = 0;
        for (int i = 0; i < k; i++) {
            total += sc.nextInt();
        }

        // TODO: 총 학점과 상태 출력
        System.out.println(name + ':' + total + " credits");
        if (total > 21) {
            System.out.println("Over limit");
        }else if ( total < 12) {
            System.out.println("Under minimum");
        }else{
            System.out.println("OK");
        } 
        sc.close();
    }
}
