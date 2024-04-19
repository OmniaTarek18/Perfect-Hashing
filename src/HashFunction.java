import java.util.Random;

public class HashFunction {
	private int row;
	private int[][] matrix;
	private final int col = 64;

	/*
	 * our constructor takes the rows and randomize the matrix
	 */
	public HashFunction(int row) {
		// this.rows = (int) Math.ceil(Math.log(row) / Math.log(2));
		// if (this.rows == 0) {
		// 	this.rows = 1;
		// }
		this.row = row;
		this.matrix = new int[this.row][this.col];
		this.RandomiseMatrix();
	}

	/*
	 * randomize the matrix here with 0 or 1
	 */
	private void RandomiseMatrix() {
		Random rand = new Random();
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				this.matrix[i][j] = rand.nextInt(2);
			}
		}
	}

	/*
	 * Do the Multiplication and return the index
	 */
	private long getIndex(long key) {

		int[] binaryKey = this.convertToBinary(key);
		// multiplication
		int[] index = new int[this.row];
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				index[i] += this.matrix[i][j] * binaryKey[j];
			}
			index[i] &= 1; // bitwise operation (index % 2 )
		}

		long decimalIndex = this.convertToDecimal(index);

		return decimalIndex;
	}

	/*
	 * convert key to binary -> 8 -> [1,0,0,0]
	 */
	private int[] convertToBinary(long key) {
		int[] binaryKey = new int[64];
		for (int i = 64 - 1; i >= 0; i--) {
			binaryKey[i] = (int) (key & 1);
			key >>= 1;
		}
		return binaryKey;
	}

	/*
	 * convert index to decimal using doubling method
	 */
	private long convertToDecimal(int[] index) {
		long decimalIndex = 0;
		for (int i = 0; i < this.row; i++) {
			decimalIndex <<= 1;
			decimalIndex += index[i];
		}
		return decimalIndex;
	}

	/*
	 * main hashing function that calculate the index and handle different type of
	 * key
	 */
	public <T> long hash(T key) {
		long newKey;
		if (key instanceof String) {
			newKey = this.toLong((String) key);
		} else if (key instanceof Character) {
			newKey = this.toLong((Character) key);
		} else
			newKey = ((Number) key).longValue();
		return this.getIndex(newKey);
	}

	/*
	 * handle different types of keys
	 */
	private long toLong(char key) {
		long newKey = (long) (key);
		return newKey;
	}

	private long toLong(String key) {
		StringBuilder binary = new StringBuilder();
		for (int i = 0; i < key.length(); i++) {
			int ascii = (int) key.charAt(i);
			String binaryStr = Integer.toBinaryString(ascii);
			binary.append(binaryStr);
		}
		long newKey = Long.parseLong(binary.toString(), 2);
		return newKey;

	}

}