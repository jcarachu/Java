/**
 * The "LinearProbingHash" class representation table of generic {key,value} pairs
 * The implementation uses a linear probing hash table
 */
public class LinearProbingHash<Key,Value> {
	private static final int INIT_CAPACITY = 4;
	private int n;		// number of {key,value} pairs in the table
	private int m;		// size of linear probing table
	private Key[] keys;
	private Value[] values;
	
	/**
	 * Initialize an empty table
	 */
	public LinearProbingHash()
	{
		this(INIT_CAPACITY);
	}
	
	/**
	 * Initialize an empty table with the specified initial capacity
	 * @param capacity - size of the table
	 */
	@SuppressWarnings("unchecked")
	public LinearProbingHash(int capacity)
	{
		m = capacity;
		n = 0;
		keys = (Key[]) new Object[m];
		values = (Value[]) new Object[m];
	}
	
	/**
	 * Return the number of {key,value} pairs in the table
	 * @return - the number of {key,value} pairs
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
	 * Check if the table contains the specified key
	 * @param key - the key to be searched
	 * @return true -if the table contains the key
	 */
	public boolean contains(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) !=null;
	}
	
	/**
	 * Hash function for keys
	 * return values between 0 and M-1
	 * hashcode can vary between objects
	 */
	private int hash(Key key)
	{
		return (key.hashCode() & 0x7fffffff) % m;
	}
	
	/**
	 * Resize the hash table to the given capacity by rehashing all the keys
	 */
	private void resize(int capacity)
	{
		LinearProbingHash<Key,Value> temp = new LinearProbingHash<Key, Value>(capacity);
		for (int i = 0; i < m; i++)
		{
			if (keys[i] != null)
				temp.put(keys[i], values[i]);
		}
		keys 	= temp.keys;
		values 	= temp.values;
		m 	= temp.m;
	}
	
	/**
	 * Inserts the specified {key,value} pair to the table, updates the 
	 * old value with the new value if the table already contains the key
	 * @param key - the specified key to be added
	 * @param value - the value affiliated with the key
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
		// double table size if 50% full
		if (n >= m/2)
			resize(2*m);
		// search for an empty slot 
		int index = hash(key);
		while (keys[index]!=null)
		{
			if (keys[index].equals(key))
			{
				values[index] = value;
				return;
			}
			index = (index+1) % m;

		}
		keys[index] 	= key;
		values[index] 	= value;
		n++;
	}
	
	/**
	 * Retrieve the value from the specified key
	 * @param key -	the key to retrieve its value
	 * @return value - the specifed value associated with the key
	 */
	public Value get(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
			if (keys[i].equals(key))
				return values[i];
		return null;
	}
	
	/**
	 * Removes the specified key and its associated value from the table
	 * (if the key is in the table)
	 * @param key - the key to be removed
	 */
	public void delete(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key))
			return;
		// find position i of key
		int i = hash(key);
		while (!key.equals(keys[i]))
			i = (i + 1) % m;
		// delete key and associated value
		keys[i]  = null;
		values[i] = null;
		// rehash all keys in the same cluster
		// basically shift cluster down one	
		i = (i + 1) % m;
		while (keys[i] != null)
		{
			// delete keys[i] plus values[i] and reinsert
			Key keyToRehash = keys[i];
			Value valueToRehash = values[i];
			keys[i] = null;
			values[i] = null;
			n--;
			put(keyToRehash, valueToRehash);
			i = (i + 1) % m;	
		}
		n--;
		// halves the size of the array if it is 12.5 % full or less
		if (n > 0 && n <= m/8)
			resize(m/2);
		assert check();
	}
	
	/**
	 * Returns all the keys in the table 
	 * @return iterable - all the keys in the table
	 */
	public Iterable<Key> keys()
	{
		LinkedQueue<Key> queue = new LinkedQueue<Key>();
		for (int i = 0; i < m; i++)
			if (keys[i] != null)
				queue.enqueue(keys[i]);
		return queue;
	}
	
	/**
	 * Integrity check
	 * @return true - if integrity is maintained
	 */
	public boolean check()
	{
		// check the hash table if it is 50% full
		if (m < 2*n)
		{
			System.err.println("Hash table m = " + m + "; array size n = " + n);
			return true;
		}
		// check that each key in table can be found by get()
		for (int i = 0; i < m; i++)
		{
			if (keys[i] == null)
				continue;
			else if (get(keys[i]) != values[i])
			{
				System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; values[i] = " + values[i]);
				return false;
			}
		}
		return true;
	}

}
