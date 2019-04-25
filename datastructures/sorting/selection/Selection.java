import java.util.Comparator;

public class Selection <E> {
	/**
	 * Rearranges the array in ascending order using natural order
	 */
	private Selection() {}

	private void sort(Comparable<E>[] arr)
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
	
	/**
	 * Rearranges the array using comparator
	 */
	public void sort(Object[] arr, Comparator<E> comparator)
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
	 * Sorting functions 						*						    ****************************************************************/
	/**
	 *Check if v < w
	 */
	@SuppressWarnings("unchecked")
	private boolean less(Comparable<E> v, Comparable<E> w)
	{
		return v.compareTo((E) w) < 0;
	}

	/** 
	 * Check if v < w
	 */
	@SuppressWarnings("unchecked")
	private boolean less(Comparator<E> comparator, Object v, Object w)
	{
		return comparator.compare((E) v,(E) w) < 0;
	}
	
	/** 
	 * exchange a[i] and a[j]
	 */
	private void exchange(Object[] arr, int i, int j)
	{
		Object swap = arr[i];
		arr[i] = arr[j];
		arr[j] = swap;
	}
	
	/****************************************************************
	 * Sorting check						*						    ****************************************************************/
	/**
	 * is the given array sorted
	 */
	private boolean isSorted(Comparable<E>[] arr)
	{
		return isSorted(arr, 0, arr.length - 1);	
	}
	
	/**
	 * is the array sorted from a given range
	 */
	private boolean isSorted(Comparable<E>[] arr, int low, int high)
	{
		for (int i = low + 1; i <= high; i++)
			if(less(arr[i], arr[i-1]))
				return false;
		return true;
	}
	
	/**
	 * is the array sorted
	 */
	public boolean isSorted(Object[] arr, Comparator<E> comparator)
	{
		return isSorted(arr, comparator, 0, arr.length - 1);
	}
	
	/**
	 * is the array sorted from a given range
	 */
	private boolean isSorted(Object[] arr, Comparator<E> comparator, int low, int high)
	{
		for (int i = low + 1; i <= high; i++)
			if(less(comparator, arr[i], arr[i-1]))
				return false;
		return true;
	}
	
	/****************************************************************
	 * Sorting display 						*						    ****************************************************************/
	private void show(Comparable<E>[] arr)
	{
		for (int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
	}

}

