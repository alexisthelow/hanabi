package hanabi.game;

import java.util.ArrayList;

import hanabi.cards.Card;
import hanabi.cards.GlobalCardTracker;
import hanabi.cards.identifiers.AttributeTracker;
import hanabi.cards.identifiers.CardAttribute;
import hanabi.cards.identifiers.FourState;
import hanabi.cards.identifiers.Number;

public class AbstractPlayer {
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<AttributeTracker[][]> cardInfoTables = new ArrayList<AttributeTracker[][]>();
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
	public void receiveInfo(ArrayList<Integer> handIndices, CardAttribute attribute, ColorVariant colorVariant) {
		
		ArrayList<AttributeTracker[][]> indicatedCards = new ArrayList<AttributeTracker[][]>();
		ArrayList<AttributeTracker[][]> notIndicatedCards = new ArrayList<AttributeTracker[][]>();
		
		for (Integer i = 0; i < this.cardInfoTables.size(); i++) { // for each card table
			if (handIndices.contains(i)) {	// if its index is in the array of indicated indices
				indicatedCards.add(this.cardInfoTables.get(i)); // add it to this array
			}
			else { // if not
				notIndicatedCards.add(this.cardInfoTables.get(i)); // add it to this one
			}
		}
		
		// which attribute did we get?
		
		if (attribute.getAttributeType().equals("number")) { // it's a number
			for (AttributeTracker[][] cardTable : indicatedCards) { // for each indicated card
				for (int i = 0; i < cardTable.length; i++) { // for each row
					for (int j = 0; j < cardTable[i].length; j++) { // for each cell
						if (i == attribute.getValue()) { // if it's the right row
							cardTable[i][j].setNumber(FourState.YES); // set it to YES
						}
						else { // otherwise
							cardTable[i][j].setNumber(FourState.NO); // set it to NO
						}
					}
				}
			}
			for (AttributeTracker[][] cardTable : notIndicatedCards) { // for each non indicated card
				for (int i = 0; i < cardTable[attribute.getValue() - 1].length; i++) {
					cardTable[attribute.getValue() - 1][i].setNumber(FourState.NO); // set the relevant row to NO
				}
			}
		}
		else { // it's a color

			//TODO deal with indicated cards
			// what color variant are we playing with?
			if (!colorVariant.equals(ColorVariant.MULTICOLOR_WILD)) {// if multicolors not indicated together
				for (AttributeTracker[][] cardTable : indicatedCards) {
					for (int i = 0; i < cardTable.length; i++) {
						for (int j = 0; j < cardTable[i].length; j++) {
							if (j == attribute.getValue() - 1) { // set the relevant column to yes
								cardTable[i][j].setColor(FourState.YES);
							}
							else { // and the others to no
								cardTable[i][j].setColor(FourState.NO);
							}
						}
					}
				}
				
			}
			else { // if multicolor indicated together:
				
				
				for (AttributeTracker[][] cardTable : indicatedCards) { // for each card
					boolean maybeFoundMatchesOld = false;
					boolean maybeFoundDoesNotMatchOld = false;
					for (int i = 0; i < cardTable.length; i++) { // has a color already been indicated on this card? 
						for (int j = 0; j < cardTable[i].length; j++) {
							if (cardTable[i][j].getColor().equals(FourState.MAYBE)) { // it has. does the newly indicated color match the old indicated color?
								if (j == attribute.getValue() - 1) { // it does
									maybeFoundMatchesOld = true;
								}
							}
						}
					}
					if (maybeFoundMatchesOld) { // maybe was found in the same color column as the indicated color
						for (int i = 0; i < cardTable.length; i++) {
							for (int j = 0; j < cardTable[i].length; j++) { // set relevant column to yes and multicolor column to no
								if (j == attribute.getValue() - 1) { 
									cardTable[i][j].setColor(FourState.YES);
								}
								else {
									cardTable[i][j].setColor(FourState.NO);
								}
							}
						}
						
					}
					else if (maybeFoundDoesNotMatchOld) { // maybe was found but it was not in the indicated color column 
						for (int i = 0; i < cardTable.length; i++) {
							for (int j = 0; j < cardTable[i].length; j++) { // set that column to no and multicolor column to yes
								if (j == 5) { // five is the value of the multicolor column
									cardTable[i][j].setColor(FourState.YES);
								}
								else {
									cardTable[i][j].setColor(FourState.NO);
								}
							}
						}
					}
					else { // no maybe found, so set that column and multicolor column to maybe 
						for (int i = 0; i < cardTable.length; i++) {
							for (int j = 0; j < cardTable[i].length; j++) {
								if (j == attribute.getValue() - 1 || j == 5) {
									cardTable[i][j].setColor(FourState.MAYBE);
								}
								else {
									cardTable[i][j].setColor(FourState.NO);
								}
							}
						}
					}
					
				}
					
					
				
				
			}

				
				
					
			//TODO deal with unindicated cards
				// multicolors indicated separately?
					//if so, set relevant column to no
					//if not, set relevant column AND multicolor column to no
		}
	}
	
//	public void receiveColorInfo(ArrayList<Integer> handIndices, CardAttribute attribute, ColorVariant colorVariant) {
//		ArrayList<AttributeTracker[][]> indicatedCards = new ArrayList<AttributeTracker[][]>();
//		ArrayList<AttributeTracker[][]> notIndicatedCards = new ArrayList<AttributeTracker[][]>();
//		
//		for (Integer i = 0; i < this.cardInfoTables.size(); i++) {
//			if (handIndices.contains(i)) {
//				indicatedCards.add(this.cardInfoTables.get(i));
//			}
//			else {
//				notIndicatedCards.add(this.cardInfoTables.get(i));
//			}
//		}
//		for (AttributeTracker[][] cardTable : indicatedCards) {
//			
//			boolean multicolorFound = false;
//			for (int i = 0; i < globalCardTracker.getCards().length; i++) {
//				if (globalCardTracker.getCards()[i][5] > 0) {
//					multicolorFound = true;
//				}
//			}
//			// if there are no multicolors remaining in the game (or they were never added) OR if multicolors are being indicated separately, we don't need to worry and can simply check maybe on the indicated color
//			if (multicolorFound || !colorVariant.equals(ColorVariant.MULTICOLOR_WILD)) { // no multicolors found or multicolors indicated separately
//				
//				for (int i = 0; i < cardTable.length; i++) {
//					for (int j = 0; j < cardTable[i].length; j++) {
//						if (j == color.getValue() - 1) {
//							cardTable[i][j] = SuitColorTracker.COLOR_TRUE;
//						} else {
//							cardTable[i][j] = SuitColorTracker.COLOR_FALSE;
//						}
//					}
//				}
//			}
//			//are we indicating multicolors separately or together with other cards?
//			else { //indicating together
//				//check what player knows about indicated card.
//				//TODO if we already have a COLOR_POSSIBLE for the indicated color, and it's indicated again, it's definitely that color
//				boolean colorPreviouslyIndicated = false;
//				for (int i = 0; i < cardTable.length; i++) {
//					if (cardTable[i][color.getValue() - 1].equals(SuitColorTracker.COLOR_POSSIBLE)) { // at least one value in this column is COLOR_POSSIBLE, so color has been previously indicated
//						colorPreviouslyIndicated = true;
//					}
//				}
//				
//				
//				//TODO if we have a maybe for a non-multicolor color and a different color is indicated, it is certainly multicolor
//				
//			}
//			
//		}
//			
//	}
	
	public void receiveSuitInfo(int[] handIndex, Number suit) {
		
	}
	//TODO deduce from visible cards
	//TODO give info
	//TODO discard
	
	public AttributeTracker[][] getNewCardInfoTable() {
		
		AttributeTracker[][] cardTable = new AttributeTracker[5][6];
		for (int i = 0; i < cardTable.length; i++) {
			for (int j = 0; j < cardTable[i].length; j++) {
				cardTable[i][j] = new AttributeTracker();
			}
		}
		
		return cardTable;
		
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public ArrayList<AttributeTracker[][]> getCardInfoTables() {
		return cardInfoTables;
	}

	public void setCardInfoTables(ArrayList<AttributeTracker[][]> cardInfoTables) {
		this.cardInfoTables = cardInfoTables;
	}

	public GlobalCardTracker getGlobalCardTracker() {
		return globalCardTracker;
	}

	public void setGlobalCardTracker(GlobalCardTracker globalCardTracker) {
		this.globalCardTracker = globalCardTracker;
	}
	
}
