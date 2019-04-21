/**
 * This is an implementation that uses a singly linked list and sequential search
 */

public class SequentialSearch<Key, Value> {
	private int n;		// the number of {key-value} pairs
	private Entry first;	// first entry of the linked list
	
	private class Entry {
		private Key key;
		private Value value;
		private Entry next;
		
		public Entry(Key key, Value value, Entry next)
		{
			this.key 	= key;
			this.value 	= value;
			this.next 	= next;
		}
	}
	
	/**
	 * Initializes an empty table
	 */
	public SequentialSearch() {}
	
	/**
	 * Returns the number of {key-value} pairs in the table
	 * @return the number of {key-value} pairs
	 */
	public int size()
	{
		return n;
	}

	/**
	 * Check if the table is empty
	 * @return true - if the table is empty
	 */
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	/**
	 * Checks if the given key exists in the table
	 * @param key - key
	 * @return true - if the key is found
	 */
	public boolean contains(Key key)
	{
		return get(key) != null;
	}
	
	/**
	 * Returns the value of the given key
	 * @parm key - key
	 * @return - the value of the given key 
	 */
	public Value get (Key key)
	{
		for (Entry x = first; x != null; x = x.next)
		{
			if (key.equals(x.key))
				return x.value;
		}
		return null;
	}
	
	/**
	 * Inserts the {key-value} pair to the table or overwriting the old entry.
	 * @param key - new key or old key
	 * @param value - value
	 */
	public void put(Key key, Value value)
	{
		if (value == null)
		{
			delete(key);
			return;
		}
		
		for (Entry x = first; x !=null; x = x.next)
		{
			if (key.equals(x.key))
			{
				x.value = value;
				return;
			}
		}
		
		first = new Entry(key, value, first);
		n++;
	}
	
	/**
	 * Removes the key(if exisit) from the table
	 * @param key - key
	 */
	public void delete(Key key)
	{
		first = delete(first, key);
	}
	
	/**
	 * Delete key in the linked list begining at entry x
	 * @param x   - search at this entry
	 * @param key - key to search for 
	 */
	private Entry delete(Entry x, Key key)
	{
		if (x == null)
			return null;
		if (key.equals(x.key))
		{
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
		
	}
	
	/**
	 * Returns all keys in the table as an Iterable
	 * @return - all the keys in the table.
	 */
	public Iterable<Key> keys()
	{
		LinkedQueue<Key> queue = new LinkedQueue<Key>();
		for (Entry x = first; x != null; x = x.next)
			queue.enqueue(x.key);
		return queue;
	}
}	




