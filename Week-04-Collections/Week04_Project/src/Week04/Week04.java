package Week04;

import java.util.Scanner;

public class Week04 {
	
	public static String Question7(String word, int n) {
		
		String sResult = "";
		// For loop to concat word, n times.
		for(int i = 0; i < n; i++) {
			sResult += word;
		}
		
		return sResult;
		
	}
	
	public static String Question8(String firstName, String lastName) {
		
		// Using + operator to concat first & last name separated by a space.
		return firstName + " " + lastName;
	}
	
	public static boolean Question9(int[] numArray) {
		
		int sum = 0;
		// For loop to add up all numbers.
		for(int num : numArray) {
			sum += num;
		}
		
		System.out.println("9. Sum of Array: " + sum);
		// User ternary operator to check if sum is greater than 100.
		boolean result = sum > 100 ? true : false; 
		return result;
	}
	
	public static double Question10(double[] doubleArray) {
		
		double sum = 0.0;
		// For loop to add up all numbers.
		for(double num : doubleArray) {
			sum += num;
		}
		return sum/doubleArray.length;
	}
	
	public static boolean Question11(double[] dArray1, double[] dArray2) {
		
		double sum1 = 0;
		double sum2 = 0;
		double avg1 = 0;
		double avg2 = 0;
		//Assuming both array are equal length.
		// For loop to add up all numbers.
		for(int i = 0; i < dArray1.length; i++) {
			sum1 += dArray1[i];
			sum2 += dArray2[i];
		}
		avg1 = sum1/dArray1.length;
		avg2 = sum2/dArray2.length;
		
		System.out.println("11. First Array Avg: " + avg1 + ", Second Array Avg: " + avg2);
		// User ternary operator to check if avg1 is larger than avg2.
		boolean result = avg1 > avg2? true : false;
		return result;
	}
	
	public static boolean willBuyDrink(boolean isHotOutside, double moneyInPocket) {
		// Use if-else statement for logic operation.
		if(isHotOutside && moneyInPocket > 10.50) {
			return true;
		}else {
			return false;
		}
		
	}
	public static void Fibonacci(int n) {
		//Calculating the Fibonacci sequence to the nth place.
		// Initialize the first two number of the fib sequence which are 0 and 1.
		int firstNum = 0; 
		int secondNum = 1;
		
		//Create for loop to go to nth place.
		for(int i = 0; i < n; i++) {
			System.out.print(firstNum + " "); // Print firstNum as starting number. 
			// To calculate the next number in the Fib sequence we need the sum of the 2 previous numbers.
			int nextSum = firstNum + secondNum;
			// After getting the next number in the sequence we will now swap the places.
			// The next sum will be placed in secondNum and secondNum will move down to firstNum;
			// Example: 0 + 1 = 1 -> 1 + 1 = 2 -> 1 + 2 = 3.
			firstNum = secondNum; 
			secondNum = nextSum;
		}
		
	}
	/**
	 * 12. Write a method called willBuyDrink that takes a boolean isHotOutside, and
	 * a double moneyInPocket, and returns true if it is hot outside and if
	 * moneyInPocket is greater than 10.50.
	 */
	public static void main(String[] args) {
	/**
	 * 1. Create an array of int called ages that contains the following values: 3, 9, 23, 64, 2, 8, 28, 93.
	 *  
	 * a. Programmatically subtract the value of the first element in the array from
	 * the value in the last element of the array (i.e. do not use ages[7] in your
	 * code). Print the result to the console.
	 * 
	 * b. Create a new array of int called ages2 with 9 elements (ages2 will be
	 * longer than the ages array, and have more elements).
	 * 
		 * i. Make sure that there are 9 elements of type int in this new array.
		 * 
		 * ii. Repeat the subtraction from Step 1.a. (Programmatically subtract the
		 * value of the first element in the new array ages2 from the last element of
		 * ages2).
		 * 
		 * iii. Show that using the index values for the elements is dynamic (works for
		 * arrays of different lengths).
	 * 
	 * c. Use a loop to iterate through the array and calculate the average age.
	 * 
	 * Print the result to the console.
	 */
		int[] ages = new int[]{3, 9, 23, 64, 2, 8, 28, 93};
		
		//a. We get the last index using array length method and subtract 1 from it.  
		int result = ages[ages.length - 1] - ages[0];
		System.out.println("1a. " + result);
		
		//b.
		int[] ages2  = new int[] {1,2,3,4,5,6,7,8,9};
		result = ages2[ages2.length-1] - ages2[0];
		System.out.println("1b. " + result);
		
		//iii. If the size of the array to 5, using the length method we can still find the last index.
		ages2 = new int[] {1,2,3,4,5};
		result = ages2[ages2.length-1] - ages2[0];
		System.out.println("1b.iii. " + result);
		
		//c. Using  a for enhanced loop to get the sum and divide by the length of the array.
		result = 0;
		for(int num : ages) {
			result +=  num;
		}
		System.out.println("c. " + result/ages.length);
	
	
	/** 
	 * 2. Create an array of String called names that contains the following values:
	 * “Sam”, “Tommy”, “Tim”, “Sally”, “Buck”, “Bob”.
	 * 
	 * a. Use a loop to iterate through the array and calculate the average number
	 * of letters per name. Print the result to the console.
	 * 
	 * b. Use a loop to iterate through the array again and concatenate all the
	 * names together, separated by spaces, and print the result to the console.
	 */
		String[] names = new String[]{"Sam", "Tommy", "Tim", "Sally", "Buck", "Bob"};
		result = 0;
		//a. Use the enhanced for loop to iterate through the array, 
		//  and String length method to get the names total length.
		for(String name : names) {
			result += name.length();
		}
		System.out.println("2a. " + result/names.length);
		
		//b. Here we have different ways of concatenate string together: 
		//	String.concat method, StringBuilder, and + operator.
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < names.length; i++) {
			sb.append(names[i]).append(" ");
		}
		System.out.println("2b. " + sb);
	
