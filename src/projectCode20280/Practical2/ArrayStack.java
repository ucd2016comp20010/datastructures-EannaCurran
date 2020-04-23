// Practical 2- Question 2 - Array Queue - Eanna Curran

package projectCode20280.Practical2;

public class ArrayStack<E> implements Stack<E> {

    // Declaring variables
    public static final int CAPACITY = 100;
    private final E[] data;
    private int topIndex = -1;


    /**
     * Constructor for Array Stack with no passed in capacity
     */
    public ArrayStack(){ this(CAPACITY);}

    /**
     * Constructor for Array Stack with capacity passed in
     * @param capacity Maximum number of elements allowed in the Array Stack
     */
    public ArrayStack(int capacity){ data =  (E[]) new Object[capacity]; }


    /**
     * Getter method for size
     * @return Number of elements in the Array Stack
     */
    @Override
    public int size() { return topIndex + 1; }


    /**
     * isEmpty method for Array Stack
     * @return Boolean result for if the Array Stack is empty
     */
    @Override
    public boolean isEmpty() { return topIndex == -1; }


    /**
     * Method to push a new element onto the Array Stack
     * @param e Element to be pushed
     * @throws IllegalStateException If the Array Stack is full
     */
    @Override
    public void push(E e) throws IllegalStateException {

        // Check if the Array Stack is full
        if(size() == data.length){
            throw new IllegalStateException("Stack is full");
        }

        // Pushes the new element to the top of the stack
        data[++topIndex] = e;
    }


    /**
     * Method to get the element on the top of the Array Stack
     * @return
     */
    @Override
    public E top() {

        // Check if the Array Stack is empty
        if(isEmpty()){
            return null;
        }

        // Returns the element at the top of the stack
        return data[topIndex];
    }


    /**
     * Method to pop the element at the top of the Array Stack
     * @return Element that was popped
     */
    @Override
    public E pop() {

        // Check if the stack is empty
        if(isEmpty()){
            return null;
        }

        // Pops and returns the element on the top of the stack
        E result = data[topIndex];
        data[topIndex] = null;
        return result;
    }


    /**
     * toString method for the Array Stack
     * @return String representation of the Array Stack
     */
    @Override
    public String toString(){

        StringBuilder answer = new StringBuilder();
        for(E e: this.data){
            if(e != null) {
                answer.append(e).append(" ");
            }
            else{
                break;
            }
        }
        answer.deleteCharAt(answer.length() - 1);
        answer.reverse();
        return answer.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> aq = new ArrayStack<>();
        aq.push(1);
        aq.push(2);
        aq.push(3);
        System.out.println(aq);
        System.out.println(aq.top());
        aq.pop();
        System.out.println(aq);

    }
}
