package hanabi.game;

import java.util.ArrayList;

import hanabi.cards.Card;
import hanabi.cards.Deck;
import hanabi.cards.PersonalCardTracker;
import hanabi.cards.identifiers.AttributeTracker;
import hanabi.cards.identifiers.CardAttribute;
import hanabi.cards.identifiers.Color;
import hanabi.cards.identifiers.Number;
import hanabi.cards.identifiers.FourState;

public class AbstractPlayer {
	
	private ArrayList<Card> hand;
	private ArrayList<AttributeTracker[][]> cardInfoTables = new ArrayList<AttributeTracker[][]>();
	private PersonalCardTracker personalCardTracker;
	private String name;
	
	public AbstractPlayer() {
		this.name = "unknown";
		this.hand = new ArrayList<Card>();
		this.personalCardTracker = new PersonalCardTracker(ColorVariant.NONE);

	}

	public AbstractPlayer(String name, ColorVariant colorVariant) {
		this.name = name;
		this.personalCardTracker = new PersonalCardTracker(colorVariant);
	}

	public AbstractPlayer(String name, ArrayList<Card> hand, ColorVariant colorVariant) {
		super();
		this.name = name;
		this.hand = hand;
		this.personalCardTracker = new PersonalCardTracker(colorVariant);
		
		for (Card c : hand) {
			this.cardInfoTables.add(getNewCardInfoTable(colorVariant));
		}
	}

	// removes card from hand, removes relevant cardInfoTable, notes card on personal tracker, returns played card
	public Card playCard(int handIndex) { //return true if successful; return false if not
		Card playedCard = this.hand.remove(handIndex);
		this.cardInfoTables.remove(handIndex);
		this.personalCardTracker.cardSeen(playedCard);
		return playedCard;
	}
	
	//gains card to hand, adds new cardInfoTable, returns card gained so other players can be updated
	public Card drawCard(Deck deck, ColorVariant colorVariant) {
		Card c = deck.getCards().pop();
		this.hand.add(c);
		this.cardInfoTables.add(getNewCardInfoTable(colorVariant));
		return c;
	}
	
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
				for (int i = 0; i < cardTable[attribute.getValue()].length; i++) {
					cardTable[attribute.getValue()][i].setNumber(FourState.NO); // set the relevant row to NO
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
							if (j == attribute.getValue()) { // set the relevant column to yes
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
								if (j == attribute.getValue()) { // it does
									maybeFoundMatchesOld = true;
								}
							}
						}
					}
					if (maybeFoundMatchesOld) { // maybe was found in the same color column as the indicated color
						for (int i = 0; i < cardTable.length; i++) {
							for (int j = 0; j < cardTable[i].length; j++) { // set relevant column to yes and multicolor column to no
								if (j == attribute.getValue()) { 
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
								if (j == attribute.getValue() || j == 5) {
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
						cardTable[i][attribute.getValue()].setColor(FourState.NO);
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
						this.personalCardTracker.cardSeen(new Card(j, k));
					}
				}
				
			}
		}
	}
	
	public void deduceFromPersonalCardTracker() {
		Integer[][] pct = this.personalCardTracker.getCards();
		for (int i = 0; i < pct.length; i++) { // iterate over each cell
			for (int j = 0; j < pct[i].length; j++) {
				if (pct[i][j] == 0) { // if a zero is found
					for (AttributeTracker[][] at : this.cardInfoTables) { // none of the cards in hand can be that card, so set the color and number fourstate of the relevant attribute tracker cell to nos
						at[i][j].setColor(FourState.NO);
						at[i][j].setNumber(FourState.NO);
					}
				}
			}
		}
	}
	
	//TODO give info
	//TODO discard
	
	public AttributeTracker[][] getNewCardInfoTable(ColorVariant colorVariant) {
		
		AttributeTracker[][] cardTable = new AttributeTracker[5][6];
		for (int i = 0; i < cardTable.length; i++) {
			for (int j = 0; j < cardTable[i].length; j++) {
				cardTable[i][j] = new AttributeTracker();
				if (colorVariant.equals(ColorVariant.NONE) && j == 5) {
					cardTable[i][j].setColor(FourState.NO);
				}
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

	public PersonalCardTracker getPersonalCardTracker() {
		return personalCardTracker;
	}

	public void setPersonalCardTracker(PersonalCardTracker personalCardTracker) {
		this.personalCardTracker = personalCardTracker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String printIdentifiedHand() {
		StringBuilder sb = new StringBuilder();
		int counter = 1;
		for (Card c : this.hand) {
			sb.append("Card #" + counter++ + ": ");
			if (c.getOwningPlayerDeducedIdentity()) {
				sb.append(c.toString()).append("/n");
			}
			else {
				AttributeTracker[][] at = this.cardInfoTables.get(counter - 1);
				for (int i = 0; i < at.length; i++) {
					for (int j = 0; j < at[i].length; j++) {
						if (at[i][j].getColor().equals(FourState.YES)) { // do we know the color?
							sb.append(Color.getColorByValue(j).toString());
						}
						else if (at[i][j].getNumber().equals(FourState.YES)) { // do we know the number?
							sb.append(Number.getNumberByValue(i).toString());
						}
						// is there anything that is a maybe?
						else if (at[i][j].getColor().equals(FourState.MAYBE)) {
							sb.append(Color.getColorByValue(j)).append("?");
						}
						else if (at[i][j].getNumber().equals(FourState.MAYBE)) {
							sb.append(Number.getNumberByValue(i)).append("?");
						}
					}
				}
				//what it is
				sb.append("Is: ");
				
				//what it might be
				//what it is not
			}
		}
		
		return sb.toString();
	}
	
	public String printHand() {
		StringBuilder sb = new StringBuilder();
		int counter = 1;
		for (Card c : this.hand) {
			sb.append(counter++ + ") "+ c.toString() + " ");
		}
		return sb.toString();
	}
	
}
