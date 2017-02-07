import java.util.*;

public class InputMethods {
	public static Scanner scanner = new Scanner( System.in );
	
	public static boolean wantsToRepeat(){

		System.out.println("Do you want to repeat? (y for yes)");

		String c = scanner.next();

		if(c.charAt(0) == 'y' || c.charAt(0) == 'Y')
			return true;

		return false;
	} // end wantsToRepeat
	
	public static int readInt(String prompt,
					int min,
					int max){
		int inputInt;
		
		System.out.print(prompt);
		inputInt = scanner.nextInt();
		while( inputInt < min || inputInt > max ){
			System.out.println("Input out of range, must be >= "
					+ min + " and <= " + max);
			System.out.print(prompt);
			inputInt = scanner.nextInt();
		}
		return inputInt;
	} // end readInt
}

