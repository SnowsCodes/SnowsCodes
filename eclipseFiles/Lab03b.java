package lab03;
import java.util.Scanner; 

public class Lab03b {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in); 
		int sum = 0; 
		int count = 0; 
		
		System.out.println("Enter numbers to be added up; end with any non-numeric string of characters to end the sequence");
		
		while(in.hasNextInt()) {
			sum += in.nextInt(); 
			count++; 
		}
		
		if (count == 0) {
			System.out.println("There is no average since we have an empty set of numbers.");
		} else {
		    System.out.println("Total = " + sum);
			System.out.println("Average = " + (1.0*sum/count));
		}
	}
	
}
