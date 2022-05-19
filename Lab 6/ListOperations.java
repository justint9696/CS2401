/*
 Name: Justin Tonkinson
 Assignment: Lab 6
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 3/26/2017
 Purpose of Program: To get more familiar and better understand how to write recursive methods.
*/

public class ListOperations {
	public static void main(String[] args) {
		MyListOfInts t = null;
		for (int i = 0; i < 5; i++) {
			// int ran = (int) (100.0* Math.random());
			int ran = i + 2;
			t = new MyListOfInts(ran, t);
		}
		System.out.println("All numbers in the list:");
		printMyList(t);
		System.out.println();

		System.out.println("Sum=" + sumOfMyList(t));
		System.out.println("Max=" + maxOfMyList(t));
		System.out.println("Length=" + lengthOfMyList(t));

		t = reverseMyList(t);
		System.out.println("All numbers in the reversed list:");
		printMyList(t);
		System.out.println();

		System.out.println("Remove 3");
		MyListOfInts tt = removeANumberFromMyList(t, 3);
		System.out.println("All numbers in the new list:");
		printMyList(tt);
		System.out.println();

		System.out.println("All numbers in the previous list:");
		printMyList(t);
		System.out.println();
		System.out.println("Insert a number in a position of the new list:");
		tt = insertANumberIntoMyList(tt, 20, 1);
		printMyList(tt);
		System.out.println();

		System.out.println("Values obtained for the special recursive method:");
		reportOnValues(tt);
	}

	/*
	 * Write a recursive method to print all the numbers separated by spaces.
	 * This method cannot contain any loop (that is, uses of for, while, do
	 * while are prohibited).
	 */
	public static void printMyList(MyListOfInts m) {
		MyListOfInts nextInt = m.restOfTheInts; // Variable of type MyListOfInts to hold the next value in the linked list.
		System.out.print(m.firstInt + " ");
		if (nextInt != null && m != null) { // Base case to make sure the next and current iteration are not equal to null. If so, then the linked list must be done.
			printMyList(nextInt); // Continue printing out the nextInt in the linked list.
		} else
			return;
	}

	/*
	 * Write a recursive method to retrieve the sum of all the numbers in a
	 * list. This method cannot contain any loop (that is, uses of for, while,
	 * do while are prohibited).
	 */
	public static int sumOfMyList(MyListOfInts m) {
		int sum = 0; // Variable of type int to hold the sum of the linked list.
		sum += m.firstInt; // Adds the current iteration to the sum.
		MyListOfInts nextInt = m.restOfTheInts;
		if (nextInt != null && m != null) {
			return sum + sumOfMyList(nextInt); // Continue adding that sum to the next iteration.
		} else
			return sum; // If finished, the above base case adds this sum to the overall sum.
	}

	/*
	 * Write a recursive method to retrieve the largest number from the list.
	 * Assume that there is at least one number in the given list when the
	 * method is called from the main function. This method cannot contain any
	 * loop (that is, uses of for, while, do while are prohibited).
	 */
	public static int maxOfMyList(MyListOfInts m) {
		int max = m.firstInt; // Variable to hold the current to have something to compare it to the next iteration.
		MyListOfInts nextInt = m.restOfTheInts;
		if (nextInt != null && m != null) {
			if (max < maxOfMyList(nextInt)) { // This case makes it possible to compare every iteration to the current max.
				max = nextInt.firstInt;
				return maxOfMyList(nextInt);
			} else
				return max;
		} else // Once it has finished searching the linked list, it returns the sum and the base case compares those numbers to see which is the max.
			return max;
	}

	/*
	 * Write a recursive method to compute the length of a list. This method
	 * cannot contain any loop (that is, uses of for, while, do while are
	 * prohibited).
	 */
	public static int lengthOfMyList(MyListOfInts m) {
		int length = 1;
		MyListOfInts nextInt = m.restOfTheInts;
		if (nextInt != null && m != null) {
			return length + lengthOfMyList(nextInt); // Basically just adding one every time the next iteration is not equal to null.
		} else
			return 1;
	}

