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
						if (i == attribute.getValue() - 1) { // if it's the right row
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
					// indicated cards are complete
				}
				for (AttributeTracker[][] cardTable : notIndicatedCards) {
					for (int i = 0; i < cardTable.length; i++) {
						cardTable[i][attribute.getValue() - 1].setColor(FourState.NO);
					}
				}
				//non indicated cards complete
			} // end multicolor indicated together
		} // end color info processing
		
		// now we need to check each card table to see if it's been identified; if any have been, the card in hand at the relevant index should be indicated as identified and marked on the global card tracker
		for (int i = 0; i < this.cardInfoTables.size(); i++) { // for each cardInfoTable
			
			AttributeTracker[][] currentCardTable = this.cardInfoTables.get(i);
			
			for (Integer j = 0; j < currentCardTable.length; j++) { // for each of its rows
				for (Integer k = 0; k < currentCardTable[j].length; k++) { // for each column in that row
					if (currentCardTable[j][k].getColor().equals(FourState.YES) && currentCardTable[j][k].getNumber().equals(FourState.YES) && !this.hand.get(i).getOwningPlayerDeducedIdentity()) { // if both color and number are yes at that location
						this.hand.get(i).setOwningPlayerDeducedIdentity(true);
						try {
							this.globalCardTracker.cardSeen(new Card(j, k));
						} catch (Exception e) {
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}
				}
				
			}
		}
	}
	
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
