package projectCode20280.Practical1;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {
	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;

	private class Node<E> {
		private E element;
		private Node<E> previous;
		private Node<E> next;

		public Node(E e, Node<E> p, Node<E> n){
			element = e;
			next = n;
			previous = p;
		}

		public void setNext(Node<E> n){
			next = n;
		}

		public void setPrevious(Node<E> p){
			previous = p;
		}

		public Node<E> getNext(){
			return next;
		}

		public Node<E> getPrevious(){
			return previous;
		}

		public E getElement(){
			return element;
		}
	}

	public DoublyLinkedList(){
		header = new Node<>(null, null, null);
		trailer = new Node<>( null, header, null);
		header.setNext(trailer);
	}

	private class ListIterator implements Iterator<E>{
		Node curr;

		public ListIterator(){
			curr = header;
		}

		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public E next() {
			var res = (E)curr.getElement();
			curr = (Node)curr.getNext();
			return res;
		}
	}

	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newNode = new Node<>(e, predecessor, successor);
		predecessor.setNext(newNode);
		successor.setPrevious(newNode);
		size++;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E get(int i) {
		Node<E> currentNode = header;
		if(i > size){
			return null;
		}
		for(int count = 0; count < size; count++){
			if(i == count){
				return currentNode.getNext().getElement();
			}
			else{
				currentNode = currentNode.getNext();
			}
		}
		return null;
	}


	@Override
	public void add(int i, E e) {
		Node<E> newNode = new Node<>(e, null, null);
		Node<E> currentNode = header;
		i--;
		if (i > size) {
			System.out.println("Index does not exist");
		} else {
			for (int count = 0; count < size; count++) {
				if (i == count) {
					newNode.setNext(currentNode.getNext());
					currentNode.setNext(newNode);
					break;
				}
				else{
					currentNode = currentNode.getNext();
				}

			}
		}
		size++;
	}

	public E removeNode(Node<E> e) {
		Node<E> previous = e.getPrevious();
		Node<E> next = e.getNext();
		previous.setNext(next);
		next.setPrevious(previous);
		size--;
		return e.getElement();
	}

	@Override
	public void remove(int i){
		Node<E> currentNode = header;
		if(i > size) {
			for (int j = 0; j < i + 1; j++) {
				if (j == i) {
					currentNode.setNext(currentNode.getNext());
					size--;
					break;
				} else {
					currentNode = currentNode.getNext();
				}
			}
		}

	}

	public E removeElement(E n){
		Node<E> currentNode = header;
		for(int j = 0; j < size; j++){
			if(n == currentNode.getElement()){
				removeNode(currentNode);
			}
			else{
				currentNode = currentNode.getNext();
			}
		}
		return null;
	}



	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}



	@Override
	public void removeFirst() {
		if(size != 0) {

			removeNode(header.getNext());
		}
	}

	@Override
	public void removeLast() {
		if(size != 0) {

			removeNode(trailer.getPrevious());
		}
	}
	

	@Override
	public void addFirst(E e) {
		addBetween(e, header, header.getNext());
	}

	@Override
	public void addLast(E e) {
		addBetween(e, trailer.getPrevious(), trailer);
	}

	@Override
	public String toString(){
		String retStr = "";

		Node<E> current = header.getNext();
		for(int i = 0; i < size; i++){
			retStr = retStr + current.getElement();
			retStr = retStr + " ";
			current = current.getNext();

		}



		return retStr;
	}
	
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
		System.out.println(ll);

		ll.addFirst(-100);
		ll.addFirst(+100);
		System.out.println(ll);

		ll.removeFirst();
		ll.removeLast();

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
