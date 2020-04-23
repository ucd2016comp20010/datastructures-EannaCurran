package projectCode20280.Practical2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayStackTest {

	@Test
	void testSize(){
		ArrayStack<Integer> aq = new ArrayStack<>();
		aq.push(1);
		assertEquals(1, aq.size());
	}

	@Test
	void testIsEmpty(){
		ArrayStack<Integer> aq = new ArrayStack<>();
		assertEquals(true, aq.isEmpty());
	}

	@Test
	void testPush(){
		ArrayStack<Integer> aq = new ArrayStack<>();
		aq.push(1);
		assertEquals("1", aq.toString());
	}

	@Test
	void testTop(){
		ArrayStack<Integer> aq = new ArrayStack<>();
		aq.push(1);
		aq.push(2);
		assertEquals(2, aq.top());
	}

	@Test
	void testPop(){
		ArrayStack<Integer> aq = new ArrayStack<>();
		aq.push(1);
		aq.push(2);
		aq.push(3);
		assertEquals(3, aq.pop());
	}

	@Test
	void testToString(){
		ArrayStack<Integer> aq = new ArrayStack<>();
		aq.push(1);
		aq.push(2);
		aq.push(3);
		assertEquals("3 2 1", aq.toString());
	}
}
