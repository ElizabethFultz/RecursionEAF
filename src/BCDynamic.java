import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This program takes user input for a variable n and a variable k and then calculates the binomial coefficient using dynamic programming. 
 * 
 * @author elifu
 * @version 1.5
 * Recursion Project
 * Fall 2019
 */
public class BCDynamic {
	
	public static int n, k, answer; //the variables used to calculate the binomial coefficient and store the answer

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
		
		System.out.print("Please enter the positive integer value for k: ");
		int maybe = scan.nextInt();
		
		if(maybe <= n) {
			if(isNatural(maybe)) {
				k = maybe;
			}//end natural if
			else
				k = 0;
		}//end n if
		else
			k = 0;
		
		long startTime = System.currentTimeMillis();
		
		answer = binomialCoefficient(n, k);
		
		long endTime = System.currentTimeMillis();
		
		time = (endTime - startTime) / 1000;
		
		if(time < 1) {
			time = 1;
			fileContent = n + ", " + k + ", " + answer + ", " + time + " second.\n";
		}//end time if
		else
			fileContent = n + ", " + k + ", " + answer + ", " + time + " seconds.\n";
		
		 try {
		    	
				BufferedWriter filewrite = new BufferedWriter(new FileWriter("BCDynamicOutput.txt", true));
				filewrite.append(fileContent);
				filewrite.close();
				
				} //end try
		 
		 catch (IOException ex) {
					
					System.out.println("Garbage Fire: failed to write to file.");
				
				}//end catch
		
		System.out.println("There are " + answer + " ways to choose " + k + " subsets from " + n + " items.");
		
	
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
	 * This method calculates the binomial coefficient dynamically
	 * @param n the number of total objects
	 * @param k the number of objects needed in a subset
	 * @return the binomial coefficient
	 */
	public static int binomialCoefficient(int n, int k) {
		
		int[][] bcArray = new int[n + 1][k + 1]; //an array to hold the values of previous binomial coefficients
		
		int i, j; //the values that control the array
		
		for(i = 0; i <= n; i++) {
			for(j = 0; j <= min(i, k); j++) {
				if(j == 0 || j == i)
					bcArray[i][j] = 1;
				else {
					bcArray[i][j] = bcArray[i - 1][j - 1] + bcArray[i - 1][j];
	
				}//end else
			}//end j for
		}//end i for
		
		
		return bcArray[n][k];
		
	}//end binominalCoefficent
	
	/**
	 * This method finds the minimum value between two integers
	 * @param i the first value to compare
	 * @param k the second value to compare
	 * @return the lower value of the two inputs
	 */
	public static int min(int i, int k) {
		
		int smallest = 0; //used to hold the smallest value 
		
		if(i < k)
			smallest = i;
		else if(k < i)
			smallest = k;
		
		return smallest;
	}//end min

}//end class
