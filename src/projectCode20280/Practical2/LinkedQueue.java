// Practical 2- Question 4 - Linked Queue - Eanna Curran

package projectCode20280.Practical2;

import projectCode20280.Practical1.SinglyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

    // Declaring variable
    private final SinglyLinkedList<E> list = new SinglyLinkedList<>();

    /**
     * Empty constructor for Linked Queue
     */
    public LinkedQueue(){ }


    /**
     * Getter method for size
     * @return Size of the Linked Queue
     */
    @Override
    public int size() { return list.size(); }


    /**
     * isEmpty method for Linked Queue
     * @return Boolean result for if the Linked Queue is empty
     */
    @Override
    public boolean isEmpty() { return list.isEmpty(); }


    /**
     * Method to add an element to the end of the Linked Queue
     * @param e Element to be added
     */
    @Override
    public void enqueue(E e) { list.addLast(e); }


    /**
     * Method to get the first element of the Linked Queue
     * @return First element
     */
    @Override
    public E first() { return list.get(0); }


    /**
     * Method to remove the element at the start of the Linked Queue
     * @return Removed element
     */
    @Override
    public E dequeue() {

        E e = list.get(0);
        list.remove(0);
        return e;
    }


    /**
     * toString method for the Linked Queue
     * @return Formatted string of the Linked Queue
     */
    @Override
    public String toString(){
        return list.toString();
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> ll = new LinkedQueue<>();
        ll.enqueue(1);
        ll.enqueue(2);
        ll.enqueue(3);
        ll.enqueue(4);
        System.out.println(ll);
        System.out.println(ll.dequeue());
        System.out.println(ll);
        System.out.println(ll.first());
    }
}
