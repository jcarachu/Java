public class SelectionMain {
	public static void main(String args[])
	{
		Selection<Integer> selection = new Selection<Integer>();
		Integer num[] = new Integer[100];
		for (int i = 0; i < 100; i++)
			num[i] = i;
		selection.shuffle(num);
		selection.sort(num);
		selection.show(num);
	}
}
