/**
 * The Selection class for sorting an array using selection sort
 */
public class Selection <T extends Comparable<T>> {
	/**
	 * Rearranges the array in ascending order using natural order
	 */
	private Selection() {}

	private void sort(T arr[])
	{
		int n = arr.length;
		for (int i = 0; i < n; i++)
		{
			int min = i;
			for (int j = i + 1; j < n; j++)
				if (less(arr[j], arr[min]))
					min = j;
			exchange(arr, i, min);
			assert isSorted(arr, 0, i);
		}
		assert isSorted(arr);
	}
	
	/****************************************************************
	 * Sorting functions 						*						    ****************************************************************/
	/**
	 *Check if v < w
	 */
	private boolean less(T v, T w)
	{
		return v.compareTo((T) w) < 0;
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
	 * Sorting check						*						    ****************************************************************/
	/**
	 * is the given array sorted
	 */
	private boolean isSorted(T arr[])
	{
		return isSorted(arr, 0, arr.length - 1);	
	}
	
	/**
	 * is the array sorted from a given range
	 */
	private boolean isSorted(T arr[], int low, int high)
	{
		for (int i = low + 1; i <= high; i++)
			if(less(arr[i], arr[i-1]))
				return false;
		return true;
	}

	/****************************************************************
	 * Sorting display 						*						    ****************************************************************/
	private void show(T arr[])
	{
		for (int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
	}

}

