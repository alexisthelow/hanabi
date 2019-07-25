package hanabi.cards;

import java.util.Stack;

import hanabi.game.ColorVariant;

public class Deck {
	
	private Stack<Card> cards = new Stack<Card>();

	public Deck(ColorVariant colorVariant) {
		super();
		//TODO logic for creation of new deck -- check variant settings, add each relevant card to deck 
		
		//create a standard deck
		
		
		//if no variant, no changes
		//if multicolor or multicolor wild, add full set of multicolors
		//if multicolor single, add only one of each multicolor
		
	}

	public Stack<Card> getCards() {
		return cards;
	}

	//TODO add shuffle
	//TODO create Deck
	
}
