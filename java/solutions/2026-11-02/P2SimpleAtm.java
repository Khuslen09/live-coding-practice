import java.util.Scanner;

public class P2SimpleAtm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = sc.nextInt();

        while (sc.hasNext()) {
            String cmd = sc.next();
            if (cmd.equals("Q")) {
                System.out.println("Bye. Final balance: " + balance);
                break;
            } else if (cmd.equals("B")) {
                System.out.println("Balance: " + balance);
            } else if (cmd.equals("D") || cmd.equals("W")) {
                int amount = sc.nextInt();
                if (amount <= 0) {
                    System.out.println("Invalid amount");
                } else if (cmd.equals("D")) {
                    balance += amount;
                    System.out.println("Deposited " + amount);
                } else if (amount > balance) {
                    System.out.println("Insufficient funds");
                } else {
                    balance -= amount;
                    System.out.println("Withdrew " + amount);
                }
            } else {
                System.out.println("Unknown command");
            }
        }
        sc.close();
    }
}
