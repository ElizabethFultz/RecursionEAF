import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This program takes user input for a variable n and then calculates the catalan number using recursion. 
 * 
 * @author elifu
 * @version 1.5
 * Recursion Project
 * Fall 2019
 */
public class CatalanDynamic {
	
	public static int n, answer; //the variable used to calculate the catalan number and store the answer

	public static void main(String[] args) {
		
		long time; //a variable to hold the time that has passed since the program started calculating the number
		String fileContent; //used to build a string that is then printed to a file
		 
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the positive integer value for n: ");
		int possible = scan.nextInt();
		
		if(isNatural(possible)) {
			n = possible;
		} else
			n = 0;
		
		long startTime = System.currentTimeMillis();
		
		answer = catalan(n);
		
		long endTime = System.currentTimeMillis();
		
		time = (endTime - startTime) / 1000;
		
		if(time < 1) {
			time = 1;
			fileContent = n + ", "  + answer + ", " + time + " second.\n";
		}//end time if
		else
			fileContent = n + ", " + answer + ", " + time + " seconds.\n";
		
		 try {
		    	
				BufferedWriter filewrite = new BufferedWriter(new FileWriter("CatalanDynamic.txt", true));
				filewrite.append(fileContent);
				filewrite.close();
				
				} //end try
		 
		 catch (IOException ex) {
					
					System.out.println("Garbage Fire: failed to write to file.");
				
				}//end catch
		
		 System.out.println("C(" + n + ") = " + answer);

	}//end main
	
	/**
	 * This method is to check that the inputed numbers are natural numbers
	 * 
	 * @param number the number to be checked 
	 * @return true if the number is natural or false if the number is not natural
	 */
	public static boolean isNatural(int number) {
		
		if(number >= 0)
			return true;
		else
			return false;
		
	}//end isNatural
	
	/**
	 * This method is what dynamically calculates the catalan number
	 * @param n the number that is having it's catalan number calculated
	 * @return the catalan number
	 */
	public static int catalan(int n) {
		
		int []catalanArray = new int[n + 2];
		
		catalanArray[0] = 1;
		catalanArray[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			catalanArray[i] = 0;
			for(int j = 0; j < i; j++) {
				catalanArray[i] += catalanArray[j] * catalanArray[i - j - 1];
			}//end j for
		}//end i for
		
		return catalanArray[n];
				
	}//end catalan

}//end class
