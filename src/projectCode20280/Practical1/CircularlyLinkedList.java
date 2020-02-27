package projectCode20280.Practical1;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

	private Node<E> tail = null;
	private int size = 0;

	private class Node<e> {
		private e element;
		private Node<e> next;
		public Node(e e, Node<e> n){
			element = e;
			next = n;
		}
		public e getElement(){
			return element;
		}
		public Node<e> getNext(){
			return next;
		}
		public void setNext(Node<e> n){
			next = n;
		}
	}

	public CircularlyLinkedList(){}

	private class ListIterator implements Iterator<E>{

		Node<E> curr;

		public ListIterator(){
			curr = tail.getNext();
		}

		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public E next() {
			E res = curr.getElement();
			curr = curr.getNext();
			return res;
		}
	}

	@Override
	public int size() { return size; }

	@Override
	public boolean isEmpty() { return size==0; }

	@Override
	public E get(int i) {
		Node<E> currentNode = tail.getNext();
		if(i > size){
			return null;
		}
		for(int count = 0; count < size; count++){
			if(i == count){
				return currentNode.getElement();
			}
			else{
				currentNode = currentNode.getNext();
			}
		}
		return null;

	}

	@Override
	public void add(int i, E e) {
		Node<E> newNode = new Node<>(e, null);
		Node<E> currentNode = tail.getNext();
		i--;
		if(i > size){
			System.out.println("Index does not exist");
		}
		else{
			for(int count = 0; count < size; count++){
				if(i == count){
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

	@Override
	public void remove(int i) {
		Node<E> currentNode = tail.getNext();
		if(i < size) {
			for (int count = 0; count < size; count++) {
				if (i == count) {
					currentNode.setNext(currentNode.getNext());

					size--;
					break;
				} else {
					currentNode = currentNode.getNext();
				}
			}
		}
	}

	@Override
	public void removeFirst() {
		if(size != 0) {
			Node<E> node = tail.getNext();
			if (node == tail) tail = null;
			else tail.setNext(node.getNext());
			size--;
		}
	}

	@Override
	public void removeLast() {
		if(size != 0) {
			Node<E> node = tail.getNext();
			if (node == tail) tail = null;
			else {
				while (node.getNext() != tail) {
					node = node.getNext();
				}
				node.setNext(tail);
			}
			size--;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	@Override
	public void addFirst(E e) {
		if(size == 0){
			tail = new Node<>(e,null);
			tail.setNext(tail);
		}else{
			Node<E> newOne = new Node<>(e, tail.getNext());
			tail.setNext(newOne);

		}
		size++;

	}

	@Override
	public void addLast(E e) {
		addFirst(e);
		tail = tail.getNext();

	}

	public void rotate() {
			Node<E> prev = null;
			Node<E> curr = tail;
			Node<E> next;

			while(curr != null){
				next = curr.getNext();
				curr.setNext(prev);
				prev = curr;
				curr = next;
			}
			tail = prev;
	}

	@Override
	public String toString(){
		String retStr = "";

		Node<E> current = tail.getNext();
		for(int i = 0; i < size; i++) {
			retStr = retStr + current.getElement();
			retStr = retStr + " ";
			current = current.getNext();
		}


			return retStr;
	}
	
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
		for(int i = 0; i < 10; i++) {
			ll.addFirst(i);
		}
		System.out.println(ll);
		ll.removeFirst();
		System.out.println(ll);
		ll.removeLast();
		System.out.println(ll);
		ll.rotate();
		System.out.println(ll);

		ll.removeFirst();
		ll.rotate();

		ll.removeLast();
		ll.rotate();

		//for (Integer e : ll) {

		//	System.out.println("value: " + e);
		//}

	}
}
