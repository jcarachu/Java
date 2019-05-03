public abstract class Sort<T extends Comparable<T>> {
	
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
	 * Sorting Output
	 ****************************************************/
	public void show(T arr[])
	{
		for (int i = 0; i < arr.length; i++)
		{
			System.out.println(arr[i]);
		}
	}
}
