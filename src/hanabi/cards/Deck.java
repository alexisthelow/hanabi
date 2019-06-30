package hanabi.cards;

import java.util.ArrayList;

public class Deck {
	
	private ArrayList<Card> cards = new ArrayList<Card>();

	public Deck(ArrayList<Card> cards) {
		super();
		this.cards = cards;
		//TODO logic for creation of new deck -- check variant settings, add each relevant card to deck 
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	//TODO add shuffle
	
	

}
