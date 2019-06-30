package hanabi.game;

import javax.swing.JTable;

import hanabi.cards.Hand;
import hanabi.cards.ThreeState;

public class AbstractPlayer {
	
	private Hand hand;
	
	
	
	private String[] columnNames = {"Blue", "Green", "Red", "White", "Yellow", "Multicolor"};
	
	private ThreeState[][] rowData = {
			{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
			{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
			{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
			{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
			{ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE, ThreeState.MAYBE}, 
	};
	
	private JTable cardInfo = new JTable(rowData, columnNames);
	
	

}
