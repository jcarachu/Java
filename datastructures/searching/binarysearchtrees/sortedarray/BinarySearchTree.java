/**
 * The BinarySearchTree class represents an ordered table of generic {key,value} pairs
 * This implementation uses a sort array
 */

import java.util.NoSuchElementException;
public class BinarySearchTree<Key extends Comparable<Key>, Value>
{
	private static final int INITIAL_CAPACITY = 2;
	private Key[] keys;
	private Value[] values;
	private int n = 0;
	
	/**
	 * Initialize empty table
	 */
	public BinarySearchTree()
	{
		this(INITIAL_CAPACITY);
	}
	
	/**
	 * Initialize a empty table with the specified capacity
	 */
	@SuppressWarnings("unchecked")
	public BinarySearchTree(int capacity)
	{
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
	}
	
	/**
	 * Resize the underlying array
	 */
	@SuppressWarnings("unchecked")
	private void resize(int capacity)
	{
		assert capacity >= n;
		Key[] tempkey = (Key[]) new Comparable[capacity];
		Value[] tempvalue = (Value[]) new Object[capacity];
		for (int i = 0; i < n; i++)
		{
			tempkey[i] = keys[i];
			tempvalue[i] = values[i];
		}
		keys = tempkey;
		values = tempvalue;
	}
	
	/**
	 * Return the number of {key,value} pairs un the table
	 * @return the {key,value} pairs
	 */
	public int size()
	{
		return n;
	}
	
	/**
	 * Check if table is empty
	 */
	public boolean isEmpty()
	{
		return size() == 0;
	}
		
	/**
	 * Check if the table contains the given key
	 * @param key - to be searched
	 * @return true - if the table contains the key
	 */
	public boolean contains(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argumentto contains() is null");
		return get(key) != null;
	}
	
