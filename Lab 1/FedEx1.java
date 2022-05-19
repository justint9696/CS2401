import java.io.*;
import java.util.Scanner;
import java.util.*;

public class FedEx1
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
	public static void findLarget(double length [], double width [], double height [])
	{
		double largest = 0;
		double volume [] = new double[length.length];
		for(int i = 0;i < volume.length;i++)
		{
			volume[i] = length[i] * width[i] * height[i];
			if(volume[i] > largest) largest = volume[i];
		}
		for(int i = 0;i < volume.length;i++) if(volume[i] == largest) System.out.println("Largest Box Dimensions: " + length[i] + " " + width[i] + " " + height[i]);
	}
	public static void findSmallest(double length [], double width [], double height [])
	{
		double smallest = 0;
		double volume [] = new double[length.length];
		for(int i = 0;i < volume.length;i++)
		{
			volume[i] = length[i] * width[i] * height[i];
			if(smallest == 0) smallest = volume[i];
			if(volume[i] < smallest) smallest = volume[i];
		}
		for(int i = 0;i < volume.length;i++) if(volume[i] == smallest) System.out.println("Smallest Box Dimensions: " + length[i] + " " + width[i] + " " + height[i]);
	}
	public static void calcVolumes(double length [], double width [], double height[])
	{
		double volume [] = new double[length.length];
		for(int i = 0;i < length.length;i++)
		{
			volume[i] = length[i] * width[i] * height[i];
			System.out.println("Volume of Box " + i + ": " + volume[i]);
		}
	}
	public static void totalVolumes(double length [], double width [], double height[])
	{
		double total = 0;
		double volume [] = new double[length.length];
		for(int i = 0;i < length.length;i++)
		{
			volume[i] = length[i] * width[i] * height[i];
			total += volume[i];
		}
		System.out.println("Sum of all Volumes: " + total);
	}
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		String filename = "info.txt";
		int currentColumn = 1;
		int rows = countRows(filename);
		
		double length [] = new double[rows];
		double width [] = new double[rows];
		double height [] = new double[rows];
		
		FileReader fr = new FileReader(filename);
		BufferedReader textReader = new BufferedReader(fr);
		String currentLine = "";
		
		while(textReader.ready())
		{
			currentLine = textReader.readLine();
			String A [] = currentLine.split(" ");
			for(int i = 0;i < rows;i++)
			{
				switch(currentColumn)
				{
					case 1:
					length[i] = Double.parseDouble(A[i]);
					break;
					case 2:
					width[i] = Double.parseDouble(A[i]);
					break;
					case 3:
					height[i] = Double.parseDouble(A[i]);
					break;
				}
			}
			currentColumn++;
		}
		calcVolumes(length,width,height);
		totalVolumes(length,width,height);
		findSmallest(length,width,height);
		findLarget(length,width,height);
	}
}