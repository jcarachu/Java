/***********************************************************************************
 * Entry class for AVL implementation 
 ***********************************************************************************/
public class Entry<Key extends Comparable<Key>, Value> {

	protected 		Key key;		// key
	protected 		Value value;	// associated value
	protected 		Entry<Key, Value> left;		// left subtree
	protected 		Entry<Key, Value> right;	// right subtree
	protected 		int size;
	protected 		int height;
		
	/**
	 * Initialize element with given parameters
	 */

	public Entry() {}
		
	public Entry(Key key, Value value, int height, int size)
	{
		this.key = key;
		this.value = value;
		this.size = size;
		this.height = height;
	}
}
