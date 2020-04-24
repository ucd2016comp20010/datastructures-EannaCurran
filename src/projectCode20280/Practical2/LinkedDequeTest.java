package projectCode20280.Practical2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LinkedDequeTest {

	@Test
	void testSize(){
		LinkedDeque<Integer> ld = new LinkedDeque<>();
		assertEquals(0, ld.size());
		ld.addFirst(1);
		assertEquals(1, ld.size());
		ld.removeFirst();
		assertEquals(0, ld.size());
	}

	@Test
	void testIsEmpty(){
		LinkedDeque<Integer> ld = new LinkedDeque<>();
		assertTrue(ld.isEmpty());
		ld.addFirst(1);
		assertFalse(ld.isEmpty());
		ld.removeFirst();
		assertTrue(ld.isEmpty());
	}

	@Test
	void testFirst(){
		LinkedDeque<Integer> ld = new LinkedDeque<>();
		ld.addFirst(1);
		assertEquals(1, ld.first());
		ld.addFirst(2);
		assertEquals(2, ld.first());
	}

	@Test
	void testAddFirst(){
		LinkedDeque<Integer> ld = new LinkedDeque<>();
		ld.addFirst(3);
		ld.addFirst(2);
		ld.addFirst(1);
		assertEquals("[1, 2, 3]", ld.toString());
	}

	@Test
	void testRemoveFirst(){
		LinkedDeque<Integer> ld = new LinkedDeque<>();
		ld.addFirst(3);
		ld.addFirst(2);
		ld.addFirst(1);
		ld.removeFirst();
		assertEquals("[2, 3]", ld.toString());
	}

	@Test
	void testLast(){
		LinkedDeque<Integer> ld = new LinkedDeque<>();
		ld.addLast(1);
		assertEquals(1, ld.last());
		ld.addLast(2);
		assertEquals(2, ld.last());
	}

	@Test
	void testAddLast(){
		LinkedDeque<Integer> ld = new LinkedDeque<>();
		ld.addLast(1);
		ld.addLast(2);
		ld.addLast(3);
		assertEquals("[1, 2, 3]", ld.toString());
	}

	@Test
	void testRemoveLast(){
		LinkedDeque<Integer> ld = new LinkedDeque<>();
		ld.addLast(1);
		ld.addLast(2);
		ld.addLast(3);
		ld.removeLast();
		assertEquals("[1, 2]", ld.toString());
	}

	@Test
	void testToString(){
		LinkedDeque<Integer> ld = new LinkedDeque<>();
		ld.addFirst(3);
		ld.addFirst(2);
		ld.addFirst(1);
		assertEquals("[1, 2, 3]", ld.toString());
	}
}
