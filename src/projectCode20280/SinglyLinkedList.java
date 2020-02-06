package projectCode20280;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;

	public SinglyLinkedList(){

	}

	public class Node<E> {
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
		public void setNext(Node <E> n){
			next = n;
		}
	}

	private class ListIterator implements Iterator<E>{
		Node<E> curr;

		public ListIterator(){
			curr = head;
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
	public String toString(){
		String retStr = "";

		Node<E> current = head;
		while(current != null){
			retStr += current.getElement();
			retStr += " ";
			current = current.getNext();

		}

		return retStr;
	}


	
	@Override
	public boolean isEmpty() {
		if(size==0){
			return true;
		}
		return false;
	}

	@Override
	public E get(int i) {
		Node<E> currentNode = head;
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
		Node<E> currentNode = head;
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
		Node<E> currentNode = head;
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
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	@Override
	public int size() {
		return size;
	}	
	

	@Override
	public E removeFirst() {
		head = head.getNext();
		size--;
		return null;
	}

	@Override
	public E removeLast() {
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
		return null;
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
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();

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
