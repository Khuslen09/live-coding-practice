package model;

/** 손님 (완성) — 칩 20개로 시작 */
public class Guest extends ChipHolder {

	public Guest() {
		super(20);
	}

	public String name() {
		return "손님";
	}
}
