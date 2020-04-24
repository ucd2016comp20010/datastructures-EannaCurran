package projectCode20280.Practical2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CircularQueueTest {

	@Test
	void testSize(){
		CircularQueue<Integer> cq = new CircularQueue<>();
		cq.enqueue(1);
		assertEquals(1, cq.size());
	}

	@Test
	void testIsEmpty(){
		CircularQueue<Integer> cq = new CircularQueue<>();
		assertTrue(cq.isEmpty());
	}

	@Test
	void testEnqueue(){
		CircularQueue<Integer> cq = new CircularQueue<>();
		cq.enqueue(1);
		cq.enqueue(2);
		assertEquals("[1, 2]", cq.toString());
	}

	@Test
	void testToString(){
		CircularQueue<Integer> cq = new CircularQueue<>();
		cq.enqueue(1);
		cq.enqueue(2);
		cq.enqueue(3);
		assertEquals("[1, 2, 3]", cq.toString());
	}

	@Test
	void testFirst(){
		CircularQueue<Integer> cq = new CircularQueue<>();
		cq.enqueue(1);
		cq.enqueue(2);
		cq.enqueue(3);
		assertEquals(1, cq.first());
	}

	@Test
	void testBack(){
		CircularQueue<Integer> cq = new CircularQueue<>();
		cq.enqueue(1);
		cq.enqueue(2);
		cq.enqueue(3);
		assertEquals(3, cq.back());
	}

	@Test
	void testDequeue(){
		CircularQueue<Integer> cq = new CircularQueue<>();
		cq.enqueue(1);
		cq.enqueue(2);
		cq.enqueue(3);
		cq.dequeue();
		assertEquals("[2, 3]", cq.toString());
	}

	@Test
	void testRotate(){
		CircularQueue<Integer> cq = new CircularQueue<>();
		cq.enqueue(1);
		cq.enqueue(2);
		cq.rotate();
		assertEquals("[2, 1]", cq.toString());

	}
}
