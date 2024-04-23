import java.util.ArrayList;

public class EnglishDictionary {
    private PerfectHashing<String> perfectHashTable;

    public EnglishDictionary(String backendType) {
        if (backendType.equals("quadratic")) {
            perfectHashTable = new QuadraticSpaceHashing<>(5); // Initially the size of the dictionary is 5
        } else if (backendType.equals("linear")) {
            perfectHashTable = new LinearSpaceHashing<>(5);
        } else {
            System.out.println("Invalid backend type specified.");
        }
    }

    public int getSize(){
        return perfectHashTable.getSize();
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

    public int[] batchInsert(ArrayList<String> words,int newSize) {
        return perfectHashTable.batchInsert(words,newSize);

    }

    public int[] batchDelete(ArrayList<String> words) {
        return perfectHashTable.batchDelete(words);

    }
}
