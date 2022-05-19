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

public class Calories1
{
	public static int [][] readFile(String filename) throws FileNotFoundException, IOException // extracts information from text file / creates and returns 2d array
	{
		FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);
		int [][] caloriesArray = new int[7][3];
		String currentLine = "";
		int currentRow = 0;
		while(textReader.ready())
		{
			currentLine = textReader.readLine();
			String [] A = currentLine.split(" ");
			for(int i = 0;i < A.length;i++)
			{
				caloriesArray[currentRow][i] = Integer.parseInt(A[i]);
			}
			currentRow++;
		}
		return caloriesArray;
	}
	
	public static void totalCalories(int [][] caloriesArray) // Calculates the total amount of calories consumed on each day
	{
		String currentDay = "Monday";
		for(int i = 0;i < caloriesArray.length;i++)
		{
			int totalCalories = 0;
			for(int j = 0;j < caloriesArray[i].length;j++)
			{
				totalCalories += caloriesArray[i][j];
			}
			System.out.print("Total Calories Consumned on ");
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
			System.out.println(currentDay + ": " + totalCalories);
		}
	}
	
	public static void avgCaloriesDay(int [][] caloriesArray) // Calculates the average amount of calories consumed for each week
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
	
	public static void avgCaloriesWeek(int [][] caloriesArray) // Calculates the average amount of calories consumed for each meal (brekafast, lunch, and dinner)
	{
		String currentMeal = "Breakfast";
		int index = 0;
		for(int i = 0;i < caloriesArray[index].length;i++)
		{
			index++;
			double avgCalories = 0;
			for(int j = 0;j < caloriesArray.length;j++)
			{
				avgCalories += caloriesArray[j][i];
			}
			avgCalories = Math.round(avgCalories / caloriesArray.length);
			switch(i)
			{
				case 0:
				currentMeal = "Breakfast";
				break;
				case 1:
				currentMeal = "Lunch";
				break;
				case 2:
				currentMeal = "Dinner";
				break;
				default: currentMeal = "Breakfast";
			}
			System.out.printf("Average Calories Consumed Each Week for %s : %.0f\n", currentMeal, avgCalories);
		}
	}
	
	public static void maxCaloriesDaily(int [][] caloriesArray) // Calculates the highest number of documented calories consumed for each day
	{
		String currentDay = "";
		for(int i = 0;i < caloriesArray.length;i++)
		{
			int max = 0;
			for(int j = 0;j < caloriesArray[i].length;j++)
			{
				if(caloriesArray[i][j] > max) max = caloriesArray[i][j];
			}
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
			System.out.println("Maximum Calories Consumed on " + currentDay + ": " + max);
		}
	}
	
	public static void maxCaloriesMeal(int [][] caloriesArray) // Calculates the highest number of documented calories consumed for each meal
	{
		String currentMeal = "Breakfast";
		int index = 0;
		for(int i = 0;i < caloriesArray[index].length;i++)
		{
			index++;
			int max = 0;
			for(int j = 0;j < caloriesArray.length;j++)
			{
				if(caloriesArray[j][i] > max) max = caloriesArray[j][i];
			}
			switch(i)
			{
				case 0:
				currentMeal = "Breakfast";
				break;
				case 1:
				currentMeal = "Lunch";
				break;
				case 2:
				currentMeal = "Dinner";
				break;
				default: currentMeal = "Breakfast";
			}
			System.out.println("Maximum Calories Consumed on " + currentMeal + ": " + max);
		}
	}
	
	public static void main(String [] args) throws FileNotFoundException, IOException // Main method used to create array from method and call every other method from that array
	{
		String filename = "info.txt";
		int [][] caloriesArray = readFile(filename);
		totalCalories(caloriesArray);
		System.out.println("");
		avgCaloriesDay(caloriesArray);
		System.out.println("");
		avgCaloriesWeek(caloriesArray);
		System.out.println("");
		maxCaloriesDaily(caloriesArray);
		System.out.println("");
		maxCaloriesMeal(caloriesArray);
	}
}