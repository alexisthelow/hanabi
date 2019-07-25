package hanabi.driver;

import java.util.Scanner;

public class ScannerResponseGetter {
	
	Scanner kb = new Scanner(System.in);

	public int intRequest(String prompt, int lowestVal, int highestVal, boolean displayRange) {
		int response;
		
		while (true) {
			System.out.print(prompt);
			System.out.print("between " + lowestVal + " and " + highestVal + ": ");
			try {
				response = Integer.parseInt(kb.nextLine());
				if (response >= lowestVal && response <= highestVal) {
					break;
				}
				throw new Exception();
			} catch (Exception e) {
				System.out.println("\n   Invalid input. Please try again.");
			} 
		}
		return response;
	}
	
	public double doubleRequest(String prompt, double lowestVal, double highestVal, boolean displayRange) {
		
		double response;
		
		while (true) {
			System.out.print(prompt);
			System.out.print(" between " + lowestVal + " and " + highestVal + ": ");
			
			try {
				response = Double.parseDouble(kb.nextLine());
				if (response >= lowestVal && response <= highestVal) {
					break;
				}
				throw new Exception();
			} catch (Exception e) {
				System.out.println("Invalid input. Please try again.");
			}
		}
		return response;
	}
	
	public boolean requestConfirmation(String prompt) {
		
		boolean answer;
		
		while (true) {
			
			System.out.print("\n" + prompt);
			
			String response = kb.nextLine();
			
			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) {
				
				answer = true;
				break;
				
			}
			else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no")) {
				
				answer = false;
				break;
				
			}
			else {
				
				System.out.println("Invalid input. Please try again.");
				
			}
		}
		
		return answer;
		
	}
	
	public String stringRequest(String prompt) {
		
		System.out.print("\n" + prompt);
		
		return kb.nextLine();
		
	}

}
