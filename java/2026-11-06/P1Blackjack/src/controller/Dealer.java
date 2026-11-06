package controller;

import model.*;

public class Dealer {

	private CardDeck deck;

	public Dealer() {
		deck = new CardDeck();
	}

	/** tester 용: 정해진 순서로 카드를 내주는 덱을 받는다 */
	public Dealer(CardDeck d) {
		deck = d;
	}

	/** dealOneTo - 덱에서 카드 1장을 뽑아 p 에게 준다 */
	public void dealOneTo(CardPlayerBehavior p) {
		// TODO
	}

	/** dealTo - p 가 원하는 동안 계속 1장씩 준다.
	 *  p 가 카드를 못 받으면(손이 꽉 참) 거기서 멈춘다. */
	public void dealTo(CardPlayerBehavior p) {
		// TODO
	}
}
