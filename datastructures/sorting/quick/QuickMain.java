public class QuickMain {
	public static void main(String args[])
	{
		Quick<Integer> quick = new Quick<Integer>();
		Integer num[] = { 1,2,3,4,5,6,7,8,9};
		quick.shuffle(num);
		quick.select(num);
		quick.show(num);
	}
}
