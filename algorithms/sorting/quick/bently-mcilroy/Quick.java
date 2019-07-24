/*
 * Quick Sort utilizing the Bently-McIlroy 3 way partitioning scheme,
 * chooses the partitioning element using Turkey's ninther, and cuts off
 * to insertion sort.
 */
import java.util.Random;
public class Quick<T extends Comparable<T>> extends Sort<T>  {
	private final int INSERTION_SORT_CUTOFF = 8;
	private final int MEDIAN_OF_3_CUTOFF = 40;
	private Insertion<T> insertion;
	/**
	 * Intialize insertion object for insertion sort
	 */
	public Quick() 
	{
		insertion = new Insertion<T>();
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}
	
	/**
	 * Rearranges the array in ascending oder, using natural order.
	 * @param arr - the array to be sorted 
	 */
	@Override
	public void sort(T arr[])
	{
		sort(arr, 0, arr.length - 1);
		assert isSorted(arr);
	}
	
	/**
	 * Recursive sort function to keep partioning to find the median element,
	 * ~ if sub arry is less than the insertioncutoff, do insertion sort,
	 * ~ if greater than but less than median cutoff find the median and apply
	 * Bentley-McIlroy 3 way partioning, 
	 * ~ if sub array is greater than 3 way partitioning, divide and partition to 
	 * find the median follow by a Bently-McIlroy 3 way partioning,
	 * ~ continue to divide and sort lower and upper bounds until fully sorted.
	 */
	private void sort(T arr[], int low, int high)
	{
		int n = high - low + 1;
		// cutoff to insertion sort
		if (n <= INSERTION_SORT_CUTOFF)
		{
			insertion.sort(arr,low,high);
			return;
		}
		
		// else median of 3 as partitioning element
		else if (n <= MEDIAN_OF_3_CUTOFF)
		{
			int m = median3(arr, low, low + n/2, high);
			exch(arr, m, low);
		}
		
		// else Turkey ninther as partioning element
		else
		{
			int eps = n/8;
			int mid = low + n/2;
			int m1 = median3(arr, low, low + eps, low + eps + eps);
			int m2 = median3(arr, mid - eps, mid, mid + eps);
			int m3 = median3(arr, high - eps - eps, high - eps, high);
			int ninther = median3(arr, m1, m2, m3);
			exch(arr, ninther, low);
		}
		
		// Bently-McIlroy 3 way partioning
		int i = low, j = high + 1;
		int p = low, q = high + 1;
		// p : left side
		// q : right side
		T v = arr[low];
		// iterate through the sub array
		while (true)
		{
			while (less(arr[++i], v))
				if (i == high)
					break;
			while (less(v, arr[--j]))
				if (j == low)
					break;	
			
			// pointers cross
			if (i == j && equals(arr[i], v))
				exch(arr, ++p, i);
			if (i >= j)
				break;
			
			exch(arr, i, j);
			
			if (equals(arr[i], v))
				exch(arr, ++p, i);
			if (equals(arr[j], v))
				exch(arr, --q, j);
		}
		
		i = j + 1;		
		// shift right side to middle
		for (int k = low; k <= p; k++)
			exch(arr, k, j--);
		
		// shift left side to middle
		for (int k = high; k >= q; k--)
			exch(arr, k, i++);

		sort(arr, low, j);
		sort(arr, i, high);
	}
	
	/**
	 * Determine the index of the median element
	 * @return - median element amonng arr[i], arr[j], arr[k]
	 */
	private int median3(T arr[], int i, int j, int k)
	{
		return  (less(arr[i], arr[j]) ?
			(less(arr[j], arr[k]) ? j : less(arr[i], arr[k]) ? k : i) :
			(less(arr[k], arr[j]) ? j : less(arr[k], arr[i]) ? k : i));
	}		
}
