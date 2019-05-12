/**
 * The queue class representation of a "first in first out" of generic elements
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Iterable<E> {
	private int n;		// number of elements in the queue
	private Entry<E> first;
	private Entry<E> last;
	
	/**
	 * entry class for linked list class
	 */
	private static class Entry<E> {
		private E element;
		private Entry<E> next;
	}

	/**
	 * An iterator class for the E element,
	 * does not implement remove().
	 */
	private class ListIterator implements Iterator<E> {
		private Entry<E> current;
		
		public ListIterator(Entry<E> first)
		{
			current = first;
		}
		
		public boolean hasNext() {	return current != null;				}
		public void remove()	 {	throw new UnsupportedOperationException();	}
		
		public E next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			E element = current.element;
			current = current.next;
			return element;
		}
	}

	/**
	 * Initialize empty queue
	 */
	public LinkedQueue()
	{
		first	= null;
		last 	= null; 
	}
	
	/**
	 * Returns true if the queue is empty
	 * @return - the number of elements in the queue
	 */
	public boolean isEmpty()
	{
		return first == null;
	}
	
	/**
	 * Returns the number of items in the queue
	 * @return - the number of item in the queue
	 */
	public int size()
	{
		return n;
	}
	
	/**
	 * Returns the number of items in the queue
	 * @return - the number of items in the queue
	 */
	public int length()
	{
		return n;
	}
	
	/**
	 * Return the element added "last" to the queue
	 * @return - the element in the front of the queue
	 */
	public E peek()
	{
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		return first.element;	
	}
	
	/**
	 * Add the item to the queue
	 * @param element - to be added to the end of the queue
	 */
	public void enqueue(E element)
	{
		Entry<E> old = last;
		last = new Entry<E>();
		last.element = element;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			old.next = last;
		n++;
	}
	
	/**
	 * Removes and returns the entry in the queue that was added last.
	 * @return - the entry in the front of the queue.
	 */
	public E dequeue()
	{
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		E element = first.element;
		first = first.next;
		n--;
		if (isEmpty())
			last = null;
		return element;	
	}
	
	/**
	 * Return a string representation of the queue
	 * @return - the sequence of the item in the FIFO order, separated by spaces
	 */
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for (E element : this)
		{
			s.append(element);
			s.append(" ");
		}
		return s.toString();
	}
	
	public Iterator<E> iterator()
	{
		return new ListIterator(first);
	}
	
}
