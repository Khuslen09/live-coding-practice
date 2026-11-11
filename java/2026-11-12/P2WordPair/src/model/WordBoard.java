package model;

/** 단어 짝맞추기 판: 2줄 x 3칸 = 카드 6장 (3쌍) — P1 의 GameBoard + GameController.turn 을 합친 역할
 *  화면 번호:  1 2 3
 *             4 5 6 */
public class WordBoard {

	private WordCard[][] grid;
	private int matched;   // 맞춘 짝 수

	/** cards(6장)를 순서대로 grid 에 채운다 (완성) */
	public WordBoard(WordCard[] cards) {
		grid = new WordCard[2][3];
		int k = 0;
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 3; j++) {
				grid[i][j] = cards[k];
				k++;
			}
	}

	public int matched() {
		return matched;
	}

	/** [2번] 단어 하나마다 WordCard 를 2장씩 따로 new 해서 길이 words.length*2 배열로 리턴
	 *  순서: words[0], words[0], words[1], words[1], ... */
	public static WordCard[] createPairs(String[] words) {
		// TODO
		return null;
	}

	/** [3번] 화면 번호 number(1~6) 의 카드. 1 → [0][0], 3 → [0][2], 4 → [1][0], 6 → [1][2]. 범위 밖이면 null */
	public WordCard cardAt(int number) {
		// TODO
		return null;
	}

	/** [4번] 두 장 고르기. 규칙은 P1 turn 과 같음:
	 *  a == b, 범위 밖, 이미 앞면인 카드가 있으면 → 아무것도 안 바꾸고 false
	 *  짝이면 둘 다 앞면으로 두고 matched 1 증가, true / 아니면 둘 다 뒷면, false */
	public boolean pick(int a, int b) {
		// TODO
		return false;
	}

	/** [5번] 정확히 "맞춘 짝 1 / 3" 형식 (전체 짝 수 = 카드 6장 / 2 = 3) */
	public String status() {
		// TODO
		return "";
	}
}
