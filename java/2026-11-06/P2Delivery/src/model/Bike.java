package model;

public class Bike extends Vehicle {
	public Bike() {
		super(3, 10);           // 상자 3개, 10kg 까지
	}
	public boolean wantsMore() {
		return totalWeight() < 8;
	}
	public int fee() {
		return 1000 * getCount();
	}
}
