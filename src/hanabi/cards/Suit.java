package hanabi.cards;

public enum Suit {
	
	ONE("One", 1), TWO("Two", 2), THREE("Three", 3), FOUR("Four", 4), FIVE("Five", 5);
	
	private String name;
	
	private int numeral;

	private Suit(String name, int numeral) {
		this.name = name;
		this.numeral = numeral;
	}

	public String getName() {
		return name;
	}

	public int getNumeral() {
		return numeral;
	}
	
}
