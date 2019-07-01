package hanabi.cards;

import java.util.ArrayList;

public class PlayedSet {
	
	private ArrayList<Card> blues = new ArrayList<Card>();
	private ArrayList<Card> greens = new ArrayList<Card>();
	private ArrayList<Card> reds = new ArrayList<Card>();
	private ArrayList<Card> whites = new ArrayList<Card>();
	private ArrayList<Card> yellows = new ArrayList<Card>();
	private ArrayList<Card> multicolors = new ArrayList<Card>();
	
	
	public Boolean playableCheck(Card card) {
		
		ArrayList<Card> relevantColor;
		
		switch (card.getColor()) {
		
			case BLUE:
				relevantColor = blues;
				break;
				
			case GREEN:
				relevantColor = 
				break;
				
			case RED:
				
				break;
				
			case WHITE:
				
				break;
				
			case YELLOW:
				
				break;
				
			case MULTICOLOR:
				
				break;
			
		}
	}
	
	//TODO play success
	//TODO play fail
	

}
