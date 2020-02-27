package projectCode20280.Practical2;

public class ArrayStack<E> implements Stack<E> {

    public static final int CAPACITY = 100;
    private E[] data;
    private int t = -1;

    public ArrayStack(){ this(CAPACITY);}

    public ArrayStack(int capacity){
        data =  (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return t + 1;
    }

    @Override
    public boolean isEmpty() {
        return t == -1;
    }

    @Override
    public void push(E e) throws IllegalStateException {
        if(size() == data.length){
            throw new IllegalStateException("Stack is full");
        }
        data[++t] = e;
    }

    @Override
    public E top() {
        if(isEmpty()){
            return null;
        }
        return data[t];
    }

    @Override
    public E pop() {
        if(isEmpty()){
            return null;
        }
        E result = data[t];
        data[t] = null;
        return result;
    }

    @Override
    public String toString(){
        String answer = "";
        for(E e: this.data){
            if(e != null) {
                answer += e + " ";
            }
            else{
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> ll = new ArrayStack<>();
        ll.push(1);
        ll.push(2);
        ll.push(3);
        System.out.println(ll);
        System.out.println(ll.top());
        ll.pop();
        System.out.println(ll);

    }
}
