package hanabi.cards;

public enum Color {
	
	RED("Red", 3), GREEN("Green", 2), YELLOW("Yellow", 4), BLUE("Blue", 1), WHITE("White", 5), MULTICOLOR("Multicolor", 6);
	
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
	
}
