/**
 * The Merge class provides an optimized version for the classic merge sort
 * Uses insertion sort for small subarrays,
 * Test whether array is in order,
 * Eliminates the time to taken to copy the aux array for merging
 * Therefore cutting the running time substantially
 * Stable but not In-Place sorting.
 * Worst:  n log n
 * Average: n log n
 * Best: (1/2) n log n
 */
public class Merge<T extends Comparable<T>> {
	
	private final int CUTOFF = 7;
	private Insertion<T> insertionsort;
	
	/**
	 * Initializes Insertion sorting with its respective generic
	 */
	public Merge()
	{
		insertionsort = new Insertion<T>();	
	}
	
	/**
	 * Sorting method creates a copy of the original once
	 * is then passed through to be sorted and or partitioned
	 * @param arr - array to be sorted
	 */
	public void sort(T arr[])
	{
		T aux[] = arr.clone();
		sort(aux, arr, 0, arr.length);
		assert insertionsort.isSorted(arr);
	}
	
	/**
	 * Sorting is accomplished if and when the range is less than or equal to the cutoff
	 * once the disired range is optiain insertionsort is then called to sort the small chuncks
	 * However only the copy will be modified and sorted until the merge phase of this method is
	 * reached, Each new instance of the stack, the copy will be reference as the destination
	 * @param src - the array to be referenced
	 * @param dst - the array to be modified
	 * @param low - the lower bound index
	 * @param high - the higher bound index
	 */
	private void sort(T src[], T dst[], int low, int high) 
	{
		if ((high - low) <= CUTOFF) { 
            		insertionsort.sort(dst, low, high);
            		return;
        	}
		int mid = low + (high - low) / 2;
		sort(dst, src, low, mid);
		sort(dst, src, mid+1, high);
		if (!insertionsort.less(src[mid+1], src[mid])) 
		{
			System.arraycopy(src, low, dst, low, high - low + 1);
            		return;
		}	

		merge(src, dst, low, mid, high);
	}
	
	/**
	 * Merging is accomplished by first verifiying if the lower and upper segments are sorted,
	 * proceding by locating where the lower bound and modifying the original array with the proper
	 * sorting order.
	 * @param src - the source array to be referenced
	 * @param dst - the destination array to be modified
	 * @param low - lower bound of the array
	 * @param mid - mid point of the array
	 * @param high - the upper bound of the array
	 */
	private void merge(T src[], T dst[], int low, int mid, int high) 
	{
		assert insertionsort.isSorted(src, low, mid);
		assert insertionsort.isSorted(src, mid+1, high);
		int i = low, j = mid+1;
        	for (int k = low; k <= high; k++) {
            		if	(i > mid)
				dst[k] = src[j++];
            		else if (j > high)          
				dst[k] = src[i++];
			else if (insertionsort.less(src[j], src[i])) 
				dst[k] = src[j++];
			else
				dst[k] = src[i++];
        	}
		
		assert insertionsort.isSorted(dst, low, high);
	}
	
	/**
	 * Displays the array's value
	 * Uses Insertion's show method.
	 */
	public void show (T arr[])
	{
		insertionsort.show(arr);
	}
}
