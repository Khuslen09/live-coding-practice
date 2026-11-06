package model;

public abstract class Vehicle {
	private Key key;

	public Vehicle(String plate) {
		key = new PlateKey(plate);
	}

	public Key getKey() {
		return key;
	}

	/** 주차 요금 (차종마다 다름) */
	public abstract int fee(int hours);
}
