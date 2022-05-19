import java.io.*;
import java.util.Scanner;
import java.util.*;

public class FedEx2
{
	public static int countRows(String filename) throws FileNotFoundException, IOException 
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
	public static int countColumns(String filename) throws FileNotFoundException, IOException 
	{
		int numLines = 0;
		FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);
		while (textReader.readLine() != null) numLines++;
		textReader.close();
		return numLines;
	}
	public static void calcVolumes(double num [][])
	{
		for(int i = 0;i < num.length;i++)
		{
			double volume = num[i][0];
			for(int j = 1;j < num[i].length;j++)
			{
				volume *= num[i][j];
			}
			System.out.println("Volume of Box " + i + ": " + volume);
		}
	}
	public static void totalVolumes(double num [][])
	{
		double total = 0;
		for(int i = 0;i < num.length;i++)
		{
			double volume = num[i][0];
			for(int j = 1;j < num[i].length;j++)
			{
				volume *= num[i][j];
			}
			total += volume;
		}
		System.out.println("Sum of all Volumes: " + total);
	}
	public static void findSmallest(double num [][])
	{
		double smallest = 0;
		double volume [] = new double[num.length];
		for(int i = 0;i < num.length;i++)
		{
			volume[i] = num[i][0];
			for(int j = 1;j < num[i].length;j++)
			{
				volume[i] *= num[i][j];
			}
			if(smallest == 0) smallest = volume[i];
			if(smallest > volume[i]) smallest = volume[i];
		}
		for(int i = 0;i < volume.length;i++) if(volume[i] == smallest) 
		{
			System.out.print("Smallest Box Dimensions: ");
			for(int j = 0;j < num[i].length;j++) System.out.print(num[i][j] + " ");
		}
	}
	public static void findLarget(double num [][])
	{
		double largest = 0;
		double volume [] = new double[num.length];
		for(int i = 0;i < num.length;i++)
		{
			volume[i] = num[i][0];
			for(int j = 1;j < num[i].length;j++)
			{
				volume[i] *= num[i][j];
			}
			if(largest == 0) largest = volume[i];
			if(volume[i] > largest) largest = volume[i];
		}
		for(int i = 0;i < volume.length;i++) if(volume[i] == largest) 
		{
			System.out.print("\nLargest Box Dimensions: ");
			for(int j = 0;j < num[i].length;j++) System.out.print(num[i][j] + " ");
		}
	}
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		String filename = "info.txt";
		int currentColumn = 0;
		int rows = countRows(filename);
		int columns = countColumns(filename);
		
		double num [][] = new double[rows][columns];
		
		FileReader fr = new FileReader(filename);
		BufferedReader textReader = new BufferedReader(fr);
		String currentLine = "";
		
		while(textReader.ready())
		{
			currentLine = textReader.readLine();
			String A [] = currentLine.split(" ");
			for(int i = 0;i < rows;i++)
			{
				num[i][currentColumn] = Double.parseDouble(A[i]);
			}
			currentColumn++;
		}
		calcVolumes(num);
		totalVolumes(num);
		findSmallest(num);
		findLarget(num);
	}
}