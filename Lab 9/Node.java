/*
 Name: Justin Tonkinson
 Assignment: Lab 9
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 4/18/2017
 Purpose of Program: A mathematician hires you to write a program that can compute some expressions written in postfix form.
*/

// This class sets up the linked list 

public class Node {
	Object info;
	Node next;

	Node(Object info, Node next) {
		this.info = info;
		this.next = next;
	}
}