	/**
	 * 3. How do you access the last element of any array?
	 */
		System.out.println("3. Use array.length - 1 to access the last element: array[array.length-1]");		
	
	/**
	 * 4. How do you access the first element of any array?
	 */
		System.out.println("4. Use index 0 to access the first element: array[0].");	
	
	/**
	 * 5. Create a new array of int called nameLengths. Write a loop to iterate over
	 * the previously created names array and add the length of each name to the
	 * nameLengths array.
	 */
		int[] nameLengths = new int[6];
		
		for(int i = 0; i < names.length; i++) {
			nameLengths[i] = names[i].length();
		}
		
	/**
	 * 6. Write a loop to iterate over the nameLengths array and calculate the sum
	 * of all the elements in the array. Print the result to the console.
	 */
		result = 0;
		for(int num : nameLengths) {
			result += num;
		}
		System.out.println("6. " + result);
		
	/**
	 * 7. Write a method that takes a String, word, and an int, n, as arguments and
	 * returns the word concatenated to itself n number of times. (i.e. if I pass in
	 * “Hello” and 3, I expect the method to return “HelloHelloHello”).
	 */
		// Get User input
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter a word:");
		String word = scanner.nextLine();
		System.out.println("Please enter a number:");
		int n = scanner.nextInt();
		scanner.nextLine(); // Consuming remainder of the line after Scanner.nextInt.
		
		// Call the method and get the result
		String sResult = Question7(word, n);
		System.out.println("7. " + sResult);
		
	/**
	 * 8. Write a method that takes two Strings, firstName and lastName, and returns
	 * a full name (the full name should be the first and the last name as a String
	 * separated by a space).
	 */
		// Get User input
		System.out.println("Please enter first name:");
		String firstName = scanner.nextLine();
		System.out.println("Please enter last name:");
		String lastName = scanner.nextLine();
		
		// Call the method and get the result
		sResult = Question8(firstName, lastName);
		System.out.println("8. " + sResult);
		
	/**
	 * 9. Write a method that takes an array of int and returns true if the sum of
	 * all the ints in the array is greater than 100.
	 */
		int[] randNums = new int[5];
		
		// Assign random integer from 0-50 to the array.
		for(int i = 0; i < randNums.length; i++) {
			randNums[i] = (int)(Math.random() * 50);
		}	
		System.out.println("9. Sum of nums is greater than 100: " + Question9(randNums));
	/**
	 * 10. Write a method that takes an array of double and returns the average of
	 * all the elements in the array.
	 */
		double[] randDoubles = new double[5];
		
		// Assign random integer from 1.1-50.0 to the array.
		for(int i = 0; i < randDoubles.length; i++) {
			randDoubles[i] = (Math.random() * 50) + 1.1;
		}	
		System.out.println("10. Average of all doubles is: " + Question10(randDoubles));

	/**
	 * 11. Write a method that takes two arrays of double and returns true if the
	 * average of the elements in the first array is greater than the average of the
	 * elements in the second array.
	 */
		double[] randDoubles2 = new double[5];
		
		// Assign random integer from 1.1-50.0 to the array.
		for(int i = 0; i < randDoubles2.length; i++) {
			randDoubles2[i] = (Math.random() * 50) + 1.1;
		}	
		System.out.println("11. Average of first array is larger than second array? " + Question11(randDoubles, randDoubles2));	
	/**
	 * 12. Write a method called willBuyDrink that takes a boolean isHotOutside, and
	 * a double moneyInPocket, and returns true if it is hot outside and if
	 * moneyInPocket is greater than 10.50.
	 */
		boolean isHotOutside = true;
		double moneyInPocket = 11.5;
		System.out.println("12. Will buy a drink? " + willBuyDrink(isHotOutside, moneyInPocket));
		
	/**
	 * 13. Create a method of your own that solves a problem. In comments, write
	 * what the method does and why you created it.
	 */
		int nth = 10;
		System.out.print("13. Fibonacci Sequence for " + nth + "th place: ");
		Fibonacci(nth);
		
	}

}
