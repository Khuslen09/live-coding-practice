package model;

import java.util.Scanner;

public class HumanPlayer extends CardPlayer {

	private static Scanner sc = new Scanner(System.in);   // 닫지 않기!
	private String name;
	private int chips = 0;

	public HumanPlayer(int max_cards, String n) {
		super(max_cards);
		name = n;
	}

	public String getName() {
		return name;
	}

	/** 콘솔에서 묻기 (tester 는 이 메소드를 재정의한 가짜 플레이어를 씀) */
	public boolean wantsACard() {
		System.out.print(name + ", 한 장 더? (Y/N) ");
		String response = sc.next();
		return response.equals("Y") || response.equals("y");
	}

	public void youWin() { chips += 1; }
	public void youWinBlackjack() { chips += 2; }
	public void youLose() { chips -= 1; }
	public void youDraw() { }
	public int countChips() { return chips; }
}
