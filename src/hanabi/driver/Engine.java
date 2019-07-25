package hanabi.driver;

import hanabi.game.ColorVariant;
import hanabi.game.Game;

public class Engine {

	//objects
	private static ScannerResponseGetter srg = new ScannerResponseGetter();
	
	private static Game game;
	
	//states
	
	private static boolean quitGame;
	
	public static void main(String[] args) {
		do {
			
			//create new game
			//select variants
			//add players
			//proceed with play
				//player cannot discard if clocks == 8; player cannot provide info if clocks == 0
			//stop one round after cards are gone OR if all fuses are burned OR if grandFinale == true, stop on losing necessary card OR stop when all colors are built to maximum
			//display score
			
		} while (!quitGame); //if new game is desired, loop to beginning
		
		
		
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
	
	//select variant menu
	public void selectVariantMenu() {
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
	
}
