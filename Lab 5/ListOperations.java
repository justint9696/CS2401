/*
 Name: Justin Tonkinson
 Assignment: Lab 5
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 3/7/2017
 Purpose of Program: To get more familiar and better understand how to write recursive methods.
*/

public class ListOperations {
	public static void main(String[] args) {
		MyListOfInts t = null;
		for (int i = 0; i < 5; i++) {
			int ran = (int) (100.0* Math.random());
			// int ran = i + 2;
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
}