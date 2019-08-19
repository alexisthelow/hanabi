package hanabi.cards.identifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Color implements CardAttribute {
	
	BLUE("Blue", 0), GREEN("Green", 1), RED("Red", 2), WHITE("White", 3), YELLOW("Yellow", 4), MULTICOLOR("Multicolor", 5);
	
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
	
	public String toString() {
		return this.getName();
	}
	
	public static List<CardAttribute> getListOfColors() {
		return new ArrayList<CardAttribute>(Arrays.asList(Color.values()));
	}
	
	public static Color getColorByValue(int value) {
		Color c;
		
		switch (value) {
			case 0:
				c = Color.BLUE;
				break;
			case 1:
				c = Color.GREEN;
				break;
			case 2:
				c = Color.RED;
				break;
			case 3:
				c = Color.WHITE;
				break;
			case 4:
				c = Color.YELLOW;
				break;
			case 5:
				c = Color.MULTICOLOR;
				break;
	
			default:
				c = null;
				break;
		}
		return c;
	}
}
