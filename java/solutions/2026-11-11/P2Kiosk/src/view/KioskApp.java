package view;

import java.util.Scanner;
import model.*;

/** 콘솔 실행용 (완성) — 채점은 tester.KioskTester */
public class KioskApp {

	public static void main(String[] args) {
		OrderBoard board = new OrderBoard(new String[] { "김밥", "라면", "떡볶이" }, new int[] { 3000, 4000, 5000 });
		Scanner in = new Scanner(System.in);
		System.out.println("메뉴 번호와 개수를 입력하세요 (예: 0 2). 잘못 넣으면 끝");
		while (in.hasNextInt()) {
			int idx = in.nextInt();
			if (!in.hasNextInt() || !board.order(idx, in.nextInt()))
				break;
		}
		in.close();
		for (int i = 0; i < board.size(); i++)
			System.out.println(board.line(i));
		System.out.println("총 매출: " + board.totalSales() + "원");
	}
}
