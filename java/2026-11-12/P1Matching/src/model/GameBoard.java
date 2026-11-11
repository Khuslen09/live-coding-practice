package model;

import java.awt.Color;
import java.util.Random;

/** 4 x 4 짝맞추기 판
 *  원본: Doggzone/cse2016 exam1-2022.md — GameBoard
 *  (채점을 위해 createTwinCells·makeSquare 를 public 으로 바꾸고, 섞지 않는 생성자와 cellAt·revealedCount·status 를 추가) */
public class GameBoard {

	private Cell[][] square;
	private int point;

	public Cell[][] square() {
		return square;
	}

	public int point() {
		return point;
	}

	public void upgrade() {
		point += 2;
	}

	/** 원래 게임용: 8가지 색 x 2 장을 섞어서 판 만들기 (완성) */
	public GameBoard() {
		square = new Cell[4][4];
		Color[] colors = { Color.BLUE, Color.CYAN, Color.PINK, Color.RED,
				Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE };
		Cell[] twins = createTwinCells(colors);
		makeSquare(shuffle(twins));
	}

	/** 채점용: 섞지 않고 cells 순서 그대로 판 만들기 (완성) */
	public GameBoard(Cell[] cells) {
		square = new Cell[4][4];
		makeSquare(cells);
	}

	/** [2번] 1차원 cells(16장)를 앞에서부터 한 줄에 4장씩 square[0][0], [0][1], ... [3][3] 에 채운다 */
	public void makeSquare(Cell[] cells) {
		// TODO
	}

	/** [3번] 색깔 cs 하나마다 Cell 을 "2개씩 따로" new 해서, 길이 cs.length*2 배열로 리턴
	 *  순서: cs[0], cs[0], cs[1], cs[1], ... */
	public Cell[] createTwinCells(Color[] cs) {
		// TODO
		return null;
	}

	/** 무작위 섞기 (완성, 원본 그대로) */
	private Cell[] shuffle(Cell[] twins) {
		int index;
		Cell temp;
		Random random = new Random();
		for (int i = twins.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = twins[index];
			twins[index] = twins[i];
			twins[i] = temp;
		}
		return twins;
	}

	/** [4번] 화면 번호 number(1~16) 의 칸. 1 → [0][0], 4 → [0][3], 5 → [1][0], 16 → [3][3]
	 *  범위 밖이면 null */
	public Cell cellAt(int number) {
		// TODO
		return null;
	}

	/** [5번] 지금 뒤집혀(revealed) 있는 칸 수 */
	public int revealedCount() {
		// TODO
		return 0;
	}

	/** [6번] 정확히 "점수: 4 / 16 (남은 짝 6쌍)" 형식. 남은 짝 = (16 - 점수) / 2 */
	public String status() {
		// TODO
		return "";
	}
}
