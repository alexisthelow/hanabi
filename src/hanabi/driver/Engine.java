package hanabi.driver;

import hanabi.game.Game;

public class Engine {

	//objects
	private static ScannerResponseGetter srg = new ScannerResponseGetter();
	
	private Game game;
	
	//states
	private boolean gameOver;
	private boolean quitGame;
	
	public static void main(String[] args) {
		//create new game
		//select variants
		//add players
		//proceed with play
			//player cannot discard if clocks == 8; player cannot provide info if clocks == 0
		//stop one round after cards are gone OR if all fuses are burned OR if grandFinale == true, stop on losing necessary card OR stop when all colors are built to maximum
		//display score
		//if new game is desired, loop to beginning
		
	}
	
	//main menu
	public int mainMenu() {
		System.out.println("~Hanabi~");
		System.out.println("--------");
		System.out.println("1) Start new game");
		System.out.println("2) Exit");
		
		//TODO add logic for selection
		
	}
	
	//start new game menu
	public int newGameMenu() {
		System.out.println("New Game");
		System.out.println("--------");
		System.out.println("1) Add player");
		System.out.println("2) Select variants");
		System.out.println("3) Start game");
		System.out.println("4) Exit");
		
	}
	
	//select variants
	public void selectVariant() {
		
	}
	
}
