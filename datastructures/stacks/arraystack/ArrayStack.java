/**
 * Stack implementation with a resizing array.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements Iterable<E> {
	private E [] arr;	// Array of elements
	private int n;		// Number of elements
		
	/** 
	 * An Iterator class
	 * does not implement remove.
	 */
	private class ReverseArrayIterator implements Iterator<E> {
		private int i;
		
		public ReverseArrayIterator()	{	i = n -1;					}
		public boolean hasNext()	{	return i >= 0;					}	
		public void remove ()		{	throw new UnsupportedOperationException();	}
		
		public E next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			return arr[i--];
		}
	}

	/**
	 * Initialize an empty stack.
	 */
	public ArrayStack()
	{
		arr 	= (E []) new Object[2];
		n	= 0; 
	}
	
	/**
	 * Checks if the stack is empty
	 * @return true if this stack is empty
	 */
	public boolean isEmpty()
	{
		return n ==0;
	}
	
	/**
	 * Resize the array with the elements
	 * @param capacity - new size for array, must be greater than number of elements. 
	 */
	private void resize(int capacity)
	{
		assert capacity >= n;
		E[] temp = (E[]) new Object[capacity];
		for (int i = 0; i < n; i++)
			temp[i] = arr[i];
		arr = temp;
	}
	
	/**
	 * Adds the element to the stack.
	 * @param element - element to add.
	 */
	public void push(E element)
	{
		if (n == arr.length)
			resize(2 * arr.length);
		arr[n++] = element;
	}
	
	/**
	 * Removes and returns the item most recently added to the stack
	 * @return the item most recently added
	 */
	public E pop()
	{
		if (isEmpty()) 
			throw new NoSuchElementException("Stack underflow");
		E element = arr[n-1];
		arr[n-1] = null;
		n--;
		
		// resize the array if applicable
		if (n > 0 && n == arr.length/4)
			resize(arr.length/2);
		return element;
	}
	
	/**
	 * Returns (but does not remove) the element added last to stack.
	 * @return the item most recently added to the stack.
	 */
	public E peek()
	{
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		return arr[n-1];
	}
	
	public Iterator<E> iterator()
	{
		return new ReverseArrayIterator();
	}
}
