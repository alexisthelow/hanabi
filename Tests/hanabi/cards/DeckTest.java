package hanabi.cards;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hanabi.game.ColorVariant;

class DeckTest {
	
	Deck sd;
	Deck msd;
	Deck mfd;

	@BeforeEach
	void setUp() throws Exception {
		sd = new Deck(ColorVariant.NONE);
		msd = new Deck(ColorVariant.MULTICOLOR_SINGLE);
		mfd = new Deck(ColorVariant.MULTICOLOR);
 	}

	@AfterEach
	void tearDown() throws Exception {
		sd = null;
		msd = null;
		mfd = null;
	}

	@Test
	void testDeck() {
		assertTrue(sd.getCards().size() == 50);
		assertTrue(msd.getCards().size() == 55);
		assertTrue(mfd.getCards().size() == 60);
	}

	@Test
	void testGetCards() {
		assertTrue(sd.getCards() instanceof Stack<?>);
		assertTrue(msd.getCards() instanceof Stack<?>);
		assertTrue(mfd.getCards() instanceof Stack<?>);
	}

	@Test
	void testShuffleDeck() {
		sd.shuffleDeck();
		msd.shuffleDeck();
		mfd.shuffleDeck();
		Deck sdTest = new Deck(ColorVariant.NONE);
		Deck msdTest = new Deck(ColorVariant.MULTICOLOR_SINGLE);
		Deck mfdTest = new Deck(ColorVariant.MULTICOLOR);
		assertFalse(sd.equals(sdTest));
		assertFalse(msd.equals(msdTest));
		assertFalse(mfd.equals(mfdTest));
	}

	@Test
	void testToString() {
		assertTrue(sd.toString() instanceof String);
		assertTrue(msd.toString() instanceof String);
		assertTrue(mfd.toString() instanceof String);
	}

}
