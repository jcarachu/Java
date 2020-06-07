import java.util.*;

class Solution {
    /*
     * Given streaming data, find the medium in the largest top elements
     * ex. k = 3
     *  [1]          => 1
     *  [1,2]        => 1.5
     *  [1,2,3]      => 2
     *  [1,2,3,1]    => 2
     *  [1,2,3,1,10] => 3
     */

    public static Queue<Integer> maxHeap = new PriorityQueue<>((x, y) -> Integer.compare(x, y));
    public static Queue<Integer> minHeap = new PriorityQueue<>();
    public static Queue<Integer> kHeap = new PriorityQueue<>();
    public static int size;

    public static FindMedianOfTop(int k)
    {
        size = k;
    }

    public static void add(int val)
    {
        if (Heap.size() > k)
        {
            maxHeap.remove(kHeap.poll());
        }

        kHeap.add(val);
        maxHeap.add(val);
        minHeap.add(maxHeap.poll());

        if (maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());
    }

    public double getMedian()
    {
        return (maxHeap.size() + minHeap.size()) % 2 == 0 ? (maxHeap.peek()) * 0.5 : (double) maxHeap.peek();
    }

    public static void main(String[] args) {
        System.out.println("It is working.");
    }
}