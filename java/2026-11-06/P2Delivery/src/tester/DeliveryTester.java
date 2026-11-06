package tester;

import java.util.function.BooleanSupplier;
import model.*;
import controller.*;

/** Day 5 ② 택배 상차 채점기 */
public class DeliveryTester {

	static int pass = 0, total = 0;

	static void check(String name, BooleanSupplier test) {
		total++;
		String result;
		try {
			if (test.getAsBoolean()) { pass++; result = "[OK]   "; }
			else result = "[FAIL] ";
		} catch (Throwable e) {
			result = "[FAIL] (" + e.getClass().getSimpleName() + ") ";
		}
		System.out.println(result + name);
	}

	static Parcel[] parcels(int... w) {
		Parcel[] p = new Parcel[w.length];
		for (int i = 0; i < w.length; i++) p[i] = new Parcel("P" + (i + 1), w[i]);
		return p;
	}

	public static void main(String[] args) {
		// --- Vehicle.totalWeight / load
		check("totalWeight: 빈 차 = 0", () -> new Van().totalWeight() == 0);
		check("load: 무게 여유 있으면 true, totalWeight 증가", () -> {
			Van v = new Van();
			return v.load(new Parcel("a", 30)) && v.load(new Parcel("b", 25)) && v.totalWeight() == 55 && v.getCount() == 2;
		});
		check("load: 무게 정확히 최대(10kg)까지는 OK", () -> {
			Bike b = new Bike();
			return b.load(new Parcel("a", 4)) && b.load(new Parcel("b", 6)) && b.totalWeight() == 10;
		});
		check("load: 무게 초과면 false, 안 실림", () -> {
			Bike b = new Bike();
			b.load(new Parcel("a", 7));
			return !b.load(new Parcel("b", 4)) && b.getCount() == 1 && b.totalWeight() == 7;
		});
		check("load: 칸(3개) 꽉 차면 false", () -> {
			Bike b = new Bike();
			b.load(new Parcel("a", 1)); b.load(new Parcel("b", 1)); b.load(new Parcel("c", 1));
			return !b.load(new Parcel("d", 1)) && b.getCount() == 3;
		});
		// --- Dispatcher.dispatchTo
		check("dispatchTo: Bike 는 8kg 이상이면 그만 받음", () -> {
			Dispatcher d = new Dispatcher(new ParcelQueue(parcels(3, 5, 1, 1)));
			Bike b = new Bike();
			int n = d.dispatchTo(b);
			return n == 2 && b.totalWeight() == 8 && d.getRejected() == 0;
		});
		check("dispatchTo: 못 실은 상자는 rejected+1 후 멈춤", () -> {
			ParcelQueue q = new ParcelQueue(parcels(5, 6, 1));
			Dispatcher d = new Dispatcher(q);
			Bike b = new Bike();
			int n = d.dispatchTo(b);
			return n == 1 && d.getRejected() == 1 && q.remaining() == 1;
		});
		check("dispatchTo: 상자가 떨어지면 멈춤", () -> {
			Dispatcher d = new Dispatcher(new ParcelQueue(parcels(10, 10)));
			Van v = new Van();
			return d.dispatchTo(v) == 2 && v.totalWeight() == 20;
		});
		check("dispatchTo: 큐 하나로 Bike → Van 차례로", () -> {
			Dispatcher d = new Dispatcher(new ParcelQueue(parcels(4, 4, 30, 30, 30, 30)));
			Bike b = new Bike(); Van v = new Van();
			int n1 = d.dispatchTo(b);
			int n2 = d.dispatchTo(v);
			return n1 == 2 && n2 == 3 && v.totalWeight() == 90 && d.getRejected() == 0;
		});
		// --- countVans / totalFee
		Vehicle[] fleet = { new Bike(), new Van(), null, new Van() };
		fleet[0].load(new Parcel("a", 2)); fleet[0].load(new Parcel("b", 3));
		fleet[1].load(new Parcel("c", 40));
		check("countVans: instanceof 로 Van 만 (null 칸 있음)", () -> Dispatcher.countVans(fleet) == 2);
		check("countVans: Van 없으면 0", () -> Dispatcher.countVans(new Vehicle[] { new Bike() }) == 0);
		check("totalFee: Bike 2000 + Van 9000 + Van 5000 = 16000 (null 건너뜀)", () -> Dispatcher.totalFee(fleet) == 16000);

		System.out.println("Score: " + pass + " / " + total);
	}
}
