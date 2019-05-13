import java.util.NoSuchElementException;
public class AVLTree<Key extends Comparable<Key>, Value> extends Tree<Key, Value> {

	/**
	 * Initialize an empty table
	 */
	public AVLTree() {}
	
	/***************************************************************************************
	 * AVL Methods
	 **************************************************************************************/	
	public int height(Entry<Key, Value> element)
	{
		if (element == null)
			return -1;
		return element.height;
	}
	
	/***************************************************************************************
	 * AVL Insert
	 **************************************************************************************/	
	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting
	 * the old value with the new value if the symbol table already contains the
	 * specified key
	 * Deletes the specified key (and its associated value) from
	 * this symbol table if the specified value is null. 
	 * @param key the key
	 * @param val the value
	 * @throws IllegalArgumentException if key is null
	 */
	@Override
	public void put(Key key, Value value)
	{
		if (key == null)
			throw new IllegalArgumentException("first argument to put() is null");
		if (value == null)
		{
			delete(key);
			return;
		}
		root = put(root, key, value);
		assert check();
	}
	
	/**
	 * Inserts the key-value pair in the subtree. It overrides the old value
	 * with the new value if the symbol table already contains the specified key
	 * and deletes the specified key (and its associated value) from this table
	 * if the specified value is null
	 * @param x the subtree
	 * @param key the key
	 * @param val the value
	 * @return the subtree
	 */
	@Override
	protected Entry<Key, Value> put(Entry<Key, Value> element, Key key, Value value)
	{
		if (element == null)
			return new Entry<Key, Value>(key, value, 0, 1);
		int compare = key.compareTo(element.key);
		if (compare < 0)
			element.left = put(element.right, key, value);
		else if (compare > 0)
			element.right = put(element.right, key, value);
		else
		{
			element.value = value;
			return element;
		}
		element.size = 1 + size(element.left) + size(element.right);
		element.height = 1 + Math.max(height(element.left), height(element.right));
		return balance(element);
	}
	
	/***************************************************************************************
	 * AVL Balance Method
	 **************************************************************************************/	
	/**
	 * Restore the AVL tree propterty of the subtree
	 */
	private Entry<Key, Value> balance(Entry<Key, Value> element)
	{
		// Right does not hold the AVL property
		if(balanceFactor(element) < -1)
		{
			if (balanceFactor(element.right) > 0)
				element.right = rotateRight(element.right);
			element = rotateLeft(element);
		}
		
		// Left does not hold the AVL property
		else if (balanceFactor(element) > 1)
		{
			if (balanceFactor(element.left) < 0)
				element.left = rotateLeft(element.left);
			element = rotateRight(element);
		}
		
		return element;
	}
	
	/**
	 * Returns the balance factor of the subtree, the balance factor is defined as the
	 * difference in height of the left subtree and right subtree, in this order, therefore
	 * a subtree with a balance factor of -1, 0, or 1 has the AVL property since the heights
	 * of the two child subtress differ by at most one.
	 */
	private int balanceFactor(Entry<Key, Value> element)
	{
		return height(element.left) - height(element.right);
	}
	
	/**
	 * Rotates the given subtree to the right
	 */
	private Entry<Key, Value> rotateRight(Entry<Key, Value> elementX)
	{
		Entry<Key, Value> elementY = elementX.left;
		elementX.left = elementY.right;
		elementY.right = elementX;
		elementX.size = 1 + size(elementX.left) + size(elementX.right);
		elementX.height = 1 + Math.max(height(elementX.left), height(elementX.right));
		elementY.height = 1 + Math.max(height(elementY.left), height(elementY.right));
		return elementY;
	}
	/**
	 * Rotates the given subtree to the left
	 */
	private Entry<Key, Value> rotateLeft(Entry<Key, Value> elementX)
	{
		Entry<Key, Value> elementY = elementX.right;
		elementX.right = elementY.left;
		elementY.left = elementX;
		elementY.size = elementX.size;
		elementX.size = 1 + size(elementX.left) + size(elementX.right);
		elementX.height = 1 + Math.max(height(elementX.left), height(elementX.right));
		elementY.height = 1 + Math.max(height(elementY.left), height(elementY.right));
		return elementY;
	}
	
	/***************************************************************************************
	 * AVL Iterator
	 **************************************************************************************/	
	@Override	
	public Iterable<Key> keys()
	{
		return keysInOrder();
	}
	
	public Iterable<Key> keysInOrder()
	{
		LinkedQueue<Key> queue = new LinkedQueue<Key>();
		keysInOrder(root, queue);
		return queue;
	}
	
	private void keysInOrder(Entry<Key, Value> element, LinkedQueue<Key> queue)
	{
		if (element == null)
			return;
		keysInOrder(element.left, queue);
		queue.enqueue(element.key);
		keysInOrder(element.right, queue);
	}
	
	/**
	 * Returns all keys in the table following a level order traversal.
	 * @return - all keys in the table following a level order traversal.
	 */
	public Iterable<Key> keysLevelOrder()
	{
		LinkedQueue<Key> queue = new LinkedQueue<Key>();
		if (!isEmpty())
		{
			LinkedQueue<Entry<Key, Value>> queue2 = new LinkedQueue<Entry<Key, Value>>();
			queue2.enqueue(root);
			while (!queue2.isEmpty())
			{
				Entry<Key, Value> element = queue2.dequeue();
				queue.enqueue(element.key);
				if (element.left != null)
					queue2.enqueue(element.left);
				if (element.right != null)
					queue2.enqueue(element.right);
			}
		}
		return queue;
	}
	
	/***************************************************************************************
	 * AVL Check
	 **************************************************************************************/	
	/**
	 * Checks if the AVL tree invariants are fine
	 */
	@Override
	protected boolean check()
	{
		if (!isBST())
			System.out.println("Symmetric order is not consistent");
		if (!isAVL())
			System.out.println("AVL property not consistent");
		if (!isSizeConsistent())
			System.out.println("Subtree counts not consistent");
		if (!isRankConsistent())
			System.out.println("Ranks not consistent");
		return isBST() && isAVL() && isSizeConsistent() && isRankConsistent();
	}
	
	private boolean isAVL()
	{
		return isAVL(root);
	}
	
	private boolean isAVL(Entry<Key, Value> element)
	{
		if (element == null)
			return true;
		int bf = balanceFactor(element);
		if (bf > 1 || bf < -1)
			return false;
		return isAVL(element.left) && isAVL(element.right);
	}
}
