/*
 Name: Justin Tonkinson
 Assignment: Lab 4
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 3/1/2017
 Purpose of Program: Creates a class of type POI to access person's infomation(id, name, threat).
*/

public class POI
{
	private long id;
	private String name;
	private int threat;
	
	public POI next;
	  
	public POI() {}
	  
	public POI(long id1, String name1, int threat1) // Constructor
	{
		id = id1;
		name = name1;
		threat = threat1;
	}
	
	// Setters & Getters since the variables are private
	
	public long getID()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getThreat()
	{
		return threat;
	}
	
	public void setID(long id1)
	{
		id = id1;
	}
	
	public void setName(String name1)
	{
		name = name1;
	}
	
	public void setThreat(int threat1)
	{
		threat = threat1;
	}
}