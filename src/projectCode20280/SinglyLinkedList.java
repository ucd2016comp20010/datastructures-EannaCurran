package projectCode20280;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;

	public SinglyLinkedList(){

	}

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
		public void setNext(Node <E> n){
			next = n;
		}
	}

	@Override
	public String toString(){
		String retStr = "Contents:\n";

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
		// TODO Auto-generated method stub
		return null;
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
		System.out.println(sll.toString());

		sll.removeFirst();
		System.out.println(sll.toString());
		
		sll.removeLast();
		System.out.println(sll.toString());

		sll.remove(2);
		System.out.println(sll.toString());


		System.out.println(sll.get(2));

	}
}
