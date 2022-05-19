/*
 Name: Justin Tonkinson
 Assignment: Lab 6
 Instructor: Dr. Luc Longpre
 TA: Anthony Ortiz
 Last Modification: 4/7/2017
 Purpose of Program: The goal of this assignment is to practice sorting algorithms.
*/

public class Runner2 {
	public static void main(String[] args) {
		Cube[] random = randomGenorator(20);
		Cube[] newCube = CopyArray(random);

		System.out.println("Averages for Bubble Sort");
		getAverageBubbleSort(5);
		System.out.println();
		
		System.out.println("Averages for Selection Sort");
		getAverageSelectionSort(5);
		System.out.println();
	}

	public static Cube[] randomGenorator(int size) {
		Cube[] random = new Cube[size];
		for (int i = 0; i < size; i++) {
			random[i] = new Cube(Math.random() * 100, Math.random() * 100, Math.random() * 100);
		}
		return random;
	}

	public static void printCube(Cube[] C) {
		for (int i = 0; i < C.length; i++) {
			System.out.println("Dimensions for Cube " + (i + 1));
			System.out.printf("Length: %.2f \nWidth: %.2f \nHeight: %.2f \nVolume: %.2f \n", C[i].getLength(),
					C[i].getWidth(), C[i].getHeight(), C[i].getVolume());
			System.out.println();
		}
	}

	public static Cube[] CopyArray(Cube[] oldCube) {
		Cube[] newCube = new Cube[oldCube.length];
		for (int i = 0; i < oldCube.length; i++) {
			newCube[i] = new Cube(oldCube[i].getLength(), oldCube[i].getWidth(), oldCube[i].getHeight());
		}
		return newCube;
	}

	public static Cube[] BubbleSort(Cube[] C) {
		Cube temp = null;
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < C.length; j++) {
				if (j + 1 >= C.length) {
				} else if (C[j].getVolume() > C[j + 1].getVolume()) {
					temp = C[j];
					C[j] = C[j + 1];
					C[j + 1] = temp;
				}
			}
		}
		return C;
	}

	public static Cube[] SelectionSort(Cube[] C) {
		Cube temp = null;
		for (int i = 0; i < C.length; i++) {
			int iter = i;
			for (int j = i + 1; j < C.length; j++) {
				if (C[iter].getVolume() > C[j].getVolume())
					iter = j;
			}
			temp = C[iter];
			C[iter] = C[i];
			C[i] = temp;
		}
		return C;
	}
	
	public static void getAverageBubbleSort(int numTests) { // This method runs x number of tests to get the average nanoseconds it takes to sort cubes starting from 2000 until 7000
		long start, end;
		for (int i = 2000; i < 8000; i += 1000) {
			long average = 0;
			Cube[] C = randomGenorator(i);
			for (int j = 0; j < numTests; j++) {
				start = System.nanoTime();
				BubbleSort(C);
				end = System.nanoTime();
				average += (end - start);
			}
			average = (average / numTests);
			System.out.println("Average Time for " + i + " Cubes: " + average);
		}
	}

	public static void getAverageSelectionSort(int numTests) { // This method runs x number of tests to get the average nanoseconds it takes to sort cubes starting from 2000 until 7000
		long start, end;
		for (int i = 2000; i < 8000; i += 1000) {
			long average = 0;
			Cube[] C = randomGenorator(i);
			for (int j = 0; j < numTests; j++) {
				start = System.nanoTime();
				SelectionSort(C);
				end = System.nanoTime();
				average += (end - start);
			}
			average = (average / numTests);
			System.out.println("Average Time for " + i + " Cubes: " + average);
		}
	}
}
