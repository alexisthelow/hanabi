package hanabi.cards;

import javax.swing.JTable;

public class GlobalCardTracker {
	
	JTable cards;

	public GlobalCardTracker(Boolean variantActive) {
		super();
		String[] columnNames = {"Blue", "Green", "Red", "White", "Yellow", "Multicolor"};
		Integer[][] rowData = {
				{3, 3, 3, 3, 3, 0},
				{2, 2, 2, 2, 2, 0},
				{2, 2, 2, 2, 2, 0},
				{2, 2, 2, 2, 2, 0},
				{1, 1, 1, 1, 1, 0}
		};
		//TODO this should update the number of multicolor cards depending on the variant
		this.cards = new JTable(rowData, columnNames);
	}
	
	
	
	//TODO eliminateCard(Card card);

}
