// Practical 2- Question 6 - Linked Deque - Eanna Curran

package projectCode20280.Practical2;

import projectCode20280.Practical1.DoublyLinkedList;

public class LinkedDeque<E> implements Deque<E> {


	// Declaring variables
	private final DoublyLinkedList<E> list = new DoublyLinkedList<>();


	/**
	 * Empty constructor for Linked Deque
	 */
	public LinkedDeque(){ }


	/**
	 * Method to get the size of the Linked Deque
	 * @return Current size of the Linked Deque
	 */
	@Override
	public int size() { return list.size(); }


	/**
	 * Method to check if the Linked Deque is empty
	 * @return Boolean result of the check
	 */
	@Override
	public boolean isEmpty() { return list.isEmpty(); }


	/**
	 * Method to get the first element of the Linked Deque
	 * @return The first element
	 */
	@Override
	public E first() { return list.get(0); }


	/**
	 * Method to get the last element of the Linked Deque
	 * @return The last element
	 */
	@Override
	public E last() { return list.get(list.size()-1); }


	/**
	 * Method to add a new element to the start of the Linked Deque
	 * @param e Element to be added
	 */
	@Override
	public void addFirst(E e) { list.addFirst(e); }


	/**
	 * Method to add a new element to the end of the Linked Deque
	 * @param e Element to be added
	 */
	@Override
	public void addLast(E e) { list.addLast(e); }


	/**
	 * Method to remove the first element of the Linked Deque
	 * @return Element that was removed
	 */
	@Override
	public E removeFirst() { return list.removeFirst(); }


	/**
	 * Method to remove the last element of the Linked Deque
	 * @return Element that was removed
	 */
	@Override
	public E removeLast() { return list.removeLast(); }


	/**
	 * toString method for the Linked Deque
	 * @return String of the Linked Deque
	 */
	public String toString(){ return list.toString(); }

}
