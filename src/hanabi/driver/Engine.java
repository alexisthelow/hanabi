package hanabi.driver;

import java.util.ArrayList;
import java.util.List;

import hanabi.cards.Card;
import hanabi.cards.identifiers.*;
import hanabi.cards.identifiers.Number;
import hanabi.game.AbstractPlayer;
import hanabi.game.ColorVariant;
import hanabi.game.ComputerPlayer;
import hanabi.game.Game;
import hanabi.game.HumanPlayer;

public class Engine {

	//objects
	private static ScannerResponseGetter srg = new ScannerResponseGetter();
	
	private static Game game;
	
	//states
	
	private static boolean startGame = false;
	private static boolean quitGame = false;
	
	public static void main(String[] args) {
		while (!quitGame) {
			mainMenu();
			
			if (startGame) {
				dealOpeningHand(); //deal cards
				do { // proceed with normal play
					for (AbstractPlayer currentPlayer : game.getPlayers()) {  // one cycle here represents one player's turn
						currentPlayer.deduceFromPersonalCardTracker(); // deduce hand from global tracker
						
						if (currentPlayer instanceof HumanPlayer) { // current player is a human
							Move selectedMove = requestMoveFromPlayer((HumanPlayer)currentPlayer); // get the human's move
							
						}
						else { // current player is the computer
							//TODO figure out what the computer is going to do
						}
					}
					
					
					
				} while (!game.isGameOver());
				
				
				// discover possible actions
					// if clocks == 0, cannot give info
					// if clocks == 8, cannot discard
					
				// if player is human, ask for move
				// if computer, discover move
				// execute move
					// if discard, regain clock and player gains new card
					// if play, lose card from hand and regain clock if card number == 5
					// if give info, lose clock and notify receiving player
				
				//at end of turn, check for game end :
					//necessary card lost OR deck empty and last turn complete?
					//all stacks complete?
				
			}
			
		}
		
	}
	
	//main menu
	public static void mainMenu() {
		System.out.println("Hanabi");
		System.out.println("--------");
		System.out.println("1) Start game");
		System.out.println("2) Quit game");
		switch (srg.intRequest("Make a selection", 1, 2, false)) {
			case 1:
				game = new Game();
				startGame = true;
				alterPlayersMenu();
				break;
			case 2:
				quitGame = true;
				break;
		}
	}
	
	//alter players menu
	public static void alterPlayersMenu() {
			boolean exitMenu = false;
			do {
				System.out.println("Add / Remove players");
				System.out.println("--------");
				System.out.println("1) Add player");
				System.out.println("2) Remove player");
				System.out.println("3) Change first player");
				System.out.println("4) Continue");
				switch (srg.intRequest("Make a selection", 1, 4, false)) {
					case 1:
						addPlayerMenu();
						break;
					case 2:
						removePlayerMenu();
						break;
					case 3:
						changeFirstPlayerMenu();
						break;
					case 4:
						if (game.getPlayers().size() < 2) { // can't leave menu with fewer than two players
							System.out.println("Too few players! Please add " + (2 - game.getPlayers().size()) + " more players.");
						}
						else if (game.getPlayers().size() > 5) { // can't leave menu with more than five players
							System.out.println("Too many players! Please remove " + (game.getPlayers().size() - 5) + " players.");
						}
						else { // must be all good
							exitMenu = true;
						}
						break;
				}
			} while (!exitMenu);
			selectVariantMenu();
		}
	
	//add player menu
	public static void addPlayerMenu() { 
		boolean exitMenu = false;
		AbstractPlayer newPlayer;
		
		do {
			System.out.println("Add Player");
			System.out.println("--------");
			System.out.println("1) Add human player");
			System.out.println("2) Add computer player");
			System.out.println("3) Go back");
			
			switch (srg.intRequest("Make a selection", 1, 3, false)) { // is it a human or a computer?
				
				case 1: // adding a human player
					newPlayer = new HumanPlayer();
					newPlayer.setName(srg.stringRequest("Enter the player's name"));
					game.getPlayers().add(newPlayer);
					
					break;
				case 2: // adding computer player
					newPlayer = new ComputerPlayer();
					newPlayer.setName(srg.stringRequest("Enter the player's name"));
					game.getPlayers().add(newPlayer);
					break;
				case 3: // exit
					exitMenu = true;
					break;
					
			}
			
		} while (!exitMenu);
		
	}
	
	//remove player menu
	public static void removePlayerMenu() {
		boolean exitRemovePlayerMenu = false;
		if (game.getPlayers().size() > 0) {
			do {
				System.out.println("Remove Player");
				System.out.println("--------");
				int counter = 1;
				for (AbstractPlayer player : game.getPlayers()) {
					System.out.println(counter++ + ") " + player.getName());
				}
				System.out.println(game.getPlayers().size() + 1 + ") Go back");
				int response = srg.intRequest("Make a selection", 1, game.getPlayers().size() + 1, false);
				if (response == game.getPlayers().size() + 1) {
					exitRemovePlayerMenu = true;
				} else {
					game.getPlayers().remove(response - 1);
				}
			} while (!exitRemovePlayerMenu);
		}
		else {
			System.out.println("No players to remove!");
		}
	}
	
