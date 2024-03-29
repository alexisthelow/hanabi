package hanabi.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.UNKNOWN;

import hanabi.cards.Card;
import hanabi.cards.Deck;
import hanabi.cards.identifiers.AttributeTracker;
import hanabi.cards.identifiers.CardAttribute;
import hanabi.cards.identifiers.Color;
import hanabi.cards.identifiers.FourState;
import hanabi.cards.identifiers.Number;

class AbstractPlayerTest {
	
	AbstractPlayer player;
	ArrayList<Card> hand;
	Deck testDeck;

	@BeforeEach
	void setUp() throws Exception {
		hand = new ArrayList<Card>();
		hand.add(new Card(Color.BLUE, Number.ONE));
		hand.add(new Card(Color.GREEN, Number.TWO));
		hand.add(new Card(Color.RED, Number.THREE));
		hand.add(new Card(Color.WHITE, Number.FOUR));
		hand.add(new Card(Color.MULTICOLOR, Number.FIVE));
		
		player = new AbstractPlayer("testPlayer", hand, ColorVariant.MULTICOLOR_WILD);
		testDeck = new Deck(ColorVariant.MULTICOLOR_WILD);
	}

	@AfterEach
	void tearDown() throws Exception {
		player = null;
		hand = null;
		testDeck = null;
	}

	@Test
	void testAbstractPlayer() {
		AbstractPlayer newPlayer = new AbstractPlayer();
		assertTrue(newPlayer instanceof AbstractPlayer);
		assertTrue(newPlayer.getName().equals("unknown"));
		assertTrue(newPlayer.getHand().size() == 0);
		assertTrue(newPlayer.getPersonalCardTracker().getCards() instanceof Integer[][]);
		assertTrue(newPlayer.getPersonalCardTracker().getCards()[0][0] == 3);
		assertTrue(newPlayer.getPersonalCardTracker().getCards()[4][5] == 0);
		assertTrue(newPlayer.getCardInfoTables().size() == 0);
	}

	@Test
	void testAbstractPlayerStringColorVariant() {
		AbstractPlayer newishPlayer = new AbstractPlayer("fuckpowers", ColorVariant.MULTICOLOR_SINGLE);
		assertTrue(newishPlayer instanceof AbstractPlayer);
		assertTrue(newishPlayer.getHand() == null);
		assertTrue(newishPlayer.getPersonalCardTracker().getCards()[4][5] == 1);
		assertTrue(newishPlayer.getPersonalCardTracker().getCards()[0][5] == 1);
		assertTrue(newishPlayer.getName().equals("fuckpowers"));
		assertTrue(newishPlayer.getCardInfoTables().size() == 0);
	}

	@Test
	void testAbstractPlayerStringArrayListOfCardColorVariant() {
		assertTrue(player instanceof AbstractPlayer);
		assertTrue(player.getName().equals("testPlayer"));
		assertTrue(player.getHand().size() == 5);
		assertTrue(player.getPersonalCardTracker().getCards() instanceof Integer[][]);
		assertTrue(player.getPersonalCardTracker().getCards()[0][0] == 3);
		assertTrue(player.getPersonalCardTracker().getCards()[4][5] == 1);
		assertTrue(player.getPersonalCardTracker().getCards()[0][5] == 3);
		assertTrue(player.getCardInfoTables().size() == 5);
	}

	@Test
	void testPlayCard() {
		Card c = player.playCard(0); // plays the blue 1
		assertTrue(c.getColor().equals(Color.BLUE));
		assertTrue(c.getNumber().equals(Number.ONE));
		assertTrue(player.getHand().size() == 4);
		assertTrue(player.getCardInfoTables().size() == 4);
		assertTrue(player.getPersonalCardTracker().getCards()[0][0] == 2);
	}

	@Test
	void testDrawCard() {
		Card c = player.drawCard(testDeck, ColorVariant.MULTICOLOR_WILD);
		assertTrue(testDeck.getCards().size() == 59);
		assertTrue(player.getHand().size() == 6);
		assertTrue(player.getCardInfoTables().size() == 6);
		assertTrue(c.getColor().equals(Color.MULTICOLOR));
		assertTrue(c.getNumber().equals(Number.FIVE));
	}

	@Test
	void testReceiveInfoNumber() {
		ArrayList<Integer> numberIndices = new ArrayList<Integer>();
		numberIndices.add(new Integer(0));
		CardAttribute numberAttribute = Number.ONE;
		
		//test receive number info
		player.receiveInfo(numberIndices, numberAttribute, ColorVariant.MULTICOLOR_WILD);
		assertTrue(player.getCardInfoTables().get(0)[0][0].getColor().equals(FourState.UNKNOWN));
		assertTrue(player.getCardInfoTables().get(0)[0][0].getNumber().equals(FourState.YES));
		assertTrue(player.getCardInfoTables().get(1)[0][0].getNumber().equals(FourState.NO));
		
		//let's also test a color here
		numberIndices.add(new Integer(4));
		player.receiveInfo(numberIndices, Color.BLUE, ColorVariant.MULTICOLOR_WILD);
		assertTrue(player.getCardInfoTables().get(0)[0][0].getColor().equals(FourState.MAYBE));
		assertTrue(player.getCardInfoTables().get(4)[0][5].getColor().equals(FourState.MAYBE));
		assertFalse(player.getHand().get(0).getOwningPlayerDeducedIdentity());
		numberIndices.remove(0);
		numberIndices.add(new Integer(1));
		player.receiveInfo(numberIndices, Color.GREEN, ColorVariant.MULTICOLOR_WILD);
		System.out.println(player.getCardInfoTables().get(0).toString());
	}
	
