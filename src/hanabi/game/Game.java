package hanabi.game;

import java.util.ArrayList;

import hanabi.cards.Card;
import hanabi.cards.Deck;
import hanabi.cards.PlayedSet;

public class Game {
	
	private Boolean variantOne = false;
	private Boolean variantTwo = false;
	private Boolean variantThree = false;
	private Boolean variantFour = false;
	private ArrayList<AbstractPlayer> players = new ArrayList<AbstractPlayer>();
	private Deck deck;
	private PlayedSet playedSet;
	private int clocks;
	private int fuses;
	private Boolean gameOver;
	
	public Boolean processPlayedCard(Card card) throws Exception {
		
		ArrayList<Card> relevantColor;
		
		switch (card.getColor()) {
		
			case BLUE:
				relevantColor = playedSet.getBlues();
				break;
				
			case GREEN:
				relevantColor = playedSet.getGreens();
				break;
				
			case RED:
				relevantColor = playedSet.getReds();
				break;
				
			case WHITE:
				relevantColor = playedSet.getWhites();
				break;
				
			case YELLOW:
				relevantColor = playedSet.getYellows();
				break;
				
			case MULTICOLOR:
				relevantColor = playedSet.getMulticolors();
				break;
				
			default:
				throw new Exception("Game.processPlayedCard(): couldn't find correct card array for card " + card.getColor() + card.getSuit().getNumeral());
			
		}
		
		if(card.getSuit().getNumeral()==relevantColor.size() + 1) {
			relevantColor.add(card);
			//TODO should regain clock if card was a 5
			if (card.getSuit().getNumeral() == 5) {
				gainClock();
			}
			return true; // play was successful
		}
		else {
			//TODO the check has failed, a fuse should be lost, and the card added to the discard pile
		}
	}
	
	public Boolean processDiscard(Card card) throws Exception {
		
		ArrayList<Card> relevantDiscardPile;
		switch (card.getColor()) {
			case BLUE:
				relevantDiscardPile = blues;
				break;
	
			case GREEN:
				relevantDiscardPile = greens;
				break;
	
			case RED:
				relevantDiscardPile = reds;
				break;
	
			case WHITE:
				relevantDiscardPile = whites;
				break;
	
			case YELLOW:
				relevantDiscardPile = yellows;
				break;
	
			case MULTICOLOR:
				relevantDiscardPile = multicolors;
				break;
				
			default:
				throw new Exception("DiscardPile.acceptDiscard was unable to locate the correct discard pile for " + card.toString());
		}
		relevantDiscardPile.add(card);
		
		
	}
	
	public Boolean gainClock() {
		if (this.clocks < 8) {
			this.clocks++;
			return true;
		}
		return false;
	}
	
	public Boolean payClock() {
		if (this.clocks > 0) {
			this.clocks--;
			return true;
		}
		return false;
	}
	
	public Boolean burnFuse() {
		if (this.fuses > 0) {
			this.fuses--;
			return true;
		}
		this.gameOver = true;
		return false;
	}
	
}