	/*
	 * Write a recursive method named reverseMyList that will reverse a given
	 * linked list m. Return the head of the reversed linked list. It is fine if
	 * you need to modify the given linked list to obtain the reversed one. The
	 * method reverseMyList may not contain any loop.
	 */
	public static MyListOfInts reverseMyList(MyListOfInts m) {
		MyListOfInts nextInt = m.restOfTheInts;
		if (nextInt != null && m != null) {
			m.restOfTheInts = null; // Sets this so the linked list is gradually being reset.

			MyListOfInts reverse = reverseMyList(nextInt); // Recursive to continue reversing the linked list.
			nextInt.restOfTheInts = m; // Sets the next int to the current int at m.

			return reverse;
		} else
			return m;
	}

	/*
	 * Write a recursive method to remove the first occurrence of a specific
	 * number from a list. As an example, if your list initially contains 20 5
	 * 10 3 5 1 and your removee is 5, the returned list should contain 20 10 3
	 * 5 1 after the removal. Return a new head. You must make sure that the
	 * parameter list remains intact after returning the new list to the main
	 * method. This method cannot contain any loop.
	 */
	public static MyListOfInts removeANumberFromMyList(MyListOfInts m, int removee) {
		MyListOfInts nextInt = m.restOfTheInts;
		MyListOfInts remove = new MyListOfInts(m.firstInt); // Creates a new linked list so no changes are made to the original.
		if (nextInt != null && m != null) {
			if (m.firstInt == removee) { // If the chosen number to remove is equal to the first index...
				remove = new MyListOfInts(nextInt.firstInt); 
				remove.restOfTheInts = nextInt.restOfTheInts;  // Removes the number
				return remove; // Returns the new linked list with no changes to the current one
			}
			else if (nextInt.firstInt == removee) { // If chosen number is not the first index..
				remove.restOfTheInts = nextInt.restOfTheInts; // Removes chosen number
				return remove;
			}
			else {
				remove.restOfTheInts = removeANumberFromMyList(nextInt, removee); // If does not exist at current index, return the normal linked list 
				return remove;
			}
		}
		else return m;
	}
	
	/*
	 * Write a recursive method to insert a number (insertee) into a specific
	 * position of a list. Positions start from 0 (that is, the position of the
	 * head of a list is 0). This method cannot contain any loop (that is, uses
	 * of for, while, do-while are prohibited).
	 */
	public static MyListOfInts insertANumberIntoMyList(MyListOfInts m, int insertee, int position) {
		MyListOfInts nextInt = m.restOfTheInts;
		if (position < 0) { // If the given index is less than 0, then throw out of bounds
			 throw new IndexOutOfBoundsException();
		}
		else if(position == 0) { // If position is 0, then this is the index where the number is to be placed
				MyListOfInts insert = new MyListOfInts(insertee, m); // Creates a new MyListOfInts to insert
				return insert;
		}
		else {
			m.restOfTheInts = insertANumberIntoMyList(nextInt, insertee, position - 1); // Recursively call until index is less than or equal to 0
			return m; // Return the new linked list
		}
	}
	
	/*
	 * Write a recursive method named reportOnValues that will use each value in
	 * a list to compute a recursive formula implemented in the method
	 * specialRecursiveFunction. This method cannot contain any loop.
	 */

	public static void reportOnValues(MyListOfInts m) {
		MyListOfInts nextInt = m.restOfTheInts;
		if (m != null) // Calls the specialRecursiveMethod 
			System.out.println(specialRecursiveFunction(m.firstInt));
		
		if (nextInt != null) // Base case
			reportOnValues(nextInt); // Recursion
	}

	/* This method cannot contain any loop */
	public static double specialRecursiveFunction(int x) {
		if (x == 0) // If variable is equal to 0...
			return 1;
		else if (x % 2 == 0) // If variable is even...
			return 1 + specialRecursiveFunction(x / 2);
		else // else the variable must be odd, so...
			return 1 + specialRecursiveFunction(x - 1);
	}
}