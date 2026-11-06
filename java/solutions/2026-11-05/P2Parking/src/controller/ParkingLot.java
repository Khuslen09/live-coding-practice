package controller;

import model.*;

public class ParkingLot {
	private Vehicle[] slots;
	private int count;

	public ParkingLot(int size) {
		slots = new Vehicle[size];
		count = 0;
	}

	public int getCount() {
		return count;
	}

	/** 번호판이 plate 인 차의 칸 번호, 없으면 -1 */
	public int findSlot(String plate) {
		Key k = new PlateKey(plate);
		for (int i = 0; i < slots.length; i++)
			if (slots[i] != null && slots[i].getKey().equals(k))
				return i;
		return -1;
	}

	public Vehicle find(String plate) {
		int i = findSlot(plate);
		if (i == -1)
			return null;
		return slots[i];
	}

	public boolean park(Vehicle v) {
		if (v == null)
			return false;
		if (findSlot(((PlateKey) v.getKey()).getPlate()) != -1)
			return false;
		for (int i = 0; i < slots.length; i++)
			if (slots[i] == null) {
				slots[i] = v;
				count++;
				return true;
			}
		return false;   // 빈 칸 없음 (크기 안 늘림)
	}

	public int leave(String plate, int hours) {
		int i = findSlot(plate);
		if (i == -1 || hours < 1)
			return -1;
		int f = slots[i].fee(hours);   // 다형성: Car/Truck 의 fee 가 알아서 불림
		slots[i] = null;
		count--;
		return f;
	}

	public int countTrucks() {
		int n = 0;
		for (int i = 0; i < slots.length; i++)
			if (slots[i] instanceof Truck)
				n++;
		return n;
	}

	public Truck heaviestTruck() {
		Truck best = null;
		for (int i = 0; i < slots.length; i++)
			if (slots[i] instanceof Truck) {
				Truck t = (Truck) slots[i];
				if (best == null || t.getTons() > best.getTons())   // > : 같으면 앞 칸
					best = t;
			}
		return best;
	}
}
