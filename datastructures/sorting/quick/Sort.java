import java.util.Random;
public abstract class Sort<T extends Comparable<T>> {
	
	protected Random random;
	protected long seed;
	/****************************************************
	 * Sorting Abstracts
	 ***************************************************/
	public abstract void sort(T arr[]);
	
	/****************************************************
	 * Sorting Functions
	 ***************************************************/
	public boolean less(T v, T w)
	{
		return v.compareTo(w) < 0;
	}
	
	public void exch(T arr[], int i, int j)
	{
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp; 
	}
	
	/****************************************************
	* Sorting Check
	***************************************************/
	public boolean isSorted(T arr[])
	{
		return isSorted(arr, 0, arr.length - 1);
	}
	
	public boolean isSorted(T arr[], int low, int high)
	{
		for (int i = low + 1; i <= high; i++)
			if (less(arr[i], arr[i-1]))
				return false;
		return true;
	}
	
	/****************************************************
	 * Sorting Output
	 ****************************************************/
	public void show(T arr[])
	{
		for (int i = 0; i < arr.length; i++)
		{
			System.out.println(arr[i]);
		}
	}
	
		
	/****************************************************
	 * Additional Functions
	 ****************************************************/
	public void shuffle(T arr[])
	{
		if (arr == null)
			throw new IllegalArgumentException("Array is null");
		int n = arr.length;
		for (int i = 0; i < n; i++)
		{
			if ((n -i) >= 0)
			{
				int r = i + random.nextInt(n - i);
				T temp = arr[i];
				arr[i] = arr[r];
		 		arr[r] = temp;
			} else 
				throw new IllegalArgumentException("argument must be positive: " + n);
		}
	}	
	
}
