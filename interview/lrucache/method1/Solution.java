/*
 * Given the total possible page numbers that can be referred, and
 * also give cache size. The LRU
 */
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;

class Solution {
    static Deque<Integer> dq;
    static HashSet<Integer> map;
    static int csize;

    public static void LRUCache(int n)
    {
        dq = new LinkedList<>();
        map = new HashSet<>();
        csize = n;
    }

    public static void refer(int x)
    {
        if (!map.contains(x))
        {
            if (dq.size() == csize)
            {
                map.remove(dq.removeLast());
            }
        } else {
            int index = 0;
            int i = 0;
            Iterator<Integer> itr = dq.iterator();
            while(itr.hasNext())
            {
                if (itr.next() == x)
                {
                    index = i;
                    break;
                }
                i++;
            }
            dq.remove(index);
        }
        dq.push(x);
        map.add(x);
    }

    public static void display()
    {
        Iterator<Integer> itr = dq.iterator();
        while (itr.hasNext())
        {
            System.out.print(itr.next() + " ");
        }
    }

    public static void main(String[] args) {        
        LRUCache(4); 
        refer(1); 
        refer(2); 
        refer(3); 
        refer(1); 
        refer(4); 
        refer(5); 
        display();
    }
}