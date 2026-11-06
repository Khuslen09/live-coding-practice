package model;

public abstract class Vehicle implements LoadBehavior {

	private Parcel[] cargo;
	private int count;
	private int maxWeight;

	public Vehicle(int max_items, int max_weight) {
		cargo = new Parcel[max_items];
		count = 0;
		maxWeight = max_weight;
	}

	public int getCount() { return count; }
	public int getMaxWeight() { return maxWeight; }

	public abstract boolean wantsMore();
	public abstract int fee();

	/** 실린 상자 무게 합 */
	public int totalWeight() {
		// TODO
		return 0;
	}

	/** 칸이 남았고, 실어도 maxWeight 이하이면 싣고 true. 아니면 false (안 실음) */
	public boolean load(Parcel p) {
		// TODO
		return false;
	}
}
