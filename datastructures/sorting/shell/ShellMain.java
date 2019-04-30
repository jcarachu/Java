public class ShellMain {
	public static void main(String args[])
	{
		Integer numbers [] = { 1,9,4,7,8,5,2,3,6};
		Shell<Integer> shell = new Shell<Integer>();
		shell.sort(numbers);
		shell.show(numbers);
	}
}
