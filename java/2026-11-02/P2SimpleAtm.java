import java.util.Scanner;

public class P2SimpleAtm {
    public static void main(String[] args) {
        // TODO: 초기 잔액 입력
        Scanner sc = new Scanner(System.in);
        int balance = sc.nextInt();

        // TODO: 명령 반복 처리 (Q가 나 오면 종료)
        while (sc.hasNext()){
            String cmd = sc.next();
            if(cmd.equals("Q")) {
                System.out.println("Bye. Final Balance is: " + balance);
                break;
            }
            if (cmd.equals("B")) {
                System.out.println("Balance is: " + balance);
            }else if(cmd.equals("D") || cmd.equals("W")) {
                int amount = sc.nextInt();
                if(amount <= 0) {
                    System.out.println("Invalid Amount");
                }else if(cmd.equals("D")) {
                    balance += amount;
                    System.out.println("Deposited:" + amount);
                }else if (amount > balance) {
                    System.out.println("Insufficient funds");
                }else{
                    balance -= amount;
                    System.out.printf("Withdrew " + amount);
                }

            }else{
                System.out.println("Unknown Command");
                break;
            }

            sc.close();
        }
    }
}
