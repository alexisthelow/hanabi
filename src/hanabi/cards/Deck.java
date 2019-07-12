package hanabi.cards;

import java.util.Stack;

public class Deck {
	
	private Stack<Card> cards = new Stack<Card>();

	public Deck(int avalancheVariant) {
		super();
		//TODO logic for creation of new deck -- check variant settings, add each relevant card to deck 
	}

	public Stack<Card> getCards() {
		return cards;
	}

	//TODO add shuffle
	//TODO create Deck
	
}
