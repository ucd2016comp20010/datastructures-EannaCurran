package projectCode20280.Practical1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularlyLinkedListTest {

	@Test
	void testSize() {
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		assertEquals(0, cll.size());
		cll.addFirst(0);
		assertEquals(1, cll.size());
	}

	@Test
	void testIsEmpty() {
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		assertEquals(true, cll.isEmpty());
		cll.addFirst(0);
		assertEquals(false, cll.isEmpty());
		cll.removeFirst();
		assertEquals(true, cll.isEmpty());
	}

	@Test
	void testRemoveLast() {
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		cll.addFirst(-1);
		cll.addFirst(-2);
		assertEquals(-1, cll.removeLast());
	}

	@Test
	void testGet() {
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		for(int i = 0; i < 5; ++i) cll.addLast(i);
			assertEquals(1, cll.get(1));
	}

	@Test
	void testRemove() {
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		for(int i = 0; i < 5; ++i) cll.addLast(i);
		cll.remove(1);
		assertEquals("[0, 2, 3, 4]", cll.toString());
	}

	@Test
	void testAdd() {
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		for(int i = 0; i < 5; ++i) cll.addLast(i);
		cll.add(2, -1);
		assertEquals("[0, 1, -1, 2, 3, 4]", cll.toString());
	}

	@Test
	void testToString() {
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		for(int i = 0; i < 5; ++i) cll.addLast(i);
		assertEquals("[0, 1, 2, 3, 4]", cll.toString());
	}

	@Test
	void testIterator() {
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		for(int i = 0; i < 5; ++i) cll.addLast(i);
		ArrayList<Integer> buf = new ArrayList<>();
		for(Integer i : cll) {
			buf.add(i);
		}
		assertEquals("[0, 1, 2, 3, 4]", buf.toString());
	}


	@Test
	void testRemoveFirst(){
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		for(int i = 0; i < 5; ++i) cll.addLast(i);
		assertEquals(0, cll.removeFirst());
	}

	@Test
	void testAddFirst(){
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		for(int i = 0; i < 5; ++i) cll.addLast(i);
		cll.addFirst(1);
		assertEquals("[1, 0, 1, 2, 3, 4]", cll.toString());
	}

	@Test
	void testAddLast(){
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		for(int i = 0; i < 5; ++i) cll.addLast(i);
		cll.addLast(1);
		assertEquals("[0, 1, 2, 3, 4, 1]", cll.toString());
	}

	@Test
	void testRotate(){
		CircularlyLinkedList<Integer> cll = new CircularlyLinkedList<>();
		for(int i = 0; i < 5; ++i) cll.addLast(i);
		cll.rotate();
		assertEquals("[1, 2, 3, 4, 0]", cll.toString());
	}
}
