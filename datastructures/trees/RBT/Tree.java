import java.util.NoSuchElementException;
public abstract class Tree<Key extends Comparable<Key>, Value> extends Entry<Key, Value> {
	
	protected Entry<Key, Value> root;

	/***********************************************************************************
     	 *  Entry helper methods.
     	 ***********************************************************************************/
	public boolean isEmpty()
	{
		return root == null;
	}
	
	/**
	 * Provides the number of {key,value} pairs in the table
	 * @return size - return the number of {key,value} pairs
	 */
	public int size()
	{
		return size(root);
	}
	
	/**
	 * Provides the number of key value pairs in the binary tree at the given root entry
	 * @param entry - the element to count the number of {key,value} associated with
	 */
	protected int size(Entry<Key, Value> element)
	{
		if (element == null)
			return 0;
		return element.size;
	}

	/**
	 * Returns the height of the BST (for debugging)
	 * @return the height of the BST 
	 */	
	public int height()
	{
		return height(root);
	}

	protected int height(Entry<Key, Value> element)
	{
		if (element == null)
			return -1;
		return 1 + Math.max(height(element.left), height(element.right));
	}


	/***********************************************************************************
	 * Standard BST Search 
	 ***********************************************************************************/
	/**
	 * Return the value associated with the given key
	 * @param key - key to return its value
	 * @return value - the value associated
	 */
	public Value get(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		Entry<Key, Value> element = get(root, key);
		if (element == null)
			return null;
		return element.value;
	}
	
	protected Entry<Key, Value> get(Entry<Key, Value> element, Key key)
	{
		if (element == null)
		{
			System.out.println("FAIL");
			return null;
		}
		int compare = key.compareTo(element.key);
		if (compare < 0)
			return get(element.left, key);
		else if (compare > 0)
			return get(element.right, key);
		else
			return element;
	}

	/**
	 * Checks if the entry exist in the table
 	 * @param key - key to be searched
	 */
	public boolean contains(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to contain() is null");
		return get(key) != null;
	}
	
	/*****************************************************************************
	 * BST Insert
	 *****************************************************************************/
	/**
	 * Insert the specified {key,value} pair into the table or updating the old
	 * value with the new value to the specified key.
	 * @param key - the key to be added or updated
	 * @param value - the value associated with the specified key
	 */
	public abstract void put(Key key, Value value);
	
	protected abstract Entry<Key, Value> put(Entry<Key, Value> element, Key key, Value value);
	
	/*****************************************************************************
	 * BST deletion
	 *****************************************************************************/
	/**
	 * Removes the specified key and its associated value
	 * @param key - key to be searched and removed
	 */
	public abstract void delete(Key key);

	protected abstract Entry<Key, Value> delete(Entry<Key, Value> element, Key key);	
	/**
	 * Removes the smallest key and the associated value from the table
	 */
	public abstract void deleteMin();
	
	protected abstract Entry<Key, Value> deleteMin(Entry<Key, Value> element);
	
	/**
	 * Removes the largest key and the associated value
	 */
	public abstract void deleteMax();
	
	protected abstract Entry<Key, Value> deleteMax(Entry<Key, Value> element);
	
	/*****************************************************************************
	 * Iterator
	 *****************************************************************************/
	/**
	 * Returns all the keys in the table as an Iterable
	 * @return - the keys
	 */
	public Iterable<Key> keys()
	{
		if (isEmpty())
		{
			System.out.println("Empty queue");
			return new LinkedQueue<Key>();
		}
		return keys(min(), max());
	}
	
	/**
	 * Return all keys in a given range
	 * @param low - starting index
	 * @param high - ending index
	 * @return - all keys in the table between the range 
	 */
	protected Iterable<Key> keys(Key low, Key high)
	{
		if (low == null)
			throw new IllegalArgumentException("first argument to keys() is null");
		if (high == null)
			throw new IllegalArgumentException("second argument to keys() is null");
		LinkedQueue<Key> queue = new LinkedQueue<Key>();
		keys(root, queue, low, high);
		return queue;	
	}

	protected void keys(Entry<Key, Value> element, LinkedQueue<Key> queue, Key low, Key high)
	{
		if(element == null)
			return;
		int comparelow = low.compareTo(element.key);
		int comparehigh = high.compareTo(element.key);
		if(comparelow < 0)
			keys(element.left, queue, low, high);
		if(comparelow <= 0 && comparehigh >= 0)
			queue.enqueue(element.key);
		if(comparehigh > 0)
			keys(element.right, queue, low, high);
	}

	/***********************************************************************************
	 * Ordered Table methods
	 ***********************************************************************************/
	/**
	 * Returns all keys in the specified range
	 * @param low - starting index
	 * @param high - ending index
	 @ @return size - the number of keys between the to endpoints
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
	 * Returns the smallest key
	 * @return key - the smallest key in the table
	 */
	public Key min()
	{
		if (isEmpty())
			throw new NoSuchElementException("calls min() with an empty table");
		return min(root).key;
	}
	
	protected Entry<Key, Value> min(Entry<Key, Value> element)
	{
		if (element.left == null)
			return element;
		else
			return min(element.left);
	}
	
	/**
	 * Return the largest key
	 * @return key - the largest key in the table
	 */
	public Key max()
	{
		if (isEmpty())
			throw new NoSuchElementException("calls max() with empty table");
		return max(root).key;
	}
	
	protected Entry<Key, Value> max(Entry<Key, Value> element)
	{
		if (element.right == null)
			return element;
		else
			return max(element.right);
	}

