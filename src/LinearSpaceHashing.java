import java.util.ArrayList;

public class LinearSpaceHashing<T> extends PerfectHashing{

	ArrayList< QuadraticSpaceHashing<T> > linearSpace;
    private int rebuild;             // Number of rebuilds due to exceeding the load factor
    private int collisions;          // Number of collisions (Number of rehashing due to collisions)
    private int N;                   // Size of first level
    private HashFunction primaryFunction;
    private int elementCounter;      // To calculate the primary load factor, when exceed 0.7 rehash

    //Constructor to initialize the table
    public LinearSpaceHashing(int n){                                        // n is the Number of elements to be inserted
        this.N = (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)) );   // Making the table size a power of 2
        this.primaryFunction = new HashFunction(this.N);                     // Initializing the primary hash function.
        this.rebuild = 0;
        this.collisions = 0;
        this.elementCounter = 0;
    }

    // Return true if inserted; false if it already exists.
    public boolean insert (T key){
        int primaryIndex = (int) primaryFunction.hash(key);

        if (linearSpace.get(primaryIndex) == null){      // If the second level empty, create an N^2 perfect hashing and insert the element.
            this.elementCounter ++;
            if (this.elementCounter / this.N > 0.7){
                this.rebuild();
                return this.insert(key);
            }
            else{
                linearSpace.set( primaryIndex, new QuadraticSpaceHashing<T>(1) );
                return this.linearSpace.get(primaryIndex).insert(key);
            }
        }
        else if (linearSpace.get(primaryIndex).searchForKey(key)) {  // If the element already exists, return false.
            return false;
        }
        else if (linearSpace.get(primaryIndex).collisionCheck(key)){  // If collision happens, rehash will occur.
            this.elementCounter ++;
            if (this.elementCounter / this.N > 0.7){
                this.rebuild();
                return this.insert(key);
            }
            else{
                return this.linearSpace.get(primaryIndex).insert(key);
            }
        }
        else {    // The element doesn't exist and no collision happens
            this.elementCounter++;
            if (this.elementCounter / this.N > 0.7){
                this.rebuild();
                return this.insert(key);
            }
            else {
                return this.linearSpace.get(primaryIndex).insert(key);
            }
        }
    }

    // Returns true if element exists and has been deleted; false otherwise.
    public boolean delete(T key){
        int primaryIndex = (int) primaryFunction.hash(key);
        
        if (this.linearSpace.get(primaryIndex) == null){
            return false;
        }
        else if (this.linearSpace.get(primaryIndex).searchForKey(key)){
            this.linearSpace.get(primaryIndex).delete(key);
            this.elementCounter --;
            return true;
        }
        return false;
    }

    // Returns true if found; false otherwise.
    public boolean searchForKey(T key){
        int primaryIndex = (int) primaryFunction.hash(key);
        if (this.linearSpace.get(primaryIndex) == null){
            return false;
        }
        return this.linearSpace.get(primaryIndex).searchForKey(key);
    }

    // Returns the number of newly added elements and already existing elements.
    public int[] batchInsert(ArrayList<T> elements) {
        int[] added = new int[2];    // Index 0 indicates how many newly added elements; Index 1 indicates how many elements already existed.

        for (T key : elements){
            if (this.insert(key)){
                added[0] ++;
            }
            else{
                added[1] ++;
            }
        }
        return added;
    }

    public void batchDelete(){
        // TODO 
        // It can return anything else, that just indicates that this method should exist.
    }

    // Rebuild occurs when load factor exceeds 0.7
    public void rebuild(){
        this.rebuild ++;
        ArrayList<T> elements = new ArrayList<>();
        for (QuadraticSpaceHashing<T> q : this.linearSpace){
            elements.addAll(q.getElements());
        }
        this.N = (int) Math.pow(2, Math.ceil(Math.log(elementCounter) / Math.log(2)) );
        this.primaryFunction = new HashFunction(this.N);
        this.elementCounter = 0;
        this.linearSpace = new ArrayList<QuadraticSpaceHashing<T>>();
        this.batchInsert(elements);
//Do I reset the number of collisions ??
    }

    // Calculate the number of collisions (Total number of rehashs occured in the second level)
    public int getCollisions(){
        for (QuadraticSpaceHashing<T> q : linearSpace){
            this.collisions += q.getCollisions();
        }
        return this.collisions;
    }

    public int getRebuild() {
        return this.rebuild;
    }
}
