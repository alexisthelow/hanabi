package hanabi.cards.identifiers;

public class AttributeTracker {
	
	private FourState color;
	private FourState number;
	
	public AttributeTracker() {
		this.color = FourState.UNKNOWN;
		this.number = FourState.UNKNOWN;
	}

	public FourState getColor() {
		return color;
	}

	public void setColor(FourState color) {
		this.color = color;
	}

	public FourState getNumber() {
		return number;
	}

	public void setNumber(FourState number) {
		this.number = number;
	}
	
}
