class Solution {
	public static void main(String[] args) {
	}
	
	public static class Entry {
		int value;
		int key;
		Entry left;
		Entry right;
	}
	
	public static class LRUCache {
		Map<Integer, Entry> hashmap;
		Entry head, tail;
		int LRU_SIZE = 7;
		
		public LRUCache()
		{
			hashmap = new HashMap<Integer, Entry>();
		}
		
		public int getEntry(int key)
		{
			if (hashmap.containsKey(key))
			{
				Entry entry = hashmap.get(key);
				removeNode(entry);
				addAtTop(entry);
				return entry.value;
			}
			
			return -1;
		}
		
		public void removeNode(Entry node)
		{
			if (node.left != null)
			{
				node.left.right = node.right;
			}
		}
	}
}