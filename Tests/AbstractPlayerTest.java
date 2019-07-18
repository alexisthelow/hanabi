import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hanabi.cards.Card;
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
		fail("Not yet implemented");
	}

}
