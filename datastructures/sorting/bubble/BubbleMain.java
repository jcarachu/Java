public class BubbleMain {
	public static void main(String args[])
	{
		Bubble<Integer> bubble = new Bubble<Integer>();
		Integer num[] = new Integer[100];
		for (int i = 0; i < 100; i++)
			num[i] = i;
		bubble.shuffle(num);
		bubble.sort(num);
		bubble.show(num);
	}
}
