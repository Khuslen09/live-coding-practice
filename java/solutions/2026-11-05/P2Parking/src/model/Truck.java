package model;

public class Truck extends Vehicle {
	private int tons;

	public Truck(String plate, int t) {
		super(plate);          // 부모 생성자 호출이 첫 줄
		tons = t;
	}

	public int getTons() {
		return tons;
	}

	public int fee(int hours) {
		return 3000 * hours + 1000 * tons;
	}
}
