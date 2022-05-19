/*
 Name: Justin Tonkinson
 Assignment: Lab 8
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 4/18/2017
 Purpose of Program: The goal of this assignment is to practice Queue operations.
*/

public class Runner {
	public static void main(String[] args) {
		Queue q = new Queue();
		/*
		 * for (int i = 0; i < 5; i++) { int ran = i + 2; q.enqueue(ran); }
		 */
		while (!q.isFull()) {
			q.enqueue((int) (Math.random() * 100.0)); // Fills queue with random numbers
		}
		printQueue(q);

		System.out.println("I am going to dequeue one element.");
		q.dequeue();
		printQueue(q);

		System.out.println("I am going to reverse my queue.");
		reverseQueue(q);
		printQueue(q);

		int num = (int) (Math.random() * 100.0);
		System.out.println("I am going to enqueue " + num);
		q.enqueue(num);
		printQueue(q);

		num = (int) (Math.random() * 100.0);
		System.out.println("I am going to enqueue " + num);
		q.enqueue(num);
		printQueue(q);

		System.out.println("I am going to reverse my queue.");
		reverseQueue(q);
		printQueue(q);
	}

	public static void printQueue(Queue q) {
		Object[] items = new Object[q.size()];
		int i = 0;
		System.out.println("My queue is as follows: ");
		while (!q.isEmpty()) {
			items[i] = q.dequeue(); // Gets items from the front
			System.out.print(items[i]);
			System.out.print(" ");
			i++;
		}
		for (i = 0; i < items.length; i++)
			q.enqueue(items[i]); // Requeues after everything is printed
		System.out.println();
	}

	public static void reverseQueue(Queue q) {
		Object[] items = new Object[q.size()];
		int i = 0;
		while (!q.isEmpty()) {
			items[i] = q.peek();
			q.dequeue();
			i++;
		}
		for (i = items.length - 1; i > -1; i--)
			q.enqueue(items[i]); // Basically does the same as the print method but instead it requeues them backwards reversing the queue
	}
}
