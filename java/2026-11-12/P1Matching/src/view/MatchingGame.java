package view;

import model.*;
import controller.*;

/** 실행용 (완성) — 창이 뜨고 번호 입력창으로 플레이. 채점은 tester.MatchingTester */
public class MatchingGame {

	public static void main(String[] args) {
		GameBoard board = new GameBoard();
		BoardWriter writer = new BoardWriter(board);
		Reader reader = new Reader();
		new GameController(board, writer, reader).play();
	}
}
