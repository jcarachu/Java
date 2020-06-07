import java.util.Scanner;
public class Evaluate
{
	public static void main(String args[])
	{
		Stack<String> ops = new Stack<String>();
		Stack<String> values = new Stack<Double>();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext() == true)
		{
			String s = sc.next();
			if (s.equals("("));
			else if (s.equals("+"))
				ops.push(s); 
			else if (s.equal("*"))
				ops.push(s);
			else if (s.equals(")"))
			{
				String op = ops.pop();
				if (op.equals("+"))
					values.push(values.pop() + values.pop());
				else if (op.equals("*"))
					values.push(values.pop() * values.pop());
			} else
				values.push(Double.parseDouble(s));
		}
		
		System.out.println(values.pop());
	}
}
