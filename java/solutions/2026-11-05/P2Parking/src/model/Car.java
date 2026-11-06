package model;

public class Car extends Vehicle {
	public static final int PER_HOUR = 2000;
	public static final int MAX_FEE = 15000;

	public Car(String plate) {
		super(plate);
	}

	public int fee(int hours) {
		int f = PER_HOUR * hours;
		if (f > MAX_FEE)
			f = MAX_FEE;
		return f;
	}
}
