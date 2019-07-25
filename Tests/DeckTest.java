import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hanabi.cards.Card;
import hanabi.cards.Deck;
import hanabi.game.ColorVariant;

public class DeckTest {

	Deck standardDeck;
	Deck singleMulticolorDeck;
	Deck multicolorDeck;
	
	@Before
	public void setUp() throws Exception {
		standardDeck = new Deck(ColorVariant.NONE);
		singleMulticolorDeck = new Deck(ColorVariant.MULTICOLOR_SINGLE);
		multicolorDeck = new Deck(ColorVariant.MULTICOLOR);
	}

	@After
	public void tearDown() throws Exception {
		standardDeck = null;
		singleMulticolorDeck = null;
		multicolorDeck = null;
	}

	@Test
	public void decks_are_correct_sizes() {
		assertEquals(50, standardDeck.getCards().size());
		assertEquals(55, singleMulticolorDeck.getCards().size());
		assertEquals(60, multicolorDeck.getCards().size());
	}
	
	@Test
	public void decks_contain_correct_cards() {
		//make something to track the expected cards
		int[][] standardDeckTracker = new int[][] {
			{3,3,3,3,3,},
			{2,2,2,2,2,},
			{2,2,2,2,2,},
			{2,2,2,2,2,},
			{1,1,1,1,1,}
		};
		int[][] singleMulticolorDeckTracker = new int[][] {
			{3,3,3,3,3,1},
			{2,2,2,2,2,1},
			{2,2,2,2,2,1},
			{2,2,2,2,2,1},
			{1,1,1,1,1,1}
		};
		int[][] multicolorDeckTracker = new int[][] {
			{3,3,3,3,3,3},
			{2,2,2,2,2,2},
			{2,2,2,2,2,2},
			{2,2,2,2,2,2},
			{1,1,1,1,1,1}
		};
		
		for (Card c : standardDeck.getCards()) { // for each card in the standard deck
			standardDeckTracker[c.getNumber().getValue()][c.getColor().getValue()]--; // decrement the value in the relevant cell of the tracker
		}
		for (Card c : singleMulticolorDeck.getCards()) { // for each card in the single multicolor deck
			singleMulticolorDeckTracker[c.getNumber().getValue()][c.getColor().getValue()]--; // decrement the blah blah you get it
		}
		for (Card c : multicolorDeck.getCards()) {
			multicolorDeckTracker[c.getNumber().getValue()][c.getColor().getValue()]--;
		}
		
		boolean standardDeckCorrect = true;
		boolean singleMulticolorDeckCorrect = true;
		boolean multicolorDeckCorrect = true;
		
		for (int i = 0; i < standardDeckTracker.length; i++) { // for each row in standardDeckTracker
			for (int j = 0; j < standardDeckTracker.length; j++) { // for each column
				if (standardDeckTracker[i][j] != 0) {
					standardDeckCorrect = false;
				}
			}
		}
		for (int i = 0; i < singleMulticolorDeckTracker.length; i++) {
			for (int j = 0; j < singleMulticolorDeckTracker.length; j++) {
				if (singleMulticolorDeckTracker[i][j] != 0) {
					singleMulticolorDeckCorrect = false;
				}
			}
		}
		for (int i = 0; i < multicolorDeckTracker.length; i++) {
			for (int j = 0; j < multicolorDeckTracker.length; j++) {
				if (multicolorDeckTracker[i][j] != 0) {
					multicolorDeckCorrect = false;
				}
			}
		}
		assertTrue(standardDeckCorrect);
		assertTrue(singleMulticolorDeckCorrect);
		assertTrue(multicolorDeckCorrect);
	}
	
	@Test
	public void do_the_decks_look_shuffled_lets_find_out() {
		System.out.println(standardDeck.toString());
		System.out.println(singleMulticolorDeck.toString());
		System.out.println(multicolorDeck.toString());
	}

}