	//change first player menu
	public static void changeFirstPlayerMenu() {
		boolean exitMenu = false;
		if (game.getPlayers().size() > 1) {
			do {
				System.out.println("Change First Player");
				System.out.println("--------");
				System.out.println(game.getPlayers().get(0).getName() + " is currently the first player.");
				for (int i = 1; i < game.getPlayers().size(); i++) {
					System.out.println(i + ") " + game.getPlayers().get(i).getName());
				}
				System.out.println(game.getPlayers().size() + ") Go back");
				int response = srg.intRequest("Select a new first player", 1, game.getPlayers().size(), false);
				if (response == game.getPlayers().size()) {
					exitMenu = true;
				}
				else {
					game.getPlayers().add(0, game.getPlayers().remove(response));
				}
			} while (!exitMenu);
		}
		else {
			System.out.println("There is only one player!");
		}
	}
	
	//select variant menu
	public static void selectVariantMenu() {
			boolean exitVariantMenu = false;
			do {
				//display grand finale
				if (game.getVariantGrandFinale() || game.getColorVariant().equals(ColorVariant.MULTICOLOR_WILD)) {
					System.out.println("1) Grand Finale - ENABLED -");
				}
				else {
					System.out.println("1) Grand Finale");
				}
				//display multicolor
				if (game.getColorVariant().equals(ColorVariant.MULTICOLOR)) {
					System.out.println("2) Avalanche of Colors - ENABLED -");
				}
				else {
					System.out.println("2) Avalanche of Colors");
				}
				//display multicolor single
				if (game.getColorVariant().equals(ColorVariant.MULTICOLOR_SINGLE)) {
					System.out.println("3) Avalanche of Colors (Single) - ENABLED -");
				}
				else {
					System.out.println("3) Avalanche of Colors (Single)");
				}
				//display multicolor wild
				if (game.getColorVariant().equals(ColorVariant.MULTICOLOR_WILD)) {
					System.out.println("4) Avalanche of Colors (Wild) - ENABLED -");
				}
				else {
					System.out.println("4) Avalanche of Colors (Wild)");
				}
				//display back
				System.out.println("5) Continue");
				
				switch (srg.intRequest("Select a variant to enable/disable", 1, 5, false)) {
					case 1:
						if (game.getColorVariant().equals(ColorVariant.MULTICOLOR_WILD)) { // if multicolor wild is enabled, grand finale must also be
							System.out.println("Avalanche of Colors (Wild) can only be played if Grand Finale is enabled.");
						}
						else { // otherwise, set it to the opposite of what it was
							game.setVariantGrandFinale(!game.getVariantGrandFinale());
						}
						break;
		
					case 2:
						game.setColorVariant(ColorVariant.MULTICOLOR);
						break;
						
					case 3:
						game.setColorVariant(ColorVariant.MULTICOLOR_SINGLE);
						break;
						
					case 4:
						if (game.getColorVariant().equals(ColorVariant.MULTICOLOR_WILD)) { // if multicolor wild is already enabled
							game.setVariantGrandFinale(false); // grand finale must be set to false
							game.setColorVariant(ColorVariant.NONE); // and colorVariant must be set to none
						}
						else { // otherwise enable both
							game.setVariantGrandFinale(true); 
							game.setColorVariant(ColorVariant.MULTICOLOR_WILD);
						}
						break;
						
					case 5:
						exitVariantMenu = true;
						break;
						
				}
			} while (!exitVariantMenu);
			
		}
	
	//deal opening hand
	public static void dealOpeningHand() {
		int cardsPerHand = game.getPlayers().size() > 3 ? 4 : 5;
		for (int i = 0; i < cardsPerHand; i++) {
			for (AbstractPlayer gainingPlayer : game.getPlayers()) {
				Card gainedCard = gainingPlayer.gainCardToHand(game.getDeck(), game.getColorVariant());
				notifyPlayersOnGainToHand(gainingPlayer, gainedCard);
			}
		}
	} 
	
	//notifies all non-drawing players to decrement card from personal tracker
	public static void notifyPlayersOnGainToHand(AbstractPlayer gainingPlayer, Card card) {
		for (AbstractPlayer nonGainingPlayer : game.getPlayers()) {
			if (!nonGainingPlayer.equals(gainingPlayer)) {
				nonGainingPlayer.getPersonalCardTracker().cardSeen(card);
			}
		}
	}
	
	// notifies player to update personal tracker after play or discard
	public static void notifyPlayerOnPlayOrDiscard(AbstractPlayer playingPlayer, Card card) {
		playingPlayer.getPersonalCardTracker().cardSeen(card);
	}
	
