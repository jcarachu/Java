public class InsertionMain {
	public static void main(String args[])
	{
		Insertion<Integer> insertion = new Insertion<Integer>();
		Integer num[] = new Integer[100];
		for (int i = 0; i < 100; i++)
			num[i] = i;
		insertion.shuffle(num);
		insertion.sort(num);
		insertion.show(num);
	}
}
