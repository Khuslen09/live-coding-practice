import java.util.Scanner;

public class P1AboveAverage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // TODO: n 입력, int 배열에 저장
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        // TODO: Average 출력
        double avg = (double) sum / n;
        System.out.printf("Average: %.2f\n", avg);

        // TODO: Above 목록과 Count 출력
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if ( arr[i] > avg) {
                sb.append(" ").append(arr[i]);
                count++;
            }
        }
        System.out.println("Above:" + (count == 0 ? " none" : sb.toString()));
        System.out.println("Count: " + count);
    }
}
