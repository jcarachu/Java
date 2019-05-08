public class QuickMain {
	public static void main(String args[])
	{
		Quick<Integer> quick = new Quick<Integer>();
		Integer num[] = new Integer[100];
		for(Integer i = 0; i < 100; i++)
			num[i] = i;
		quick.shuffle(num);
		quick.sort(num);
		quick.show(num);
	}
}
