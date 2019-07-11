package hanabi.cards;

public class Card {
	
	private Color color;
	private Suit suit;
	private Boolean owningPlayerDeducedIdentity;
	
	public Card(Color color, Suit suit) {
		super();
		this.color = color;
		this.suit = suit;
		this.owningPlayerDeducedIdentity = false;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	public Boolean getOwningPlayerDeducedIdentity() {
		return owningPlayerDeducedIdentity;
	}

	public void setOwningPlayerDeducedIdentity(Boolean owningPlayerDeducedIdentity) {
		this.owningPlayerDeducedIdentity = owningPlayerDeducedIdentity;
	}

	@Override
	public String toString() {
		return color + " " + suit;
	}
	
	
	
	
	
}
