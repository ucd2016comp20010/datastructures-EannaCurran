package projectCode20280.Practical4;


import projectCode20280.Practical1.SinglyLinkedList;

public class SinglyLinkedListRecursion<E> extends SinglyLinkedList<E>{

	public void reverse(){
		if(head != null){
			head = recursiveReverse(null, head);
		}
	}

	private Node<E> recursiveReverse(Node<E> prev, Node<E> curr){

		Node<E> next = curr.getNext();
		curr.setNext(prev);
		if(next == null){
			return curr;
		}
		return recursiveReverse(curr, next);

	}

	public SinglyLinkedListRecursion<E> recursiveCopy() {

		if (head == null){
			return null;
		}

		SinglyLinkedListRecursion<E> copy = new SinglyLinkedListRecursion<>();

		copy.head = head;
		return copy;
	}


	public static void main(String[] args) {
		SinglyLinkedListRecursion<Integer> list = new SinglyLinkedListRecursion<>();
		list.addFirst(1);
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(4);
		list.addFirst(5);
		System.out.println(list);
		SinglyLinkedListRecursion<Integer> list2 = list.recursiveCopy();
		System.out.println(list2);
	}


}
