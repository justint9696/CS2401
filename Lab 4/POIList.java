/*
 Name: Justin Tonkinson
 Assignment: Lab 4
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 3/1/2017
 Purpose of Program: Creates a class of type POIList that creates a linked list of type POI. Contains various methods on how to edit the linked list.
*/

import java.io.*;
import java.util.*;

public class POIList
{
 public static void main(String [] args) {}
 
 POI head;
 
 public POIList() {}
  
 public POIList(POI head1) // Constructor
 {
  head = head1;
 }
 
 public void setHead(POI head1)
 {
  head = head1;
 }
  
 public void printList() // Print out the entire linked list and it's parameters; name, id, & threat
 {
  POIList list = this;
  POI header = list.head;
  while (header != null)
  {
   System.out.printf("Name: %s \nID: %d \nThreat Level: %d\n", header.getName(), header.getID(), header.getThreat());
   System.out.println();
   header = header.next;
  }
 }
 
 public void insertAtIndex(POI person, int index)
 {
  int currentIndex = 0;
  boolean foundID = false;
  POIList header = this;
  POI [] newList = new POI[header.getSize()];
  POI head = header.head;
  
  if(index > newList.length)
  {
   System.out.println("Index was out of bounds. Please check and try again.");
   return;
  }
  
  while (head != null)
  {
   newList[currentIndex] = head;
   head = head.next;
   currentIndex++;
  }
  
  POI info = null;
  POI list = null;
  
  for(currentIndex = 0;currentIndex < newList.length;currentIndex++)
  {
   if(currentIndex == index)
   {
    if (head != null)
    {
     list = info;
     info = new POI(person.getID(),person.getName(),person.getThreat());
     list.next = info;
    }
    else
    {
     info = new POI(person.getID(),person.getName(),person.getThreat());
     head = info;
     list = info;
    }
   }
   if (head != null)
   {
    list = info;
    info = new POI(newList[currentIndex].getID(),newList[currentIndex].getName(),newList[currentIndex].getThreat());
    list.next = info;
   }
   else
   {
    info = new POI(newList[currentIndex].getID(),newList[currentIndex].getName(),newList[currentIndex].getThreat());
    head = info;
    list = info;
   }
  }
  if(index == newList.length) list.next.next = person;
  header.setHead(head); // Updates linked list to new linked list!
 }
 
 public void swapIndicies(int index1, int index2) // Switches the two user inputted indicies
 {
  POIList header = this;
  POI head = header.head;
  POI [] newList = new POI[header.getSize()];
  
  int index = 0;
  
  while (head != null)
  {
   newList[index] = head;
   head = head.next;
   index++;
  }
  
  if (index1 < 0||index2 > index)
  {
   System.out.println("Index was out of bounds. Please check and try again."); // Index out of bounds..
   return;
  }
  
  POI info = null;
  POI list = null;
  POI temp;
  
  if (index2 == newList.length) index2 -= 1; // I'm not sure why i had to do this, but it fixes the problem that occured when index2 is the size of the array.
  
  temp = newList[index1];
  newList[index1] = newList[index2];
  newList[index2] = temp;
  
  for(index = 0;index < newList.length;index++)
  {
   if (head != null)
   {
    list = info;
    info = new POI(newList[index].getID(),newList[index].getName(),newList[index].getThreat());
    list.next = info;
   }
   else
   {
    info = new POI(newList[index].getID(),newList[index].getName(),newList[index].getThreat());
    head = info;
    list = info;
   }
  }
  header.setHead(head); // Updates linked list to new linked list!
 }
  
 
 public int getSize()
 {
  int size = 0;
  POIList header = this;
  POI head = header.head;
  while (head != null)
  {
   head = head.next;
   size++;
  }
  return size;
 }
 
 public void findID(long ID1) // Find person by ID
 {
  POIList list = this;
  POI header = list.head;
  boolean foundID = false;
  while (header != null && !foundID)
  {
   if (header.getID() == ID1)
   {
    System.out.printf("Name: %s \nID: %d \nThreat Level: %d\n", header.getName(), header.getID(), header.getThreat());
    System.out.println();
    foundID = true;
   }
   header = header.next;
  }
  if (!foundID)
   System.out.println("Could not find " + ID1 + " in the list of IDs. \nPlease check the ID and try again.");
 }
 
 public void findName(String name1) // Find person by name
 {
  POIList list = this;
  POI header = list.head;
  boolean foundName = false;
  while (header != null && !foundName)
  {
   if (header.getName().toLowerCase().contains(name1.toLowerCase()))
   {
    System.out.printf("Name: %s \nID: %d \nThreat Level: %d\n", header.getName(), header.getID(), header.getThreat());
    System.out.println();
    foundName = true;
   }
   header = header.next;
  }
  if (!foundName)
   System.out.println("Could not find " + name1 + " in the list of names. \nPlease check the name and try again.");
 }
 
 public void removeThreat(int threat) // Removes every person that has the threat level of 'threat' variable.
 {
  int index = 0;
  POIList header = this;
  POI [] newList = new POI[header.getSize()];
  POI head = header.head;
  while (head != null)
  {
   newList[index] = head;
   head = head.next;
   index++;
  }
  
  POI info = null;
  POI list = null;
  
  for(index = 0;index < newList.length;index++)
  {
   if (newList[index].getThreat() != threat)
   {
    if (head != null)
    {
     list = info;
     info = new POI(newList[index].getID(),newList[index].getName(),newList[index].getThreat());
     list.next = info;
    }
    else
    {
     info = new POI(newList[index].getID(),newList[index].getName(),newList[index].getThreat());
     head = info;
     list = info;
    }
   }
  }
  header.setHead(head); // resets linked list.. again.
 }
 
 public void removeID(long ID) // Removes person from linked list by the user inputted ID.
 {
  int index = 0;
  boolean foundID = false;
  POIList header = this;
  POI [] newList = new POI[header.getSize()];
  POI head = header.head;
  while (head != null)
  {
   newList[index] = head;
   head = head.next;
   index++;
  }
  
  POI info = null;
  POI list = null;
  
  for(index = 0;index < newList.length;index++)
  {
   if (newList[index].getID() != ID)
   {
    if (head != null)
    {
     list = info;
     info = new POI(newList[index].getID(),newList[index].getName(),newList[index].getThreat());
     list.next = info;
    }
    else
    {
     info = new POI(newList[index].getID(),newList[index].getName(),newList[index].getThreat());
     head = info;
     list = info;
    }
   }
   else foundID = true;
  }
  if(!foundID)
   System.out.println("Could not find the inputted ID. \nNo changes were made."); // ID was not found in linked list.
  header.setHead(head);
 }
 
 public void writeToFile() throws FileNotFoundException, IOException // Writes infomation to a default named file. (PDF didn't specify whether to prompt the user) 
 {
  PrintWriter writer = new PrintWriter("POIList.txt");
  
  POI head = this.head;
  
  while (head != null)
  {
   writer.println(head.getID());
   writer.println(head.getName());
   writer.println(head.getThreat());
   head = head.next;
  }
  writer.close();
 }
}