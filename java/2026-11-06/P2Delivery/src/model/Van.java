package model;

public class Van extends Vehicle {
	public Van() {
		super(10, 100);         // 상자 10개, 100kg 까지
	}
	public boolean wantsMore() {
		return totalWeight() <= 80;
	}
	public int fee() {
		return 5000 + 100 * totalWeight();
	}
}
