package hanabi.cards;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiscardPileTest {
	
	DiscardPile dp;

	@BeforeEach
	void setUp() throws Exception {
		dp = new DiscardPile();
	}

	@AfterEach
	void tearDown() throws Exception {
		dp = null;
	}

	@Test
	void testDiscardPile() {
		for (ArrayList<Card> colorPile : dp.getCollectedPiles()) {
			assertTrue(colorPile.isEmpty());
		}
	}

	@Test
	void testGetBlues() {
		assertTrue(dp.getBlues() instanceof ArrayList<?>);
	}

	@Test
	void testGetGreens() {
		assertTrue(dp.getGreens() instanceof ArrayList<?>);
	}

	@Test
	void testGetReds() {
		assertTrue(dp.getReds() instanceof ArrayList<?>);
	}

	@Test
	void testGetWhites() {
		assertTrue(dp.getWhites() instanceof ArrayList<?>);
	}

	@Test
	void testGetYellows() {
		assertTrue(dp.getYellows() instanceof ArrayList<?>);
	}

	@Test
	void testGetMulticolors() {
		assertTrue(dp.getMulticolors() instanceof ArrayList<?>);
	}
	
	@Test 
	void testGetCollectedPiles() {
		assertTrue(dp.getCollectedPiles() instanceof ArrayList<?>);
	}

}
