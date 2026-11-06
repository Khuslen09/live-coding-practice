package model;

public class ComputerPlayer extends CardPlayer {

	public ComputerPlayer(int max_cards) {
		super(max_cards);
	}

	/** 점수가 16 이하이면 한 장 더 받는다 */
	public boolean wantsACard() {
		// TODO
		return false;
	}
}
