/*
 Name: Justin Tonkinson
 Assignment: Lab 4
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 3/1/2017
 Purpose of Program: Prompts user what to do with information read from file with various methods.
*/

import java.io.*;
import java.util.*;
import java.io.File;

public class AnalyzePOI
{
	public static void main(String [] args) throws FileNotFoundException, IOException
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the input file: ");
		File filename = new File(input.next());
		
		while (!checkFile(filename)) // Checks to see if the file exists; if not, loops until a valid file name is entered
		{
			System.out.println("Please enter the name of the input file: ");
			filename = new File(input.next());
		}
		
		POI info = createLinkedList(filename); // Creates linked list of POI
		POIList list = new POIList(info); // Sets head of POIList
		
		int option = 0;
		boolean programRunning = true;
		while (programRunning) // Runs program until it is ended.
		{
			printControls();
			option = input.nextInt();
			switch(option)
			{
				case 0: programRunning = false;
				break;
				case 1: list.printList();
				break;
				case 2: searchPerson(list);
				break;
				case 3: insertPerson(list);
				break;
				case 4: swapPeople(list);
				break;
				case 5: promptRemoveID(list);
				break;
				case 6: promptRemoveThreat(list);
				break;
				case 7: list.writeToFile();
				break;
			}
		}
		input.close();
	}
	
	public static void promptRemoveID(POIList list) // Prompts the user for the ID they wish to remove.
	{
		String ID;
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the ID you want removed: ");
		ID = input.next();
		while (ID.length() != 6) // Loops until a valid ID is entered.
		{
			System.out.println("ID level can only be between 0 & 5");
			ID = input.next();
		}
		list.removeID(Long.parseLong(ID));
		input.close();
	}
	
	public static void promptRemoveThreat(POIList list) // Prompts the user for threat levels they wish to remove.
	{
		int threat = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the threat levels you want removed: ");
		threat = input.nextInt();
		while (threat < 0 && threat > 5) // Loops until valid threat level is entered.
		{
			System.out.println("Threat level can only be between 0 & 5");
			threat = input.nextInt();
		}
		list.removeThreat(threat);
		input.close();
	}
	
	public static void swapPeople(POIList list) // Prompts the user what indicies they wish to switch.
	{
		int index1, index2;
		System.out.println("Note: Index starts at 0.");
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter index 1: ");
		index1 = input.nextInt();
		System.out.println("Please enter index 2: ");
		index2 = input.nextInt();
		if (index1 < index2) list.swapIndicies(index1, index2); // Makes sure index1 in the method is always the smallest and index2 is the largest
		else list.swapIndicies(index2, index1);
		input.close();
	}
	
	public static void searchPerson(POIList list) // Prompts user to search a person by either ID or name.
	{
		System.out.println("Search for a person by using their id or their name: ");
		Scanner input = new Scanner(System.in);
		String option = input.nextLine();
		long ID = 0;
		try // If option is a number, search by ID...
		{
			ID = Long.parseLong(option);
			if (option.length() != 6) System.out.println("Please enter a valid ID.");
			else list.findID(ID);
		}
		catch (NumberFormatException e) // ... otherwise, search by name.
		{
			list.findName(option);
		}
		input.close();
	}
	
	public static void insertPerson(POIList list) // Prompts the user where to insert the id, and information about the person they wish to insert in the linked list.
	{
		POI person = new POI();
		int currentInfoIndex = 0;
		int index = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Please type in the person's info: ");
		System.out.println("What is the person's name: ");
		while (currentInfoIndex != 4)
		{
			String option = input.nextLine();
			if (currentInfoIndex == 0)
			{
				person.setName(option);
				currentInfoIndex++;
				System.out.println("What is the person's ID: ");
			}
			else if (currentInfoIndex == 1)
			{
				long ID = 0;
				if (option.length() != 6) System.out.println("ID must be 6 characters. ");
				else try
				{
					ID = Long.parseLong(option);
					person.setID(ID);
					currentInfoIndex++;
					System.out.println("What is the person's threat level: ");
				}
				catch (NumberFormatException e)
				{
					System.out.println("Please enter a valid number.");
				}
			}
			else if (currentInfoIndex == 2)
			{
				int threat = 0;
				try
				{
					threat = Integer.parseInt(option);
					if (threat < 0||threat > 5) System.out.println("Please enter a valid number from 0 - 5.");
					else
					{
						person.setThreat(threat);
						currentInfoIndex++;
						System.out.println("What index would you like the person at? (Starting at index 0)");
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Please enter a valid number from 0 - 5.");
				}
			}
			else
			{
				try
				{
					index = Integer.parseInt(option);
					currentInfoIndex++;
				}
				catch (NumberFormatException e)
				{
					System.out.println("Please enter a valid number.");
				}
			}
		}
		list.insertAtIndex(person, index);
		input.close();
	}
	
	public static void printControls() // These are the controls to the main method.
	{
		System.out.println("Choose an option: \n1 - Print out linked list \n2 - Search for a person by ID or name \n3 - Insert a person at a certain index \n4 - Swap two people by their index \n5 - Remove a record containing a specific ID \n6 - Remove all records with a certain threat level \n7 - Write the content of the linked list in an output file \n0 - End program");
	}
	
	public static POI createLinkedList(File filename) throws FileNotFoundException, IOException // This is the method that creates the linked list from the input file.
	{
		FileReader fr = new FileReader(filename);
		BufferedReader textReader = new BufferedReader(fr);
		String currentLine = "";
		long ID = 0;
		String name = "";
		int threat = 0;
		int lineOfInfo = 0;
		POI list = null;
		POI info = null;
		POI head = null;
		while (textReader.ready())
		{
			lineOfInfo++;
			currentLine = textReader.readLine();
			switch (lineOfInfo)
			{
				case 1: try
				{
					ID = Long.parseLong(currentLine);
				}
				catch (NumberFormatException e)
				{
					System.out.println(currentLine + " is not a long."); // Checks if ID is a valid long.
					threat = 0;
				}
				if (currentLine.length() != 6) System.out.println(currentLine + " is not a valid length for an ID. \nID length must be 6 characters."); // Checks if ID is the right number of characters; 6
				break;
				case 2: name = currentLine;
				break;
				case 3: try
				{
					threat = Integer.parseInt(currentLine); // Checks if threat is a valid integer
				}
				catch (NumberFormatException e)
				{
					System.out.println(currentLine + " is not an integer.");
					threat = 0;
				}
				if (threat > 5 || threat < 0) System.out.println(currentLine + " is not a valid threat level. \nThreat level must be between 0 & 5."); // Threat is invalid if it is not between 0 & 5
				break;
			}
			if (lineOfInfo == 3)
			{
				lineOfInfo = 0;
				if (head != null)
				{
					list = info;
					info = new POI(ID, name, threat);
					list.next = info;
				}
				else
				{
					info = new POI(ID, name, threat);
					head = info;
					list = info;
				}
			}
		}
		return head;
	}
	
	public static boolean checkFile(File filename) throws FileNotFoundException, IOException // Checks if file exists, if not, user is remprompted for filename
	{
		if (filename.isFile()) return true;
		else System.out.println("File does not exist; please check filename");
		System.out.println();
		return false;
	}
}