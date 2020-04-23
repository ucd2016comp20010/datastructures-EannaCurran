// Practical 1 - Question 1 - Singly Linked List - Eanna Curran

package projectCode20280.Practical1;
import java.util.Iterator;


/**
 * Implementation of Singly Linked List
 * @param <E> Type of data held in the list
 */
public class SinglyLinkedList<E> implements List<E> {

	// Declaring variables for head node and size
	protected Node<E> head = null;
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
		private final E element;
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
		 * hasNext method in Iterator
		 * @return Boolean answer to if curr is pointing to no Node
		 */
		public boolean hasNext() { return curr != null; }


		/**
		 * next method in Iterator
		 * @return Next node in the Iterator
		 */
		public E next() {

			// Gets the element in the current Node and moves to the next Node
			var res = (E)curr.getElement();
			curr = curr.getNext();

			// Returns the value in the current Node
			return res;
		}
	}


	/**
	 * toString method
	 * @return String of values in the Singly Linked List
	 */
	public String toString() {

		// Declaring empty String
		StringBuilder retStr = new StringBuilder();

		retStr.append("[");

		// Accessing head Node
		Node<E> current = head;

		// Loops through each Node and appends their element to the String
		while(current.getNext() != null){

			retStr.append(current.getElement());
			retStr.append(", ");
			current = current.getNext();
		}
		retStr.append(current.getElement());
		retStr.append("]");

		// Returns the modified String
		return retStr.toString();
	}


	/**
	 * Method to check if the Linked List is empty
	 * @return Boolean result for checking if the size is 0
	 */
	public boolean isEmpty() { return size==0; }


	/**
	 *  Method to get the element of the Node in a given position
	 * @param i: Index of Node to get element
	 * @return Element of requested Node
	 */
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
	 * Method to add Node into Linked List at a given position
	 * @param i: Index to add the node at
	 * @param e: Node to add
	 */
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
	 * Method to remove the Node with index i
	 * @param i: index of Node to be remove
	 */
	public E remove(int i) {

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

		return currentNode.getElement();

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
			if((temp.next != null) && (Integer.parseInt(temp.next.element.toString()) < Integer.parseInt(smallest.element.toString()))){

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
	 * Method to create new ListIterator
	 */
	public Iterator<E> iterator() { return new ListIterator(); }


	/**
	 * Method to return the size of the Linked List
	 * @return The size of Linked List
	 */
	public int size() { return size; }


	/**
	 * Method to remove the first Node
	 */
	public E removeFirst() {
		E first = head.element;
		if(head.getNext() == null){
			head = null;
		}

		else {
			head = head.getNext();
		}
		size--;
		return first;
	}


	/**
	 * Method to remove the last Node
	 */
	public E removeLast() {
		Node<E> currentNode = head;
		Node<E> removeNode = null;

		// Returns null if the last element is null
		if(head == null){
			return null;
		}

		// Removes the last node if that node is the head
		if (currentNode.getNext() == null) {
			head = null;
		}

		else{

			// Loops until the Node before the last node is reached
			while( currentNode.getNext().getNext() != null){
				currentNode = currentNode.getNext();
			}

			// Removes the next Node and sets the current Node as the tail
			removeNode = currentNode.getNext();
			currentNode.setNext(null);
		}

		size--;
		return removeNode.getElement();
	}


	/**
	 * Method to return the value of the last Node
	 * @return Value of the last node
	 */
	public E last(){ return this.get(this.size() - 1); }


	/**
	 * Method to add a value to the front of the Linked List
	 * @param e Value to be added
	 */
	public void addFirst(E e) {
		head = new Node<>(e, head);
		size++;
	}


	/**
	 * Method to return the first element in the List
	 * @return Element within head
	 */
	public E first(){
		if(head != null) {
			return head.getElement();
		}
		return null;
	}


	/**
	 * Method to add a value to the end of the Linked List
	 * @param e Element to be added to the end of the Linked List
	 */
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
		}

		size++;

	}


	public static void main(String[] args) {

		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		ll.addFirst(1);
		ll.addFirst(2);

		ll.addFirst(0);
		ll.addFirst(1);
		ll.addFirst(3);
		ll.addFirst(4);
		ll.addFirst(5);
		ll.add(3,2);
		System.out.println(ll);

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
		System.out.println(ll.first());
		System.out.println(ll.get(1));

	}
}
