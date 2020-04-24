// Practical 2- Question 5 - Linked Stack - Eanna Curran

package projectCode20280.Practical2;

import projectCode20280.Practical1.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E> {


    // Declaring variables
    private final SinglyLinkedList<E> list = new SinglyLinkedList<>();


    /**
     * Empty constructor for the Linked Stack
     */
    public LinkedStack(){ }


    /**
     * Getter method for the size of the Linked Stack
     * @return Current size of the Linked Stack
     */
    @Override
    public int size() { return list.size(); }


    /**
     * isEmpty method for the Linked Stack
     * @return Boolean result for if the Linked Stack is empty
     */
    @Override
    public boolean isEmpty() { return list.isEmpty(); }


    /**
     * Method to push a new method to the top of Linked Stack
     * @param e Element to be pushed
     */
    @Override
    public void push(E e) { list.addFirst(e); }


    /**
     * Method to get the element at the top of the Linked Stack
     * @return Element at the top
     */
    @Override
    public E top() { return list.get(0); }


    /**
     * Method to pop the first element of the Linked Stack
     * @return Element that was popped
     */
    @Override
    public E pop() {

        E e = list.get(0);
        list.remove(0);
        return e;
    }


    /**
     * toString method for the Linked Stack
     * @return Formatted string of the Linked Stack
     */
    @Override
    public String toString(){ return list.toString(); }

    public static void main(String[] args) {
        LinkedStack<Integer> ll = new LinkedStack<>();
        ll.push(1);
        ll.push(2);
        ll.push(3);
        System.out.println(ll);
        System.out.println(ll.top());
        ll.pop();
        System.out.println(ll);
    }
}
