package controller;

import model.*;

/** 주사위 칩 게임 테이블 — P1 GameController 와 같은 역할 */
public class DiceTable {

	private Guest guest;
	private Dealer dealer;

	public DiceTable(Guest g, Dealer d) {
		guest = g;
		dealer = d;
	}

	/** [4번] 한 판: 주사위 눈이 큰 쪽이 bet 개를 가져감 */
	public String round(int guestDice, int dealerDice, int bet) {
		if (guestDice > dealerDice) {
			guest.addChips(bet);
			dealer.removeChips(bet);
			return "손님 승! +" + bet;
		} else if (guestDice < dealerDice) {
			guest.removeChips(bet);
			dealer.addChips(bet);
			return "딜러 승! -" + bet;
		} else
			return "무승부";
	}

	/** [5번] 손님 칩, 딜러 칩, 10 중 가장 작은 값 */
	public int maxBet() {
		int max = 10;
		if (guest.getChips() < max)
			max = guest.getChips();
		if (dealer.getChips() < max)
			max = dealer.getChips();
		return max;
	}
}
