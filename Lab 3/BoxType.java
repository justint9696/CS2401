/*
 Name: Justin Tonkinson
 Assignment: Lab 3
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 2/15/2017
 Purpose of Program: Creates a new class of type BoxType to easily access the dimensions of the box.
*/

public class BoxType
{
	public static void main(String[] args) {}
	
	double length;
	double width;
	double height;
	
	boolean used = false;
	
	public BoxType() {} // Default Constructor
	
	public BoxType(double l, double w, double h) // Constructor
	{
		length = l;
		width = w;
		height = h;
	}
	
	public double getVolume() // Calculates the volume of the given box.
	{
		double volume;
		volume = length * width * height;
		return volume;
	}
	
	public boolean fitsInBoxType(Basketball b) // Determines if the ball fits in the box based on the diameter of the ball and the dimensions of the box.
	{
		BoxType Box = this;
		double length = Box.length;
		double width = Box.width;
		return (b.getDiameter() <= length && b.getDiameter() <= width);
	}
	
	public double emptySpace(Basketball b) // Calculates the space left after ball is placed inside of the box.
	{
		BoxType Box = this;
		double volume = Box.getVolume();
		if(fitsInBoxType(b))
		{
			volume -= b.getVolume();
		}
		return volume;
	}
}