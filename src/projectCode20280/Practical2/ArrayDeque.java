package projectCode20280.Practical2;

public class ArrayDeque<E> implements Deque<E> {

    private E[] data;
    private int f = 0;
    private int size = 0;
    public static final int CAPACITY = 100;

    public ArrayDeque(int capacity){
        data = (E[]) new Object[capacity];
    }
    public ArrayDeque(){ this(CAPACITY);}

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E first() {
        return data[f];
    }

    @Override
    public E last() {
        return data[size];
    }

    @Override
    public void addFirst(E e) throws IllegalStateException{
        if (size + 1 >= 0) System.arraycopy(data, 0, data, 1, size + 1);
        data[0] = e;
        size++;
    }

    @Override
    public void addLast(E e) throws IllegalStateException{
        if(size == data.length) throw new IllegalStateException("Queue is full");
        int avail = (f +size)%data.length;
        data[avail] = e;
        size++;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()) return null;
        E answer = data[f];
        data[f] = null;
        f = (f + 1)%data.length;
        size--;
        return answer;
    }

    @Override
    public E removeLast() {
        if(isEmpty()) return null;
        E answer = data[size];
        data[size] = null;
        size--;
        return answer;
    }

    @Override
    public String toString(){
        StringBuilder answer = new StringBuilder();
        for(int i = f; i < size+f; i++){
            answer.append(data[i]);
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> ll = new ArrayDeque<>();
        ll.addLast(1);
        ll.addLast(2);
        ll.addFirst(3);
        System.out.println(ll);
        ll.removeFirst();
        ll.removeLast();
        System.out.println(ll);
    }
}
