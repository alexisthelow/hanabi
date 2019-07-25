package hanabi.game;

import java.util.ArrayList;

import hanabi.cards.Card;
import hanabi.cards.Deck;
import hanabi.cards.DiscardPile;
import hanabi.cards.PlayedSet;

public class Game {
	
	//options
	private Boolean variantGrandFinale = false; // continue play until all fireworks are complete (win) OR a necessary card is discarded (lose)
	private ColorVariant colorVariant;
	
	//card locations
	private Deck deck;
	private PlayedSet playedSet;
	private DiscardPile discardPile;
	
	//resources
	private ArrayList<AbstractPlayer> players = new ArrayList<AbstractPlayer>();
	private int clocks;
	private int fuses;
	
	//states
	private static boolean gameOver;
	
	public Game() {
		this.clocks = 8;
		this.fuses = 3;
	}

	public void playCard(AbstractPlayer player, int handIndex) {
		Card playedCard = player.getHand().remove(handIndex);
		player.getCardInfoTables().remove(handIndex);
		player.gainCardToHand(this.deck.getCards().pop());
		try {
			processPlayedCard(player, playedCard);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void processPlayedCard(AbstractPlayer player, Card card) throws Exception {
		
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
				throw new Exception("Game.processPlayedCard(): couldn't find correct card array for card " + card.getColor() + card.getNumber().getValue());
			
		}
		
		if(card.getNumber().getAdjustedValue()==relevantColor.size() + 1) {
			relevantColor.add(card);
			if (card.getNumber().getAdjustedValue() == 5) {
				gainClock();
			}
			
			
			//TODO notify players to update GlobalCardTrackers
		}
		else {
			burnFuse();
			processDiscard(card);
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

	public Boolean getVariantGrandFinale() {
		return variantGrandFinale;
	}

	public void setVariantGrandFinale(Boolean variantGrandFinale) {
		this.variantGrandFinale = variantGrandFinale;
	}

	public ColorVariant getColorVariant() {
		return colorVariant;
	}

	public void setColorVariant(ColorVariant colorVariant) {
		this.colorVariant = colorVariant;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public PlayedSet getPlayedSet() {
		return playedSet;
	}

	public void setPlayedSet(PlayedSet playedSet) {
		this.playedSet = playedSet;
	}

	public DiscardPile getDiscardPile() {
		return discardPile;
	}

	public void setDiscardPile(DiscardPile discardPile) {
		this.discardPile = discardPile;
	}

	public ArrayList<AbstractPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<AbstractPlayer> players) {
		this.players = players;
	}

	public int getClocks() {
		return clocks;
	}

	public void setClocks(int clocks) {
		this.clocks = clocks;
	}

	public int getFuses() {
		return fuses;
	}

	public void setFuses(int fuses) {
		this.fuses = fuses;
	}
	
}
