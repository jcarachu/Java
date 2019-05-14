import java.util.NoSuchElementException;
public class RedBlack<Key extends Comparable<Key>, Value> extends Tree<Key, Value>{
	
	/**
	 * Initialize an empty table
	 */
	protected final boolean RED = true;
	protected final boolean BLACK = false;
	
	public RedBlack() {}

	/***************************************************************************
    	 *  Entry helper methods.
   	 ***************************************************************************/
	private boolean isRed(Entry<Key, Value> element)
	{
		if (element == null)
			return false;
		
		return element.color == RED;
	}

	/***********************************************************************************
	 * Red Black Tree insertion
	 ***********************************************************************************/
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
		root.color = BLACK;
	}

	/**
	 * insert the key value pair in the subtree rooted at the element.
	 */
	@Override
	protected Entry<Key, Value> put(Entry<Key, Value> element, Key key, Value value)
	{
		if (element == null)
			return new Entry<Key, Value>(key, value, RED, 1);

		int compare = key.compareTo(element.key);
		if (compare < 0)
			element.left = put(element.left, key, value);
		
		else if (compare > 0)
			element.right = put(element.right, key, value);
		
		else
			element.value = value;

		// fix up any right leaning links
		if (isRed(element.right) && !isRed(element.left))
			element = rotateLeft(element);
		
		if (isRed(element.left) && !isRed(element.right))
			element = rotateRight(element);
		
		if (isRed(element.left) && isRed(element.right))
			flipColors(element);
		
		element.size = size(element.left) + 1 + size(element.right);
		return element;
	}

	/***********************************************************************************
	 * Red Black Tree deletion
	 ***********************************************************************************/
	/**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param key the key
     */
	@Override
	public void delete(Key key)
	{
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		
		if (!contains(key))
			return;
		// if both children of root are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = delete(root, key);

		if (!isEmpty())
			root.color = BLACK;
	}

	@Override
	protected Entry<Key, Value> delete(Entry<Key, Value> elementH, Key key)
	{
		// Left
		if (key.compareTo(elementH.key) < 0)
		{
			// left left red
			if (!isRed(elementH.left) && !isRed(elementH.left.left))
				elementH = moveRedLeft(elementH);
			elementH.left = delete(elementH.left, key);
		
		}
		
		else // Right
		{
			// red is only on left side
			if (isRed(elementH.left))
				elementH = rotateRight(elementH);
			
			if (key.compareTo(elementH.key) == 0 && (elementH.right == null))
				return null;
			
			if (!isRed(elementH.right) && !isRed(elementH.right.left))
				elementH = moveRedRight(elementH);
			
			if (key.compareTo(elementH.key) == 0)
			{
				Entry<Key, Value> elementX = min(elementH.right);
				elementH.key = elementX.key;
				elementH.value = elementX.value;
				elementH.right = deleteMin(elementH.right);
			
			}
			else
				elementH.right = delete(elementH.right, key);
		}

		return balance(elementH);
	}

	/**
	 * Removes the smallest key and associated value from the table
	 */
	@Override
	public void deleteMin()
	{
		if (isEmpty())
			throw new NoSuchElementException("BST underflow");
		
		// if both children of root are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		
		root = deleteMin(root);
		if (!isEmpty())
			root.color = BLACK;
	}

	/**
	 * Delete the {key, value} with the minimum key rooted at element
	 */
	@Override
	protected Entry<Key, Value> deleteMin(Entry<Key, Value> elementH)
	{
		if (elementH.left == null)
			return null;

		if (!isRed(elementH.left) && !isRed(elementH.left.left))
			elementH = moveRedLeft(elementH.left);

		elementH.left = deleteMin(elementH.left);
		return balance(elementH);
	}

	/**
     * Removes the largest key and associated value from the symbol table.
     */
	@Override
	public void deleteMax()
	{
		if (isEmpty())
			throw new NoSuchElementException("BST underflow");

		// if both children of root are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMax(root);
		if (!isEmpty())
			root.color = BLACK;
	}

	/**
	 * Delete the {key, value} pair with the maximum key rooted at element
	 */
	@Override
	public Entry<Key, Value> deleteMax(Entry<Key, Value> elementH)
	{
		if (isRed(elementH.left))
			elementH = rotateRight(elementH);
		if (elementH.right == null)
			return null;
		if (!isRed(elementH.right) && !isRed(elementH.right.left))
			elementH = moveRedRight(elementH);
		elementH.right = deleteMax(elementH.right);

		return balance(elementH);
	}


	/***********************************************************************************
	 * Red Black Tree helper functions
	 ***********************************************************************************/
	/**
	 * makes a left leaning link lean to the right
	 */
	private Entry<Key, Value> rotateRight(Entry<Key, Value> elementH)
	{
		Entry<Key, Value> elementX = elementH.left;
		elementH.left = elementX.right;
		elementX.right = elementH;
		elementX.color = elementX.right.color;
		elementX.size = elementH.size;
		elementH.size = size(elementH.left) + 1 + size(elementH.right);
		return elementX;
	}

	/**
	 * makes a right leaning link lean to the left
	 */
	private Entry<Key, Value> rotateLeft(Entry<Key, Value> elementH)
	{
		Entry<Key, Value> elementX = elementH.right;
		elementH.right = elementX.left;
		elementX.left = elementH;
		elementX.color = elementX.left.color;
		elementX.size = elementH.size;
		elementH.size = size(elementH.left) + 1 + size(elementH.right);
		return elementX;
	}

	// flip the colors of a node and its two children
	private void flipColors(Entry<Key, Value> elementH)
	{
		// elementH must have opposite colors of its two children
		assert (elementH !=null) && (elementH.left !=null) && (elementH.right!=null);
		assert 	(!isRed(elementH) && isRed(elementH.left) && isRed(elementH.right)) ||
				(isRed(elementH) && !isRed(elementH.left) && !isRed(elementH.right));
		elementH.color = !elementH.color;
		elementH.left.color = !elementH.left.color;
		elementH.right.color = !elementH.right.color;
	}

	/** 
	 * Assuming that h is red and both h.left and h.left.left are black, 
	 * make h.left or one of its children red.
	 */
	private Entry<Key, Value> moveRedLeft(Entry<Key, Value> elementH)
	{
		flipColors(elementH);
		if (isRed(elementH.right.left))
		{
			elementH.right = rotateRight(elementH.right);
			elementH = rotateLeft(elementH);
			flipColors(elementH);
		}
		return elementH;
	}

	/** 
	 * Assuming that h is red and both h.right and h.right.left
	 * are black, make h.right or one of its children red.
     */
    private Entry<Key, Value> moveRedRight(Entry<Key, Value> elementH)
    {
    	flipColors(elementH);
    	if (isRed(elementH.left.left))
    	{
    		elementH = rotateRight(elementH);
    		flipColors(elementH);
    	}
    	return elementH;
    }

    /**
     * Restore red black tree invariant
     */
    private Entry<Key, Value> balance(Entry<Key, Value> elementH)
    {
    	if (isRed(elementH.right))
    		elementH = rotateLeft(elementH);
    	if (isRed(elementH.left) && isRed(elementH.left.left))
    		elementH = rotateRight(elementH);
    	if (isRed(elementH.left) && isRed(elementH.right))
    		flipColors(elementH);

    	elementH.size = size(elementH.left) + 1 + size(elementH.right);
    	return elementH;
    }

    /***********************************************************************************
	 * Red Black Tree integrity check
	 ***********************************************************************************/
    @Override
    protected boolean check()
    {
    	if (!isBST())
    		System.out.println("Not in symmetric order");
    	if (!isSizeConsistent())
    		System.out.println("Subtree counts not consistent");
    	if (!is23())
    		System.out.println("Not a 2-3 tree");
    	if (!isBalanced())
    		System.out.println("Not balanced");
    	return
    		isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }

    /**
     * Does the tree have no red right links?
     * and at most one 'left' red links in a row on any path?
     */
    private boolean is23() 
    {
    	return is23(root);
    }

    private boolean is23(Entry<Key, Value> elementH)
    {
    	if (elementH == null)
    		return true;
    	if (isRed(elementH.right))
    		return false;
    	if (elementH != root && isRed(elementH) && isRed(elementH.left))
    		return false;
    	return is23(elementH.left) && is23(elementH.right);
    }

    /**
     * Check if all paths from root to leaf have the same number of black edges
     */
   	private boolean isBalanced()
   	{
   		int black = 0;
   		Entry<Key, Value> element = root;
   		while (element != null)
   		{
   			if (!isRed(element))
   				black++;
   			element = element.left;
   		}
   		return isBalanced(root, black);
   	}

   	private boolean isBalanced(Entry<Key, Value> element, int black)
   	{
   		if (element == null)
   			return black == 0;
   		if (!isRed(element))
   			black--;
   		return isBalanced(element.left, black) && isBalanced(element.right, black);
   	}
}
