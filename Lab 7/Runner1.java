public class Runner1 {
	public static void main(String[] args) {
		Cube[] random = randomGenorator(20);

		System.out.println("Before Bubble Sort");
		System.out.println();
		
		printCube(random);

		Cube[] newCube = CopyArray(random);

		System.out.println("After Bubble Sort");
		System.out.println();
		
		BubbleSort(random);
		printCube(random);

		System.out.println("Before Selection Sort");
		System.out.println();
		
		printCube(newCube);
		
		System.out.println("After Selection Sort");
		System.out.println();
		
		SelectionSort(newCube);
		printCube(newCube);
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
}
