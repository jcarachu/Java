/**
 * The Shell class provides sorting using Shellsort with Knuth;s increment sequence
 * The idea is to rearrange the array to give it the property that taking every hth entry
 * could lead to a sorting array.
 * This is accomplished by finding a gap with, iterating through the gap at every segment,
 * until a single gap is left between the array a simple insertionsort will make the final 
 * evaluations but with fewer swaps, therefore tight code, and is subquadratic,
 * Stable but not In-Place Sort.
 * Worst: c n n^(3/2)
 * Average: -
 * Best: n log3 n
 */

public class Shell<T extends Comparable<T>> extends Insertion<T> {
	
	/**
	 * Initialize class
	 */
	public Shell() {}
	
	/**
	 * Rearranges the array in ascending order, using natural order.
	 * @param arr - the array to be sorted
	 */	
	public void sort(T arr[])
	{
		int num = arr.length;
		// 3x+1 increment sequence: 
		// { 1, 4, 13, 40, 121, 364, 1093, ... n}
		int gap = 1;
		// keeps incrementing until it reaches n.
		while (gap < num/3)
			h = 3 * h + 1;
		System.out.println("length is: " +arr.length);
		System.out.println("H is: "+h);
		// compare until reaches the first sequence
		while (gap >= 1)
		{
			// iterated through the entire gap
			for (int i = gap; i < num; i++) 
				// if index is greater than the gap and is less than its predecessor
				// keep swapping until false, every iteration swap decrement by its gap width 
				for (int j = i; j >=h && less(arr[j], arr[j-h]); j -=h)
					exchange(arr, j, j-h);
			assert isSorted(arr,h,arr.length);
			// decrement the gap further to make further comparisons
			gap /= 3;
		}
		assert isSorted(arr);
	}
	
}
