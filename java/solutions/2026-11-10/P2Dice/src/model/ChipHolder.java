package model;

/** 칩을 가진 참가자 (부모, 추상 클래스) — P1 Gamer 와 같은 역할 */
public abstract class ChipHolder {

	private int chips;

	public ChipHolder(int start) {
		chips = start;
	}

	/** "손님", "딜러" — 자식이 정한다 */
	public abstract String name();

	public void addChips(int n) {
		chips += n;
	}

	public void removeChips(int n) {
		chips -= n;
	}

	public int getChips() {
		return chips;
	}

	/** [1번] 칩이 0개 이하면 true */
	public boolean isBroke() {
		return chips <= 0;
	}

	/** [2번] 정확히 "손님의 칩 : 20" 형식 */
	public String status() {
		return name() + "의 칩 : " + chips;
	}
}
