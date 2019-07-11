package hanabi.cards;

public class Card {
	
	private Color color;
	
	private Suit suit;
	
	public Card(Color color, Suit suit) {
		super();
		this.color = color;
		this.suit = suit;
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

	@Override
	public String toString() {
		return color + " " + suit;
	}
	
	
	
	
	
}
