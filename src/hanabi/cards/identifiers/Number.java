package hanabi.cards.identifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	public String toString() {
		return "" + this.getAdjustedValue();
	}
	
	public static List<CardAttribute> getListOfNumbers() {
		return new ArrayList<CardAttribute>(Arrays.asList(Color.values()));
	}
	
	public static Number getNumberByValue(int value) {
		Number n;
		switch (value) {
			case 0:
				n = Number.ONE;
				break;
			case 1:
				n = Number.TWO;
				break;
			case 2:
				n = Number.THREE;
				break;
			case 3:
				n = Number.FOUR;
				break;
			case 4:
				n = Number.FIVE;
				break;
	
			default:
				n = null;
				break;
		}
		return n;
	}
	
}
