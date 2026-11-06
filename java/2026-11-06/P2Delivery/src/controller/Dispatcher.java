package controller;

import model.*;

public class Dispatcher {

	private ParcelQueue queue;
	private int rejected = 0;

	public Dispatcher(ParcelQueue q) {
		queue = q;
	}

	public int getRejected() { return rejected; }

	/** dispatchTo - 큐에 상자가 있고 v 가 더 원하는 동안 1개씩 꺼내 싣는다.
	 *  싣지 못한 상자가 나오면 rejected 를 1 늘리고 바로 멈춘다.
	 *  @return 이번에 실은 상자 수 */
	public int dispatchTo(LoadBehavior v) {
		// TODO
		return 0;
	}

	/** countVans - 배열에서 Van 인 차 수 (null 칸이 있을 수 있음) */
	public static int countVans(Vehicle[] vs) {
		// TODO
		return 0;
	}

	/** totalFee - 모든 차의 fee() 합 (null 칸은 건너뜀). Bike/Van 을 구분하지 말 것! */
	public static int totalFee(Vehicle[] vs) {
		// TODO
		return 0;
	}
}
