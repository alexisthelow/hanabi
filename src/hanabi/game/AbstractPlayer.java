package hanabi.game;

import java.util.ArrayList;

import javax.swing.JTable;

import hanabi.cards.Card;
import hanabi.cards.Color;
import hanabi.cards.GlobalCardTracker;
import hanabi.cards.Suit;
import hanabi.cards.ThreeState;

public class AbstractPlayer {
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<ThreeState[][]> cardInfoTables = new ArrayList<ThreeState[][]>();
	private GlobalCardTracker globalCardTracker;


	public AbstractPlayer(ArrayList<Card> hand, int colorVariant) {
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
	
	
	public void receiveColorInfo(ArrayList<Integer> handIndices, Color color) {
		ArrayList<ThreeState[][]> indicatedCards = new ArrayList<ThreeState[][]>();
		ArrayList<ThreeState[][]> notIndicatedCards = new ArrayList<ThreeState[][]>();
		
		for (Integer i = 0; i < this.cardInfoTables.size(); i++) {
			if (handIndices.contains(i)) {
				indicatedCards.add(this.cardInfoTables.get(i));
			}
			else {
				notIndicatedCards.add(this.cardInfoTables.get(i));
			}
		}
		for (ThreeState[][] cardTable : indicatedCards) {
			
		}
			
	}
	
	public void receiveSuitInfo(int[] handIndex, Suit suit) {
		
	}
	//TODO deduce from visible cards
	//TODO give info
	//TODO discard
	
	public ThreeState[][] getNewCardInfoTable() {
		
		ThreeState[][] cardTable = {
				{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
				{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
				{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
				{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
				{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
		};
		
		return cardTable;
		
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public ArrayList<ThreeState[][]> getCardInfoTables() {
		return cardInfoTables;
	}

	public void setCardInfoTables(ArrayList<ThreeState[][]> cardInfoTables) {
		this.cardInfoTables = cardInfoTables;
	}

	public GlobalCardTracker getGlobalCardTracker() {
		return globalCardTracker;
	}

	public void setGlobalCardTracker(GlobalCardTracker globalCardTracker) {
		this.globalCardTracker = globalCardTracker;
	}
	
}
