import java.util.ArrayList;
import java.util.Objects;

public class QuadraticSpaceHashing<T> implements PerfectHashing<T> {
    ArrayList<T> quadraticSpace;
    private int rebuild; // Number of rebuilds due to exceeding the load factor
    private int collisions; // Number of collisions (Number of rehashing due to collisions)
    private int elementCounter; // To calculate the primary load factor, when exceed 0.7 rehash
    private int N; // Size of hash table ( n^2 )
    private HashFunction hashFunction;
    private static final Object DELETED_MARKER = new Object();

    public QuadraticSpaceHashing(int n){                                         // Number of elements to be inserted
        this.N = (int) Math.pow(2, Math.ceil(Math.log(n * n) / Math.log(2)) );   // Making the table size a power of 2
        this.hashFunction = new HashFunction(this.N);
        this.quadraticSpace = new ArrayList<>(N); // Initialize quadraticSpace with size N
        for (int i = 0; i < N; i++) { // Initialize it with null values to fix IndexOutOfBoundException
            quadraticSpace.add(null);
        }
        this.rebuild = 0;
        this.collisions = 0;
        this.elementCounter = 0;
    }

    // Returns true if inserted; false if it already exists.
    public boolean insert(T key) {

        int index = (int) hashFunction.hash(key);

        if (Objects.equals(this.quadraticSpace.get(index), null)
                || Objects.equals(this.quadraticSpace.get(index), DELETED_MARKER)) { // If the place is empty, check for
                                                                                     // load factor and
            // insert element
            // and return true.
            this.elementCounter++;
            if (this.elementCounter / this.N > 0.7) {
                this.rebuild();

                return this.insert(key);
            } else {

                this.quadraticSpace.set(index, key);
            }

            return true;
        } else if (Objects.equals(this.quadraticSpace.get(index), key)) { // If the element exists, return false.
            return false;
        } else if (!Objects.equals(this.quadraticSpace.get(index), key)) { // If the place is not empty, rehash.
            this.collisions++;
            this.rehash();
            return this.insert(key);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean delete(T key) {
        int index = (int) hashFunction.hash(key);
        if (Objects.equals(this.quadraticSpace.get(index), key)) {
            this.quadraticSpace.set(index, (T) DELETED_MARKER);
            this.elementCounter--;
            return true;
        } else {
            // If the key is not found at its original index, it means it doesn't exist
            return false;
        }
    }

    // Returns true if found; false otherwise.
    public boolean searchForKey(T key) {
        int index = (int) hashFunction.hash(key);
        if (this.quadraticSpace.get(index) != null && Objects.equals(this.quadraticSpace.get(index), key)) {
            return true;
        }
        return false;
    }

    // Returns the number of newly added elements and already existing elements.
    public int[] batchInsert(ArrayList<T> elements) {
        int[] added = { 0, 0 }; // Index 0 indicates how many newly added elements; Index 1 indicates how many
                                // elements already existed.

        for (T key : elements) {
            if (this.insert(key)) {

                added[0]++;
            } else {
                added[1]++;
            }
        }
        return added;
    }

    public int[] batchDelete(ArrayList<T> elements) {
        int[] deleted = { 0, 0 }; // Index 0 indicates how many elements are deleted; Index 1 indicates how many
                                  // elements doesn't exist.

        for (T key : elements) {

            if (this.delete(key)) {

                deleted[0]++;

            } else {
                deleted[1]++;

            }
        }
        return deleted;
    }

    public boolean collisionCheck(T key) {
        int index = (int) hashFunction.hash(key);
        if (this.quadraticSpace.get(index) != null && !Objects.equals(this.quadraticSpace.get(index), key)) {
            return true;
        }
        return false;
    }

    private void rehash() {
        ArrayList<T> elements = new ArrayList<>();
        this.hashFunction = new HashFunction(this.N);
        for (T key : this.quadraticSpace) {
            if (key != null) {
                elements.add(key);
            }
        }
        this.quadraticSpace = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            this.quadraticSpace.add(null);
        }
        // System.out.println("rehashed");
        this.elementCounter = 0;
        this.batchInsert(elements);
    }

    // Rebuild occurs when load factor exceeds 0.7
    private void rebuild(){
        this.rebuild ++;
        ArrayList<T> elements = new ArrayList<>();
        for(T key: this.quadraticSpace){
            if (key != null){
                elements.add(key);
            }
        }
        this.N = (int) Math.pow(2, Math.ceil(Math.log(elementCounter * elementCounter) / Math.log(2)) );
        this.hashFunction = new HashFunction(this.N);

        // Reinitialize the hash table with the new size
        this.quadraticSpace = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            quadraticSpace.add(null);
        }

        // Reinsert elements into the resized hash table
        this.elementCounter = 0;
        for (T key : elements) {
            if (key != null) {
                insert(key);
            }
        }
        // Do I reset the number of collisions ??
    }

    public int getCollisions() {
        return this.collisions;
    }

    public int getRebuild() {
        return this.rebuild;
    }

    public ArrayList<T> getElements() {
        ArrayList<T> elements = new ArrayList<>();
        for (T key : this.quadraticSpace) {
            if (key != null) {
                elements.add(key);
            }
        }
        return elements;
    }

}
