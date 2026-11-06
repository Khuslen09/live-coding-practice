package controller;

import model.*;

public class BlackjackController {

	private Dealer dealer;
	private HumanPlayer player;
	private ComputerPlayer computer;

	public BlackjackController(Dealer d, HumanPlayer p, ComputerPlayer c) {
		dealer = d;
		player = p;
		computer = c;
	}

	/** playRound - 한 판 진행하고 결과 문자열을 리턴 ("BLACKJACK" / "WIN" / "LOSE" / "DRAW")
	 *  1. 사람, 컴퓨터, 사람, 컴퓨터 순서로 1장씩 (dealOneTo 4번)
	 *  2. 사람 점수가 바로 21 → youWinBlackjack(), "BLACKJACK" (여기서 끝)
	 *  3. 사람에게 dealTo → 21 초과면 youLose(), "LOSE"
	 *  4. 컴퓨터에게 dealTo → 컴퓨터 21 초과 또는 사람이 더 크면 youWin() "WIN",
	 *     같으면 youDraw() "DRAW", 아니면 youLose() "LOSE" */
	public String playRound() {
		// TODO
		return "";
	}

	public static void main(String[] args) {
		HumanPlayer me = new HumanPlayer(11, "Khuslen");
		BlackjackController game = new BlackjackController(new Dealer(), me, new ComputerPlayer(11));
		String result = game.playRound();
		System.out.println("Result: " + result + ", chips: " + me.countChips());
	}
}
