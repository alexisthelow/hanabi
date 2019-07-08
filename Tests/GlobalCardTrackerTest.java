import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hanabi.cards.GlobalCardTracker;

class GlobalCardTrackerTest {
	
	GlobalCardTracker gct;

	@BeforeEach
	void setUp() throws Exception {
		gct = new GlobalCardTracker(0);
	}

	@AfterEach
	void tearDown() throws Exception {
		gct = null;
	}

	@Test
	void test_toString() {
		System.out.println(gct.toString());
	}

}
