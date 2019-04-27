/**
 * The Bubble class provides sorting for an array using bubble sort
 * This implementation worst case makes ~ 1/2 n^2 compares
 * Best case for few passes of bubble sort are needed
 * This sorting algorithm is stable and uses O(1) space
 */

public class Bubble<T extends Comparable<T>>
{
	/**
	 * Intialize bubble class
	 */
	public Bubble() {}
	
	/**
	 * Rearranges the array in ascending order using natural order
	 */
	public void sort(T arr[])
	{
		int num = arr.length;
		for (int i = 0; i < num; i++)
		{
			int exchanges = 0;
			for (int j = num-1; j > i; j--)
			{
				if (less(arr[j], arr[j-1]))
				{
					swap(arr, j, j-1);
					exchanges++;
				}
			}
			if (exchanges == 0)
				break;
		}
	}
	
	/**
	 * Check if v < w?
	 */
	private boolean less(T v, T w)
	{
		return v.compareTo(w) < 0;
	}
	
	/**
	 * Swap arr[i] and arr[j]
	 */
	private void swap(T arr[], int i, int j)
	{
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/**
	 * Print the array out
	 */
	public void show(T arr[])
	{
		for (int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
	}
}
