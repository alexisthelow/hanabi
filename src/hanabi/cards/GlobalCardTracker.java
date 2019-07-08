package hanabi.cards;


public class GlobalCardTracker {
	
	Integer[][] cards = new Integer[5][6];

	public GlobalCardTracker(int colorVariant) {
		super();
		if (colorVariant == 0 || colorVariant > 3) { // no color variant
			for (int i = 0; i < cards.length; i++) {
				if (i == 0) { //ones
					for (int j = 0; j < cards[i].length - 1; j++) {
						cards[i][j] = 3;
					}
				}
				else if (i > 0 && i < 4) { // twos, threes, fours
					for (int j = 0; j < cards[i].length - 1; j++) {
						cards[i][j] = 2;
					}
				}
				else { //fives
					for (int j = 0; j < cards[i].length - 1; j++) {
						cards[i][j] = 1;
					}
				}
			}
			cards[0][5] = 0;
			cards[1][5] = 0;
			cards[2][5] = 0;
			cards[3][5] = 0;
			cards[4][5] = 0;
			
		}
		else if (colorVariant == 1 || colorVariant == 3) { // avalanche or wild

		}
		else  { // avalanche single

		}
	}
	
	public void cardSeen(Card card) {
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("       | Blue | Green | Red | White | Yellow | Multicolor |\n")
		.append("Ones:    " + cards[0][0] + "  " + cards[0][1] + "  " + cards[0][2] + "  " + cards[0][3] + "  " + cards[0][4] + "  " + cards[0][5] + "\n")
		.append("Twos: " + cards[1][0] + "  " + cards[1][1] + "  " + cards[1][2] + "  " + cards[1][3] + "  " + cards[1][4] + "  " + cards[1][5] + "\n")
		.append("Threes: " + cards[2][0] + "  " + cards[2][1] + "  " + cards[2][2] + "  " + cards[2][3] + "  " + cards[2][4] + "  " + cards[2][5] + "\n")
		.append("Fours: " + cards[3][0] + "  " + cards[3][1] + "  " + cards[3][2] + "  " + cards[3][3] + "  " + cards[3][4] + "  " + cards[3][5] + "\n")
		.append("Fives: " + cards[4][0] + "  " + cards[4][1] + "  " + cards[4][2] + "  " + cards[4][3] + "  " + cards[4][4] + "  " + cards[4][5]);
		return sb.toString();
	}

}
