package hanabi.cards;

import java.util.Stack;

import hanabi.game.ColorVariant;

public class Deck {
	
	private Stack<Card> cards;

	public Deck(ColorVariant colorVariant) {
		super();
		
		this.cards = new Stack<Card>();
		//TODO logic for creation of new deck -- check variant settings, add each relevant card to deck 
		
		//create a standard deck
		
		for (int i = 0; i < 5; i++) { // for each number
			for (int j = 0; j < 5; j++) { // for each color except multicolor
				
				int repeat = i == 0 ? 3 : i == 4 ? 1 : 2; // if i is 0, add 3 cards; if 4, add 1; if 1-3, add 2
				int current = 0;
				
				do { 
					cards.add(new Card(i, j));
					current++;
				} while (current < repeat); // add the card to the deck that many times
			}
		}
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
