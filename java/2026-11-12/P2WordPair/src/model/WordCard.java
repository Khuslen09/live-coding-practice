package model;

/** 단어 카드 한 장 (단어 + 앞면이 보이는지) — P1 의 Cell 과 같은 역할 */
public class WordCard {

	private String word;
	private boolean faceUp;

	public WordCard(String word) {
		this.word = word;
	}

	public String word() {
		return word;
	}

	public boolean faceUp() {
		return faceUp;
	}

	public void show() {
		faceUp = true;
	}

	public void hide() {
		faceUp = false;
	}

	/** [1번] c 와 단어가 같으면 true */
	public boolean isPairWith(WordCard c) {
		// TODO
		return false;
	}
}
