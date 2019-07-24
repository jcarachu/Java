public class MergeMain {
	public static void main(String args[])
	{
		Merge<Integer> merge = new Merge<Integer>();
		Integer num[] = new Integer[100];
		for (int i = 0; i < 100; i++)
			num[i] = i;
		merge.shuffle(num);
		merge.sort(num);
		merge.show(num);
	}
}
