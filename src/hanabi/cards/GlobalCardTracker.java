package hanabi.cards;

import javax.swing.JTable;

public class GlobalCardTracker {
	
	JTable cards;

	public GlobalCardTracker(int colorVariant) {
		super();
		String[] columnNames = {"Blue", "Green", "Red", "White", "Yellow", "Multicolor"};
		if (colorVariant == 0 || colorVariant > 3) { // no color variant
			Integer[][] rowData = {
					{3, 3, 3, 3, 3, 0},
					{2, 2, 2, 2, 2, 0},
					{2, 2, 2, 2, 2, 0},
					{2, 2, 2, 2, 2, 0},
					{1, 1, 1, 1, 1, 0}
			};
			this.cards = new JTable(rowData, columnNames);
		}
		else if (colorVariant == 1 || colorVariant == 3) { // avalanche or wild
			Integer[][] rowData = {
					{3, 3, 3, 3, 3, 3},
					{2, 2, 2, 2, 2, 3},
					{2, 2, 2, 2, 2, 3},
					{2, 2, 2, 2, 2, 3},
					{1, 1, 1, 1, 1, 3}
			};
			this.cards = new JTable(rowData, columnNames);
		}
		else  { // avalanche single
			Integer[][] rowData = {
					{3, 3, 3, 3, 3, 1},
					{2, 2, 2, 2, 2, 1},
					{2, 2, 2, 2, 2, 1},
					{2, 2, 2, 2, 2, 1},
					{1, 1, 1, 1, 1, 1}
			};
			this.cards = new JTable(rowData, columnNames);
		}
	}
	
	public void cardSeen(Card card) {
	}

}
