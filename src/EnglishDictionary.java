import java.util.ArrayList;

public class EnglishDictionary {
    private PerfectHashing<String> perfectHashTable;

    public EnglishDictionary(String backendType) {
        if (backendType.equals("quadratic")) {
            perfectHashTable = new QuadraticSpaceHashing<>(1);
        } else if (backendType.equals("linear")) {
            perfectHashTable = new LinearSpaceHashing<>(1);
        } else {
            System.out.println("Invalid backend type specified.");
        }
    }

    public boolean insert(String word) {
        return perfectHashTable.insert(word);
    }

    public boolean delete(String word) {
        return perfectHashTable.delete(word);
    }

    public boolean search(String word) {
        return perfectHashTable.searchForKey(word);
    }

    public int[] batchInsert(ArrayList<String> words) {
        return perfectHashTable.batchInsert(words);

    }

    public int[] batchDelete(ArrayList<String> words) {
        return perfectHashTable.batchDelete(words);

    }
}