	/**
	 * Return the value associated with the given key in the table
	 * @param key - to return is associated value
	 * @return - the value associated witht the given key
	 */
	public Value get(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < n && keys[i].compareTo(key) == 0)
			return values[i];
		return null;
	}
	
	/**
	 * Return the number of keys in the table strictly less than the specified key
	 * @param key - the key to be analyze
	 * @return - the number of keys in the table less than the specified key 
	 */
	public int rank(Key key)
	{
		if (key == null)
			throw  new IllegalArgumentException("argument to rank() is null");
		int low = 0;
		int high = n-1;
		while (low <= high)
		{
			int mid = low + (high - low) / 2;
			int compare = key.compareTo(keys[mid]);
			// key is less than the mid
			// - shift the high index to mid -1 
			if (compare < 0)
				high = mid - 1;
			// key is greater than the mid
			// - shift the low index to mid + 1
			else if (compare > 0)
				low = mid + 1;
			else    
				return mid;
		}
		return low;
	}
	
	/**
	 * Insert the specified {key,value} pair to the table or update the old
	 * value with the new value if the key is found
	 * Delete the specified key from the table if the value is null.
	 * @param key - the specified key to be inserted or updated
	 * @param value - the new value
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
		// find the rank 
		int i = rank(key);
		// check if the key is in the table
		if (i < n && keys[i].compareTo(key) == 0)
		{
			values[i] = value;
			return;
		}
		
		// insert new {key,value} pair
		// increase the size if have too
		if (n == keys.length)
			resize(2*keys.length);
		// iterate down to the rank to find its appropriate position
		// leave one space in between to fit new key
		for (int j = n; j > i; j--)
		{
			keys[j] = keys[j-1];
			values[j] = values[j-1];
		}

		keys[i] = key;
		values[i] = value;
		n++;
		assert check();
	}
	
	/**
	 * Remove the specified key and associated value from the table
	 * @param key - key to be deleted
	 */
	public void delete(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		if (isEmpty())
			return;
		// compute rank
		int i = rank(key);
		// key not in table
		if (i == n || keys[i].compareTo(key) != 0)
		{
			return;
		}
		
		for (int j = i; j < n -1; j++)
		{
			keys[j] = keys[j+1];
			values[j] = values[j+1];
		}
		
		n--;
		keys[n] = null;
		values[n] = null;
		// resize if 1/4 full
		if (n > 0 && n == keys.length/4)
			resize(keys.length/2);
		assert check();
	}
	
	/**
	 * Removes the smallest key and associated value from the table
	 */
	public void deleteMin()
	{
		if (isEmpty())
			throw new NoSuchElementException("Table underflow error");
		delete(min());
	}
	
	/**
	 * Removes the largest key and associated value from the table
	 */
	public void deleteMax()
	{
		if (isEmpty())
			throw new NoSuchElementException("Table underflow error");
		delete(max());
	}
	
	/**
	 * Returns the smallest key in the table
	 */
	public Key min()
	{
		if (isEmpty())
			throw new NoSuchElementException("called min() with empty symbol table");
		return keys[0];
	}
	
	/**
	 * Returns the largest key in the table
	 */
	public Key max()
	{
		if (isEmpty())
			throw new NoSuchElementException("called max() with empty symbol table");
		return keys[n-1];
	}
	
	/**
	 * Return the kth smallest key in the table
	 * @param k - the order statistic
	 * @return - the kth smallest key in the table
	 */
	public Key select(int k)
	{
		if (k < 0 || k >= size ())
			throw new IllegalArgumentException("called select() with invalid argument: " + k);
		return keys[k];
	}
	
	/**
	 * Return the largest key in the table less than or equal to key
	 * @param key - key be analyze
	 * @return - the largest key in the table less than or equal to the key
	 */
	public Key floor(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to floor() is null");
		int i = rank(key);
		if (i < n && key.compareTo(keys[i]) == 0)
			return keys[i];
		if (i == 0)
			return null;
		else
			return keys[i-1];
	}
	
	/**
	 * Return the smallest key in the symbol table greater than or equal to the key
	 * @param key - the key to be anaylze
	 * @return rank - smallest key in the table greater than or equal to key
	 */
	public Key ceiling(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to ceiling() is null");
		int i = rank(key);
		if (i == n)
			return null;
		else
			return keys[i];
	}
	
	/**
	 * Return the number of keys in the table in the specified range
	 * @param low - minimum endpoint
	 * @param high - maximum endpoint
	 * @return the number of keys in the table between low and high
	 */
	public int size(Key low, Key high)
	{
		if (low == null)
			throw new IllegalArgumentException("first argument to size() is null");
		if (high == null)
			throw new IllegalArgumentException("second argument to size() is null");
		if (low.compareTo(high) > 0)
			return 0;
		if (contains(high))
			return rank(high) - rank(low) + 1;
		else
			return rank(high) - rank(low); 
	}
	
	/**
	 * Returns all keys in the table as an iterable to iterate over all the keys
	 * in the table
	 * @return all the keys in the table 
	 */
	public Iterable<Key> keys()
	{
		return keys(min(), max());
	}
	
	/**
	 * Return all keys in the table given in the range
	 * @param low - minimum endpoint
	 * @param high - maximum endpoint
	 * @return - all the keys in the table between low and high 
	 */
	public Iterable<Key> keys(Key low, Key high)
	{
		if (low == null)
			throw new IllegalArgumentException("first argument to keys() is null");
		if (high == null)
			throw new IllegalArgumentException("second argument to keys() is null");
		LinkedQueue<Key> queue = new LinkedQueue<Key>();
		if (low.compareTo(high) > 0)
			return queue;
		for (int i = rank(low); i < rank(high); i++)
			queue.enqueue(keys[i]);
		if (contains(high))
			queue.enqueue(keys[rank(high)]);
		return queue;
	}
	
	/**
	 * Check interal invariants
	 */
	private boolean check()
	{
		return isSorted() && rankCheck();
	}
	
	/*
	 * Check if the items are in ascending order
	 */
	private boolean isSorted()
	{
		for (int i = 1; i < size(); i++)
			if (keys[i].compareTo(keys[i]) != 0)
				return false;
		return true;
	}
	
	/*
	 * Check that rank(select(i)) = i
	 */
	private boolean rankCheck()
	{
		for (int i = 0; i < size(); i++)
			if (i != rank(select(i)))
				return false;
		for (int i = 0; i < size(); i++)
			if (keys[i].compareTo(select(rank(keys[i]))) != 0)
				return false;
		return true;
	}
	
}
