package tester;

import java.util.function.BooleanSupplier;
import model.*;

/** Day 11 Java P2 — 단어 짝맞추기 채점기 (12항목) */
public class WordPairTester {

	static int pass = 0, total = 0;

	static void check(String name, BooleanSupplier test) {
		total++;
		String result;
		try {
			if (test.getAsBoolean()) { pass++; result = "[OK]   "; }
			else result = "[FAIL] ";
		} catch (Throwable e) {
			result = "[FAIL] (" + e.getClass().getSimpleName() + ") ";
		}
		System.out.println(result + name);
	}

	/** 섞지 않은 판:  1 apple  2 cat  3 sky
	 *                4 sky    5 apple 6 cat   → 1-5, 2-6, 3-4 가 짝 */
	static WordBoard fixed() {
		String[] w = { "apple", "cat", "sky", "sky", "apple", "cat" };
		WordCard[] cards = new WordCard[6];
		for (int k = 0; k < 6; k++)
			cards[k] = new WordCard(new String(w[k]));   // 일부러 서로 다른 String 객체
		return new WordBoard(cards);
	}

	public static void main(String[] args) {
		// --- 1. isPairWith
		check("1-1 isPairWith: 같은 단어(다른 String 객체) → true (== 말고 equals)", () ->
				new WordCard(new String("apple")).isPairWith(new WordCard(new String("apple"))));
		check("1-2 isPairWith: \"cat\" vs \"Cat\" → false", () ->
				!new WordCard("cat").isPairWith(new WordCard("Cat")));

		// --- 2. createPairs
		check("2-1 createPairs(3단어) → 길이 6, 순서 a a b b c c", () -> {
			WordCard[] c = WordBoard.createPairs(new String[] { "a", "b", "c" });
			return c.length == 6 && c[0].word().equals("a") && c[1].word().equals("a")
					&& c[2].word().equals("b") && c[5].word().equals("c");
		});
		check("2-2 createPairs: 짝 두 장은 서로 다른 객체", () -> {
			WordCard[] c = WordBoard.createPairs(new String[] { "a", "b" });
			return c[0] != c[1] && c[2] != c[3];
		});

		// --- 3. cardAt
		check("3-1 cardAt: 1 → apple, 3 → sky, 4 → sky, 6 → cat", () -> {
			WordBoard b = fixed();
			return b.cardAt(1).word().equals("apple") && b.cardAt(3).word().equals("sky")
					&& b.cardAt(4).word().equals("sky") && b.cardAt(6).word().equals("cat");
		});
		check("3-2 cardAt: 0, 7 → null (예외 없이)", () -> {
			WordBoard b = fixed();
			return b.cardAt(0) == null && b.cardAt(7) == null && b.cardAt(2) != null;
		});

		// --- 4. pick
		check("4-1 pick(1, 5): 짝 → true, 둘 다 앞면, matched 1", () -> {
			WordBoard b = fixed();
			return b.pick(1, 5) && b.cardAt(1).faceUp() && b.cardAt(5).faceUp() && b.matched() == 1;
		});
		check("4-2 pick(1, 2): 짝 아님 → false, 둘 다 뒷면, matched 0", () -> {
			WordBoard b = fixed();
			boolean r = b.pick(1, 2);
			return !r && b.cardAt(1) != null && !b.cardAt(1).faceUp() && !b.cardAt(2).faceUp() && b.matched() == 0;
		});
		check("4-3 pick(2, 2) / pick(0, 3) / pick(4, 7) → false, matched 0", () -> {
			WordBoard b = fixed();
			return !b.pick(2, 2) && !b.pick(0, 3) && !b.pick(4, 7) && b.matched() == 0 && b.pick(3, 4);
		});
		check("4-4 이미 맞춘 카드: pick(6, 2) 후 pick(2, 6) → false, matched 1 그대로", () -> {
			WordBoard b = fixed();
			b.pick(6, 2);
			return !b.pick(2, 6) && b.matched() == 1 && b.cardAt(2).faceUp();
		});

		// --- 5. status
		check("5-1 status: 처음 \"맞춘 짝 0 / 3\"", () -> fixed().status().equals("맞춘 짝 0 / 3"));
		check("5-2 status: 두 쌍 맞춘 뒤 \"맞춘 짝 2 / 3\"", () -> {
			WordBoard b = fixed();
			b.pick(1, 5); b.pick(1, 2); b.pick(3, 4);
			return b.status().equals("맞춘 짝 2 / 3");
		});

		System.out.println("Score: " + pass + " / " + total);
	}
}
