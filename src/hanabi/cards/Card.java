package hanabi.cards;

import hanabi.cards.identifiers.Color;
import hanabi.cards.identifiers.Number;

public class Card implements Comparable {
	
	private Color color;
	private Number number;
	private Boolean ownerIdentified;
	
	public Card(Color color, Number number) {
		super();
		this.color = color;
		this.number = number;
		this.ownerIdentified = false;
	}
	
	public Card(Integer colorValue, Integer numberValue) {
		
		switch (numberValue) {
		
		case 0:
			this.number = Number.ONE;
			break;
		
		case 1: 
			this.number = Number.TWO;
			break;
		
		case 2:
			this.number = Number.THREE;
			break;
		
		case 3:
			this.number = Number.FOUR;
			break;
		
		case 4:
			this.number = Number.FIVE;
			break;

		}
		
		switch (colorValue) {
		
		case 0:
			this.color = Color.BLUE;
			break;

		case 1:
			this.color = Color.GREEN;
			break;
			
		case 2:
			this.color = Color.RED;
			break;
			
		case 3:
			this.color = Color.WHITE;
			break;
			
		case 4: 
			this.color = Color.YELLOW;
			break;
			
		case 5:
			this.color = Color.MULTICOLOR;
			break;
		}
		this.ownerIdentified = false;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Number getNumber() {
		return number;
	}

	public void setNumber(Number number) {
		this.number = number;
	}
	
	public Boolean getOwningPlayerDeducedIdentity() {
		return ownerIdentified;
	}

	public void setOwningPlayerDeducedIdentity(Boolean owningPlayerDeducedIdentity) {
		this.ownerIdentified = owningPlayerDeducedIdentity;
	}

	@Override
	public String toString() {
		return color.toString() + " " + number.toString();
	}

	@Override
	public int compareTo(Object o) {
		Card c = (Card) o; // the argument will always be a card
		return this.color.getValue() < c.getColor().getValue() ? -1 : this.color.getValue() > c.getColor().getValue() ? 1 : this.number.getValue() < c.getNumber().getValue() ? -1 : this.number.getValue() > c.getNumber().getValue() ? 1 : 0;
	}
	
}
