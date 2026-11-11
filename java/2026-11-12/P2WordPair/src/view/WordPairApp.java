package view;

import java.util.Scanner;
import model.*;

/** 콘솔 실행용 (완성) — 채점은 tester.WordPairTester */
public class WordPairApp {

	static void print(WordBoard board) {
		for (int n = 1; n <= 6; n++) {
			WordCard c = board.cardAt(n);
			System.out.print(c.faceUp() ? "[" + c.word() + "] " : "[" + n + "] ");
			if (n % 3 == 0)
				System.out.println();
		}
	}

	public static void main(String[] args) {
		WordBoard board = new WordBoard(WordBoard.createPairs(new String[] { "apple", "cat", "sky" }));
		Scanner in = new Scanner(System.in);
		while (board.matched() < 3) {
			print(board);
			System.out.print("두 번호 (예: 1 2) > ");
			if (!in.hasNextInt())
				break;
			int a = in.nextInt(), b = in.nextInt();
			WordCard c1 = board.cardAt(a), c2 = board.cardAt(b);
			if (c1 != null && c2 != null)
				System.out.println(a + "번 = " + c1.word() + ", " + b + "번 = " + c2.word());
			System.out.println(board.pick(a, b) ? "짝!" : "땡");
			System.out.println(board.status());
		}
		in.close();
	}
}
