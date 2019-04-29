/**
 * The Selection class for sorting an array using selection sort
 * n exchanges, quadratic in best case
 * Not stable but In-Place sort.
 * Worst: (1/2) n^2
 * Average: (1/4) n^2
 * Best: n
 */
import java.util.Comparator;
public class Selection <T extends Comparator<T>> {
	/**
	 * Rearranges the array in ascending order using natural order
	 */
	private Selection() {}

	/**
	 * Rearranges the array using comparator
	 */
	public void sort(T arr[], T comparator)
	{
		int n = arr.length;
		for (int i = 0; i < n; i++)
		{
			int min = i;
			for (int j = i +1; j < n; j++)
				if (less(comparator, arr[j], arr[min]))
					min = j;
			exchange(arr, i, min);
			assert isSorted(arr, comparator, 0, i);
		}
		assert isSorted(arr, comparator);
	}

	/****************************************************************
	 * Sorting functions
	 ****************************************************************/
	/**
	 * Check if v < w
	 */
	private boolean less(T comparator, T v, T w)
	{
		return comparator.compare(v, w) < 0;
	}
	
	/** 
	 * exchange a[i] and a[j]
	 */
	private void exchange(T arr[], int i, int j)
	{
		T swap = arr[i];
		arr[i] = arr[j];
		arr[j] = swap;
	}
	
	/****************************************************************
	 * Sorting check						    
	 ****************************************************************/
	/**
	 * is the array sorted
	 */
	public boolean isSorted(T arr[], T comparator)
	{
		return isSorted(arr, comparator, 0, arr.length - 1);
	}
	
	/**
	 * is the array sorted from a given range
	 */
	private boolean isSorted(T arr[], T comparator, int low, int high)
	{
		for (int i = low + 1; i <= high; i++)
			if(less(comparator, arr[i], arr[i-1]))
				return false;
		return true;
	}
	
	/****************************************************************
	 * Sorting display
	 ****************************************************************/
	private void show(T arr[])
	{
		for (int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
	}

}

