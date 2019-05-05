import java.util.Random;
public class Quick <T extends Comparable<T>> extends Sort<T> {
	public Quick() 
	{
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}
	
	@Override
	public void sort(T arr[])
	{
		shuffle(arr);
		sort(arr, 0, arr.length - 1);
		assert isSorted(arr);
	}
	
	public void sort(T arr[], int low, int high)
	{
		if (high <= low)
			return;
		int j = partition(arr, low, high);
		sort(arr, low, j-1);
		sort(arr, j+1, high);
		assert isSorted(arr, low, high);
	}

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
	
	public void select(T arr[])
	{
		for (int i =0; i <arr.length; i++)
		{	
			T ith = select(arr, i);
		}
	}
	
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