	/**
	 * Returns the largest key in the table less than or equal to key
	 * @param key - to be compared to
	 * @return - the largest key in the table less than or equal to
	 */
	public Key floor(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to floor() is null");
		if (isEmpty())
			throw new NoSuchElementException("calls floor() with empty table");
		Entry<Key, Value> element = floor(root, key);
		if (element == null)
			return null;
		else
			return element.key;
	}
	
	protected Entry<Key, Value> floor(Entry<Key, Value> element, Key key)
	{
		if (element == null)
			return null;
		int compare = key.compareTo(element.key);
		if (compare == 0)
			return element;
		if (compare < 0)
			return floor(element.right, key);
		Entry<Key, Value> temp = floor(element.right, key);
		if(temp != null)
			return temp;
		else
			return element;			
	}
	
	public Key floor2(Key key)
	{
		return floor2(root, key, null);
	}

	protected Key floor2(Entry<Key, Value> element, Key key, Key best)
	{
		if (element == null)
			return best;
		int compare = key.compareTo(element.key);
		if (compare < 0)
			return floor2(element.left, key, best);
		if (compare > 0)
			return floor2(element.right, key, best);
		else
			return element.key;
	}

	/**
	 * Returns the smallest key in the table greater than or equal to the key
	 * @param key - the key to be match (upper bound)
	 * @return key - the smallest key equal to or greater than the specified key
	 */
	public Key ceiling(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to ceiling() is null");
		if (isEmpty())
			throw new NoSuchElementException("calls ceiling() with empty table");
		Entry<Key, Value> element = ceiling(root, key);
		if (element == null)
			return null;
		else
			return element.key;
	}
	
	protected Entry<Key, Value> ceiling(Entry<Key, Value> element, Key key)
	{
		if (element == null)
			return null;
		int compare = key.compareTo(element.key);
		if (compare == 0)
			return element;
		if (compare < 0){
			Entry<Key, Value> temp = ceiling(element.left, key);
			if (temp != null)
				return temp;
			else
				return element;
		}
		return ceiling(element.right, key);
	}

	/**
	 * Return the key in the table who ranks is equal to k
	 * if the number of keys 't' in the left subtree is larger than k
	 * 	look recursively for the key of rank k in left subtree
	 * if 't' is equal to k return the key at the root
	 * if 't' is smaller than k 
	 * 	look recursively for key of rank 'k- t- 1' at right subtree
	 * @param k - the order
	 * @return keys - the order of keys of rank k
	 */
	public Key select(int k)
	{
		if (k < 0 || k >= size())
			throw new IllegalArgumentException("argument to select() is invalid: " + k);
		Entry<Key, Value> element = select(root, k);
		return element.key;
	}
	
	protected Entry<Key, Value> select(Entry<Key, Value> element, int k)
	{
		if (element == null)
			return null;
		int t = size(element.left);
		if (t > k)
			return select(element.left, k);
		else if (t < k)
			return select(element.right, k - t - 1);
		else
			return element;
	}

	/**
	 * Return the key of rank k
	 * @parm element - analyze the current element
	 * @return - the single element by its respective rank
	 */
	public int rank(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to rank() is null");
		return rank(key, root);
	}		

	/**
	 * Count the keys less than the specified key
	 * If the given key is equal to the key at the root, return the number of keys in its subtree
	 * If the given key is less than the key at the root, return the rank of the key in the left subtree
	 * If the given key is larger than the key at the root, return the subtree + 1 and the keys in the right.
	 */
	protected int rank(Key key, Entry<Key, Value> element)
	{
		if (element == null)
			return 0;
		int compare = key.compareTo(element.key);
		if (compare < 0)
			return rank(key, element.left);
		if (compare > 0)
			return size(element.left) + 1 + rank(key, element.right);
		else
			return size(element.left);
	}

	/***********************************************************************************
	 * Itegrity check
	 ***********************************************************************************/
	protected boolean check()
	{
		if (!isBST())
			System.out.println("Not in symmetric order");
		if (!isSizeConsistent())
			System.out.println("Subtree counts not consistent");
		if (!isRankConsistent())
			System.out.println("Ranks not consistent");
		return isBST() && isSizeConsistent() && isRankConsistent();
	}
	
	/**
	 * Checks if the binary tree satisfy symmetric order
	 */
	protected boolean isBST()
	{
		return isBST(root, null, null);
	}
	
	/**
	 * Is the tree rooted at the element a binary search tree with all keys strictly between min and max
	 * if {min} or {max} is null, treat as empty contraints
	 */
	protected boolean isBST(Entry<Key, Value> element, Key min, Key max)
	{
		if (element == null)
			return true;
		if (min != null && element.key.compareTo(min) <= 0)
			return false;
		if (max != null && element.key.compareTo(max) >= 0)
			return false;
		return isBST(element.left, min, element.key) && isBST(element.right, element.key, max);
	}	

	/**
	 * Check if the size fields correct
	 */
	public boolean isSizeConsistent()
	{
		return isSizeConsistent(root);
	}
	
	public boolean isSizeConsistent(Entry<Key, Value> element)
	{
		if (element == null)
			return true;
		if (element.size != size(element.left) + 1 + size(element.right))
			return false;
		return true;
	}
	
	/**
	 * Check if the ranks are consistent
	 */
	public boolean isRankConsistent()
	{
		for (int i = 0; i < size(); i++)
			if (i != rank(select(i)))
				return false;
		for (Key key: keys())
			if (key.compareTo(select(rank(key))) != 0)
				return false;
		return true;
	}

}
