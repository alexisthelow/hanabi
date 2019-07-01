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
		
		switch (card.getColor()) {
			case BLUE:
				
				break;
				
			case GREEN:
				
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
