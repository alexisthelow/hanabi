package hanabi.driver;

import hanabi.game.Game;

public class Engine {

	//objects
	private static ScannerResponseGetter srg = new ScannerResponseGetter();
	
	private static Game game;
	
	//states
	private static boolean gameOver;
	private static boolean quitGame;
	
	public static void main(String[] args) {
		do {
			
		} while (!quitGame); //if new game is desired, loop to beginning
		//create new game
		//select variants
		//add players
		//proceed with play
			//player cannot discard if clocks == 8; player cannot provide info if clocks == 0
		//stop one round after cards are gone OR if all fuses are burned OR if grandFinale == true, stop on losing necessary card OR stop when all colors are built to maximum
		//display score
		
		
	}
	
	//main menu
	public int mainMenu() {
		System.out.println("~Hanabi~");
		System.out.println("--------");
		System.out.println("1) Start new game");
		System.out.println("2) Exit");
		
		return srg.intRequest("Make a selection", 1, 2, false);
		
	}
	
	//start new game menu
	public int newGameMenu() {
		System.out.println("New Game");
		System.out.println("--------");
		System.out.println("1) Add player");
		System.out.println("2) Select variants");
		System.out.println("3) Start game");
		System.out.println("4) Back to main menu");
		
		return srg.intRequest("Make a selection", 1, 4, false);
		
	}
	
	//select variants
	public void selectVariant() {
		
	}
	
}
