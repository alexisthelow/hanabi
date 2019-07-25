package hanabi.cards.identifiers;

public enum Number implements CardAttribute {
	
	ONE("One", 0), TWO("Two", 1), THREE("Three", 2), FOUR("Four", 3), FIVE("Five", 4);
	
	private String name;
	private int value;

	private Number(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public int getAdjustedValue() {
		return value + 1;
	}

	@Override
	public String getAttributeType() {
		return "number";
	}
	
}
