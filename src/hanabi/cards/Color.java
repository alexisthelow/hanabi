package hanabi.cards;

public enum Color {
	
	RED("Red"), GREEN("Green"), YELLOW("Yellow"), BLUE("Blue"), WHITE("White"), MULTICOLOR("Multicolor");
	
	private String name;

	private Color(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	

}
