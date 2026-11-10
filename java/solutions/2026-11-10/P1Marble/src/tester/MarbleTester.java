package tester;

import java.util.function.BooleanSupplier;
import model.*;
import controller.*;

/** Day 9 Java P1 — 홀짝 구슬 게임(상속으로 중복 제거) 채점기 (20항목) */
public class MarbleTester {

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

	interface Pick { int get(); }
	static boolean inRange(Pick p, int lo, int hi) {
		for (int i = 0; i < 500; i++) {
			int v = p.get();
			if (v < lo || v > hi) return false;
		}
		return true;
	}
	static boolean hitsBothEnds(Pick p, int lo, int hi) {
		boolean seenLo = false, seenHi = false;
		for (int i = 0; i < 2000; i++) {
			int v = p.get();
			if (v == lo) seenLo = true;
			if (v == hi) seenHi = true;
		}
		return seenLo && seenHi;
	}

	public static void main(String[] args) {
		check("1-1 addBeads: 10 + 3 = 13", () -> {
			Player p = new Player();
			p.addBeads(3);
			return p.getBeads() == 13;
		});
		check("1-2 removeBeads: 10 - 4 = 6", () -> {
			Computer c = new Computer();
			c.removeBeads(4);
			return c.getBeads() == 6;
		});
		check("2-1 lose: 10개면 false", () -> !new Player().lose());
		check("2-2 lose: 0개면 true", () -> {
			Player p = new Player();
			p.removeBeads(10);
			return p.lose();
		});
		check("2-3 lose: 음수여도 true / 1개면 false", () -> {
			Computer c = new Computer();
			c.removeBeads(9);
			boolean one = c.lose();
			c.removeBeads(3);
			return !one && c.lose();
		});
		check("3-1 status: \"당신의 구슬 : 10\"", () -> new Player().status().equals("당신의 구슬 : 10"));
		check("3-2 status: \"컴퓨터의 구슬 : 10\"", () -> new Computer().status().equals("컴퓨터의 구슬 : 10"));
		check("3-3 다형성: Gamer 변수 + 구슬 변화 후 \"당신의 구슬 : 13\"", () -> {
			Gamer g = new Player();
			g.addBeads(3);
			return g.status().equals("당신의 구슬 : 13");
		});
		check("4-1 pickForAttack: 10개일 때 항상 1~5", () -> {
			Computer c = new Computer();
			return inRange(c::pickForAttack, 1, 5);
		});
		check("4-2 pickForAttack: 1 과 5 가 둘 다 나옴 (무작위)", () -> {
			Computer c = new Computer();
			return hitsBothEnds(c::pickForAttack, 1, 5);
		});
		check("4-3 pickForAttack: 3개일 때 1~3 (3도 나옴)", () -> {
			Computer c = new Computer();
			c.removeBeads(7);
			return inRange(c::pickForAttack, 1, 3) && hitsBothEnds(c::pickForAttack, 1, 3);
		});
		check("5-1 pickForGuard: 10개일 때 항상 0~5", () -> {
			Computer c = new Computer();
			return inRange(c::pickForGuard, 0, 5);
		});
		check("5-2 pickForGuard: 0 과 5 가 둘 다 나옴", () -> {
			Computer c = new Computer();
			return hitsBothEnds(c::pickForGuard, 0, 5);
		});
		check("5-3 pickForGuard: 2개일 때 0~2 (2도 나옴)", () -> {
			Computer c = new Computer();
			c.removeBeads(8);
			return inRange(c::pickForGuard, 0, 2) && hitsBothEnds(c::pickForGuard, 0, 2);
		});
		check("6-1 playerTurn: 짝 맞힘 → \"당신이 이겼습니다. 구슬 획득! : 3\"", () -> {
			GameController gc = new GameController(new Player(), new Computer());
			return gc.playerTurn(3, true, 4).equals("당신이 이겼습니다. 구슬 획득! : 3");
		});
		check("6-2 playerTurn: 이기면 당신 13 / 컴퓨터 7", () -> {
			Player p = new Player();
			Computer c = new Computer();
			new GameController(p, c).playerTurn(3, false, 1);
			return p.getBeads() == 13 && c.getBeads() == 7;
		});
		check("6-3 playerTurn: 틀림 → \"당신이 졌습니다. 구슬 제출! : 2\", 당신 8 / 컴퓨터 12", () -> {
			Player p = new Player();
			Computer c = new Computer();
			String m = new GameController(p, c).playerTurn(2, true, 3);
			return m.equals("당신이 졌습니다. 구슬 제출! : 2") && p.getBeads() == 8 && c.getBeads() == 12;
		});
		check("7-1 computerTurn: 홀짝 같음 → \"당신이 졌습니다. 구슬 제출! : 4\", 6 / 14", () -> {
			Player p = new Player();
			Computer c = new Computer();
			String m = new GameController(p, c).computerTurn(2, 4);
			return m.equals("당신이 졌습니다. 구슬 제출! : 4") && p.getBeads() == 6 && c.getBeads() == 14;
		});
		check("7-2 computerTurn: 홀짝 다름 → \"당신이 이겼습니다. 구슬 획득! : 4\", 14 / 6", () -> {
			Player p = new Player();
			Computer c = new Computer();
			String m = new GameController(p, c).computerTurn(1, 4);
			return m.equals("당신이 이겼습니다. 구슬 획득! : 4") && p.getBeads() == 14 && c.getBeads() == 6;
		});
		check("8-1 상속: Gamer g = new Computer() 가 되고 isOver() 가 끝을 알아챔", () -> {
			Gamer g = new Computer();
			Player p = new Player();
			Computer c = new Computer();
			GameController gc = new GameController(p, c);
			gc.playerTurn(5, true, 0);
			gc.playerTurn(5, true, 2);
			return g instanceof Gamer && g.name().equals("컴퓨터") && gc.isOver() && c.lose();
		});
		System.out.println("Score: " + pass + " / " + total);
	}
}
