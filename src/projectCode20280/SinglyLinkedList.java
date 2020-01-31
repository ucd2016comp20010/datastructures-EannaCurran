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
		Node curr;


		public ListIterator(){
			curr = head;
		}

		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public E next() {
			E res = (E) curr.getElement();
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
		Node<E> newNode = new Node<E>(e, null);
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
		head = new Node<E>(e, head);
		size++;
	}

	@Override
	public void addLast(E e) {
		Node<E> newLast = new Node<E>(e, null);
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

	public static void main(String[] args) {
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		SinglyLinkedList<String> sll = new SinglyLinkedList<String>();
		for (String s : alphabet) {
			sll.addFirst(s);
			sll.addLast(s);
		}
		System.out.println(sll);

		sll.removeFirst();
		System.out.println(sll);
		
		sll.removeLast();
		System.out.println(sll);

		sll.remove(2);
		System.out.println(sll);


		System.out.println(sll.get(2));
		sll.add(1, "A");
		System.out.println(sll);

		for(String s: sll){
			System.out.println(s);

		}
	}
}
