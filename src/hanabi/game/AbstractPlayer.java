package hanabi.game;

import javax.swing.JTable;

import hanabi.cards.Hand;
import hanabi.cards.ThreeState;

public class AbstractPlayer {
	
	private Hand hand;
	
	
	
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
