package hanabi.cards;

import java.util.ArrayList;

public class PlayedSet {
	
	private ArrayList<Card> blues = new ArrayList<Card>();
	private ArrayList<Card> greens = new ArrayList<Card>();
	private ArrayList<Card> reds = new ArrayList<Card>();
	private ArrayList<Card> whites = new ArrayList<Card>();
	private ArrayList<Card> yellows = new ArrayList<Card>();
	private ArrayList<Card> multicolors = new ArrayList<Card>();
	
	
	public Boolean playableCheck(Card card) throws Exception {
		
		ArrayList<Card> relevantColor;
		
		switch (card.getColor()) {
		
			case BLUE:
				relevantColor = blues;
				break;
				
			case GREEN:
				relevantColor = greens;
				break;
				
			case RED:
				relevantColor = reds;
				break;
				
			case WHITE:
				relevantColor = whites;
				break;
				
			case YELLOW:
				relevantColor = yellows;
				break;
				
			case MULTICOLOR:
				relevantColor = multicolors;
				break;
			default:
				throw new Exception("PlayedSet.playableCheck: couldn't find correct card array for card " + card.getColor() + card.getSuit().getNumeral());
				break;
			
		}
		
		if(card.getSuit().getNumeral()==relevantColor.size() + 1) {
			relevantColor.add(card);
		}
		else {
			//TODO the check has failed, a fuse should be lost, and the card added to the discard pile
		}
	}
	
	//TODO play success
	//TODO play fail
	

}
