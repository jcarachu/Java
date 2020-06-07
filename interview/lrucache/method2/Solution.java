import java.util.Map;
import java.util.HashMap;

class Solution {
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

        public void putEntry(int key, int value)
        {
            if (hashmap.containsKey(key))
            {
                Entry entry = hashmap.get(key);
                entry.value =  value;
                removeNode(entry);
                addAtTop(entry);
            } else {
                Entry newnode = new Entry();
                newnode.left = null;
                newnode.right = null;
                newnode.value = value;
                newnode.key = key;
                if (hashmap.size() > LRU_SIZE)
                {
                    hashmap.remove(tail.key);
                    removeNode(tail);
                    addAtTop(newnode);
                } else {
                    addAtTop(newnode);
                }

                hashmap.put(key, newnode);
            }

        }

        public void addAtTop(Entry node)
        {
            node.right = head;
            node.left = null;
            if (head != null)
            {
                head.left = node;
            }

            head = node;
            if (tail == null)
                tail = head;
        }

        public void removeNode(Entry node)
        {
            if (node.left != null)
            {
                node.left.right = node.right;
            } else {
                head = node.right;
            }

            if (node.right != null)
            {
                node.right.left = node.left;
            } else {
                tail = node.left;
            }
        }
    }

    public static void main(String[] args) {
        
        LRUCache lrucache = new LRUCache();
		lrucache.putEntry(1, 1);
		lrucache.putEntry(10, 15);
		lrucache.putEntry(15, 10);
		lrucache.putEntry(10, 16);
		lrucache.putEntry(12, 15);
		lrucache.putEntry(18, 10);
        lrucache.putEntry(13, 16);
        
        System.out.println(lrucache.getEntry(1));
		System.out.println(lrucache.getEntry(10));
		System.out.println(lrucache.getEntry(15));
    }
}