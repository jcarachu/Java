/**
 * Quick sort 3 way implementation of generic type arrays
 * 3-way partioning is writen for clarity rather than optimal performance,
 * exhibits poor locality and performs more swaps than necessary.
 * Not stable but In-Place sort
 * Worst: n^2
 * Average: n log n
 */
import java.util.Random;
public class Quick<T extends Comparable<T>> extends Sort<T> {
	
	/**
	 * Initialize quick object and random
	 */
	public Quick() 
	{
		seed = System.currentTimeMillis();
		random = new Random(seed);		
	}
	
	/**
	 * Sort arry utilizing quick sort
	 * @param arr- array to be sorted
	 */
	public void sort(T arr [])
	{
		sort(arr, 0, arr.length - 1);
		assert(isSorted(arr));
	}
	
	/**
	 * Recursive sort method to sort along its  range and create 
	 * sub array, while grouping all duplicates in a specified
	 * location to continuous shrink its sub arrays and sort the
	 * remaining lower and upper bounds.
	 * @param arr- array to be sorted
	 * @param low - low index of the subarray
	 * @param high - high array of the subarray
	 */
	private void sort(T arr[], int low, int high)
	{
		if (high <= low)
			return;
		int lessthan = low;
		int greaterthan = high;
		T v = arr[low];
		int i = low + 1;
		while (i <= greaterthan)
		{
			// compare i to the lowest
			int compare = arr[i].compareTo(v);
			// left side (less)
			if (compare < 0)
				exch(arr, lessthan++, i++);
			// right side (greater)
			else if (compare > 0)
				exch(arr, i, greaterthan--); 
			else
				i++;
		}
		// arr[low .. lt - 1] < arr[lt .. gt] < arr[gt + 1 .. hi]		
		sort(arr, low, lessthan - 1);
		sort(arr, greaterthan + 1, high);
		assert isSorted(arr, low, high);
	}
}
