package projectCode20280.Practical2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayQueueTest {

	@Test
	void testSize(){
		ArrayQueue<Integer> aq = new ArrayQueue<>();
		aq.enqueue(1);
		assertEquals(1, aq.size());
	}

	@Test
	void testIsEmpty(){
		ArrayQueue<Integer> aq = new ArrayQueue<>();
		assertEquals(true, aq.isEmpty());
	}

	@Test
	void testEnqueue(){
		ArrayQueue<Integer> aq = new ArrayQueue<>();
		aq.enqueue(1);
		assertEquals("1", aq.toString());
	}

	@Test
	void testFirst(){
		ArrayQueue<Integer> aq = new ArrayQueue<>();
		aq.enqueue(1);
		aq.enqueue(2);
		aq.enqueue(3);
		assertEquals(1, aq.first());
	}

	@Test
	void testDeque(){
		ArrayQueue<Integer> aq = new ArrayQueue<>();
		aq.enqueue(1);
		aq.enqueue(2);
		aq.enqueue(3);
		aq.dequeue();
		assertEquals("2 3", aq.toString());
	}

	@Test
	void testToString(){
		ArrayQueue<Integer> aq = new ArrayQueue<>();
		aq.enqueue(1);
		aq.enqueue(2);
		aq.enqueue(3);
		assertEquals("1 2 3", aq.toString());
	}
}
