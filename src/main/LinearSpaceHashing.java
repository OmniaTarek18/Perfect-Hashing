package main;

import java.util.ArrayList;
import java.util.Objects;

public class LinearSpaceHashing<T> implements PerfectHashing<T> {

    ArrayList<QuadraticSpaceHashing<T>> linearSpace;
    private int rebuild; // Number of rebuilds due to exceeding the load factor
    private int collisions; // Number of collisions (Number of rehashing due to collisions)
    private int N; // Size of first level
    private HashFunction primaryFunction;
    private int elementCounter; // To calculate the primary load factor, when exceed 0.7 rehash
    private static final Object DELETED_MARKER = new Object();

    // Constructor to initialize the table
    public LinearSpaceHashing(int n) { // n is the Number of elements to be inserted
        this.N = (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2))); // Making the table size a power of 2
        if (this.N == 1) {
            this.N++;
        }
        this.primaryFunction = new HashFunction(this.N); // Initializing the primary hash function.
        this.rebuild = 0;
        this.collisions = 0;
        this.elementCounter = 0;
        // Initialize the linearSpace array list with the size N
        this.linearSpace = new ArrayList<>(this.N);
        for (int i = 0; i < this.N; i++) {
            this.linearSpace.add(null);
        }
    }

    // Return true if inserted; false if it already exists.
    public boolean insert(T key) {
        int primaryIndex = (int) primaryFunction.hash(key);

        if (linearSpace.get(primaryIndex) == null || Objects.equals(linearSpace.get(primaryIndex), DELETED_MARKER)) { // If the second level empty,create an N^2 perfect hashing and insert the element

            this.elementCounter++;
            if (this.elementCounter / this.N > 0.7) {
                this.rebuild(elementCounter);
                return this.insert(key);
            } else {
                linearSpace.set(primaryIndex, new QuadraticSpaceHashing<T>(1));
                return this.linearSpace.get(primaryIndex).insert(key);
            }
        } else if (linearSpace.get(primaryIndex).searchForKey(key)) { // If the element already exists, return false.
            return false;
        } else if (linearSpace.get(primaryIndex).collisionCheck(key)) { // If collision happens, rehash will occur.
            this.elementCounter++;
            if (this.elementCounter / this.N > 0.7) {
                this.rebuild(elementCounter);
                return this.insert(key);
            } else {
                return this.linearSpace.get(primaryIndex).insert(key);
            }
        } else { // The element doesn't exist and no collision happens
            this.elementCounter++;
            if (this.elementCounter / this.N > 0.7) {
                this.rebuild(elementCounter);
                return this.insert(key);
            } else {
                return this.linearSpace.get(primaryIndex).insert(key);
            }
        }
    }

    // Returns true if element exists and has been deleted; false otherwise.
    public boolean delete(T key) {
        int primaryIndex = (int) primaryFunction.hash(key);

        if (this.linearSpace.get(primaryIndex) == null) {
            return false;
        } else if (this.linearSpace.get(primaryIndex).searchForKey(key)) {
            this.linearSpace.get(primaryIndex).delete(key);
            this.elementCounter--;
            return true;
        }
        return false;
    }

    // Returns true if found; false otherwise.
    public boolean searchForKey(T key) {
        int primaryIndex = (int) primaryFunction.hash(key);
        if (this.linearSpace.get(primaryIndex) == null) {
            return false;
        }
        return this.linearSpace.get(primaryIndex).searchForKey(key);
    }

    // Returns the number of newly added elements and already existing elements.
    public int[] batchInsertHelper(ArrayList<T> elements) {
        int[] added = new int[2]; // Index 0 indicates how many newly added elements; Index 1 indicates how many
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
        int[] deleted = new int[2]; // Index 0 indicates how many elements are deleted; Index 1 indicates how many
                                    // elements doesn't exist.

        for (T key : elements) {
            int primaryIndex = (int) primaryFunction.hash(key);
            if (this.linearSpace.get(primaryIndex) != null && this.linearSpace.get(primaryIndex).searchForKey(key)) {
                deleted[0]++;
                this.linearSpace.get(primaryIndex).delete(key);
            } else {
                deleted[1]++;
            }
        }
        return deleted;
    }

    public int[] batchInsert(ArrayList<T> elements, int newSize) {
        rebuild(newSize);
        return batchInsertHelper(elements);
    }

    // Rebuild occurs when load factor exceeds 0.7
    private void rebuild(int m) {
        this.rebuild++;
        ArrayList<T> elements = new ArrayList<>();
        for (QuadraticSpaceHashing<T> q : this.linearSpace) {
            if (q != null) {
                elements.addAll(q.getElements());
            }
        }
        this.N = (int) Math.pow(2, Math.ceil(Math.log(m) / Math.log(2)));
        if (this.N == 1) {
            this.N++;
        }
        this.primaryFunction = new HashFunction(this.N);
        this.linearSpace = new ArrayList<>(this.N);
        for (int i = 0; i < this.N; i++) {
            this.linearSpace.add(null);
        }
        this.collisions = 0;
        this.batchInsertHelper(elements);
        this.elementCounter = elements.size(); // Update the element counter with the size of the elements list
    }

    // Calculate the number of collisions (Total number of rehashs occured in the
    // second level)
    public int getCollisions() {
        for (QuadraticSpaceHashing<T> q : linearSpace) {
            if (q != null) {
                this.collisions += q.getCollisions();
            }
        }
        return this.collisions;
    }

    public int getRebuild() {
        return this.rebuild;
    }

    public int getSize() {
        return this.N;
    }
}