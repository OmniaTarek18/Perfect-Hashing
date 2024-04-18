
public class HashFunction {
	// random the matrix (until rehashing change the matrix)
	private int row;
	private int[][] matrix;
	static int col = 64;

	static void RandomiseMatrix() {

//			randomize the matrix here with 0/1
	}

	/*
	 * our constructor takes the rows and random the matrix
	 */
	public HashFunction(int row) {
			this.row = row;
			RandomiseMatrix();
		}

	/*
	 * Do the Multiplication and return the index
	 */
	public long getIndex() {
		return 0;
	}

//		convert key to binary
	public static long convertToBinary(long key) {
		return 0;
	}

//		convert index to decimal
	public static long convertToDecimal() {
		return 0;
	}

}
