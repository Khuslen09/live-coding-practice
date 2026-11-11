package model;

import java.awt.Color;

/** 카드 한 칸 (색깔 + 뒤집혔는지)
 *  원본: Doggzone/cse2016 exam1-2022.md — Cell */
public class Cell {

	private Color color;
	private boolean revealed;

	public Cell(Color c) {
		color = c;
	}

	public Color color() {
		return color;
	}

	public boolean revealed() {
		return revealed;
	}

	public void reveal() {
		revealed = true;
	}

	public void hide() {
		revealed = false;
	}

	/** [1번] c 와 색깔이 같으면 true (원본 시험 문제 그대로) */
	public boolean isATwinWith(Cell c) {
		// TODO
		return false;
	}
}
