/**
 * Implementation of a separate chaining hash table
 */

public class SeparateChainingHash<Key, Value> {
	private static final int INIT_CAPACITY = 4;
	private int n;	// number of {key,value} pairs
	private int m;	// hash table size
	private SequentialSearch<Key, Value>[] table;	// array of linked list
	
	/**
	 * initialize an empty table with a given number of chains.
	 */
	public SeparateChainingHash()
	{
		this(INIT_CAPACITY);
	}
	
	/*
	 * @param m - number of chains.
	 */
	@SuppressWarnings("unchecked")
	public SeparateChainingHash(int m)
	{
		this.m = m;
		table = (SequentialSearch<Key, Value>[]) new Object[m];
		for (int i = 0; i < m; i++)
			table[i] = new SequentialSearch<Key, Value>();
	}
	
	/**
	 * resize the hash table to the given number of chains,
	 * and rehash all the keys.
	 */
	private void resize(int chains)
	{
		SeparateChainingHash<Key,Value> temp = new SeparateChainingHash<Key, Value>(chains);
		for (int i = 0; i < m; i++)
		{
			for (Key key: table[i].keys())
				temp.put(key, table[i].get(key));
		}
		this.m = temp.m;
		this.n = temp.n;
		this.table = temp.table;
	}

	/**
	 * hash value between 0 and m-1
	 * hashcode depends on the key type
	 */
	private int hash(Key key)
	{
		return (key.hashCode() & 0x7fffffff) % m;
	}
	
	/**
	 * returns the number of {key,value} pairs in the table
	 * @return - the number of {key,value} pairs
	 */
	public int size()
	{
		return n;
	}
	
	/**
	 * checks if the table is empty
	 * @return true - if the table is empty
	 */
	public boolean isEmpty()
	{
		return size() == 0;
	}

	/**
	 * return true if the table contains the key
	 * @param key - key to be searched
	 * @return true - if the table contains the key
	 */
	public boolean contains(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) !=null;
	}
	
	/**
	 * return the value associated with the specified key found in the table
	 * @param key - key return value from
	 * @return value - the value associated with the key
	 */
	public Value get(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		return table[i].get(key);
	}
	
	/**
	 * inserts the specified {key,value} pair into the table, or updating the 
	 * old value with the new value if the table contains the key
	 * @param key - key to be added/updated
	 * @param val - value to be assigned/updated
	 */
	public void put(Key key, Value value)
	{
		if (key == null)
			throw new IllegalArgumentException("first argument to put() is null");
		if (value == null)
		{
			delete(key);
			return;
		}
		// double the table size IF number of pairs is greater than >= 10
		// if collision are accumulating more than the table size.
		if (n >= 10*m)
			resize(2*m);
		int i = hash(key);
		if (!table[i].contains(key))
			n++;
		table[i].put(key,value);
	}
	
	/**
	 * removes the specified key and its associated value from the table
	 * @param key - key to be removed
	 */
	public void delete(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		int i = hash(key);
		if (table[i].contains(key))
			n--;
		table[i].delete(key);
		// halve the table size if the length of the table is less than <= 2
		// if the number of entries and collisions are less than twice the table size;
		if (m > INIT_CAPACITY && n <= 2*m)
			resize(m/2);
	}
	
	/**
	 * return keys in the table as an Iterable
	 */
	public Iterable<Key> keys()
	{
		LinkedQueue<Key> queue = new LinkedQueue<Key>();
		for (int i = 0; i < m; i++)
		{
			for (Key key: table[i].keys())
				queue.enqueue(key);
		}
		return queue;
	}
	
}
