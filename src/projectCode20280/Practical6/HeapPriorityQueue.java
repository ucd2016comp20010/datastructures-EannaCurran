// Practical 6 - Question 1 -  Heap Priority Queue - Eanna Curran

package projectCode20280.Practical6;

import java.util.ArrayList;
import java.util.Comparator;


public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();


      /** Creates an empty priority queue based on the natural ordering of its keys. */
    public HeapPriorityQueue() { super(); }


    /**
     * Creates an empty priority queue using the given comparator to order keys.
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) { super(comp); }


    /**
     * Creates a priority queue initialized with the respective
     * key-value pairs.  The two arrays given will be paired
     * element-by-element. They are presumed to have the same
     * length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     * @param keys an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        super();

        for(int i = 0; i < Math.min(keys.length, values.length); i++){
            heap.add(new PQEntry<>(keys[i], values[i]) {
              @Override
              public int compareTo(Entry<K, V> o) {
                return 0;
              }
            });
        }

        heapify();
    }


    /** Protected utilities **/
    protected int parent(int j) { return (j-1) / 2;}

    protected int left(int j) { return 2*j + 1;}

    protected int right(int j) {return 2*j + 2;}

    protected boolean hasLeft(int j) { return left(j) < heap.size();}

    protected boolean hasRight(int j) {return right(j) < heap.size();}


    /** Exchanges the entries at indices i and j of the array list. */
    protected void swap(int i, int j) {

        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }


    /** Moves the entry at index j higher, if necessary, to restore the heap property. */
    protected void upheap(int j){

        while(j > 0){

            int p = parent(j);

            if (compare(heap.get(j), heap.get(p)) >= 0) { break; }

            swap(j, p);
            j = p;
        }
    }


    /** Moves the entry at index j lower, if necessary, to restore the heap property. */
    protected void downHeap(int j) {

        while(hasLeft(j)){

            int leftIndex = left(j);
            int smallChildIndex = leftIndex;

            if(hasRight(j)){
                int rightIndex = right(j);
                if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0){ smallChildIndex = rightIndex; }
            }

            if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0){ break; }

            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }


    /** Performs a bottom-up construction of the heap in linear time. */
    protected void heapify() {

        int startIndex = parent(size() - 1);

        for(int i = startIndex; i>=0; i--){ downHeap(i); }
    }


    /**
     * Returns the number of items in the priority queue.
     * @return Number of items
     */
    @Override
    public int size() { return heap.size(); }


    /**
     * Returns (but does not remove) an entry with minimal key.
     * @return Entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K,V> min() {

        if(heap.isEmpty()){ return null; }

        return heap.get(0);
    }


    /**
     * Inserts a key-value pair and return the entry created.
     * @param key The key of the new entry
     * @param value The associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {

        checkKey(key);

        Entry<K, V> newest = new PQEntry<>(key, value) {
            @Override
            public int compareTo(Entry<K, V> o) {return 0; }
        };

        heap.add(newest);
        upheap(heap.size() - 1);

        return newest;
    }


    /**
     * Removes and returns an entry with minimal key.
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K,V> removeMin() {

        if(heap.isEmpty()){ return null; }

        Entry<K, V> min = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downHeap(0);
        return min;
    }

  /**
   * toString method for Heap Priority Queue
   * @return Formatted String
   */
  @Override
    public String toString(){

        StringBuilder answer = new StringBuilder();
        answer.append("[");

        for(int i = 0; i < heap.size() - 1; i++){ answer.append(heap.get(i).getKey()).append(", "); }

        answer.append(heap.get(heap.size() - 1).getKey());
        answer.append("]");

        return answer.toString();
    }


    /** Used for debugging purposes only */
    private void sanityCheck() {

        for (int j=0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0)
                System.out.println("Invalid left child relationship");
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0)
                System.out.println("Invalid right child relationship");
        }
    }


    public static void main(String[] args) {
        HeapPriorityQueue<String, Integer> heap = new HeapPriorityQueue<>();
        heap.insert("A", 1);
        heap.insert("B", 2);

        System.out.println(heap);
    }
}

