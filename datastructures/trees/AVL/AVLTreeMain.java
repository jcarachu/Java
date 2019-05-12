public class AVLTreeMain {
	public static void main(String args[])
	{
		AVLTree<String, Integer> avl = new AVLTree<String, Integer>();
		String str [] = {"A","C","E","H","L","M","P","R","S","X"};
		for (Integer i = 0; i < str.length; i++)
		{
			avl.put(str[i],i);
		}
		
		System.out.println("--Start--");		
		for (String s: avl.keys())
		{	
			System.out.println(s + " " + avl.get(s));
		}
		System.out.println("--End--");
	}
}
