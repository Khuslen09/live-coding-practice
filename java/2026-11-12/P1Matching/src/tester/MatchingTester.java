package tester;

import java.awt.Color;
import java.util.function.BooleanSupplier;
import model.*;
import controller.*;

/** Day 11 Java P1 — 짝맞추기 게임(2차원 객체 배열) 채점기 (20항목) */
public class MatchingTester {

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

	static final Color[] C8 = { Color.BLUE, Color.CYAN, Color.PINK, Color.RED,
			Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE };

	/** 섞지 않은 16장: 번호 1~4 = 색 0,1,2,3 / 5~8 = 0,1,2,3 / 9~12 = 4,5,6,7 / 13~16 = 7,6,5,4
	 *  → 1 과 5 가 짝(BLUE), 1 과 2 는 짝 아님, 9 와 16 이 짝(GRAY) */
	static Cell[] cells16() {
		int[] idx = { 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7, 7, 6, 5, 4 };
		Cell[] cells = new Cell[16];
		for (int k = 0; k < 16; k++)
			cells[k] = new Cell(C8[idx[k]]);
		return cells;
	}

	static GameBoard fixed() {
		return new GameBoard(cells16());
	}

	public static void main(String[] args) {
		// --- 1. Cell.isATwinWith
		check("1-1 isATwinWith: 같은 색(다른 객체) → true", () ->
				new Cell(Color.BLUE).isATwinWith(new Cell(Color.BLUE)));
		check("1-2 isATwinWith: 다른 색 → false", () ->
				!new Cell(Color.BLUE).isATwinWith(new Cell(Color.RED)));
		check("1-3 isATwinWith: new Color(255,0,0) 과 Color.RED → true (== 말고 equals)", () ->
				new Cell(new Color(255, 0, 0)).isATwinWith(new Cell(Color.RED)));

		// --- 2. makeSquare
		check("2-1 makeSquare: square[0][0] 은 cells[0] (같은 객체)", () -> {
			Cell[] cells = cells16();
			GameBoard b = new GameBoard(cells);
			return b.square()[0][0] == cells[0] && b.square()[0][3] == cells[3];
		});
		check("2-2 makeSquare: square[1][0] == cells[4], square[3][3] == cells[15]", () -> {
			Cell[] cells = cells16();
			GameBoard b = new GameBoard(cells);
			return b.square()[1][0] == cells[4] && b.square()[2][1] == cells[9] && b.square()[3][3] == cells[15];
		});
		check("2-3 makeSquare: 16칸 모두 null 이 아님", () -> {
			Cell[][] sq = fixed().square();
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++)
					if (sq[i][j] == null) return false;
			return true;
		});

		// --- 3. createTwinCells
		check("3-1 createTwinCells(색 3개) → 길이 6", () ->
				fixed().createTwinCells(new Color[] { Color.RED, Color.BLUE, Color.GREEN }).length == 6);
		check("3-2 createTwinCells 순서: 0,1번 RED / 4,5번 GREEN", () -> {
			Cell[] t = fixed().createTwinCells(new Color[] { Color.RED, Color.BLUE, Color.GREEN });
			return t[0].color().equals(Color.RED) && t[1].color().equals(Color.RED)
					&& t[2].color().equals(Color.BLUE) && t[4].color().equals(Color.GREEN) && t[5].color().equals(Color.GREEN);
		});
		check("3-3 createTwinCells: 짝 두 장은 서로 다른 객체 (new 두 번)", () -> {
			Cell[] t = fixed().createTwinCells(new Color[] { Color.RED, Color.BLUE });
			t[0].reveal();
			return t[0] != t[1] && !t[1].revealed();
		});
		check("3-4 기본 생성자 GameBoard(): 8가지 색이 정확히 2장씩, null 없음", () -> {
			Cell[][] sq = new GameBoard().square();
			for (Color c : C8) {
				int cnt = 0;
				for (int i = 0; i < 4; i++)
					for (int j = 0; j < 4; j++)
						if (sq[i][j].color().equals(c)) cnt++;
				if (cnt != 2) return false;
			}
			return true;
		});

