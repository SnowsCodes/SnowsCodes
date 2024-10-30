package lab03;
import java.util.Scanner;  

public class Lab03a {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String input = "";
		int num; 
		int count = 0; 
		boolean notZero = true; 
		
		System.out.println("Enter your paragraph; enter 0 to end the paragraph: ");
		
		while (notZero) {
			if (in.hasNextInt() && in.nextInt() == 0) {
				notZero = false; 
			} else {
				input = in.next(); 
				count++; 
			}
		}
		
		System.out.println("Total # of words = " + count);
		in.close(); 
	}
	
}
