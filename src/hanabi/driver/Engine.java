package hanabi.driver;

import hanabi.cards.Deck;
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
	
	private static boolean startGame;
	private static boolean quitGame;
	
	public static void main(String[] args) {
		do {
			//create new game
			game = new Game();
			do {
				mainMenu();
			} while (!startGame && !quitGame);
			
			if (!quitGame) {
				//create a deck
				game.setDeck(new Deck(game.getColorVariant()));
				//deal hands to each player
				if (game.getPlayers().size() > 3) {
					for (int i = 0; i < 4; i++) {
						for (AbstractPlayer player : game.getPlayers()) {
							player.gainCardToHand(game.getDeck().getCards());
						}
					}
				} else {
					for (int i = 0; i < 5; i++) {
						for (AbstractPlayer player : game.getPlayers()) {
							player.gainCardToHand(game.getDeck().getCards());
						}
					}
				}
				//proceed with play
				//player cannot discard if clocks == 8; player cannot provide info if clocks == 0
				//stop one round after cards are gone OR if all fuses are burned OR if grandFinale == true, stop on losing necessary card OR stop when all colors are built to maximum
				//display score
			}
			
		} while (!quitGame); //if new game is desired, loop to beginning
		
		
		
	}
	
	//main menu
	public static void mainMenu() {
		System.out.println("~Hanabi~");
		System.out.println("--------");
		System.out.println("1) Start new game");
		System.out.println("2) Exit");
		
		switch (srg.intRequest("Make a selection", 1, 2, false)) {
			case 1:
				newGameMenu();
				break;
			case 2:
				quitGame = true;
				break;
		}
	}
	
	//start new game menu
	public static void newGameMenu() {
		boolean exitMenu = false;
		do {
			System.out.println("New Game");
			System.out.println("--------");
			System.out.println("1) Add / Remove / Change Players");
			System.out.println("2) Select variants");
			System.out.println("3) Start game");
			System.out.println("4) Back to main menu");
			switch (srg.intRequest("Make a selection", 1, 4, false)) {
				case 1:
					alterPlayersMenu();
					break;
				case 2:
					selectVariantMenu();
					break;
				case 3:
					startGame = true; 
					break;
				case 4:
					exitMenu = true;
					break;
			}
		} while (!exitMenu && !startGame);
		
		
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
			System.out.println("5) Go back");
			
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
	
	//alter players menu
	public static void alterPlayersMenu() {
		boolean exitAlterPlayersMenu = false;
		do {
			System.out.println("Add / Remove players");
			System.out.println("--------");
			System.out.println("1) Add player");
			System.out.println("2) Remove player");
			System.out.println("3) Change first player");
			System.out.println("4) Go back");
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
					exitAlterPlayersMenu = true;
					break;
			}
		} while (!exitAlterPlayersMenu);
	}
	
	//add player menu
	public static void addPlayerMenu() { // TODO only 2-5 players is allowed
		boolean exitAddPlayerMenu = false;
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
					exitAddPlayerMenu = true;
					break;
					
			}
			
		} while (!exitAddPlayerMenu);
		
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
	
	
	
}
