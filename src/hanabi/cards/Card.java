package hanabi.cards;

import javax.swing.JTable;

public class Card {
	
	private Color color;
	
	private Suit suit;
	
	String[] columnNames = {"Blue", "Green", "Red", "White", "Yellow", "Multicolor"};
	
	ThreeState[][] rowData = {
			{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
			{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
			{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
			{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
			{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
	};
	
	private JTable cardInfoTable = new JTable(rowData, columnNames);

}