		// --- 4. cellAt
		check("4-1 cellAt: 1 → [0][0], 4 → [0][3], 5 → [1][0]", () -> {
			GameBoard b = fixed();
			Cell[][] sq = b.square();
			return b.cellAt(1) != null && b.cellAt(1) == sq[0][0] && b.cellAt(4) == sq[0][3] && b.cellAt(5) == sq[1][0];
		});
		check("4-2 cellAt: 10 → [2][1], 16 → [3][3]", () -> {
			GameBoard b = fixed();
			Cell[][] sq = b.square();
			return b.cellAt(10) != null && b.cellAt(10) == sq[2][1] && b.cellAt(16) == sq[3][3];
		});
		check("4-3 cellAt: 0, 17, -1 → null (예외 없이)", () -> {
			GameBoard b = fixed();
			return b.cellAt(0) == null && b.cellAt(17) == null && b.cellAt(-1) == null && b.cellAt(1) != null;
		});

		// --- 5. revealedCount
		check("5-1 revealedCount: 처음엔 0 (칸이 채워진 판에서)", () -> {
			GameBoard b = fixed();
			return b.square()[0][0] != null && b.revealedCount() == 0;
		});
		check("5-2 revealedCount: 3칸 reveal → 3", () -> {
			GameBoard b = fixed();
			b.square()[0][0].reveal(); b.square()[2][3].reveal(); b.square()[3][3].reveal();
			return b.revealedCount() == 3;
		});

		// --- 6. status
		check("6-1 status: \"점수: 0 / 16 (남은 짝 8쌍)\"", () ->
				fixed().status().equals("점수: 0 / 16 (남은 짝 8쌍)"));
		check("6-2 upgrade 2번 후 status: \"점수: 4 / 16 (남은 짝 6쌍)\"", () -> {
			GameBoard b = fixed();
			b.upgrade(); b.upgrade();
			return b.status().equals("점수: 4 / 16 (남은 짝 6쌍)");
		});

		// --- 7. GameController.turn
		check("7-1 turn(1, 5): 짝 → true, 점수 2, 두 칸 뒤집힌 채로", () -> {
			GameBoard b = fixed();
			GameController gc = new GameController(b, null, null);
			return gc.turn(1, 5) && b.point() == 2 && b.cellAt(1).revealed() && b.cellAt(5).revealed();
		});
		check("7-2 turn(1, 2): 짝 아님 → false, 두 칸 다시 숨김, 점수 0", () -> {
			GameBoard b = fixed();
			GameController gc = new GameController(b, null, null);
			boolean r = gc.turn(1, 2);
			return !r && b.point() == 0 && !b.cellAt(1).revealed() && !b.cellAt(2).revealed() && b.cellAt(1) != null;
		});
		check("7-3 turn(3,3) / turn(0,5) / turn(9,17) → false, 아무것도 안 바뀜 / turn(16, 9) → true", () -> {
			GameBoard b = fixed();
			GameController gc = new GameController(b, null, null);
			boolean bad = !gc.turn(3, 3) && !gc.turn(0, 5) && !gc.turn(9, 17) && b.revealedCount() == 0 && b.point() == 0;
			return bad && gc.turn(16, 9) && b.point() == 2;
		});
		check("7-4 이미 맞춘 칸: turn(1,5) 후 turn(1,5) / turn(5,2) → false, 점수 2 그대로, 5번은 계속 뒤집힘", () -> {
			GameBoard b = fixed();
			GameController gc = new GameController(b, null, null);
			gc.turn(1, 5);
			boolean r1 = gc.turn(1, 5);
			boolean r2 = gc.turn(5, 2);
			return !r1 && !r2 && b.point() == 2 && b.cellAt(5).revealed() && !b.cellAt(2).revealed();
		});

		System.out.println("Score: " + pass + " / " + total);
	}
}
