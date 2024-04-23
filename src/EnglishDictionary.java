import java.util.ArrayList;

public class EnglishDictionary {
    private PerfectHashing<String> perfectHashTable;
    private int size;

    public EnglishDictionary(String backendType, int n) {
        if (backendType.equals("quadratic")) {
            perfectHashTable = new QuadraticSpaceHashing<>(n);
        } else if (backendType.equals("linear")) {
            perfectHashTable = new LinearSpaceHashing<>(n);
        } else {
            System.out.println("Invalid backend type specified.");
        }
        this.size = n;
    }

    public int size(){
        return this.size;
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
