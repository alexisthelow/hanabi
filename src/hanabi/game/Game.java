package hanabi.game;

import java.util.ArrayList;

import hanabi.cards.Card;
import hanabi.cards.Deck;
import hanabi.cards.DiscardPile;
import hanabi.cards.PlayedSet;

public class Game {
	
	//options
	private Boolean variantOne = false;
	private Boolean variantTwo = false;
	private Boolean variantThree = false;
	private Boolean variantFour = false;
	
	//card locations
	private Deck deck;
	private PlayedSet playedSet;
	private DiscardPile discardPile;
	
	//resources
	private ArrayList<AbstractPlayer> players = new ArrayList<AbstractPlayer>();
	private int clocks;
	private int fuses;
	
	//states
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
			if (card.getSuit().getNumeral() == 5) {
				gainClock();
			}
			return true; // play was successful
		}
		else {
			//TODO the check has failed, a fuse should be lost, and the card added to the discard pile
			burnFuse();
			processDiscard(card);
			return false;
		}
	}
	
	public Boolean processDiscard(Card card) throws Exception {
		
		ArrayList<Card> relevantDiscardPile;
		switch (card.getColor()) {
			case BLUE:
				relevantDiscardPile = this.discardPile.getBlues();
				break;
	
			case GREEN:
				relevantDiscardPile = this.discardPile.getGreens();
				break;
	
			case RED:
				relevantDiscardPile = this.discardPile.getReds();
				break;
	
			case WHITE:
				relevantDiscardPile = this.discardPile.getWhites();
				break;
	
			case YELLOW:
				relevantDiscardPile = this.discardPile.getYellows();
				break;
	
			case MULTICOLOR:
				relevantDiscardPile = this.discardPile.getMulticolors();
				break;
				
			default:
				throw new Exception("DiscardPile.acceptDiscard was unable to locate the correct discard pile for " + card.toString());
		}
		
		relevantDiscardPile.add(card);
		//TODO sort here
		return true;
		
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
