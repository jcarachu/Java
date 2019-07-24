public class ShellMain {
	public static void main(String args[])
	{
		Shell<Integer> shell = new Shell<Integer>();
		Integer num[] = new Integer[100];
		for (int i = 0; i < 100; i++)
			num[i] = i;
		shell.shuffle(num);
		shell.sort(num);
		shell.show(num);
	}
}
