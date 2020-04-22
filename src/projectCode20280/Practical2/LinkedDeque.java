package projectCode20280.Practical2;

import projectCode20280.Practical1.SinglyLinkedList;

public class LinkedDeque<E> implements Deque<E> {

	private final SinglyLinkedList<E> list = new SinglyLinkedList<>();

	public LinkedDeque(){ }

	@Override
	public int size() { return list.size(); }

	@Override
	public boolean isEmpty() { return list.isEmpty(); }

	@Override
	public E first() { return list.get(0); }

	@Override
	public E last() { return list.get(list.size()-1); }

	@Override
	public void addFirst(E e) { list.addFirst(e); }

	@Override
	public void addLast(E e) { list.add(list.size()-1, e); }

	@Override
	public E removeFirst() { return list.removeFirst(); }

	@Override
	public E removeLast() { return list.removeLast(); }

}
