import java.util.ArrayList;

public class QuadraticSpaceHashing<T> implements PerfectHashing<T>{
	ArrayList<T> quadraticSpace;
    private int rebuild;             // Number of rebuilds due to exceeding the load factor
    private int collisions;          // Number of collisions (Number of rehashing due to collisions)
    private int elementCounter;      // To calculate the primary load factor, when exceed 0.7 rehash
    private int N;                   // Size of hash table ( n^2 )
    private HashFunction hashFunction;

    public QuadraticSpaceHashing(int n){                                         // Number of elements to be inserted
        this.N = (int) Math.pow(2, Math.ceil(Math.log(n * n) / Math.log(2)) );   // Making the table size a power of 2
        if (this.N == 1){
            this.N ++;
        }
        this.hashFunction = new HashFunction(this.N);
        this.rebuild = 0;
        this.collisions = 0;
        this.elementCounter = 0;
    }

    // Returns true if inserted; false if it already exists.
    public boolean insert(T key){
        int index = (int) hashFunction.hash(key);

        if (this.quadraticSpace.get(index) == null){    // If the place is empty, check for load factor and insert element and return true.
            this.elementCounter ++;
            if ( this.elementCounter / this.N > 0.7){
                this.rebuild();
                return this.insert(key);
            }
            else{
                this.quadraticSpace.set(index, key);
            }
            return true;
        }
        else if (this.quadraticSpace.get(index) == key){  // If the element exists, return false.
            return false;
        }
        else if (this.quadraticSpace.get(index) != key){  // If the place is not empty, rehash.
            this.collisions++ ;
            this.rehash();
            return this.insert(key);
        }
        return false;
    }

    public boolean delete(T key){
        int index = (int) hashFunction.hash(key);

        if (this.quadraticSpace.get(index) == null){
            return false;
        }
        else if (this.quadraticSpace.get(index) == key){
            this.quadraticSpace.set(index, null);
            this.elementCounter --;
            return true;
        }
        return false;
    }

    // Returns true if found; false otherwise.
    public boolean searchForKey(T key){
        int index = (int) hashFunction.hash(key);
        if (this.quadraticSpace.get(index) != null && this.quadraticSpace.get(index) == key){
            return true;
        }
        return false;
    }

    // Returns the number of newly added elements and already existing elements.
    public int[] batchInsert(ArrayList<T> elements){
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

    public int[] batchDelete(ArrayList<T> elements){
        int[] deleted = new int[2];  // Index 0 indicates how many elements are deleted; Index 1 indicates how many elements doesn't exist.
        
        for (T key : elements){
            if (this.delete(key)){
                deleted[0] ++;
            }
            else{
                deleted[1] ++;
            }
        }
        return deleted;
    }

    public boolean collisionCheck(T key){
        int index = (int) hashFunction.hash(key);
        if (this.quadraticSpace.get(index) != null && this.quadraticSpace.get(index) != key){
            return true;
        }
        return false;
    }

    private void rehash(){
        ArrayList<T> elements = new ArrayList<>();
        this.hashFunction = new HashFunction(this.N);
        for(T key: this.quadraticSpace){
            if (key != null){
                elements.add(key);
            }
        }
        this.elementCounter = 0;
        this.quadraticSpace = new ArrayList<>();
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
        if (this.N == 1){
            this.N ++;
        }
        this.hashFunction = new HashFunction(this.N);
        this.elementCounter = 0;
        this.quadraticSpace = new ArrayList<>();
        this.batchInsert(elements);
//Do I reset the number of collisions ??
    }

    public int getCollisions(){
        return this.collisions;
    }
    
    public int getRebuild() {
        return this.rebuild;
    }

    public ArrayList<T> getElements(){
        ArrayList<T> elements = new ArrayList<>();
        for(T key: this.quadraticSpace){
            if (key != null){
                elements.add(key);
            }
        }
        return elements;
    }

}
