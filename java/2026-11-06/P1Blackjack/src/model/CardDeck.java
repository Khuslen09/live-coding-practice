package model;

/** 원본 그대로 (labs/lab08/cardgame/CardDeck.java) */
public class CardDeck {

	private int card_count;
	private Card[] deck = new Card[4 * Card.SIZE_OF_ONE_SUIT];

	public CardDeck() {
		createDeck();
	}

	private void createSuit(String which_suit) {
		for (int i = 1; i <= Card.SIZE_OF_ONE_SUIT; i++) {
			deck[card_count] = new Card(which_suit, i);
			card_count = card_count + 1;
		}
	}

	private void createDeck() {
		createSuit(Card.SPADES);
		createSuit(Card.HEARTS);
		createSuit(Card.CLUBS);
		createSuit(Card.DIAMONDS);
	}

	/** newCard - 임의의 카드 한 장. 다 떨어지면 새 한 벌을 만든다. */
	public Card newCard() {
		if (!moreCards())
			createDeck();
		int index = (int) (Math.random() * card_count);
		Card card_to_deal = deck[index];
		for (int i = index + 1; i < card_count; i++)
			deck[i - 1] = deck[i];
		card_count = card_count - 1;
		return card_to_deal;
	}

	private boolean moreCards() {
		return card_count > 0;
	}
}
