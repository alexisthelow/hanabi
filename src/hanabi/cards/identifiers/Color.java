package hanabi.cards.identifiers;

public enum Color implements CardAttribute {
	
	BLUE("Blue", 0), GREEN("Green", 1), RED("Red", 2), YELLOW("Yellow", 3),  WHITE("White", 4), MULTICOLOR("Multicolor", 5);
	
	private String name;
	private int value;

	private Color(String name, int value) {
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
		return "color";
	}
	
}
