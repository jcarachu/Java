/**
 * Sorts a sequence of of generic elements using dual pivot quicksort
 * Dual pivot quick sort takes two pivots:
 * ~ one in the left end of the array
 * ~ one in the right end of the array
 * The left pivot must be less than or equal to the right pivot, swap if necessary.
 * Worst: n^2
 * Average: n log n
 * Best: n log n
 * 
 */
import java.util.Random;
public class Quick<T extends Comparable<T>> extends Sort<T> {
	
	public Quick()
	{
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}
	
	/**
	 * Quicksort the given array using pivot quicksort
	 * @param arr - the array to be sorted
	 */
	@Override
	public void sort(T arr[])
	{
		sort(arr, 0, arr.length - 1);
		assert isSorted(arr);	
	}
	
	/**
	 * Quicksort the subarray low to high using dual pivot quicksort
	 */
	private void sort(T arr[], int low, int high)
	{
		if (high <= low)
			return;
		
		// check  arr[low] is less or equal to arr[high]
		if (less(arr[high], arr[low]))
			exch(arr,low, high);
		
		int lessthan = low + 1;
		int greaterthan = high - 1;
		int i = low + 1;
		// iterate through the array until crosses greaterthan pos
		while (i <= greaterthan)
		{
			// if element is less than low
			if (less(arr[i], arr[low]))
				exch(arr, lessthan++, i++);
			// if element is greater than high
			else if (less(arr[high], arr[i]))
				exch(arr, i, greaterthan--);
			// if equal then cross
			else
				i++;
		}
		
		// update low element, with lessthan
		exch(arr, low, --lessthan);
		// update high element with greaterthan
		exch(arr, high, ++greaterthan);
		
		// recursively sort three subarrays
		sort(arr, low, lessthan - 1);
		// sort mid partition
		if (less(arr[lessthan], arr[greaterthan]))
			sort(arr, lessthan+1, greaterthan-1);
		// sort upper partition
		sort(arr, greaterthan + 1, high);
		assert isSorted(arr, low, high);
	}
}
