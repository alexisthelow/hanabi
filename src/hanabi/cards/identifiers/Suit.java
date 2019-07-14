package hanabi.cards.identifiers;

public enum Suit implements CardAttribute {
	
	ONE("One", 1), TWO("Two", 2), THREE("Three", 3), FOUR("Four", 4), FIVE("Five", 5);
	
	private String name;
	
	private int value;

	private Suit(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String getAttributeType() {
		return "suit";
	}
	
	
	
}
