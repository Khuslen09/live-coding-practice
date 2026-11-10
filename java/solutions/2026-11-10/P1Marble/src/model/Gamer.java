package model;

/** 구슬을 가진 게임 참가자 (부모, 추상 클래스)
 *  원본: Hyunha/2023CSE2016 MidTermCheck/src/marblegame/Player.java · Computer.java
 *  — 원본은 Player 와 Computer 에 같은 코드가 두 번 들어 있음 → 공통 부분을 이 부모로 올림 */
public abstract class Gamer {

	private int beads;

	public Gamer(int start) {
		beads = start;
	}

	/** 화면에 쓸 이름 ("당신", "컴퓨터") — 자식이 정한다 */
	public abstract String name();

	/** [1번] n 개 받기 */
	public void addBeads(int n) {
		beads += n;
	}

	/** [1번] n 개 내주기 */
	public void removeBeads(int n) {
		beads -= n;
	}

	public int getBeads() {
		return beads;
	}

	/** [2번] 구슬이 0개 이하면 true */
	public boolean lose() {
		return beads <= 0;
	}

	/** [3번] 정확히 "당신의 구슬 : 10" 형식 — 부모가 자식의 name() 을 불러 씀 */
	public String status() {
		return name() + "의 구슬 : " + beads;
	}
}
