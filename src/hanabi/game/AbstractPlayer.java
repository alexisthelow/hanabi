package hanabi.game;

import java.util.ArrayList;

import javax.swing.JTable;

import hanabi.cards.Card;
import hanabi.cards.Color;
import hanabi.cards.GlobalCardTracker;
import hanabi.cards.Suit;
import hanabi.cards.SuitColorTracker;

public class AbstractPlayer {
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<SuitColorTracker[][]> cardInfoTables = new ArrayList<SuitColorTracker[][]>();
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
		ArrayList<SuitColorTracker[][]> indicatedCards = new ArrayList<SuitColorTracker[][]>();
		ArrayList<SuitColorTracker[][]> notIndicatedCards = new ArrayList<SuitColorTracker[][]>();
		
		for (Integer i = 0; i < this.cardInfoTables.size(); i++) {
			if (handIndices.contains(i)) {
				indicatedCards.add(this.cardInfoTables.get(i));
			}
			else {
				notIndicatedCards.add(this.cardInfoTables.get(i));
			}
		}
		for (SuitColorTracker[][] cardTable : indicatedCards) {
			
			boolean multicolorFound = false;
			for (int i = 0; i < globalCardTracker.getCards().length; i++) {
				if (globalCardTracker.getCards()[i][5] > 0) {
					multicolorFound = true;
				}
			}
			// if there are no multicolors remaining in the game (or they were never added) OR if multicolors are being indicated separately, we don't need to worry and can simply check maybe on the indicated color
			if (multicolorFound || !colorVariant.equals(ColorVariant.MULTICOLOR_WILD)) { // no multicolors found or multicolors indicated separately
				
				for (int i = 0; i < cardTable.length; i++) {
					for (int j = 0; j < cardTable[i].length; j++) {
						if (j == color.getValue() - 1) {
							cardTable[i][j] = SuitColorTracker.COLOR_TRUE;
						} else {
							cardTable[i][j] = SuitColorTracker.COLOR_FALSE;
						}
					}
				}
			}
			//are we indicating multicolors separately or together with other cards?
			else { //indicating together
				//check what player knows about indicated card.
				//TODO if we already have a COLOR_POSSIBLE for the indicated color, and it's indicated again, it's definitely that color
				boolean colorPreviouslyIndicated = false;
				for (int i = 0; i < cardTable.length; i++) {
					if (cardTable[i][color.getValue() - 1].equals(SuitColorTracker.MAYBE)) { // at least one value in this column is maybe, so color has been previously indicated
						colorPreviouslyIndicated = true;
					}
				}
				
				
				//TODO if we have a maybe for a non-multicolor color and a different color is indicated, it is certainly multicolor
				
			}
			
		}
			
	}
	
	public void receiveSuitInfo(int[] handIndex, Suit suit) {
		
	}
	//TODO deduce from visible cards
	//TODO give info
	//TODO discard
	
	public SuitColorTracker[][] getNewCardInfoTable() {
		
		SuitColorTracker[][] cardTable = {
				{SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN}, 
				{SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN}, 
				{SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN}, 
				{SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN}, 
				{SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN, SuitColorTracker.UNKNOWN}, 
		};
		
		return cardTable;
		
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public ArrayList<SuitColorTracker[][]> getCardInfoTables() {
		return cardInfoTables;
	}

	public void setCardInfoTables(ArrayList<SuitColorTracker[][]> cardInfoTables) {
		this.cardInfoTables = cardInfoTables;
	}

	public GlobalCardTracker getGlobalCardTracker() {
		return globalCardTracker;
	}

	public void setGlobalCardTracker(GlobalCardTracker globalCardTracker) {
		this.globalCardTracker = globalCardTracker;
	}
	
}
