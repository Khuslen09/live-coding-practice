package model;

public interface CardPlayerBehavior {

	/** wantsACard - 카드 한 장을 더 받겠는지 답한다. */
	public boolean wantsACard();

	/** receiveCard - 카드를 한 장 받는다. 손이 꽉 찼으면 받지 못하고 false. */
	public boolean receiveCard(Card c);
}
