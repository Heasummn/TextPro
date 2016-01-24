/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			int b = list1.remove(-1);
			fail("Remove: Index Out of Bound exception is thrown");
		} catch (IndexOutOfBoundsException e) {
			
		}
		list1.add(56);
		int b = list1.remove(1);
		assertEquals("Remove: check b is correct", 42, b);
		assertEquals("Remove: check element 1 is correct", (Integer)56, list1.get(1));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			emptyList.remove(0);
			fail("Remove: Removing from empty list");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		int lastIndex = list1.get(list1.size() - 1);
		assertEquals(lastIndex, 42);
		list1.add(12);
		lastIndex = list1.get(list1.size() - 1);
		assertEquals(lastIndex, 12);
		assertEquals("AddAtEnd: Size changes", 4, list1.size());
		emptyList.add(12);
		assertEquals((Integer)12, emptyList.get(0));
		try {
			emptyList.add(null);
			fail();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals(longerList.size(), LONG_LIST_LENGTH);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		list1.add(0, 4);
		assertEquals("AddAtIndex: adding to front", (Integer)4, list1.get(0));
		assertEquals("AddAtIndex: Element 1 is correct", (Integer)65, list1.get(1));
		
		try {
			list1.add(-1, 8);
			fail("AddAtIndex: Throws Index Out Of Bounds Exception");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		
		try {
			list1.add(58, 8);
			fail("AddAtIndex: Throws Index Out Of Bounds Exception");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		
		assertEquals("Size is correct", 4, list1.size());
		emptyList.add(0, 12);
		assertEquals((Integer)12, emptyList.get(0));
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		list1.set(0, 5);
		assertEquals("Set: value at first changed", (Integer) 5, list1.get(0));
		try {
			list1.set(-1, 24);
			fail("Set: Thrown Index Out Of Bounds");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		
		try {
			list1.set(58, 24);
			fail("Set: Thrown Index Out Of Bounds");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		
		list1.set(2, 12);
		assertEquals("Set: Value changes", (Integer)12, list1.get(2));
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
