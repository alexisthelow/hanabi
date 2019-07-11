package hanabi.game;

import java.util.ArrayList;

import hanabi.cards.Card;
import hanabi.cards.Deck;
import hanabi.cards.DiscardPile;
import hanabi.cards.PlayedSet;

public class Game {
	
	//options
	private Boolean variantGrandFinale = false; // continue play until all fireworks are complete (win) OR a necessary card is discarded (lose)
	private Boolean variantAvalancheOfColors = false; //add all multicolors, indicate as separate color
	private Boolean variantAvalancheOfColorsSingle = false; // add one of each multicolor, indicate as separate color
	private Boolean variantAvalancheOfColorsWild = false; // add all multicolors, indicate as wild color
	
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
	
	public Boolean processPlayedCard(AbstractPlayer player, Card card) throws Exception {
		
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
			
			
			//TODO notify players to update GlobalCardTrackers
			return true; // play was successful
		}
		else {
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
				throw new Exception("Game.processDiscard was unable to locate the correct discard pile for " + card.toString());
		}
		
		relevantDiscardPile.add(card);
		//TODO sort here
		//TODO notify discarding player to update GlobalCardTracker
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
	
	public void notifyPlayersOnDraw(AbstractPlayer drawingPlayer, Card card) {
		
		//for each player that is not the drawing player, get their globalCardTracker and run cardSeen()
		for (AbstractPlayer player : players) {
			if (player != drawingPlayer) {
				try {
					player.getGlobalCardTracker().cardSeen(card);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public void notifyPlayerOnDiscardOrPlay(AbstractPlayer player, Card card) {
		if (card.getOwningPlayerDeducedIdentity()) {
			return;
		}
		try {
			player.getGlobalCardTracker().cardSeen(card);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
