package hanabi.cards;

import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> cards = new ArrayList<Card>();

	public Hand(ArrayList<Card> cards) {
		super();
		this.cards = cards;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

}
