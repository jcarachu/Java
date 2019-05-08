/**
 * The Selection class for sorting an array using selection sort
 * n exchanges, quadratic in best case
 * Not stable but In-Place sort.
 * Worst: (1/2) n^2
 * Average: (1/4) n^2
 * Best: n
 */
import java.util.Random;
public class Selection <T extends Comparable<T>> extends Sort<T> {
	/**
	 * Rearranges the array in ascending order using natural order
	 */
	public Selection() 
	{
		seed = System.currentTimeMillis();
		random = new Random();
	}
	
	@Override
	public void sort(T arr[])
	{
		int n = arr.length;
		for (int i = 0; i < n; i++)
		{
			int min = i;
			for (int j = i + 1; j < n; j++)
				if (less(arr[j], arr[min]))
					min = j;
			exch(arr, i, min);
			assert isSorted(arr, 0, i);
		}
		assert isSorted(arr);
	}
	
}

