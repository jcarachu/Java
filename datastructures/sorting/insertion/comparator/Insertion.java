/**
 * The insertion class implemention for sorting an array using insertion sort
 * The implemention makes ~ 1/2 n^2 compares and exchanges in the worst case
 * The sorting algorithm is stable and uses O(1) space
 */
import java.util.Comparator;
public class Insertion<T extends Comparator<T>> {
	
	public Insertion() {}
	
	/**
 	 * Rearranges the array in ascending order, using a comparator
 	 */
	public void sort(T arr[], T comparator)
	{
		int n = arr.length;
		for (int i = 1; i < n; i++)
		{
			for (int j = i; j > 0 && less(arr[j], arr[j-1], comparator); j--)
				exchange(arr, i, j-1);
			assert isSorted(arr, 0, i, comparator); 
		}
		assert isSorted(arr, comparator);
	}
	
	/**
	 * Rearranges the subarry from low to high in ascending order using a comparator
	 */
	public void sort(T arr[], int low, int high, T comparator)
	{
		for (int i = low + 1; i < high; i++)
			for (int j = i; j > low && less(arr[j], arr[j-1], comparator); j--)
				exchange(arr, j, j -1);
		assert isSorted(arr, low, high, comparator);
	}
	
	/***************************************************************************
    	*  Sorting functions.
    	***************************************************************************/
	
	/**
	 * check if v < w
	 */
	private boolean less(T v, T w, T comparator)
	{
		return comparator.compare(v, w) < 0;
	}
	
	/**
	 * exchange value from i to j
	 */
	private void exchange(T arr[], int i, int j)
	{
		T swap = arr[i];
		arr[i] = arr[j];
		arr[j] = swap;
	}
	
	/***************************************************************************
    	*  Sorting check.
    	***************************************************************************/
	
	private boolean isSorted(T arr[], T comparator)
	{
		return isSorted(arr, 0, arr.length, comparator);
	}
	
	private boolean isSorted(T arr[], int low, int high, T comparator)
	{
		for (int i = low + 1; i < high; i++)
			if (less(arr[i], arr[i-1], comparator))
				return false;
		return true;
	}
	
	/***************************************************************************
    	* Sorting display.
    	***************************************************************************/
	public void show(T arr[])
	{
		for (int i = 0; i < arr.length; i ++)
			System.out.println(arr[i]);
	}
} 
