package projectCode20280.Practical7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UnsortedTableMapTest {

	@Test
	void testSize(){
		UnsortedTableMap<Integer, Integer> utm = new UnsortedTableMap<>();
		assertEquals(0, utm.size());
		utm.put(1,1);
		assertEquals(1, utm.size());
		utm.remove(1);
		assertEquals(0, utm.size());
	}

	@Test
	void testGet(){
		UnsortedTableMap<Integer, Integer> utm = new UnsortedTableMap<>();
		utm.put(1,1);
		utm.put(2,2);
		utm.put(3,3);
		assertEquals(2, utm.get(2));
	}

	@Test
	void testPut(){
		UnsortedTableMap<Integer, Integer> utm = new UnsortedTableMap<>();
		assertEquals(1, utm.put(1,1));
	}

	@Test
	void testRemove(){
		UnsortedTableMap<Integer, Integer> utm = new UnsortedTableMap<>();
		utm.put(1,1);
		assertEquals(1, utm.remove(1));
		assertTrue(utm.isEmpty());
	}

	@Test
	void testToString(){
		UnsortedTableMap<Integer, Integer> utm = new UnsortedTableMap<>();
		utm.put(1,1);
		assertEquals("[ <1, 1> ]", utm.toString());
	}



}
