/**
 * The LinkedStack class represents a "Last In ..First out" (LIFO) of generic items
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<E> implements Iterable<E> {
	private int n;
	private Entry first;
	
	/**
	 * Linked list entry class
	 */
	private class Entry {
		private E element;
		private Entry next;
	}
	
	/**
	 * An iterator class
	 * - doesn't implement remove().
	 */
	private class ListIterator implements Iterator <E> {
		private Entry current = first;
		public boolean hasNext()	{	return current != null;				}
		public void remove()		{	throw new UnsupportedOperationException();	}
		
		public E next()
		{
			if(!hasNext()) throw new NoSuchElementException();
			E element = current.element;
			current = current.next;
			return element;
		}
	} 
	
	/**
	 * Initialize an empty stack.
	 */
	public LinkedStack()
	{
		first = null;
		n = 0;
	}
	
	/**
	 * Checks if stack is empty.
	 */
	public boolean isEmpty() 
	{
		return first == null;
	}
	
	/**
	 * Returns the number of Elements in the stack.
	 */
	public int size() 
	{
		return n;
	}
	
	/**
	 * Adds the Element to the stack.
	 */
	public void push(E element)
	{
		Entry old = first;
		first = new Entry();
		first.element = element;
		first.next = old;
		n++;
	}
	
	/**
	 * Removes and returns the item most recently added to the stack.
	 */
	public E pop() 
	{
		if(isEmpty()) 
			throw new NoSuchElementException("Stack underflow");
		E element = first.element;
		first = first.next;
		n--;
		return element;
	}

	/**
	 * Returns (but does not remove) the element most recently added to the stack.
	 */
	public E peek()
	{
		if (isEmpty()) 
			throw new NoSuchElementException("Stack underflow");
		return first.element;
	}

	/**
	 * Returns a string reprensentation of the stack.
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		for(E element : this)
			s.append(element + " ");
		return s.toString();
	}
	
	/**
	 * Returns an iterator to the stack that iterates through the elements in LIFO order.
	 */
	public Iterator<E> iterator()
	{
		return new ListIterator();
	}
	
	/**
	 * Check interval invariants
	 */
	private boolean check()
	{	
		if (n < 0)  
			return false;
		if (n == 0)
			if (first !=null) return false;
		else if (n == 1)
		{
			if (first == null) 	return false;
			if (first.next != null) return false;
		}
		else
		{
			if (first == null)	return false;
			if (first.next == null) return false;
		}
		
		// check internal consistency of instance variable n.
		int numberOfEntrys = 0;
		for (Entry x = first; x != null && numberOfEntrys <= n; x = x.next)
			numberOfEntrys++;
		if (numberOfEntrys != n) 	
			return false;
		return true;
	}
}
