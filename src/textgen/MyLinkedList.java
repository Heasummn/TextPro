package textgen;

import java.util.AbstractList;



/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	private int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null, head);
		
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null)
			throw new NullPointerException();
		LLNode<E> node = new LLNode<E>(element, tail.prev, tail);
		tail.prev.next = node;
		tail.prev = node;
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		LLNode<E> currNode = getAtIndex(index);
		return currNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		
		if(element == null)
			throw new NullPointerException();
		
		if(size == 0) {
			add(element);
			return;
		}
		LLNode<E> node = new LLNode<E>(element);
		LLNode<E> currNode = getAtIndex(index);
		// The new node's previous is the current index's previous
		node.prev = currNode.prev;
		// The node after the new node is the current node
		node.next = currNode;
		// The node before our current node's next is now the new node
		currNode.prev.next = node;
		// Finally, the current Node's previous is the new node
		currNode.prev = node;
		
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// Get the object at the index
		LLNode<E> node = getAtIndex(index);
		// Change it's surroundings
		node.prev.next = node.next;
		node.next.prev = node.prev;
		// Make it vanish
		node.next = null;
		node.prev = null;
		size--;
			
		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException();
		}
		LLNode<E> node = getAtIndex(index);
		node.data = element;
		return node.data;
	}
	
	private LLNode<E> getAtIndex(int index) {
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		
		//Check which direction to loop through
		if(index < size/2) {
			int counter = 0;
			LLNode<E> currNode = head.next;
			while(counter != index) {
				counter++;
				currNode = currNode.next;
			}
			// Change currNode's surroundings
			return currNode;
		} else {
			int counter = size - 1;
			LLNode<E> currNode = tail.prev;
			while(counter != index) {
				counter--;
				currNode = currNode.prev;
			}
			return currNode;
		}
	}
	
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e, LLNode<E> prev) 
	{
		this.data = e;
		this.prev = prev;
		this.next = null;
	}
	
	public LLNode(E e, LLNode<E> prev, LLNode<E> next) 
	{
		this.data = e;
		this.prev = prev;
		this.next = next;
	}

}
