package projectCode20280;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

	private Node<E> tail = null;
	private int size = 0;

	private class Node<E> {
		private E element;
		private Node<E> next;
		public Node(E e, Node<E> n){
			element = e;
			next = n;
		}
		public E getElement(){
			return element;
		}
		public Node<E> getNext(){
			return next;
		}
		public void setNext(Node<E> n){
			next = n;
		}
	}

	public CircularlyLinkedList(){}

	private class ListIterator implements Iterator<E>{
		Node curr;

		public ListIterator(){
			curr = tail.getNext();
		}

		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public E next() {
			E res = (E)curr.getElement();
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
		Node<E> newNode = new Node<E>(e, null);
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
	public E remove(int i) {
		Node<E> currentNode = tail.getNext();
		if(i > size){
			return null;
		}
		for(int count = 0; count < size; count++){
			if(i == count){
				currentNode.setNext(currentNode.getNext());
				break;
			}
			else{
				currentNode = currentNode.getNext();
			}
		}
		size--;
		return null;
	}

	@Override
	public E removeFirst() {
		if(isEmpty()) return null;
		Node<E> node = tail.getNext();
		if(node == tail) tail = null;
		else tail.setNext(node.getNext());
		size--;
		return node.getElement();
	}

	@Override
	public E removeLast() {
		if(isEmpty()) return null;
		Node<E> node = tail.getNext();
		if(node == tail) tail = null;
		else {
			while( node.getNext() != tail){
				node = node.getNext();
			}
			node.setNext(tail);
		}
		size--;
		return null;

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
			retStr += current.getElement();
			retStr += " ";
			current = current.getNext();
		}


			return retStr;
	}
	
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
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
