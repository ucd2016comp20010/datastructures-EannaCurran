// Practical 2- Question 1 - Array Queue - Eanna Curran

package projectCode20280.Practical2;

public class ArrayQueue<E> implements Queue<E> {

    // Declaring variables
    private final E[] data;
    private int f = 0;
    private int size = 0;
    public static final int CAPACITY = 100;


    /**
     * Constructor for Array Queue of a given size
     * @param capacity Max size of the  Array Queue
     */
    public ArrayQueue(int capacity){ data = (E[]) new Object[capacity]; }


    /**
     * Constructor for Array Queue
     */
    public ArrayQueue(){ this(CAPACITY);}


    /**
     * Getter method for size
     * @return Size of Array Queue
     */
    @Override
    public int size() { return size; }


    /**
     * isEmpty method for Array Queue
     * @return Boolean result for if the Array Queue is empty
     */
    @Override
    public boolean isEmpty() { return size == 0; }


    /**
     * Method to add a new element to the end of the queue
     * @param e Element to be added ot the queue
     * @throws IllegalStateException If the queue is full
     */
    @Override
    public void enqueue(E e) throws IllegalStateException{

        // Checks the size of the queue
        if(size == data.length) throw new IllegalStateException("Queue is full");

        // Positions the new element at the end of the queue and increments the size
        int avail = (f + size) %data.length;
        data[avail] = e;
        size++;
    }


    /**
     * Method to return the element at the head of the queue
     * @return First element
     */
    @Override
    public E first() {
        if(isEmpty()) return null;
        return data[f];
    }


    /**
     * Method to remove the element at the head of the queue
     * @return Element removed from the head of the queue
     */
    @Override
    public E dequeue() {

        // Check for empty queue
        if(isEmpty()) return null;

        // Dereferences the head element and updates size and f
        E answer = data[f];
        data[f] = null;
        f = (f + 1)%data.length;
        size--;
        return answer;
    }

    /**
     * ToString method for Array Queue
     * @return Formatted string representation of the Array Queue
     */
    @Override
    public String toString(){
        StringBuilder answer = new StringBuilder();
        for(int i = f; i < size+f; i++){
            answer.append(data[i]);
            answer.append(" ");
        }
        answer.deleteCharAt(answer.length() - 1);

        return answer.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> aq = new ArrayQueue<>();
        aq.enqueue(1);
        aq.enqueue(2);
        aq.enqueue(3);
        aq.enqueue(4);
        System.out.println(aq);
        System.out.println(aq.dequeue());
        System.out.println(aq);
        System.out.println(aq.first());

    }
}
