/**
 * Testing the ArrayStack class
 */

public class Main {

	public static void main (String args [] )
	{
		ArrayStack<String> stack = new Stack<String>();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
		
		while(!stack.isEmpty())
		{
			System.out.println("Element: " + stack.pop());
		}
	}
}
