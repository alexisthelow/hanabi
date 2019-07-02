package hanabi.game;

import java.util.ArrayList;

import javax.swing.JTable;

import hanabi.cards.Card;
import hanabi.cards.ThreeState;

public class AbstractPlayer {
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<JTable> cardInfoTables = new ArrayList<JTable>();


	public AbstractPlayer(ArrayList<Card> hand, ArrayList<JTable> cardInfoTables) {
		super();
		this.hand = hand;
		this.cardInfoTables = cardInfoTables;
		
		for (Card c : hand) {
			this.cardInfoTables.add(getNewCardInfoTable());
		}
	}

	public Card playCard(int handIndex) { //return true if successful; return false if not
		Card playedCard = this.hand.remove(handIndex);
		//TODO draw card
		
		return playedCard;
	}
	
	//TODO draw card
	public void gainCardToHand(Card card) {
		this.hand.add(card);
	}
	//TODO receive and interpret info
	//TODO deduce from visible cards
	//TODO give info
	//TODO discard
	
	public JTable getNewCardInfoTable() {
		
		String[] columnNames = {"Blue", "Green", "Red", "White", "Yellow", "Multicolor"};
		
		ThreeState[][] rowData = {
				{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
				{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
				{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
				{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
				{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
		};
		
		return new JTable(rowData, columnNames);
		
	}
	

}
