package hanabi.cards;

import java.util.ArrayList;

public class DiscardPile {
	
	private ArrayList<Card> blues;
	private ArrayList<Card> greens;
	private ArrayList<Card> reds;
	private ArrayList<Card> whites;
	private ArrayList<Card> yellows;
	private ArrayList<Card> multicolors;
	
	public DiscardPile() {
		super();
		this.blues = new ArrayList<Card>();
		this.greens = new ArrayList<Card>();
		this.reds = new ArrayList<Card>();
		this.whites = new ArrayList<Card>();
		this.yellows = new ArrayList<Card>();
		this.multicolors = new ArrayList<Card>();
	}
	
	//TODO add sorter
	
	public ArrayList<Card> getBlues() {
		return blues;
	}
	
	public ArrayList<Card> getGreens() {
		return greens;
	}

	public ArrayList<Card> getReds() {
		return reds;
	}
	
	public ArrayList<Card> getWhites() {
		return whites;
	}
	
	public ArrayList<Card> getYellows() {
		return yellows;
	}
	
	public ArrayList<Card> getMulticolors() {
		return multicolors;
	}

}
