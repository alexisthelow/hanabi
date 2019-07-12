package hanabi.cards;

import hanabi.game.ColorVariant;

public class GlobalCardTracker {
	
	Integer[][] cards = new Integer[5][6];

	public GlobalCardTracker(ColorVariant colorVariant) {
		super();
		if (colorVariant.equals(ColorVariant.NONE)) { // no color variant
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
			for (int i = 0; i < 5; i++) {
				cards[i][5] = 0;
			}
			
		}
		else if (colorVariant.equals(ColorVariant.MULTICOLOR) || colorVariant.equals(ColorVariant.MULTICOLOR_WILD)) { // avalanche or wild
			for (int i = 0; i < cards.length; i++) {
				if (i == 0) { //ones
					for (int j = 0; j < cards[i].length; j++) {
						cards[i][j] = 3;
					}
				}
				else if (i > 0 && i < 4) { // twos, threes, fours
					for (int j = 0; j < cards[i].length; j++) {
						cards[i][j] = 2;
					}
				}
				else { //fives
					for (int j = 0; j < cards[i].length; j++) {
						cards[i][j] = 1;
					}
				}
			}
		}
		else  { // avalanche single
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
			for (int i = 0; i < 5; i++) {
				cards[i][5] = 1;
			}
		}
	}
	
	public void cardSeen(Card card) throws Exception {
		int colorIndex;
		
		switch (card.getColor()) {
			case BLUE:
				colorIndex = 0;
				break;
				
			case GREEN:
				colorIndex = 1;
				break;
				
			case RED:
				colorIndex = 2;
				break;
			
			case WHITE:
				colorIndex = 3;
				break;
			
			case YELLOW:
				colorIndex = 4;
				break;
			
			case MULTICOLOR:
				colorIndex = 5;
				break;
	
			default:
				throw new Exception("GlobalCardTracker.cardSeen() was unable to find the correct color for card " + card.toString());
		}
		
		cards[card.getSuit().getNumeral() - 1][colorIndex]--;
	}
	
	public Integer[][] getCards() {
		return cards;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("       | Blue | Green | Red | White | Yellow | Multicolor |\n")
		.append("Ones:    " + cards[0][0] + "      " + cards[0][1] + "       " + cards[0][2] + "     " + cards[0][3] + "       " + cards[0][4] + "        " + cards[0][5] + "\n")
		.append("Twos:    " + cards[1][0] + "      " + cards[1][1] + "       " + cards[1][2] + "     " + cards[1][3] + "       " + cards[1][4] + "        " + cards[1][5] + "\n")
		.append("Threes:  " + cards[2][0] + "      " + cards[2][1] + "       " + cards[2][2] + "     " + cards[2][3] + "       " + cards[2][4] + "        " + cards[2][5] + "\n")
		.append("Fours:   " + cards[3][0] + "      " + cards[3][1] + "       " + cards[3][2] + "     " + cards[3][3] + "       " + cards[3][4] + "        " + cards[3][5] + "\n")
		.append("Fives:   " + cards[4][0] + "      " + cards[4][1] + "       " + cards[4][2] + "     " + cards[4][3] + "       " + cards[4][4] + "        " + cards[4][5]);
		return sb.toString();
	}

}
