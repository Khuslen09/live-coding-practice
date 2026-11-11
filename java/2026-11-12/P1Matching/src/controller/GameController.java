package controller;

import model.*;
import view.*;

/** 게임 진행
 *  원본: Doggzone/cse2016 exam1-2022.md — GameController.play() 의 한 턴을 turn 메소드로 뺀 버전 */
public class GameController {

	private GameBoard board;
	private BoardWriter writer;
	private Reader reader;

	public GameController(GameBoard b, BoardWriter w, Reader r) {
		board = b;
		writer = w;
		reader = r;
	}

	/** [7번] 한 턴: 번호 fst, snd (1~16) 두 칸을 뒤집어 본다.
	 *  - fst == snd 이거나, 둘 중 하나라도 범위 밖이면 아무것도 바꾸지 않고 false
	 *  - 두 칸 중 하나라도 이미 뒤집혀(revealed) 있으면 아무것도 바꾸지 않고 false
	 *  - 둘 다 reveal 한 뒤, 짝이면 board.upgrade() 하고 true (뒤집힌 채로 둠)
	 *  - 짝이 아니면 둘 다 hide 하고 false */
	public boolean turn(int fst, int snd) {
		// TODO
		return false;
	}

	/** 실제 게임 반복 (완성) — 화면에 보여 주고 2초 뒤 결과 반영 */
	public void play() {
		while (board.point() < 16) {
			int fst = reader.readInput();
			Cell c1 = board.cellAt(fst);
			if (c1.revealed())            // 이미 맞춘 칸은 다시 고르기
				continue;
			c1.reveal();
			writer.repaint();
			int snd = reader.readInput();
			while (snd == fst)
				snd = reader.readInput();
			Cell c2 = board.cellAt(snd);
			if (c2.revealed()) {
				c1.hide();
				writer.repaint();
				continue;
			}
			c2.reveal();
			writer.repaint();
			delay(2000);
			c1.hide();                    // 판정은 turn 에게 맡기려고 원래대로 돌려놓고
			c2.hide();
			turn(fst, snd);                // 짝이면 다시 뒤집힌 채로, 아니면 그대로 숨김
			writer.repaint();
			System.out.println(board.status());
		}
		System.out.println("모두 맞췄어요!");
	}

	/** delay - how_long millisecond 동안 실행 정지 */
	private void delay(int how_long) {
		try {
			Thread.sleep(how_long);
		} catch (InterruptedException e) {
		}
	}
}
