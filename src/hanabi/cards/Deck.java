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
		
		if (colorVariant.equals(ColorVariant.NONE)) { //if no variant, no changes (deck size is 50)
			//do nothing
		}
		else if (colorVariant.equals(ColorVariant.MULTICOLOR_SINGLE)) { // add one of each multicolor (deck size is 55)
			for (int i = 0; i < 5; i++) { // for each number
				this.cards.add(new Card(i, 5));
			}
		}
		else { // we must be using all of the multicolors! (deck size is 60)
			for (int i = 0; i < 5; i++) { // for each number
				
				int repeat = i == 0 ? 3 : i == 4 ? 1 : 2; // if i is 0, add 3 cards; if 4, add 1; if 1-3, add 2
				int current = 0;
				
				do { 
					cards.add(new Card(i, 5));
					current++;
				} while (current < repeat); // add the card to the deck that many times
			}
		}
		
	}

	public Stack<Card> getCards() {
		return cards;
	}

	//TODO add shuffle
	//TODO create Deck
	
}
