// Practical 1 - Question 2 - Double Linked List - Eanna Curran

package projectCode20280.Practical1;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {


	// Declaring variables for header node, trailer node and size
	private final Node<E> header;
	private final Node<E> trailer;
	private int size = 0;


	/**
	 * Implementation of Node
	 * @param <E>: Data type of the Node
	 */
	private static class Node<E> {


		// Declaring variables
		private final E element;
		private Node<E> previous;
		private Node<E> next;


		/**
		 * Constructor for Node
		 * @param e Value of the Node
		 * @param p Previous Node in the Linked List
		 * @param n Next Node in the Linked List
		 */
		public Node(E e, Node<E> p, Node<E> n){
			element = e;
			next = n;
			previous = p;
		}


		/**
		 * Setter method for next Node
		 * @param n new Node to be next
		 */
		public void setNext(Node<E> n){ next = n; }


		/**
		 * Setter method for previous Node
		 * @param p new Node to be previous
		 */
		public void setPrevious(Node<E> p){ previous = p; }


		/**
		 * Getter method for next Node
		 * @return the next Node
		 */
		public Node<E> getNext(){ return next; }


		/**
		 * Getter method for previous Node
		 * @return the previous Node
		 */
		public Node<E> getPrevious(){ return previous; }


		/**
		 * Getter method for the Nodes element
		 * @return value within Node
		 */
		public E getElement(){ return element; }
	}


	/**
	 * Constructor for Doubly Linked List
	 */
	public DoublyLinkedList(){
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}


	/**
	 * Implementation of an Iterator for the Doubly Linked List
	 */
	private class ListIterator implements Iterator<E>{


		// Declaring variable to hold the current Node
		Node<E> curr;


		/**
		 * Constructor for Iterator
		 */
		public ListIterator(){
			curr = header;
		}

		/**
		 * hasNext method for Iterator
		 * @return If the current Node has a next element
		 */
		public boolean hasNext() {
			return curr != null;
		}


		/**
		 * next method for Iterator
		 * @return the next Node in the iterator
		 */
		public E next() {
			var res = curr.getElement();
			curr = curr.getNext();
			return res;
		}
	}


	/**
	 * Method to add an element between two Nodes
	 * @param e Element to add
	 * @param predecessor Node before new Node to be placed
	 * @param successor Node after new Node to be placed
	 */
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {

		Node<E> newNode = new Node<>(e, predecessor, successor);
		predecessor.setNext(newNode);
		successor.setPrevious(newNode);
		size++;
	}


	/**
	 * Method to return the size of the Linked List
	 * @return size of the Linked List
	 */
	public int size() { return size; }


	/**
	 * Method to check if the Linked List is empty
	 * @return Boolean result
	 */
	public boolean isEmpty() { return size == 0; }


	/**
	 * Method to return the element of Node with the ith index
	 * @param i index of Node
	 * @return Node with index i
	 */
	public E get(int i) {

		// Starts at the header of the Linked List
		Node<E> currentNode = header;

		// Check that i is within the range of the Linked List
		if(i > size){
			return null;
		}

		// Loops through the Linked List until the ith index is reached
		for(int count = 0; count < size; count++){

			// If the ith index is reached, its element is returned
			if(i == count){
				return currentNode.getNext().getElement();
			}

			else{
				currentNode = currentNode.getNext();
			}
		}

		// Returns null if i exists but has no value
		return null;
	}


	/**
	 * Method to add a new Node
	 * @param i Index of the new Node
	 * @param e Value of the new Node
	 */
	public void add(int i, E e) {

		// Creates a new node and gets the header node
		Node<E> newNode = new Node<>(e, null, null);
		Node<E> currentNode = header;

		// Checks that i is within the range of the Linked List
		if (i > size) {
			System.out.println("Index does not exist");
		} else {

			// Loops until index i is reached
			for (int count = 0; count < size; count++) {
				if (i == count) {

					// Adds the new Node to the Linked List
					newNode.setNext(currentNode.getNext());
					currentNode.setNext(newNode);
					newNode.setPrevious(currentNode);
					currentNode.getNext().setPrevious(newNode);
					size++;
					break;
				}
				else{
					currentNode = currentNode.getNext();
				}

			}
		}
	}


	/**
	 * Method to remove a Node from the Linked List
	 * @param e Node to be removed
	 * @return Element of removed Node
	 */
	public E removeNode(Node<E> e) {

		// Gets the nodes previous and next Node
		Node<E> previous = e.getPrevious();
		Node<E> next = e.getNext();

		// Passes over the Node and reduces the size of the Linked List
		previous.setNext(next);
		next.setPrevious(previous);
		size--;

		// Returns the element of Node e
		return e.getElement();
	}


	/**
	 * Method to remove the Node
	 * @param node  Node to be removed
	 * @return
	 */
	public E remove(Node<E> node){

		// Gets starting Node and Node to hold previous Node
		Node<E> previousNode = node.getPrevious();
		Node<E> nextNode = node.getNext();

		previousNode.setNext(nextNode);
		nextNode.setPrevious(previousNode);

		size--;
		return node.getElement();

	}


	/**
	 * Iterator method for the Doubly Linked List
	 * @return New instance of an iterator
	 */
	public Iterator<E> iterator() { return new ListIterator(); }


	/**
	 * Method to remove the first Node
	 */
	public E removeFirst() {
		if(size != 0) {
			removeNode(header.getNext());
		}
		return null;
	}


	/**
	 * Method to remove the last Node
	 * @return
	 */
	public E removeLast() {
		if (isEmpty()) {
			return null;
		}
		return remove(trailer.getPrevious());
	}


	/**
	 * Method to add a new Node to the start of the Doubly Linked List
	 * @param e Value of Node to be Added
	 */
	public void addFirst(E e) { addBetween(e, header, header.getNext()); }


	/**
	 * Method to add a new Node to the end of the Doubly Linked List
	 * @param e Value of Node to be added
	 */
	public void addLast(E e) { addBetween(e, trailer.getPrevious(), trailer); }

	/**
	 * Method to remove a node of index i from the Doubly Linked List
	 * @param i index of node to remove
	 * @return element of removed node
	 */
	@Override
	public E remove(int i) {

		Node<E> currentNode = header.getNext();
		if(i < size()) {
			for (int j = 0; j <= i; j++){
				if(j == i){
					currentNode.getPrevious().setNext(currentNode.getNext());
					currentNode.getNext().setPrevious(currentNode.getPrevious());
					return currentNode.getElement();
				}
				else{
					currentNode = currentNode.getNext();
				}
			}
		}
		return null;
	}

	/**
	 * toString method for DoublyLinkedList
	 * @return String containing the values within the Doubly Linked List
	 */
	public String toString() {

		// Declaring empty String
		StringBuilder retStr = new StringBuilder();

		// Accessing head Node
		Node<E> current = header.getNext();

		// Loops through each Node and appends their element to the String
		retStr.append("[");
		while(current.getNext().getNext() != null){

			retStr.append(current.getElement());
			retStr.append(", ");
			current = current.getNext();
		}
		retStr.append(current.getElement());

		// Returns the modified String
		retStr.append("]");
		return retStr.toString();
	}


	/**
	 * Method to get the first value in the Linked List
	 * @return Value in the first Node
	 */
	public E first(){ return header.getNext().getElement(); }


	/**
	 * Method to get the last value in the Linked List
	 * @return Value held within the last Node
	 */
	public E last(){ return trailer.getPrevious().getElement(); }


	public static void main(String[] args) {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		ll.addFirst(1);
		ll.addFirst(2);

		ll.addFirst(0);
		ll.addFirst(1);
		ll.addFirst(3);
		ll.addFirst(4);
		ll.addFirst(5);
		ll.add(3,2);

		ll.addFirst(-100);
		ll.addFirst(+100);
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

		ll.addFirst(9999);
		ll.addFirst(8888);
		ll.addFirst(7777);
		System.out.println(ll);
		System.out.println(ll.get(1));
	}

	
}
