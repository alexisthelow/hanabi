package hanabi.cards;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hanabi.cards.identifiers.Color;
import hanabi.cards.identifiers.Number;

class CardTest {
	
	Card c;

	@BeforeEach
	void setUp() throws Exception {
		c = new Card(Color.RED, Number.THREE);
	}

	@AfterEach
	void tearDown() throws Exception {
		c = null;
	}

	@Test
	void testCardColorNumber() {
		Card c2 = new Card(Color.BLUE, Number.ONE);
		Card c3 = new Card(Color.GREEN, Number.TWO);
		Card c4 = new Card(Color.RED, Number.THREE);
		Card c5 = new Card(Color.WHITE, Number.FOUR);
		Card c6 = new Card(Color.YELLOW, Number.FIVE);
		Card c7 = new Card(Color.MULTICOLOR, Number.ONE);
		
		assertEquals("Blue 1", c2.toString());
		assertEquals("Green 2", c3.toString());
		assertEquals("Red 3", c4.toString());
		assertEquals("White 4", c5.toString());
		assertEquals("Yellow 5", c6.toString());
		assertEquals("Multicolor 1", c7.toString());
	}

	@Test
	void testCardIntegerInteger() {
		Card c2 = new Card(0,0);
		Card c3 = new Card(1,1);
		Card c4 = new Card(2,2);
		Card c5 = new Card(3,3);
		Card c6 = new Card(4,4);
		Card c7 = new Card(5,0);
		assertEquals("Blue 1", c2.toString());
		assertEquals("Green 2", c3.toString());
		assertEquals("Red 3", c4.toString());
		assertEquals("White 4", c5.toString());
		assertEquals("Yellow 5", c6.toString());
		assertEquals("Multicolor 1", c7.toString());
	}

	@Test
	void testGetColor() {
		assertEquals(Color.RED, c.getColor());
	}

	@Test
	void testSetColor() {
		c.setColor(Color.BLUE);
		assertEquals(Color.BLUE, c.getColor());
	}

	@Test
	void testGetNumber() {
		assertEquals(Number.THREE, c.getNumber());
	}

	@Test
	void testSetNumber() {
		c.setNumber(Number.ONE);
		assertEquals(Number.ONE, c.getNumber());
	}

	@Test
	void testGetOwningPlayerDeducedIdentity() {
		assertFalse(c.getOwningPlayerDeducedIdentity());
	}

	@Test
	void testSetOwningPlayerDeducedIdentity() {
		c.setOwningPlayerDeducedIdentity(true);
		assertTrue(c.getOwningPlayerDeducedIdentity());
	}

	@Test
	void testToString() {
		assertEquals("Red 3", c.toString());;
	}

	@Test
	void testCompareTo() {
		assertTrue(c.compareTo(new Card(0, 0)) == 1);
		assertFalse(c.compareTo(new Card(0, 0)) == -1);
		assertFalse(c.compareTo(new Card(0, 0)) == 0);
	}

}
