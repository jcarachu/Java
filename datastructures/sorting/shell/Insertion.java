/**
 * The insertion class implemention for sorting an array using insertion sort,
 * Not suitable for sorting large arbitrary arrays,
 * The number of exchanges is exactly equal to the number of inversions
 * Use for small or partially sorted arrays.
 * Stable and In-Place sort.
 * Worst: (1/2) n^2
 * Average: (1/2) n^4
 * Best: n
 */
import java.util.Random;
public class Insertion<T extends Comparable<T>> extends Sort<T> {
	
	public Insertion() 
	{
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}
	
	/**
 	 * Rearranges the array in ascending order using natural order
 	 */
	@Override
	public void sort(T arr[])
	{
		int n = arr.length;
		for (int i = 1; i < n; i++)
		{
			for (int j = i; j > 0 && less(arr[j], arr[j-1]); j--)
				exch(arr, j, j - 1);
			assert isSorted(arr, 0, i);		
		}
		assert isSorted(arr);
	}

	/**
 	 * Rearrages the subarray from low to high in ascending order
	 * using the natural order
 	 */
	public void sort(T arr[], int low, int high)
	{
		for (int i = low; i <= high; i ++)
			for (int j = i; j > low && less(arr[j], arr[j-1]); j--)
				exch(arr, j, j -1);
		assert isSorted(arr, low, high);
	}
	
} 
