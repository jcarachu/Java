/**
 * Testing the LinkedStack data type
 */

public class LinkedStackMain{
	public static void main (String args [])
	{
		LinkedStack<String> stack = new LinkedStack<String>();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
		
		while(stack.size()!=0)
			System.out.println("Element: " + stack.pop() + ": Size "+ stack.size());
	}
}
