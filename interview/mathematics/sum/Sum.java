import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class Sum {

	public List<Set<Integer>> naive(int arr [], int sum)
	{
		List<Set<Integer>> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = i + 1; j < arr.length; j++)
			{
				if (arr[i] + arr[j] == sum)
				{
					Set<Integer> temp = new HashSet<Integer>();
					temp.add(arr[i]);
					temp.add(arr[j]);
					list.add(temp);
				}
			}
		}

		printList(list);
		return list;
	}

	public void printList(List<Set<Integer>> list)
	{
		for (Set<Integer> set: list)
		{
			System.out.print("[ ");
			for(Integer element: set)
			{
				System.out.print(element + " ");
			}
			System.out.println(" ]");
		}
	}

	public static void main(String args[])
	{
		int arr [] = { 3, 5, 2, -4, 8, 11};
		int sum = 7; 
		List<Set<Integer>> list = new Sum().naive(arr, sum);
		//int fast = new Sum().faster(list);
	}
}