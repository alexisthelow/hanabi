package hanabi.cards.identifiers;

public class IndicatedAttributeTracker {
	
	private FourState color;
	private FourState suit;
	
	public IndicatedAttributeTracker() {
		this.color = FourState.UNKNOWN;
		this.suit = FourState.UNKNOWN;
	}

	public FourState getColor() {
		return color;
	}

	public void setColor(FourState color) {
		this.color = color;
	}

	public FourState getSuit() {
		return suit;
	}

	public void setSuit(FourState suit) {
		this.suit = suit;
	}
	
}
