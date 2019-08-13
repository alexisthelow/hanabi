package hanabi.cards;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hanabi.cards.Card;
import hanabi.cards.Deck;
import hanabi.cards.PersonalCardTracker;
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
		PersonalCardTracker pcts = new PersonalCardTracker(ColorVariant.NONE);
		PersonalCardTracker pctm = new PersonalCardTracker(ColorVariant.MULTICOLOR);
		PersonalCardTracker pctms = new PersonalCardTracker(ColorVariant.MULTICOLOR_SINGLE);
		
		for (Card c : standardDeck.getCards()) { // for each card in the standard deck
			pcts.cardSeen(c);
		}
		for (Card c : singleMulticolorDeck.getCards()) { // for each card in the single multicolor deck
			pctms.cardSeen(c);
		}
		for (Card c : multicolorDeck.getCards()) {
			pctm.cardSeen(c);
		}
		
		boolean standardDeckCorrect = true;
		boolean singleMulticolorDeckCorrect = true;
		boolean multicolorDeckCorrect = true;
		
		for (Integer[] at : pcts.getCards()) {
			for (Integer integer : at) {
				if (integer != 0) {
					standardDeckCorrect = false;
				}
			}
		}
		for (Integer[] at : pctm.getCards()) {
			for (Integer integer : at) {
				if (integer != 0) {
					multicolorDeckCorrect = false;
				}
			}
		}
		for (Integer[] at : pctms.getCards()) {
			for (Integer integer : at) {
				if (integer != 0) {
					singleMulticolorDeckCorrect = false;
				}
			}
		}
		assertTrue(standardDeckCorrect);
		assertTrue(singleMulticolorDeckCorrect);
		assertTrue(multicolorDeckCorrect);
	}
	
	@Test
	public void do_the_decks_look_shuffled_lets_find_out() {
		standardDeck.shuffleDeck();
		singleMulticolorDeck.shuffleDeck();
		multicolorDeck.shuffleDeck();
		System.out.println(standardDeck.toString());
		System.out.println(singleMulticolorDeck.toString());
		System.out.println(multicolorDeck.toString());
	}

}
