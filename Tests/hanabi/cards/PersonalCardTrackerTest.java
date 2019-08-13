package hanabi.cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hanabi.cards.identifiers.Color;
import hanabi.cards.identifiers.Number;
import hanabi.game.ColorVariant;


class PersonalCardTrackerTest {

	PersonalCardTracker pcts;
	PersonalCardTracker pctm;
	PersonalCardTracker pctms;

	@BeforeEach
	void setUp() throws Exception {
		pcts = new PersonalCardTracker(ColorVariant.NONE);
		pctm = new PersonalCardTracker(ColorVariant.MULTICOLOR);
		pctms = new PersonalCardTracker(ColorVariant.MULTICOLOR_SINGLE);
	}

	@AfterEach
	void tearDown() throws Exception {
		pcts = null;
		pctm = null;
		pctms = null;
	}

	@Test
	void testPersonalCardTracker() {
		assertTrue(pcts instanceof PersonalCardTracker);
		assertTrue(pctm instanceof PersonalCardTracker);
		assertTrue(pctms instanceof PersonalCardTracker);
	}

	@Test
	void testCardSeen() {
		ArrayList<Card> testCards = new ArrayList<Card>();
		testCards.add(new Card(Color.BLUE, Number.ONE)); 
		testCards.add(new Card(Color.GREEN, Number.TWO));
		testCards.add(new Card(Color.RED, Number.THREE));
		testCards.add(new Card(Color.WHITE, Number.FOUR));
		testCards.add(new Card(Color.YELLOW, Number.FIVE));
		testCards.add(new Card(Color.MULTICOLOR, Number.ONE));
		
		ArrayList<PersonalCardTracker> trackers = new ArrayList<PersonalCardTracker>();
		trackers.add(pcts);
		trackers.add(pctm);
		trackers.add(pctms);
		
		
		for (PersonalCardTracker personalCardTracker : trackers) {
			for (Card c : testCards) {
				personalCardTracker.cardSeen(c);
			}
		}
		for (PersonalCardTracker pct : trackers) {
			System.out.println(pct.toString());
			assertTrue(pct.getCards()[0][0] == 2);
			assertTrue(pct.getCards()[1][1] == 1);
			assertTrue(pct.getCards()[2][2] == 1);
			assertTrue(pct.getCards()[3][3] == 1);
			assertTrue(pct.getCards()[4][4] == 0);
			System.out.println(pct.toString());
		}
		assertTrue(pcts.getCards()[0][5] == -1);
		assertTrue(pctm.getCards()[0][5] == 2);
		assertTrue(pctms.getCards()[0][5] == 0);
		
	}

	@Test
	void testGetCards() {
		assertTrue(pcts.getCards() instanceof Integer[][]);
		assertTrue(pctm.getCards() instanceof Integer[][]);
		assertTrue(pctms.getCards() instanceof Integer[][]);
	}

	@Test
	void testToString() {
		pcts.toString();
		pctm.toString();
		pctms.toString();
	}

}
