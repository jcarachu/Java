public class RedBlackMain {
	
	public static void main(String args[])
	{
		RedBlack<String, Integer> rb = new RedBlack<String, Integer>();
		char letter = 'a';
		for (int i = 0; i < 26; i++)
			rb.put(String.valueOf(letter++), i);

		for(String keys: rb.keys())
			System.out.println( keys + " : " + rb.get(keys));
	}
}