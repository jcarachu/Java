import java.util.*;

class Solution {
	public static void main(String[] args) {
		generate();
		//System.out.println(generateAbbreviations("word"));
	}
	
//	public List<String> generateAbbreviations(String word)
//	{
//		List<String> result = new ArrayList<>();
//		result.add(word);
//		generateHelper(0, word, result);
//		return result;
//
//	}
	
	public static void generate()
	{
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j + i <  10; j++)
			{
				System.out.println("i:"+ i + " | j:" + j);
			}
			System.out.println("----------");
		}
	}
	
//	public void generateHelper(int start, String s, List<String> result)
//	{
//		if (start >= s.length())
//			return;
//			
//		for (int i = start; i < s.length(); i++)
//		{
//			for (int j = 1; i + j <= s.length(); j++)
//			{
//				String num = Integer.toString(j);
//				String abbr = s.substring(0, i) + num + s.substring(i+j);
//			}
//		}
//	}
	
}