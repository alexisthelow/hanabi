package hanabi.game;

import java.util.ArrayList;

import hanabi.cards.Card;
import hanabi.cards.Deck;
import hanabi.cards.PlayedSet;

public class Game {
	
	private Boolean variantOne = false;
	private Boolean variantTwo = false;
	private Boolean variantThree = false;
	private Boolean variantFour = false;
	private ArrayList<AbstractPlayer> players = new ArrayList<AbstractPlayer>();
	private Deck deck;
	private PlayedSet playedSet;
	
	public Boolean processPlayedCard(Card card) throws Exception {
		
		ArrayList<Card> relevantColor;
		
		switch (card.getColor()) {
		
			case BLUE:
				relevantColor = playedSet.getBlues();
				break;
				
			case GREEN:
				relevantColor = playedSet.getGreens();
				break;
				
			case RED:
				relevantColor = playedSet.getReds();
				break;
				
			case WHITE:
				relevantColor = playedSet.getWhites();
				break;
				
			case YELLOW:
				relevantColor = playedSet.getYellows();
				break;
				
			case MULTICOLOR:
				relevantColor = playedSet.getMulticolors();
				break;
				
			default:
				throw new Exception("Game.processPlayedCard(): couldn't find correct card array for card " + card.getColor() + card.getSuit().getNumeral());
			
		}
		
		if(card.getSuit().getNumeral()==relevantColor.size() + 1) {
			relevantColor.add(card);
		}
		else {
			//TODO the check has failed, a fuse should be lost, and the card added to the discard pile
		}
	}
	
}
