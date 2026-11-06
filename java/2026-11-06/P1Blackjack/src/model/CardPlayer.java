package model;

public abstract class CardPlayer implements CardPlayerBehavior {

	private Card[] hand;     // 갖고 있는 카드
	private int card_count;  // 갖고 있는 카드 장 수

	public CardPlayer(int max_cards) {
		hand = new Card[max_cards];
		card_count = 0;
	}

	/** 자식 클래스가 정한다 */
	public abstract boolean wantsACard();

	/** receiveCard - hand 에 자리가 있으면 맨 뒤에 넣고 true, 꽉 찼으면 false */
	public boolean receiveCard(Card c) {
		// TODO
		return false;
	}

	public Card[] showCards() {
		Card[] card = new Card[card_count];
		for (int i = 0; i < card_count; i++)
			card[i] = hand[i];
		return card;
	}

	/** totalScore - 블랙잭 점수
	 *  2~10 은 숫자 그대로, J Q K 는 10, A 는 11.
	 *  합이 21 을 넘으면 A 하나를 1 로 바꿔서(-10) 다시 본다. A 가 남아 있는 동안 반복. */
	public int totalScore() {
		// TODO
		return 0;
	}
}
