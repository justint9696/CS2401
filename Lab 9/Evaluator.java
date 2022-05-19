import java.util.Scanner;
import java.io.*;

/*
Name: Justin Tonkinson
Assignment: Lab 9
Instructor: Dr. Luc Longpre
TA: Anthony Ortiz
Last Modification: 4/18/2017
Purpose of Program: A mathematician hires you to write a program that can compute some expressions written in postfix form.
 */

public class Evaluator {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the input file: ");
		File filename = new File(input.next()); // Prompts to enter filename

		while (!checkFile(filename)) { // If file does not exist, the user will
										// be prompted the re-enter the filename
										// until a valid file is entered.
			System.out.println("File does not exist; please check filename");
			System.out.println("Please enter the name of the input file: ");
			filename = new File(input.next());
		}

		evaulatePostfix(filename); // Evaluates postfix expressions

		input.close();
	}

	public static void evaulatePostfix(File filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader textReader = new BufferedReader(fr);
		String currentLine = "";

		GenericStack s = new GenericStack();
		s.createStack();

		while (textReader.ready()) {
			currentLine = textReader.readLine();
			String[] content = currentLine.split(" "); // Creates an array from
														// seperating string
														// with " "
			for (String token : content) {
				if (isOperator(token)) { // Checks if current toke is an
											// operator; + - / *
					if (!s.isEmpty()) {
						int obj1 = (int) s.pop(), obj2 = (int) s.pop(), result = 0;
						switch (token) {
						case "+":
							result = obj1 + obj2;
							break;
						case "-":
							result = obj1 - obj2;
							break;
						case "*":
							result = obj1 * obj2;
							break;
						case "/":
							result = obj1 / obj2;
							break;
						}
						s.push(result);
					} else // Else statck is empty and cannot be evaluated
						System.out.println("Cannot evaluate postfix expression.");
				} else {
					s.push(Integer.parseInt(token));
				}
			}
		}
		textReader.close();
		int result = (int) s.pop();
		if (s.isEmpty()) // If the stack is not empty at this point, the
							// expression cannot be evaluated because it is
							// incomplete
			System.out.println("Postfix Value: " + result);
		else
			System.out.println("Cannot evaluate postfix expression; \nExpression is incomplete.");
	}

	public static boolean isOperator(String s) {
		switch (s) {
		case "+":
		case "-":
		case "*":
		case "/":
			return true;
		default:
			return false;
		}
	}

	public static boolean checkFile(File filename) throws FileNotFoundException, IOException {
		return (filename.isFile());
	}
}
