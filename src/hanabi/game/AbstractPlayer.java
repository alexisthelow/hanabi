package hanabi.game;

import java.util.ArrayList;

import javax.swing.JTable;

import hanabi.cards.Card;
import hanabi.cards.Color;
import hanabi.cards.GlobalCardTracker;
import hanabi.cards.Suit;
import hanabi.cards.FourState;

public class AbstractPlayer {
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<FourState[][]> cardInfoTables = new ArrayList<FourState[][]>();
	private GlobalCardTracker globalCardTracker;


	public AbstractPlayer(ArrayList<Card> hand, ColorVariant colorVariant) {
		super();
		this.hand = hand;
		this.globalCardTracker = new GlobalCardTracker(colorVariant);
		
		for (Card c : hand) {
			this.cardInfoTables.add(getNewCardInfoTable());
		}
	}

	public Card playCard(int handIndex) { //return true if successful; return false if not
		Card playedCard = this.hand.remove(handIndex);
		//TODO draw card
		//TODO replace card info table
		return playedCard;
	}
	
	public void gainCardToHand(Card card) {
		this.hand.add(card);
	}
	
	//TODO receive and interpret info
	
	
	public void receiveColorInfo(ArrayList<Integer> handIndices, Color color, ColorVariant colorVariant) {
		ArrayList<FourState[][]> indicatedCards = new ArrayList<FourState[][]>();
		ArrayList<FourState[][]> notIndicatedCards = new ArrayList<FourState[][]>();
		
		for (Integer i = 0; i < this.cardInfoTables.size(); i++) {
			if (handIndices.contains(i)) {
				indicatedCards.add(this.cardInfoTables.get(i));
			}
			else {
				notIndicatedCards.add(this.cardInfoTables.get(i));
			}
		}
		for (FourState[][] cardTable : indicatedCards) {
			
			boolean multicolorFound = false;
			for (int i = 0; i < globalCardTracker.getCards().length; i++) {
				if (globalCardTracker.getCards()[i][5] > 0) {
					multicolorFound = true;
				}
			}
			
			if (multicolorFound) { // if there are no multicolors remaining in the game, we don't need to worry and can simply check yes on the indicated color
				
				for (int i = 0; i < cardTable.length; i++) {
					for (int j = 0; j < cardTable[i].length; j++) {
						if (j == color.getValue() - 1) {
							cardTable[i][j] = FourState.YES;
						} else {
							cardTable[i][j] = FourState.NO;
						}
					}
				}
			}
			//if there are multicolors:
			//TODO are we indicating multicolors separately or together with other cards?
			//TODO if we already have a maybe for a color, and it's indicated again, it's definitely that color
			//TODO if we have a maybe for a non-multicolor color and a different color is indicated, it is certainly multicolor
			
		}
			
	}
	
	public void receiveSuitInfo(int[] handIndex, Suit suit) {
		
	}
	//TODO deduce from visible cards
	//TODO give info
	//TODO discard
	
	public FourState[][] getNewCardInfoTable() {
		
		FourState[][] cardTable = {
				{FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN}, 
				{FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN}, 
				{FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN}, 
				{FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN}, 
				{FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN, FourState.UNKNOWN}, 
		};
		
		return cardTable;
		
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public ArrayList<FourState[][]> getCardInfoTables() {
		return cardInfoTables;
	}

	public void setCardInfoTables(ArrayList<FourState[][]> cardInfoTables) {
		this.cardInfoTables = cardInfoTables;
	}

	public GlobalCardTracker getGlobalCardTracker() {
		return globalCardTracker;
	}

	public void setGlobalCardTracker(GlobalCardTracker globalCardTracker) {
		this.globalCardTracker = globalCardTracker;
	}
	
}
