public class Solution {
	public static void main(String args[])
	{
		int input[] = { 10, 2, 5, 1, 11, 3, 15, 0};
		System.out.println(findKthSmallest(input, 1));
	}

	public static int findKthSmallest(int arr[], int m)
	{
		int start = 0;
		int end = arr.length - 1;
		int index = m-1;

		while (start < end)
		{
			int pivot = partition(arr, start, end);
			if (pivot < index)
				start = pivot + 1;
			else if (pivot > index)
				end = pivot - 1;
			else
				return arr[pivot];
		}

		return arr[start];
	}

	private static int partition(int arr[], int start, int end)
	{
		int pivot = start;
		int temp;

		while (start <= end)
		{
			while (start <= end && arr[start] <= arr[pivot])
				start++;
			while (start <= end && arr[end] > arr[pivot])
				end--;
			if (start > end)
				break;
			temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
		}

		temp = arr[end];
		arr[end] = arr[pivot];
		arr[pivot] = temp;
		return end;
	}
}