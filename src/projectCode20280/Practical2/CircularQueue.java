// Practical 2- Question 3 - Circular Queue - Eanna Curran

package projectCode20280.Practical2;

import projectCode20280.Practical1.CircularlyLinkedList;

public class CircularQueue<E> implements Queue<E> {

	// Declaring variables
	private final CircularlyLinkedList<E> queue = new CircularlyLinkedList<>();
	int front, back;

	/**
	 * Constructor for Circular Queue
	 */
	public CircularQueue(){
		front = -1;
		back = 0;
	}


	/**
	 * Size method for Circular Queue
	 * @return the current size of the  Circular Queue
	 */
	@Override
	public int size() { return queue.size(); }


	/**
	 * isEmpty method for Circular Queue
	 * @return Boolean check if the Circular Queue is empty
	 */
	@Override
	public boolean isEmpty() { return queue.isEmpty(); }


	/**
	 * Getter method for the first element in the Circular Queue
	 * @return First element
	 */
	@Override
	public E first() { return queue.get(front); }


	/**
	 * Getter method for the last element in the Circular Queue
	 * @return Last element
	 */
	public E back(){ return queue.get(back); }


	/**
	 * toString method for the Circular Queue
	 * @return Formatted string of the Circular Queue
	 */
	public String toString(){ return queue.toString(); }


	/**
	 * Method to rotate the Circular Queue
	 */
	public void rotate(){ queue.rotate(); }


	/**
	 * Method to add a new element to the end of the Circular Queue
	 * @param e Element to be added
	 */
	@Override
	public void enqueue(E e) {

		// Adds the element to the end of the queue
		queue.addLast(e);

		// Updates values for back and front
		if(front == -1) {
			front = back;
		}

		back = (back+1)%queue.size();
	}


	/**
	 * Method to remove the element at the front of the Circular Queue
	 * @return Element that was removed
	 */
	@Override
	public E dequeue() {

		// Check if attempt to remove from an empty queue
		if (isEmpty()) {
			return null;
		}

		// Gets and removes the element from the front
		E val = queue.get(front);
		queue.remove(front);

		// Updates the front value
		front = (front+1)% queue.size();

		if (front == back) {
			front = -1;
		}

		return val;
	}
}