	@Test
	void testReceiveInfoColor() {
		ArrayList<Integer> colorIndices = new ArrayList<Integer>();
		colorIndices.add(new Integer(4));
		CardAttribute colorAttribute = Color.BLUE;
		
		player.receiveInfo(colorIndices, colorAttribute, ColorVariant.MULTICOLOR_WILD);
		assertTrue(player.getCardInfoTables().get(4)[0][5].getColor().equals(FourState.MAYBE));
		assertTrue(player.getCardInfoTables().get(4)[0][5].getNumber().equals(FourState.UNKNOWN));
		
		ArrayList<Integer> numberIndices = new ArrayList<Integer>();
		numberIndices.add(new Integer(4));
		CardAttribute numberAttribute = Number.FIVE;
		
		player.receiveInfo(numberIndices, numberAttribute, ColorVariant.MULTICOLOR_WILD);
		assertTrue(player.getCardInfoTables().get(4)[4][5].getNumber().equals(FourState.YES));
		
		ArrayList<Integer> colorIndices2 = new ArrayList<Integer>();
		colorIndices2.add(new Integer(4));
		CardAttribute colorAttribute2 = Color.RED;
		
		player.receiveInfo(colorIndices2, colorAttribute2, ColorVariant.MULTICOLOR_WILD);
		assertTrue(player.getCardInfoTables().get(4)[0][5].getColor().equals(FourState.YES));
		assertTrue(player.getCardInfoTables().get(4)[4][5].getNumber().equals(FourState.YES));
		assertTrue(player.getHand().get(4).getOwningPlayerDeducedIdentity());
		
	}

	@Test
	void testReceiveInfoColorNoColorVariant() {
	    //TODO need to test receiveInfo where maybeFoundMatchesOld becomes true
	    //TODO need to test where in multicolor_wild a card previously indicated as maybe is revealed to be a yes
	    ArrayList<Card> hand = new ArrayList<Card>();
	    hand.add(new Card(Color.BLUE, Number.ONE));
	    hand.add(new Card(Color.GREEN, Number.TWO));
	    hand.add(new Card(Color.RED, Number.THREE));
	    hand.add(new Card(Color.WHITE, Number.FOUR));
	    hand.add(new Card(Color.YELLOW, Number.FIVE));
	    hand.add(new Card(Color.MULTICOLOR, Number.ONE));
	    AbstractPlayer player = new AbstractPlayer("whatever", hand, ColorVariant.NONE);
	    ArrayList<Integer> handIndices = new ArrayList<Integer>();
	    handIndices.add(new Integer(2));
	}
	
	@Test
	void testDeduceFromPersonalCardTracker() {
		Card blueThree = new Card(Color.BLUE, Number.THREE);
		Card anotherBlueThree = new Card(Color.BLUE, Number.THREE);
		Card multicolorOne = new Card(Color.MULTICOLOR, Number.ONE);
		Card anotherMulticolorOne = new Card(Color.MULTICOLOR, Number.ONE);
		Card yetAnotherMulticolorOne = new Card(Color.MULTICOLOR, Number.ONE);
		
		player.getPersonalCardTracker().cardSeen(blueThree);
		player.getPersonalCardTracker().cardSeen(anotherBlueThree);
		player.getPersonalCardTracker().cardSeen(multicolorOne);
		player.getPersonalCardTracker().cardSeen(anotherMulticolorOne);
		player.getPersonalCardTracker().cardSeen(yetAnotherMulticolorOne);
		
		assertTrue(player.getPersonalCardTracker().getCards()[2][0] == 0);
		assertTrue(player.getPersonalCardTracker().getCards()[0][5] == 0);
		player.deduceFromPersonalCardTracker();
		assertTrue(player.getCardInfoTables().get(0)[2][0].getColor().equals(FourState.NO));
		assertTrue(player.getCardInfoTables().get(0)[2][0].getNumber().equals(FourState.NO));
		assertFalse(player.getCardInfoTables().get(0)[0][0].getNumber().equals(FourState.NO));
		assertTrue(player.getCardInfoTables().get(0)[0][5].getColor().equals(FourState.NO));
		assertTrue(player.getCardInfoTables().get(0)[0][5].getNumber().equals(FourState.NO));
		assertFalse(player.getCardInfoTables().get(0)[1][5].getNumber().equals(FourState.NO));
		
	}

	@Test
	void testGetNewCardInfoTable() {
		AttributeTracker[][] atMulticolor = player.getNewCardInfoTable(ColorVariant.MULTICOLOR);
		assertTrue(atMulticolor instanceof AttributeTracker[][]);
		assertTrue(atMulticolor[0][0].getColor().equals(FourState.UNKNOWN));
		assertTrue(atMulticolor[4][5].getColor().equals(FourState.UNKNOWN));
		AttributeTracker[][] atNone = player.getNewCardInfoTable(ColorVariant.NONE);
		assertTrue(atNone instanceof AttributeTracker[][]);
		assertTrue(atNone[0][0].getColor().equals(FourState.UNKNOWN));
		assertTrue(atNone[4][5].getColor().equals(FourState.NO));
	}

	@Test
	void testGetHand() {
		fail("Not yet implemented");
	}

	@Test
	void testSetHand() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCardInfoTables() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCardInfoTables() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPersonalCardTracker() {
		fail("Not yet implemented");
	}

	@Test
	void testSetPersonalCardTracker() {
		fail("Not yet implemented");
	}

	@Test
	void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	void testPrintHand() {
		fail("Not yet implemented");
	}

	@Test
	void testPrintIdentifiedCard() {
		fail("Not yet implemented");
	}

}