	// returns a move built from requested: moveType, targetPlayer, handIndices
	public static Move requestMoveFromPlayer(HumanPlayer currentPlayer) { // this should only be called for a human player
		// first discover what player can do
		boolean giveInfoPossible = game.isGiveInfoPossible();
		boolean discardPossible = game.isDiscardPossible();
		int numberOfOptions;
		Move newMove = new Move();
		MoveType secondOption;
		
		// request move type
		System.out.println("1) Play a card");
		if (discardPossible && giveInfoPossible) {
			System.out.println("2) Discard a card");
			System.out.println("3) Give information");
			numberOfOptions = 3;
			secondOption = MoveType.DISCARD_CARD;
		}
		else if (discardPossible) {
			System.out.println("2) Discard a card");
			numberOfOptions = 2;
			secondOption = MoveType.DISCARD_CARD;
		}
		else if (giveInfoPossible) {
			System.out.println("2) Give information");
			numberOfOptions = 2;
			secondOption = MoveType.GIVE_INFO;
		}
		else { // unreachable code, but won't compile without this else statement, hope it never causes a problem!! lol
			numberOfOptions = 1; 
			secondOption = MoveType.DISCARD_CARD;
		}
		switch (srg.intRequest("Make a selection", 1, numberOfOptions, false)) {
			case 1:
				newMove.setMoveType(MoveType.PLAY_CARD);
				break;
	
			case 2:
				newMove.setMoveType(secondOption);
				break;
				
			case 3:
				newMove.setMoveType(MoveType.GIVE_INFO);
				break;
		}
		if (newMove.getMoveType().equals(MoveType.PLAY_CARD) || newMove.getMoveType().equals(MoveType.DISCARD_CARD)) { // movetype is play card or discard, get single index
			newMove.setTargetPlayer(currentPlayer);
			newMove.setSingleTargetHandIndex(requestSingleHandIndexFromPlayer(currentPlayer, newMove.getMoveType()));
		}
		else { // movetype is give info, request target player, request color or number
			newMove.setTargetPlayer(requestTargetPlayer(currentPlayer));
			newMove.setTargetHandIndices(discoverHandIndicesByCardAttribute(newMove.getTargetPlayer(), requestSpecificCardAttribute(requestCardAttributeType()))); // woah
		}
		//the move should now be complete!
		return newMove;
		
	}
	
	//returns an int indicating a hand index, value corrected to zero start
	public static int requestSingleHandIndexFromPlayer(HumanPlayer currentPlayer, MoveType moveType) {
		return srg.intRequest("Select a card from your hand to " + moveType.getActionName(), 1, currentPlayer.getHand().size(), false) - 1;
	}
	
	// returns an abstractPlayer for the purpose of giving info 
	public static AbstractPlayer requestTargetPlayer(HumanPlayer currentPlayer) {
		int playerCounter = 0;
		ArrayList<AbstractPlayer> targetablePlayers = game.getPlayers(); // get the list of players
		targetablePlayers.remove(currentPlayer); // remove the current player from consideration
		for (AbstractPlayer targetablePlayer : targetablePlayers) {
			System.out.println(++playerCounter + ") " + targetablePlayer.getName());
		}
		return targetablePlayers.get(srg.intRequest("Select a player", 1, targetablePlayers.size(), false) - 1);
	}
	
	//returns a cardAttribute type for the purpose of giving info
	public static String requestCardAttributeType() {
		String attributeType;
		System.out.println("1) Color");
		System.out.println("2) Number");
		switch (srg.intRequest("Select a card attribute", 1, 2, false)) {
			case 1:
				attributeType = "color";
				break;
			case 2:
				attributeType = "number";
				break;
			default: // sigh
				attributeType = "crashnowplz";
		}
		return attributeType;
	}
	
	//returns a specific cardAttribute for the purpose of giving info
	public static CardAttribute requestSpecificCardAttribute(String attributeType) {
		List<CardAttribute> attributeList = attributeType.equalsIgnoreCase("color") ? Color.getListOfColors() : Number.getListOfNumbers();
		int counter = 0;
		for (CardAttribute cardAttribute : attributeList) {
			System.out.println(++counter + ") " + cardAttribute.getName());
		}
		return attributeList.get(srg.intRequest("Select a " + attributeType, 1, attributeList.size(), false) - 1);
	}
	
	//accepts a target player and specific cardAttribute (e.g., Color.BLUE, Number.ONE) and returns list of indices of cards with that attribute
	public static ArrayList<Integer> discoverHandIndicesByCardAttribute(AbstractPlayer targetPlayer, CardAttribute cardAttribute) {
		ArrayList<Integer> handIndices = new ArrayList<Integer>();
		int counter = 0;
		for (Card c: targetPlayer.getHand()) {
			if (c.getColor().equals(cardAttribute) || c.getNumber().equals(cardAttribute)) { // if the card in current iteration has the indicated cardAttribute
				handIndices.add(counter);
			}
			counter++;
		}
		return handIndices;
	}
	
}
