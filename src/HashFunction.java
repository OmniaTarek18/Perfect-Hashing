import java.util.Arrays;
import java.util.Random;

public class HashFunction {
	private int row;
	private int[][] matrix;
	private final int col = 256;

	/*
	 * our constructor takes the rows and randomize the matrix
	 */
	public HashFunction(int n) {
		int row = (int) Math.ceil(Math.log(n)/Math.log(2));
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
	private int getIndex(String key) {

		int[] binaryKey = this.convertToBinaryArr(key);
		// multiplication
		int[] index = new int[this.row];
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				index[i] += this.matrix[i][j] * binaryKey[j];
			}
			index[i] &= 1; // bitwise operation (index % 2 )
		}

		int decimalIndex = this.convertToDecimal(index);

		return decimalIndex;
	}

	/*
	 * convert binary string to binary array of size 256
	 */
	public int[] convertToBinaryArr(String bitsRep) {
		int[] binaryKey = new int[256];
		int start = 256 - bitsRep.length() ;
		for(int i=start ; i<256  ; i++) {
			binaryKey[i] = bitsRep.charAt(i-start) == '0' ? 0:1;
		}
		System.out.println(Arrays.toString(binaryKey));
		return binaryKey;
	}

	/*
	 * convert index to decimal using doubling method
	 */
	private int convertToDecimal(int[] index) {
		int decimalIndex = 0;
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
	public <T> int hash(T key) {
		String newKey ;
		if (key instanceof String) {
			newKey = this.toBinaryString((String) key);
		} else if (key instanceof Double) {
			newKey = this.toBinaryString((Double) key);
		} else if (key instanceof Long)
			newKey = this.toBinaryString((Long) key);
		else {
			newKey =this.toBinaryString((Integer) key);
		}
		return this.getIndex(newKey);
	}

	/*
	 * handle different types of keys turn the key to binary rep. in string form
	 */
	private String toBinaryString(int key) {
        String binaryString = Integer.toBinaryString(key);
		return binaryString;
	}
	
	private String toBinaryString(double key) {
		long longBits = Double.doubleToLongBits(key);
		String binaryString = Long.toBinaryString(longBits);
		return binaryString;
	}
	private String toBinaryString(long key) {
        String binaryString = Long.toBinaryString(key);
		return binaryString;
	}
	private String toBinaryString(String key) {
		StringBuilder binary = new StringBuilder();
		for (int i = 0; i < key.length(); i++) {
			int ascii = (int) key.charAt(i);	 //we can use getBytes() to get the each character then convert it to binary
			String binaryStr = Integer.toBinaryString(ascii);
			binaryStr=String.format("%7s",binaryStr).replace(' ', '0');		//to ensure that all letters are represented in 7 bits
			System.out.println(binaryStr);
			binary.append(binaryStr);
		}
		String binaryString = binary.toString();
		return binaryString;

	}
	


}