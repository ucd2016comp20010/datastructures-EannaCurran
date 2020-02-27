// Practical 1 - Question 1 - Singly Linked List

package projectCode20280.Practical1;
import java.util.Iterator;


/**
 * Implementation of Singly Linked List
 * @param <E> Type of data held in the list
 */
public class SinglyLinkedList<E> implements List<E> {

	// Declaring variables for head node, tail node and size
	protected Node<E> head = null;
	protected Node<E> tail = null;
	protected int size = 0;


	/**
	 * Empty constructor for Singly Linked List
	 */
	public SinglyLinkedList() { }


	/**
	 * Implementation of Node
	 * @param <E>: Data type of the Node
	 */
	public static class Node<E> {

		// Declaring variables
		private E element;
		private Node<E> next;


		/**
		 * Constructor forNode
		 * @param e: Value to be held in the Node
		 * @param n: Next Node in the list
		 */
		public Node(E e, Node<E> n) {

			element = e;
			next = n;
		}


		/**
		 * Method to get the element of the Node
		 * @return Value of element
		 */
		public E getElement() { return element; }


		/**
		 * Method to get the next Node
		 * @return Next Node
		 */
		public Node<E> getNext() { return next; }


		/**
		 * Method to set a new next Node
		 * @param n: Next Node to be set
		 */
		public void setNext(Node <E> n) { next = n; }
	}


	/**
	 * Implementation of an Iterator for the Singly Linked List
	 */
	private class ListIterator implements Iterator<E>{

		// Declaring to hold the current Node
		Node<E> curr;


		/**
		 * Constructor for ListIterator
		 */
		public ListIterator(){ curr = head; }


		/**
		 * Overriding hasNext method in Iterator
		 * @return Boolean answer to if curr is pointing to no Node
		 */
		@Override
		public boolean hasNext() { return curr != null; }


		/**
		 * Overriding next method in Iterator
		 * @return Next node in the Iterator
		 */
		@Override
		public E next() {

			// Gets the element in the current Node and moves to the next Node
			var res = (E)curr.getElement();
			curr = curr.getNext();

			// Returns the value in the current Node
			return res;
		}
	}


	/**
	 * Overriding the toString
	 * @return String of values in the Singly Linked List
	 */
	@Override
	public String toString() {

		// Declaring empty String
		String retStr = "";

		// Accessing head Node
		Node<E> current = head;

		// Loops through each Node and appends their element to the String
		while(current != null){

			retStr = retStr + current.getElement();
			retStr = retStr + " ";
			current = current.getNext();
		}

		// Returns the modified String
		return retStr;
	}


	/**
	 * Overriding the isEmpty method
	 * @return Boolean result for checking if the size is 0
	 */
	@Override
	public boolean isEmpty() { return size==0; }


	/**
	 * Overriding method to get the element of the Node in a given position
	 * @param i: Index of Node to get element
	 * @return Element of requested Node
	 */
	@Override
	public E get(int i) {

		// Declares variable to hold current Node
		Node<E> currentNode = head;

		// If the index is outside the range of the List, null is returned
		if(i > size) {
			return null;
		}

		// Loops through each Node until the Node of index i is reached, the it's element is returned
		for(int count = 0; count < size; count++) {

			if(i == count) {
				return currentNode.getElement();
			}

			else {
				currentNode = currentNode.getNext();
			}
		}

		return currentNode.getElement();
	}


	/**
	 * Overriding method to add Node into Linked List at a given position
	 * @param i: Index to add the node at
	 * @param e: Node to add
	 */
	@Override
	public void add(int i, E e) {

		// Creates new Node and current Node
		Node<E> newNode = new Node<>(e, null);
		Node<E> currentNode = head;

		// If the index is outside the range of the List, the user is told
		if(i > size) {

			System.out.println("Index does not exist");
		}


		else {

			// Loops through each Node in the Linked List
			for(int count = 0; count < size; count++) {

				// If the current index is equal to i, the new Node is added to the Linked List
				if(i == count) {

					newNode.setNext(currentNode.getNext());
					currentNode.setNext(newNode);
					break;
				}

				else {
					currentNode = currentNode.getNext();
				}
			}

			// Increases the size of the Linked List
			size++;
		}
	}


	/**
	 * Overriding the remove method
	 * @param i: index of Node to be remove
	 */
	@Override
	public void remove(int i) {

		// Declaring variable to hold the current Node
		Node<E> currentNode = head;

		// If the index is outside the range of the List, the user is told
		if(i > size) {
			System.out.println("Index does not exist");
		}

		else {

			// Loops through each Node in the Linked List
			for (int count = 0; count < size; count++) {

				// If the current index is equal to i, the Node is removed from the Linked List
				if (i == count) {
					currentNode.setNext(currentNode.getNext());
					break;
				}

				else {

					currentNode = currentNode.getNext();
				}
			}

			// Decreases the size of the Linked List
			size--;
		}
	}


	/**
	 * Overriding method to create new ListIterator* @return
	 */
	@Override
	public Iterator<E> iterator() { return new ListIterator(); }


	/**
	 * Overloading method to return the size of the Linked List
	 * @return The size of Linked List
	 */
	@Override
	public int size() { return size; }


	/**
	 * Overloading method to remove the first Node
	 */
	@Override
	public void removeFirst() {
		head = head.getNext();
		size--;
	}

	@Override
	public void removeLast() {
		Node<E> currentNode = head;
		if (currentNode == null) {
			head = tail;
		}
		else{
			while( currentNode.getNext().getNext() != null){
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(tail);
		}
		size--;
	}

	@Override
	public void addFirst(E e) {
		head = new Node<>(e, head);
		size++;
	}

	@Override
	public void addLast(E e) {
		Node<E> newLast = new Node<>(e, null);
		Node<E> currentNode = head;
		if (currentNode == null) {
			head = newLast;
		}
		else {
			while( currentNode.getNext() != null){
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(newLast);
		}

		size++;

	}

	public E first(){
		return head.getElement();
	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();

		ll.addFirst(0);
		ll.addFirst(1);
		ll.addFirst(3);
		ll.addFirst(4);
		ll.addFirst(5);
		ll.add(3, 2);

		System.out.println(ll);

		ll.addFirst(-100);
		ll.addLast(100) ;

		System.out.println(ll);

		ll.removeFirst();
		ll.removeLast();

		System.out.println(ll);

		ll.remove(2);

		System.out.println(ll);

		ll.removeFirst();

		System.out.println(ll);

		ll.removeLast();

		System.out.println(ll);

		ll.removeFirst();

		System.out.println(ll);

		ll.addFirst(9999) ;
		ll.addFirst(8888) ;
		ll.addFirst(7777) ;

		System.out.println(ll);
		System.out.println(ll.get(0));
		System.out.println(ll.get(1));
		System.out.println(ll.get(2));


	}
}
