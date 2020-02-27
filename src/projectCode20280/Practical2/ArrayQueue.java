package projectCode20280.Practical2;

public class ArrayQueue<E> implements Queue<E> {

    private E[] data;
    private int f = 0;
    private int size = 0;
    public static final int CAPACITY = 100;

    public ArrayQueue(int capacity){
        data = (E[]) new Object[capacity];
    }
    public ArrayQueue(){ this(CAPACITY);}

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) throws IllegalStateException{
        if(size == data.length) throw new IllegalStateException("Queue is full");
        int avail = (f +size)%data.length;
        data[avail] = e;
        size++;
    }

    @Override
    public E first() {
        if(isEmpty()) return null;
        return data[f];
    }

    @Override
    public E dequeue() {
        if(isEmpty()) return null;
        E answer = data[f];
        data[f] = null;
        f = (f + 1)%data.length;
        size--;
        return answer;
    }

    @Override
    public String toString(){
        String answer = "";
        for(int i = f; i < size+f; i++){
            answer = answer + data[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> ll = new ArrayQueue<>();
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
