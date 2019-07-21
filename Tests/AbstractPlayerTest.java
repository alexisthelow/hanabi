import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hanabi.cards.Card;
import hanabi.cards.identifiers.AttributeTracker;
import hanabi.cards.identifiers.CardAttribute;
import hanabi.cards.identifiers.Color;
import hanabi.cards.identifiers.Number;
import hanabi.game.AbstractPlayer;
import hanabi.game.ColorVariant;

class AbstractPlayerTest {

	AbstractPlayer player;
	ArrayList<Card> hand; 
	
	
	@BeforeEach
	void setUp() throws Exception {
		hand = new ArrayList<Card>();
		hand.add(new Card(Color.MULTICOLOR, Number.ONE));
		hand.add(new Card(Color.MULTICOLOR, Number.FIVE));
		hand.add(new Card(Color.RED, Number.ONE));
		hand.add(new Card(Color.BLUE, Number.FIVE));
		hand.add(new Card(Color.WHITE, Number.TWO));
		player = new AbstractPlayer(hand, ColorVariant.MULTICOLOR);
	}

	@AfterEach
	void tearDown() throws Exception {
		player = null;
		hand = null;
	}

	@Test
	void test() {
		ArrayList<Integer> handIndices = new ArrayList<Integer>();
		handIndices.add(0);
		handIndices.add(2);
		player.receiveInfo(handIndices, Number.ONE, ColorVariant.MULTICOLOR);
		for (AttributeTracker[][] cardTable : player.getCardInfoTables()) {
			System.out.println(cardTable);
			for (int i = 0; i < cardTable.length; i++) {
				for (int j = 0; j < cardTable[i].length; j++) {
					System.out.println(i + " " + j + " " + cardTable[i][j].getColor() + " " + cardTable[i][j].getNumber());
				}
			}
		}
		handIndices.remove(1);
		handIndices.remove(0);
		handIndices.add(2);
		player.receiveInfo(handIndices, Color.RED, ColorVariant.MULTICOLOR);
		System.out.println(player.getGlobalCardTracker().toString());
		handIndices.remove(0);
		handIndices.add(0);
		handIndices.add(1);
		player.receiveInfo(handIndices, Color.MULTICOLOR, ColorVariant.MULTICOLOR);
		System.out.println(player.getGlobalCardTracker().toString());
		
	}

}
