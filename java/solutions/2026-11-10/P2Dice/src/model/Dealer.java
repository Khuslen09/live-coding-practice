package model;

/** 딜러 — 칩 50개로 시작 */
public class Dealer extends ChipHolder {

	public Dealer() {
		super(50);
	}

	public String name() {
		return "딜러";
	}

	/** [3번] 주사위 1 ~ 6 중 무작위 */
	public int rollDice() {
		return (int) (Math.random() * 6) + 1;
	}
}
