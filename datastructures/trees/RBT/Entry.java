/***********************************************************************************
 * Entry class for Red-Black Tree implementation 
 ***********************************************************************************/
public class Entry<Key extends Comparable<Key>, Value> {
	
	protected Key key;		// key
	protected Value value;	// associated value
	protected Entry<Key, Value> left;		// left subtree
	protected Entry<Key, Value> right;	// right subtree
	protected int size;		// subtree count
	protected boolean color; 	// color of parent link
	
	public Entry() {}
	/**
	 * Initialize element with given parameters
	 */
	public Entry(Key key, Value value, boolean color, int size)
	{
		this.key 	= key;
		this.value 	= value;
		this.size 	= size;
		this.color	= color;
		this.color 	= color;
	}
}