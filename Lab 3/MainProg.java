/*
 Name: Justin Tonkinson
 Assignment: Lab 3
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 2/15/2017
 Purpose of Program: Help FedEx prioritize the shipping of basketballs in boxes.
*/

import java.io.*;

public class MainProg
{
	public static int countDimensions(String filename) throws FileNotFoundException, IOException // Counts number of boxes in text file based on dimensions.
	{
		int numLines = 0;
		String currentLine = "";
		FileReader fr = new FileReader(filename);
		BufferedReader textReader = new BufferedReader(fr);
		while(textReader.ready())
		{
			currentLine = textReader.readLine();
			String [] A = currentLine.split(" ");
			if(A.length == 3) numLines++;
		}
		textReader.close();
		return numLines;
	}
	
	public static BoxType [] getDimensions(String filename) throws FileNotFoundException, IOException // Reads and creates array of type BoxType from text file based on length of current line after splitting it.
	{
		double length, width, height;
		FileReader fr = new FileReader(filename);
		BufferedReader textReader = new BufferedReader(fr);
		int numLines = 0;
		String currentLine = "";
		BoxType [] Box = new BoxType[countDimensions(filename)];
		while(textReader.ready())
		{
			currentLine = textReader.readLine();
			String [] A = currentLine.split(" ");
			if(A.length == 3)
			{
				length = Double.parseDouble(A[0]);
				width = Double.parseDouble(A[1]);
				height = Double.parseDouble(A[2]);
				Box[numLines] = new BoxType(length,width,height);
			}
			numLines++;
		}
		textReader.close();
		return Box;
	}
	
	public static int countBasketballs(String filename) throws FileNotFoundException, IOException // Counts number of basketballs in text file based on length of current line after splitting it.
	{
		int numLines = 0;
		String currentLine = "";
		FileReader fr = new FileReader(filename);
		BufferedReader textReader = new BufferedReader(fr);
		while(textReader.ready())
		{
			currentLine = textReader.readLine();
			String [] A = currentLine.split(" ");
			if(A.length == 1) numLines++;
		}
		textReader.close();
		return numLines;
	}
	
	public static Basketball [] getBasketballs(String filename) throws FileNotFoundException, IOException // Reads and creates array of type Basketball from text file.
	{
		FileReader fr = new FileReader(filename);
		BufferedReader textReader = new BufferedReader(fr);
		int numLines = 0;
		String currentLine = "";
		Basketball [] Ball = new Basketball[countBasketballs(filename)];
		while(textReader.ready())
		{
			currentLine = textReader.readLine();
			String [] A = currentLine.split(" ");
			if(A.length == 1)
			{
				Ball[numLines] = new Basketball(Double.parseDouble(A[0]));
				numLines++;
			}
		}
		textReader.close();
		return Ball;
	}
	
	public static double largeBox(BoxType [] Box) // Finds the largest box in the array of BoxType
	{
		double largest = 0;
		for(int i = 0;i < Box.length;i++)
		{
			if(Box[i].getVolume() > largest) largest = Box[i].getVolume(); 
		}
		return largest;
	}
	
	public static void bestBox(Basketball [] Ball, BoxType [] Box) // Finds the smallest box that a basketball can fit in and prints out information regarding it.
	{
		int i,j;
		for(i = 0;i < Ball.length;i++)
		{
			int bestBoxNum = 0;
			double bestBox = largeBox(Box);
			for(j = 0;j < Box.length;j++)
			{
				if(Box[j].fitsInBoxType(Ball[i]))
				{
					double volume = Box[j].getVolume();
					if(volume <= bestBox)
					{
						bestBox = Box[j].getVolume();
						Ball[i].used = true; // Determine if the ball can fit in any box.
						bestBoxNum = j;
					}
				}
			}
			Box[bestBoxNum].used = true; // Determines if the box can be used at all.
			if(Ball[i].used) // If variable bestBoxNum is not equal to zero, that means the ball fit inside of at least one box.
			{
				System.out.printf("Best Box for Ball %d is Box %d\n", i + 1, bestBoxNum);
				System.out.printf("Volume of Box: %.1f\n", Box[bestBoxNum].getVolume());
				System.out.printf("Volume of Basketball: %.1f\n", Ball[i].getVolume());
				System.out.printf("Space left after placing basketball inside: %.1f\n", Box[bestBoxNum].emptySpace(Ball[i]));
				int numBalls = 0;
				double volumeLeft = Box[bestBoxNum].getVolume();
				while(volumeLeft > Ball[i].getVolume())
				{
					if(volumeLeft > Ball[i].getVolume())
					{
						volumeLeft -= Ball[i].getVolume();
						numBalls++;
					}
				}
				System.out.println("Number of balls that fit inside this box: " + numBalls);
			}
			else // Otherwise it was not used.
			{
				System.out.println("Box not available for this basketball.");
				System.out.printf("Volume of Basketball: %.1f\n", Ball[i].getVolume());
			}
			System.out.println();
		}
	}
	
	public static void ballNotUsed(Basketball [] Ball, BoxType [] Box) // Finds the balls that are not used and prints out their radius.
	{
		int i,j;
		System.out.println("Radius of balls that did not fit in any box: ");
		for(i = 0;i < Ball.length;i++)
		{
			boolean fitsInBox = false;
			for(j = 0;j < Box.length;j++)
			{
				if(Box[j].fitsInBoxType(Ball[i]))
				{
					fitsInBox = true;
				}
			}
			if(!fitsInBox) { System.out.println(Ball[i].radius); } // If the ball does not fit in any box, then print out the radius of the ball.
		}
		System.out.println();
	}
	
	public static void boxNotUsed(Basketball [] Ball, BoxType [] Box) // Finds the boxes that are not used and prints out their volumes.
	{
		int i;
		System.out.println("Dimensions of boxes that were not used: ");
		for(i = 0;i < Box.length;i++) if(!Box[i].used) { System.out.printf("%.2f %.2f %.2f\n", Box[i].length, Box[i].width, Box[i].height); } // If the box was not used for any of the given balls, print out the dimensions of the box.
		System.out.println();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException // Main method used to call all sub methods.
	{
		String filename = "info.txt";
		BoxType [] Box = getDimensions(filename);
		Basketball [] Ball = getBasketballs(filename);
		bestBox(Ball,Box);
		ballNotUsed(Ball,Box);
		boxNotUsed(Ball,Box);
	}
}