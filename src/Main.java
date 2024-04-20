import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static final String PURPLE = "\u001B[95m";
	public static final String RESET = "\u001B[0m";
	private EnglishDictionary dictionary;
	private Scanner scanner;

	public Main(EnglishDictionary dictionary) {
		this.dictionary = dictionary;
		this.scanner = new Scanner(System.in);
	}

	public void run() {
		boolean exitProgram = false;
		while (!exitProgram) {
			String backendType = selectBackendType();
			if (backendType.equals("exit")) {
				exitProgram = true;
				continue;
			}
			initializeDictionary(backendType);
			boolean exitMenu = false;
			while (!exitMenu) {
				printCommandsMenu();
				System.out.print(PURPLE + "\nEnter a command (1-6): " + RESET);
				int command = scanner.nextInt();
				scanner.nextLine();

				switch (command) {
					case 1:
						insertWord();
						break;
					case 2:
						deleteWord();
						break;
					case 3:
						searchWord();
						break;
					case 4:
						batchInsert();
						break;
					case 5:
						batchDelete();
						break;
					case 6:
						exitMenu = true;
						break;
					default:
						System.out.println("Invalid command.");
						break;
				}
			}
		}
		System.out.println("Exiting the program.");
	}

	private String selectBackendType() {
		String backendType = "";
		while (true) {
			System.out.print(
					PURPLE + "\nPlease enter the type of perfect hashing to use:\n" + RESET
							+ "\t1. Quadratic space solution. \n\t2. Linear space solution. \n\t3. Exit program. ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					backendType = "quadratic";
					break;
				case 2:
					backendType = "linear";
					break;
				case 3:
					backendType = "exit";
					break;
				default:
					System.out.println(PURPLE + "Please enter a valid choice!" + RESET);
					continue;
			}
			break;
		}
		return backendType;
	}

	private void initializeDictionary(String backendType) {
		if (!backendType.equals("exit")) {
			dictionary = new EnglishDictionary(backendType);
			System.out.println(PURPLE + "Initialized with " + backendType + " hashing." + RESET);
		}
	}

	private void insertWord() {
		System.out.print(PURPLE + "Enter the word to insert: " + RESET);
		String word = scanner.nextLine();
		boolean inserted = dictionary.insert(word);
		if (inserted) {
			System.out.println("Word '" + word + "' successfully inserted.");
		} else {
			System.out.println("Word '" + word + "' already exists in the dictionary.");
		}
	}

	private void deleteWord() {
		System.out.print(PURPLE + "Enter the word to delete: " + RESET);
		String word = scanner.nextLine();
		boolean deleted = dictionary.delete(word);
		if (deleted) {
			System.out.println("The Word '" + word + "' successfully deleted.");
		} else {
			System.out.println("The word '" + word + "' does not exist in the dictionary.");
		}
	}

	private void searchWord() {
		System.out.print(PURPLE + "Enter the word to search: " + RESET);
		String word = scanner.nextLine();
		boolean exists = dictionary.search(word);
		if (exists) {
			System.out.println("The word '" + word + "' exists in the dictionary.");
		} else {
			System.out.println("The word '" + word + "' does not exist in the dictionary.");
		}
	}

	private void batchInsert() {
		System.out.print(PURPLE + "Enter the path to the file containing words to insert: " + RESET);
		String filePath = scanner.nextLine();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			ArrayList<String> words = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) != null) {
				words.add(line.trim());
			}
			int[] result = dictionary.batchInsert(words);
			System.out.println(result[0] + " words added to the dictionary.");
			System.out.println(result[1] + " words already existed in the dictionary.");
		} catch (IOException e) {
			System.out.println(PURPLE + "Error reading the file." + RESET);
		}
	}

	private void batchDelete() {
		System.out.print(PURPLE + "Enter the path to the file containing words to delete: " + RESET);
		String filePath = scanner.nextLine();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			ArrayList<String> words = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) != null) {
				words.add(line.trim());
			}
			int[] result = dictionary.batchDelete(words);
			System.out.println(result[0] + " words deleted from the dictionary.");
			System.out.println(result[1] + " words do not exist in the dictionary.");
		} catch (IOException e) {
			System.out.println(PURPLE + "Error reading the file." + RESET);
		}
	}

	private void printCommandsMenu() {
		System.out.println(PURPLE + "\nCommands:" + RESET);
		System.out.println("\t1. Insert a word.");
		System.out.println("\t2. Delete a word.");
		System.out.println("\t3. Search for a word.");
		System.out.println("\t4. Batch insert.");
		System.out.println("\t5. Batch delete.");
		System.out.println("\t6. Back to previous menu.");
	}

	public static void main(String[] args) {
		Main main = new Main(null);
		main.run();
	}
}
