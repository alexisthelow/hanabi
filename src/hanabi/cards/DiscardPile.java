package hanabi.cards;

import java.util.ArrayList;

public class DiscardPile {
	
	private ArrayList<Card> blues;
	private ArrayList<Card> greens;
	private ArrayList<Card> reds;
	private ArrayList<Card> whites;
	private ArrayList<Card> yellows;
	private ArrayList<Card> multicolors;
	
	public Boolean processDiscard(Card card) throws Exception {
		
		ArrayList<Card> relevantDiscardPile;
		switch (card.getColor()) {
			case BLUE:
				relevantDiscardPile = blues;
				break;
	
			case GREEN:
				relevantDiscardPile = greens;
				break;
	
			case RED:
				relevantDiscardPile = reds;
				break;
	
			case WHITE:
				relevantDiscardPile = whites;
				break;
	
			case YELLOW:
				relevantDiscardPile = yellows;
				break;
	
			case MULTICOLOR:
				relevantDiscardPile = multicolors;
				break;
				
			default:
				throw new Exception("DiscardPile.acceptDiscard was unable to locate the correct discard pile for " + card.toString());
		}
		relevantDiscardPile.add(card);
		
		
	}

}
