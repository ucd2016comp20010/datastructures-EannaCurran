// Practical 1 - Question 1 - Singly Linked List - Eanna Curran

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

		// Declaring variable to hold the current Node
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
		StringBuilder retStr = new StringBuilder();

		// Accessing head Node
		Node<E> current = head;

		// Loops through each Node and appends their element to the String
		while(current != null){

			retStr.append(current.getElement());
			retStr.append(" ");
			current = current.getNext();
		}

		// Returns the modified String
		return retStr.toString();
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
		if(i >= size) {
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
		Node<E> previousNode = null;

		// If the index is outside the range of the List, the user is told
		if(i >= size) {

			System.out.println("Index does not exist");
		}

		else {

			// Loops through each Node in the Linked List
			for(int count = 0; count < size; count++) {

				// If the current index is equal to i, the new Node is added to the Linked List
				if(i == count) {
					if(currentNode == head) {

						newNode.setNext(currentNode);
						head = newNode;

					}
					else if(currentNode == tail){
						newNode.setNext(null);
						previousNode.setNext(newNode);
						tail = newNode;
					}
					else{

						newNode.setNext(currentNode);
						previousNode.setNext(newNode);
					}
					break;
				}

				else {
					previousNode = currentNode;
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
		Node<E> previousNode = null;

		// If the index is outside the range of the List, the user is told
		if(i >= size) {
			System.out.println("Index does not exist");
		}

		else {

			// Loops through each Node in the Linked List
			for (int count = 0; count <= size; count++) {

				// If the current index is equal to i, the Node is removed from the Linked List
				if (i == count) {
					if(currentNode == head) {

						head = head.getNext();
					}
					else if(currentNode == tail){
						previousNode.setNext(null);
						tail = previousNode;
					}
					else{
						previousNode.setNext(currentNode.getNext());
					}
					break;
				}

				else {
					previousNode = currentNode;
					currentNode = currentNode.getNext();
				}
			}

			// Decreases the size of the Linked List
			size--;
		}
	}


	/**
	 * Method to remove the smallest within the Linked ;ist, implemented to implement selection sort
	 * @return Node containing the smallest element
	 */
	public Node<E> removeMin(){

		// Declaring variables
		Node<E> temp = head;
		Node<E> smallest = head;
		Node<E> prev = null;

		// Loops through the entire Linked List
		while (temp != null){

			// Checks if the current node has a smaller value than the currently smallest node found
			if((temp.next != null) && ((Integer) temp.next.element < (Integer) smallest.element)){

				// Stores the new smallest node
				smallest = temp.next;
				prev = temp;
			}
			temp = temp.next;
		}

		// Removes the smallest node if it is not the head
		if(smallest != head){
			prev.next = smallest.next;
		}

		// Removes the smallest node if it is the head and assigns a new head
		else{
			temp = head.getNext();
			head = temp;
		}

		// Returns the smallest node
		size--;
		return smallest;
	}


	/**
	 * Overriding method to create new ListIterator
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


	/**
	 * Overloading method to remove the last Node
	 */
	@Override
	public void removeLast() {
		Node<E> currentNode = head;

		// Removes the last node if that node is the head
		if (currentNode == null) {
			head = tail;
		}

		else{

			// Loops until the Node before the last node is reached
			while( currentNode.getNext().getNext() != null){
				currentNode = currentNode.getNext();
			}

			// Removes the next Node and sets the current Node as the tail
			currentNode.setNext(null);
			tail = currentNode;
		}

		size--;
	}

	public E last(){ return tail.getElement(); }


	/**
	 * Overloading method to add a value to the front of the Linked List
	 * @param e Value to be added
	 */
	@Override
	public void addFirst(E e) {
		head = new Node<>(e, head);
		size++;
	}


	/**
	 * Method to return the first element in the List
	 * @return Element within head
	 */
	public E first(){ return head.getElement(); }


	/**
	 * Overloading method to add a value to the end of the Linked List
	 * @param e Element to be added to the end of the Linked List
	 */
	@Override
	public void addLast(E e) {

		Node<E> newLast = new Node<>(e, null);
		Node<E> currentNode = head;

		// Checks if the Linked List is empty, if so the new Node is added to the front of the list
		if(currentNode == null) {
			head = newLast;
		}

		else {

			// Loops through until the end of the list is found
			while( currentNode.getNext() != null){
				currentNode = currentNode.getNext();
			}

			// Adds the new Node
			currentNode.setNext(newLast);
			tail = newLast;
		}

		size++;

	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
		System.out.println(test);
		test.addFirst(1);
		System.out.println(test);
		test.addLast(2);
		System.out.println(test);

		System.out.println(test.last());
		test.add(0,4);
		test.add(2,5);
		System.out.println(test);
		test.remove(2);
		test.remove(0);
		System.out.println(test);
		System.out.println(test.get(0));
		System.out.println(test.first());
		System.out.println(test.last());
		test.removeFirst();
		test.removeLast();
		System.out.println(test + "\n");
		System.out.println(test.size());

	}
}
