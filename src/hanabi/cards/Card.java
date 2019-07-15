package hanabi.cards;

import hanabi.cards.identifiers.Color;
import hanabi.cards.identifiers.Number;

public class Card {
	
	private Color color;
	private Number suit;
	private Boolean owningPlayerDeducedIdentity;
	
	public Card(Color color, Number suit) {
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

	public Number getSuit() {
		return suit;
	}

	public void setSuit(Number suit) {
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
