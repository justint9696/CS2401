/*
 Name: Justin Tonkinson
 Assignment: Lab 8
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 4/18/2017
 Purpose of Program: The goal of this assignment is to practice Queue operations.
*/

public class Queue {
	private int QUEUE_SIZE = 5;
	private Object[] items;
	private int front, back, count;

	public Queue() {
		items = new Object[QUEUE_SIZE];
		front = 0;
		back = QUEUE_SIZE - 1;
		count = 0;
	}

	public boolean isEmpty() {
		return (count == 0);
	}

	public boolean isFull() {
		return (count == QUEUE_SIZE);
	}

	public void enqueue(Object newItem) {
		if (!isFull()) {
			back = (back + 1) % QUEUE_SIZE;
			items[back] = newItem;
			count++;
			return;
		} else {
			QUEUE_SIZE *= 2; // Doubles the queue size
			System.out.println("Queue is full. Doubling the size.");
			System.out.println("New max. size is: " + QUEUE_SIZE);
			System.out.println("Entered the new item.");
			Object[] temp = items.clone(); // Clones items array
			items = new Object[QUEUE_SIZE];
			dequeueAll(); // Dequeues everything
			for (int i = 0; i < temp.length; i++)
				enqueue(temp[i]); // Requeues everything so items array is filled
			back = (back + 1) % QUEUE_SIZE;
			items[back] = newItem;
			count++;
			return;
		}
	}

	public Object dequeue() {
		if (!isEmpty()) {
			Object queueFront = items[front];
			front = (front + 1) % QUEUE_SIZE;
			count--;
			return queueFront;
		} else
			System.out.println("Trying to dequeue from empty queue");
		return null;
	}

	public void dequeueAll() {
		items = new Object[QUEUE_SIZE];
		front = 0;
		back = QUEUE_SIZE - 1;
		count = 0;
	}

	public Object peek() {
		if (!isEmpty()) {
			return items[front];
		} else
			System.out.println("Trying to peek with empty queue");
		return null;
	}

	public int size() {
		return count;
	}

}