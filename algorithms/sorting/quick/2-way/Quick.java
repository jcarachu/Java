/**
 * Quick sort implementation utilizing generics,
 * A divide and conquer method for sorting, works by partitioning an array into two parts,
 * then sorting the parts independently.There it achieves a complete sort by partitioning,
 * then recursively applying the methid to the subarrays, Its is a randomized algorithm,
 * because it randomly shuffles the array before sorting it.
 * Not stable but In-Place sort
 * Worst: (1/2) n^2
 * Average: 2n log n
 * Best: n log n
 */
import java.util.Random;
public class Quick <T extends Comparable<T>> extends Sort<T> {
	/**
	 * Intialize quick sort and randomness
	 */
	public Quick() 
	{
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}
	
	/**
	 * Abstract recurisve sort method
	 * @param arr - array to be sorted
	 */
	@Override
	public void sort(T arr[])
	{
		shuffle(arr);
		sort(arr, 0, arr.length - 1);
		assert isSorted(arr);
	}
	
	/**
	 * Array will compared from its high and low index,
	 * and sorted according to its value,
	 * Leftside of pivot less than, Right side greater
	 * @param array - to be sorted
	 * @param low - low index position
	 * @param high - high index postion
	 */
	public void sort(T arr[], int low, int high)
	{
		if (high <= low)
			return;
		int j = partition(arr, low, high);
		sort(arr, low, j-1);
		sort(arr, j+1, high);
		assert isSorted(arr, low, high);
	}
	
	/**
	 * Recursive partition to find its pivot point,
	 * compares its next avaiable position to its lowest and highest index,
	 * once its meets its intersection, its respective pivot is found.
	 * @param arr - array to be sorted
	 * @param low - low index position to compare its next value ++ 
	 * @param high - high index position to compare its next value --
	 */
	private int partition(T arr[], int low, int high)
	{
		int i = low;
		int j = high + 1;
		T v = arr[i];
		while (true)
		{
			// find element low to swap
			while (less(arr[++i], v))
				if (i == high)
					break;
			// find element on high to swap
			while (less(v, arr[--j]))
				if (j == low)
					break;
			// Check if pointers cross
			if (i >= j)
				break;
			exch(arr, i, j);
		}
		
		// Put partitioning item v at arr[j]
		exch(arr, low, j);
		// Now, a[low .. j-1] <= a[j] <= a[j+1 .. high]
		return j;
	}
	
	/**
	 * Non recursive quick sort method
	 * @param arr - array to be sorted
	 */	
	public void select(T arr[])
	{
		shuffle(arr);
		for (int i =0; i <arr.length; i++)
		{	
			T ith = select(arr, i);
		}
	}
	
	/**
	 * Array partitioned and sorted up to position k,
	 * keeps iterating down until k position is reached,
	 * partition position is compared with position k,
	 * if not equal, keeps decrementing/incrementing and 
	 * reiterates until matched
	 * @param arr - array to be sorted
	 * @param k - postion to be iterated to 
	 */
	private T select(T arr[], int k)
	{
		if (k < 0 || k >= arr.length)
			throw new IllegalArgumentException("Index is not between 0 and " + arr.length + ": " + k);
		int low = 0;
		int high = arr.length - 1;
		while (high > low)
		{
			int i = partition(arr, low, high);
			if (i > k)
				high = i -1;
			else if (i < k)
				low = i + 1;
			else
				return arr[i];
		}
		return arr[low];
	}
}
