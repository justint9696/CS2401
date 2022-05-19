public class MyListOfInts 
{
	public int firstInt;
	public MyListOfInts restOfTheInts;
	
	public MyListOfInts(int f)
	{
		firstInt=f;
	}
	
	public MyListOfInts(int f, MyListOfInts r)
	{
		firstInt=f;
		restOfTheInts=r;
	}
}