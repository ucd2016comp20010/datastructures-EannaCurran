package projectCode20280.Practical4;


/**
 * Implementation of Collatz Problem
 */
public class Collatz {

	/**
	 * Method which implements a solution to Collatz Problem suing recursion
	 * @param i: Number to begin the sequence 1
	 * @return Integer 1
	 */
	public static int CollatzSolution(int i) {

		// If i is 1 then the recursive calls are terminated
		if (i == 1) {
			return i;
		}

		// If i is even then i is divided by 2 and recursively passed in Collatz
		if (i % 2 == 0) {

			return CollatzSolution(i / 2);
		}

		// If i is odd then i is multiplied by 3, incremented by 1 and recursively passed in Collatz
		else {
			return CollatzSolution((i * 3) + 1);
		}
	}


	public static void main(String[] args) {
		System.out.println(CollatzSolution(9));
	}
}
