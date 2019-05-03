/**
 * The Heap class for heapsorting an array
 * Heap sort beaks into two phases: Heap construction and sortdown
 * Achieves sorted result by pulling items out of the heap in decreasing order
 * Not stable but In-Place sorting algorithm.
 * Worst: 2n log n
 * Average: 2n log n
 * Best: n (assumung if all keys are distinct)
 */
public class Heap<T extends Comparable<T>> extends Sort<T>{
	
	public Heap() {}
	
	public void sort(T pq[])
	{
		int n = pq.length-1;
		// ~Heap Construction
		// reorganize the original arry into a heap.
		for (int k = n/2; k > 0; k--)
			sink(pq, k, n);
		// ~Sort Down, 
		// Accomplished by removing the largest remaining items 
		// from the heap and putting it into the array positioned 
		// vacated as the heap shrinks.
		while (n > 0)
		{
			// pull the items out of the heap in decreasing order,
			// exchange first and last postion of the tree
			exch(pq, 0, n);
			// reorders the queue to accommodate the misplacement
			sink(pq, 1, n);
			// decrement the size
			n--;	
		}
	
	}
	
	
	/**********************************
	 * Sorting functions - restore heap
	 **********************************/
	/**
	 * Proceeds from right to left to creates subheaps dynamically
	 * Every position in the array is the root of a small subheap
	 * @param pq - the arr to be sorted
	 * @param k - the left index
	 * @param n - the length the heap to be sorted  
	 */	
	private void sink(T pq[], int k, int n)
	{
		while (2*k <= n)
		{
		
			int j = 2 * k;
			if (j < n && less(pq, j-1, j))
				j++;
			if (!less(pq, k-1, j-1))
				break;
			exch(pq, k-1, j-1);
			k = j;
		}
			
	}
	
	/**********************************
	 * Sorting functions - evaluate
	 **********************************/
	/**
	 * Heap sort comparision method for arrays
	 * @param pq - array to be sorted
	 * @param i - the position of the left side of the subheap
	 * @param j - the postion of the right side of the subheap
	 */
	private boolean less(T pq[], int i, int j)
	{
		return pq[i].compareTo(pq[j]) < 0;
	}
	
}
