/*
	Name: Justin Tonkinson
	Assignment: Lab 2
	Instructor: Dr. Luc Longpre
	TA: Anthony Ortiz
	Last Modification: 2/7/2017
	Purpose of Program: Help a nutritionist keep track of how many calories they consume.
*/

import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Calories2
{
	public static int countRows(String filename) throws FileNotFoundException, IOException // counts rows of text file for the array size
	{
		int numRows = 0;
		String currentLine = "";
		FileReader fr = new FileReader(filename);
		BufferedReader textReader = new BufferedReader(fr);
		if(textReader.ready()) currentLine = textReader.readLine();
		String A [] = currentLine.split(" ");
		for (int i = 0;i < A.length;i++) numRows++;		
		textReader.close();
		return numRows;
	}
	public static int countColumns(String filename) throws FileNotFoundException, IOException // counts columns of text file for the array size
	{
		int numLines = 0;
		FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);
		while (textReader.readLine() != null) numLines++;
		textReader.close();
		return numLines;
	}
	public static int [][] readFile(String filename) throws FileNotFoundException, IOException // extracts information from text file / creates and returns 2d array
	{
		FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);
		int [][] caloriesArray = new int[7][];
		String currentLine = "";
		int currentRow = 0;
		while(textReader.ready())
		{
			currentLine = textReader.readLine();
			String [] A = currentLine.split(" ");
			caloriesArray[currentRow] = new int[A.length];
			for(int i = 0;i < A.length;i++)
			{
				caloriesArray[currentRow][i] = Integer.parseInt(A[i]);
			}
			currentRow++;
		}
		return caloriesArray;
	}
	
	public static void avgCaloriesDay(int [][] caloriesArray) // Calculates the average calories consumed for each day
	{
		String currentDay = "Monday";
		for(int i = 0;i < caloriesArray.length;i++)
		{
			double avgCalories = 0;
			for(int j = 0;j < caloriesArray[i].length;j++)
			{
				avgCalories += caloriesArray[i][j];
			}
			avgCalories = Math.round(avgCalories / caloriesArray[i].length);
			switch(i)
			{
				case 0:
				currentDay = "Monday";
				break;
				case 1:
				currentDay = "Tuesday";
				break;
				case 2:
				currentDay = "Wednesday";
				break;
				case 3:
				currentDay = "Thursday";
				break;
				case 4:
				currentDay = "Friday";
				break;
				case 5: 
				currentDay = "Saturday";
				break;
				case 6:
				currentDay = "Sunday";
				break;
				default: currentDay = "Monday"; break;
			}
			System.out.printf("Average Calories Consumed on %s : %.0f\n", currentDay, avgCalories);
		}
	}
	
	public static int largestRow(int [][] caloriesArray) // Calculates the largest row in the document / text file for the loops
	{
		int max = 0;
		for(int i = 0;i < caloriesArray.length;i++)
		{
			if(caloriesArray[i].length > max) max = caloriesArray[i].length;
		}
		return max;
	}
	
	public static void avgCaloriesWeek(int [][] caloriesArray) // Calculates the average amount of calories consumed for each documented meal
	{
		int largestRow = largestRow(caloriesArray);
		int index = 0;
		for(int i = 0;i < largestRow;i++)
		{
			int length = 0;
			double avgCalories = 0;
			for(int j = 0;j < caloriesArray.length;j++)
			{
				if(caloriesArray[j].length < (i + 1)) {avgCalories += 0;length++;}
				else avgCalories += caloriesArray[j][i];
			}
			avgCalories = Math.round(avgCalories / (caloriesArray.length - length));
			System.out.printf("Average Calories Consumed Each Week for Meal %d : %.0f\n", i + 1, avgCalories);
			index++;
		}
	}
	
	public static void main(String [] args) throws FileNotFoundException, IOException // Main method used to create array from method and call every other method from that array
	{
		String filename = "info.txt";
		int [][] caloriesArray = readFile(filename);
		avgCaloriesDay(caloriesArray);
		System.out.println("");
		avgCaloriesWeek(caloriesArray);
	}
}