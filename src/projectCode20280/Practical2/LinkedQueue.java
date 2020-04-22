package projectCode20280.Practical2;

import projectCode20280.Practical1.SinglyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

    private final SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public LinkedQueue(){ }
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E first() { return list.get(0); }

    @Override
    public E dequeue() {

        E e = list.get(0);
        list.remove(0);
        return e;
    }

    @Override
    public String toString(){
        return list.toString();
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> ll = new LinkedQueue<>();
        ll.enqueue(1);
        ll.enqueue(2);
        ll.enqueue(3);
        ll.enqueue(4);
        System.out.println(ll);
        System.out.println(ll.dequeue());
        System.out.println(ll);
        System.out.println(ll.first());
    }
}
