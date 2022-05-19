/*
 Name: Justin Tonkinson
 Assignment: Lab 9
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 4/18/2017
 Purpose of Program: A mathematician hires you to write a program that can compute some expressions written in postfix form.
*/

// This class handles everything dealing with type GenericStack along with setting up a linked list of type Node

public class GenericStack {
	private Node top;

	public void createStack() {
		top = null;
	}

	public boolean isEmpty() {
		return (top == null);
	}

	public void push(Object newItem) {
		top = new Node(newItem, top);
	}

	public Object pop() {
		if (isEmpty()) {
			System.out.println("Trying to pop when stack is empty");
			return null;
		} else {
			Node temp = top;
			top = top.next;
			return temp.info;
		}
	}

	public void popAll() {
		top = null;
	}

	public Object peek() {
		if (isEmpty()) {
			System.out.println("Trying to peek when stack is empty");
			return null;
		} else {
			return top.info;
		}
	}
}
