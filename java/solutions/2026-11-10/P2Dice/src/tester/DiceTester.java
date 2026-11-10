package tester;

import java.util.function.BooleanSupplier;
import model.*;
import controller.*;

/** Day 9 Java P2 — 주사위 칩 게임 채점기 (12항목) */
public class DiceTester {

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

	public static void main(String[] args) {
		check("1-1 isBroke: 처음엔 false", () -> !new Guest().isBroke() && !new Dealer().isBroke());
		check("1-2 isBroke: 0개 이하면 true", () -> {
			Guest g = new Guest();
			g.removeChips(20);
			boolean zero = g.isBroke();
			g.removeChips(1);
			return zero && g.isBroke();
		});
		check("2-1 status: \"손님의 칩 : 20\"", () -> new Guest().status().equals("손님의 칩 : 20"));
		check("2-2 다형성: ChipHolder 변수로 \"딜러의 칩 : 50\"", () -> {
			ChipHolder h = new Dealer();
			return h.status().equals("딜러의 칩 : 50") && h instanceof ChipHolder;
		});
		check("3-1 rollDice: 항상 1~6", () -> {
			Dealer d = new Dealer();
			for (int i = 0; i < 500; i++) {
				int v = d.rollDice();
				if (v < 1 || v > 6) return false;
			}
			return true;
		});
		check("3-2 rollDice: 1 과 6 이 둘 다 나옴", () -> {
			Dealer d = new Dealer();
			boolean one = false, six = false;
			for (int i = 0; i < 2000; i++) {
				int v = d.rollDice();
				if (v == 1) one = true;
				if (v == 6) six = true;
			}
			return one && six;
		});
		check("4-1 round: 손님 5 > 딜러 2 → \"손님 승! +5\"", () ->
				new DiceTable(new Guest(), new Dealer()).round(5, 2, 5).equals("손님 승! +5"));
		check("4-2 round: 손님 승 → 손님 25 / 딜러 45", () -> {
			Guest g = new Guest();
			Dealer d = new Dealer();
			new DiceTable(g, d).round(6, 1, 5);
			return g.getChips() == 25 && d.getChips() == 45;
		});
		check("4-3 round: 딜러 승 → \"딜러 승! -5\", 손님 15 / 딜러 55", () -> {
			Guest g = new Guest();
			Dealer d = new Dealer();
			String m = new DiceTable(g, d).round(2, 4, 5);
			return m.equals("딜러 승! -5") && g.getChips() == 15 && d.getChips() == 55;
		});
		check("4-4 round: 같으면 \"무승부\", 칩 그대로", () -> {
			Guest g = new Guest();
			Dealer d = new Dealer();
			String m = new DiceTable(g, d).round(3, 3, 7);
			return m.equals("무승부") && g.getChips() == 20 && d.getChips() == 50;
		});
		check("5-1 maxBet: 처음엔 10", () -> new DiceTable(new Guest(), new Dealer()).maxBet() == 10);
		check("5-2 maxBet: 손님 3개 → 3, 딜러 4개 → 4", () -> {
			Guest g = new Guest();
			g.removeChips(17);
			boolean a = new DiceTable(g, new Dealer()).maxBet() == 3;
			Dealer d = new Dealer();
			d.removeChips(46);
			boolean b = new DiceTable(new Guest(), d).maxBet() == 4;
			return a && b;
		});
		System.out.println("Score: " + pass + " / " + total);
	}
}
