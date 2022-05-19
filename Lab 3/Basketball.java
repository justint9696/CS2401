/*
 Name: Justin Tonkinson
 Assignment: Lab 3
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 2/15/2017
 Purpose of Program: Creates a new class of type Basketball to easier access the dimensions of the ball.
*/

public class Basketball
{
	public static void main(String[] args) {}
	
	double radius;
	
	boolean used = false;
	
	public Basketball() {} // Default Constructor
	
	public Basketball(double r) // Constructor
	{
		radius = r;
	}
	
	public double getDiameter() // Returns the diameter of the ball.
	{
		return radius * 2;
	}
	
	public double getVolume() // Calculates the volume of the basketball.
	{
		double volume = 4/3 * Math.PI * Math.pow(radius, 3);
		return volume;
	}
}