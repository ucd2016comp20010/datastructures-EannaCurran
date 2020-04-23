// Practical 1 - Question 3 - Circularly Linked List - Eanna Curran

package projectCode20280.Practical1;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {


	// Declaring variables
	private Node<E> tail = null;
	private int size = 0;


	/**
	 * Empty constructor for Circularly Linked List
	 */
	public CircularlyLinkedList() { }


	/**
	 * Method to get the size of the Circularly Linked List
	 *
	 * @return Integer representing the size
	 */
	public int size() { return size; }


	/**
	 * Method to check if the Circularly Linked List contains any Nodes
	 *
	 * @return Boolean result
	 */
	public boolean isEmpty() { return size == 0; }


	/**
	 * Method to get the element of the Node with a given index
	 *
	 * @param i Index of Node
	 * @return Element of Node with index i
	 */
	public E get(int i) {

		// Node to store starting Node
		Node<E> currentNode = tail.getNext();

		// Check for is in the range of the Circularly Linked List
		if (i > size) {
			return null;
		}

		// Loops through until index i is reached and returns the element of Node with index i
		for (int count = 0; count < size; count++) {
			if (i == count) {
				return currentNode.getElement();
			} else {
				currentNode = currentNode.getNext();
			}
		}
		return null;

	}


	/**
	 * Method to add a new Node
	 *
	 * @param i Index of the new Node
	 * @param e Value of the new Node
	 */
	public void add(int i, E e) {

		// Creates a new Node and head Node
		Node<E> newNode = new Node<>(e, null);
		Node<E> currentNode = tail.getNext();

		// Checks that i is in the range of the Circularly Linked List
		if (i >= size) {
			System.out.println("Index does not exist");
		} else {

			// Loops through until index i is reached
			for (int count = 0; count < size; count++) {

				if (i-1 == count) {

					// Adds the new node
					newNode.setNext(currentNode.getNext());
					currentNode.setNext(newNode);

					size++;
					break;
				} else {
					currentNode = currentNode.getNext();
				}
			}
		}

	}


	/**
	 * Method to remove the Node with index i
	 *
	 * @param i : index of Node to be remove
	 * @return Removed Node
	 */
	public E remove(int i) {

		// Gets the Node at the head
		Node<E> currentNode = tail.getNext();
		Node<E> previousNode = tail;
		if (i < size) {

			// Loops until index i is reached
			for (int count = 0; count < size; count++) {

				// Removes the node of index i
				if (i == count) {

					previousNode.setNext(currentNode.getNext());
					size--;
					break;

				} else {
					previousNode = currentNode;
					currentNode = currentNode.getNext();
				}
			}
		}
		return currentNode.getElement();
	}


	/**
	 * Method to remove the first Node
	 * @return Removed Node
	 */
	public E removeFirst() {

		// Checks that there is a Node to remove
		if (size != 0) {

			// Gets tail node
			Node<E> node = tail.getNext();

			// Removes tail if it is the only Node
			if (node == tail) {
				tail = null;
			}

			// Removes the head Node
			else {
				tail.setNext(node.getNext());
				return node.getElement();
			}

			size--;
		}
		return null;
	}


	/**
	 * Method to remove the last Node
	 * @return Removed Node
	 */
	public E removeLast() {


		Node<E> removedNode = null;
		Node<E> node = null;
		// Checks that there is a Node to remove
		if (size != 0) {

			// GEts the head Node
			node = tail.getNext();

			// Removes the tail Node if it is the only Node
			if (node == tail) {
				tail = null;
			} else {

				// Gets to the Node before the current tail
				while (node.getNext() != tail) {
					node = node.getNext();
				}

				// Removes the tail Node
				removedNode = tail;
				node.setNext(tail.getNext());
				tail = node;
			}
			size--;
		}
		return removedNode.getElement();
	}


	/**
	 * Iterator constructor
	 *
	 * @return New List Iterator
	 */
	public Iterator<E> iterator() { return new ListIterator(); }


	/**
	 * Method to add a new Node to the start
	 *
	 * @param e Value of new Node
	 */
	public void addFirst(E e) {

		// Checks if the new Node will be the tail
		if (size == 0) {

			// Sets that new Node to the tail
			tail = new Node<>(e, null);
			tail.setNext(tail);
		} else {

			// Adds a new Node to the start
			Node<E> newNode = new Node<>(e, tail.getNext());
			tail.setNext(newNode);
		}
		size++;

	}


	/**
	 * Method to add a new Node to the end
	 *
	 * @param e Value of the
	 */
	public void addLast(E e) {
		addFirst(e);
		tail = tail.getNext();

	}


	/**
	 * Method to move the first Node to the end
	 */
	public void rotate() {
		if (tail != null) {
			tail = tail.getNext();
		}
	}


	/**
	 * toString method for Circularly Linked List
	 *
	 * @return String of Circularly Linked List
	 */
	public String toString() {
		StringBuilder retStr = new StringBuilder();

		retStr.append("[");
		Node<E> current = tail.getNext();
		for (int i = 0; i < size-1; i++) {
			retStr.append(current.getElement());
			retStr.append(", ");
			current = current.getNext();
		}

		retStr.append(current.getElement());
		retStr.append("]");

		return retStr.toString();
	}


	/**
	 * Iterator class for Circularly Linked List
	 */
	private class ListIterator implements Iterator<E> {


		// Declaring variables for Iterator
		Node<E> curr;
		int count = 0;


		/**
		 * Constructor for Iterator
		 */
		public ListIterator() { curr = tail.getNext(); }


		/**
		 * Check if the current Node points to another Node
		 *
		 * @return Boolean result
		 */
		public boolean hasNext() {
			return curr != null && count != size;
		}


		/**
		 * Method to get the next Node in the Circularly Linked List
		 *
		 * @return The Next Node
		 */
		public E next() {
			E res = curr.getElement();
			curr = curr.getNext();
			count++;
			return res;
		}
	}


	/**
	 * Implementation of Node
	 *
	 * @param <E> Data type held within the Node
	 */
	private static class Node<E> {

		// Declaring variables for the Node
		private final E element;
		private Node<E> next;


		/**
		 * Constructor for Node
		 *
		 * @param e Element for the Node
		 * @param n Pointer to next Node
		 */
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}


		/**
		 * Getter method for element
		 *
		 * @return element of Node
		 */
		public E getElement() { return element; }


		/**
		 * Getter method for next Node
		 *
		 * @return Pointer to next Node
		 */
		public Node<E> getNext() { return next; }


		/**
		 * Setter method for next Node
		 *
		 * @param n New Node to be set to next
		 */
		public void setNext(Node<E> n) { next = n; }
	}


	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
		for (int i = 0; i < 10; i++) {
			ll.addFirst(i);
		}
		System.out.println(ll);
		ll.removeFirst();
		System.out.println(ll);
		ll.removeLast();
		System.out.println(ll);
		ll.rotate();
		System.out.println(ll);
	}
}
