package tester;

import java.util.function.BooleanSupplier;
import model.*;

/** Day 10 Java P2 — 분식집 키오스크(객체 배열) 채점기 (12항목) */
public class KioskTester {

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

	static OrderBoard board() {
		return new OrderBoard(new String[] { "김밥", "라면", "떡볶이" }, new int[] { 3000, 4000, 5000 });
	}

	public static void main(String[] args) {
		// --- 1. 생성자
		check("1-1 생성자: 메뉴 3개, 1번은 라면 4000원", () -> {
			OrderBoard b = board();
			return b.size() == 3 && b.get(1).getName().equals("라면") && b.get(1).getPrice() == 4000;
		});
		check("1-2 생성자: 처음엔 모두 0개", () -> {
			OrderBoard b = board();
			return b.get(0).getCount() == 0 && b.get(2).getCount() == 0;
		});

		// --- 2. order
		check("2-1 order(0, 3): true, 김밥 3개", () -> {
			OrderBoard b = board();
			return b.order(0, 3) && b.get(0).getCount() == 3;
		});
		check("2-2 order(3, 1) / order(-1, 1): false", () -> {
			OrderBoard b = board();
			return !b.order(3, 1) && !b.order(-1, 1);
		});
		check("2-3 order(1, 0) / order(1, -2): false, 라면 0개 그대로", () -> {
			OrderBoard b = board();
			return !b.order(1, 0) && !b.order(1, -2) && b.get(1).getCount() == 0;
		});

		// --- 3. totalSales
		check("3-1 totalSales: 처음엔 0", () -> board().totalSales() == 0);
		check("3-2 totalSales: 김밥 2 + 떡볶이 1 → 11000", () -> {
			OrderBoard b = board();
			b.order(0, 2); b.order(2, 1); b.order(5, 9);
			return b.totalSales() == 11000;
		});

		// --- 4. bestSeller
		check("4-1 bestSeller: 아무것도 안 팔림 → -1", () -> board().bestSeller() == -1);
		check("4-2 bestSeller: 라면 4개 → 1", () -> {
			OrderBoard b = board();
			b.order(0, 1); b.order(1, 4); b.order(2, 2);
			return b.bestSeller() == 1;
		});
		check("4-3 bestSeller: 김밥·떡볶이 2개 동점 → 0", () -> {
			OrderBoard b = board();
			b.order(2, 2); b.order(0, 2);
			return b.bestSeller() == 0;
		});

		// --- 5. line
		check("5-1 line(0): \"김밥 x3 = 9000원\"", () -> {
			OrderBoard b = board();
			b.order(0, 3);
			return b.line(0).equals("김밥 x3 = 9000원");
		});
		check("5-2 line(2): 안 팔린 메뉴 \"떡볶이 x0 = 0원\"", () -> board().line(2).equals("떡볶이 x0 = 0원"));

		System.out.println("Score: " + pass + " / " + total);
	}
}
