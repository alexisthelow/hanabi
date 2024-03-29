package hanabi.game;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hanabi.cards.Card;
import hanabi.cards.PersonalCardTracker;
import hanabi.cards.identifiers.Color;
import hanabi.cards.identifiers.Number;
import hanabi.game.ColorVariant;

class GlobalCardTrackerTest {
	
	PersonalCardTracker gct;

	@BeforeEach
	void setUp() throws Exception {
		gct = new PersonalCardTracker(ColorVariant.MULTICOLOR);
	}

	@AfterEach
	void tearDown() throws Exception {
		gct = null;
	}

	@Test
	void test_toString() {
		System.out.println(gct.toString());
	}
	
	@Test
	void test_decrement_correct_cell() {
		try {
			gct.cardSeen(new Card(Color.RED, Number.FIVE));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(gct.toString());
		assertEquals(new Integer(0), gct.getCards()[4][2]);
	}

}
