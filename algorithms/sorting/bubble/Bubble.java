/**
 * The Bubble class provides sorting an array using bubble sort,
 * Best case for this sort is when few passes of bubble sort are needed,
 * Stable and In-Place sort.
 * Worst: (1/2) n^2
 * Average: (1/2) n^2
 * Best: n
 */
import java.util.Random;
public class Bubble<T extends Comparable<T>> extends Sort<T>
{
	/**
	 * Intialize bubble class
	 */
	public Bubble() 
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
		int num = arr.length;
		for (int i = 0; i < num; i++)
		{
			int exchanges = 0;
			for (int j = num-1; j > i; j--)
			{
				if (less(arr[j], arr[j-1]))
				{
					exch(arr, j, j-1);
					exchanges++;
				}
			}
			if (exchanges == 0)
				break;
		}
	}

}
