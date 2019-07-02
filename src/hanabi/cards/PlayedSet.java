package hanabi.cards;

import java.util.ArrayList;

public class PlayedSet {
	
	private ArrayList<Card> blues = new ArrayList<Card>();
	private ArrayList<Card> greens = new ArrayList<Card>();
	private ArrayList<Card> reds = new ArrayList<Card>();
	private ArrayList<Card> whites = new ArrayList<Card>();
	private ArrayList<Card> yellows = new ArrayList<Card>();
	private ArrayList<Card> multicolors = new ArrayList<Card>();
	
	public PlayedSet() {
		super();
		this.blues = new ArrayList<Card>();
		this.greens = new ArrayList<Card>();
		this.reds = new ArrayList<Card>();
		this.whites = new ArrayList<Card>();
		this.yellows = new ArrayList<Card>();
		this.multicolors = new ArrayList<Card>();
	}

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
