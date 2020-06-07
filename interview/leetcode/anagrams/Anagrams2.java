public class Anagrams2
{
    public static void main(String[] args) 
    {
        String test1 = "ab";
        String test2 = "b";
        areAnagrams(test1, test2);
    }

    private static boolean areAnagrams(String first, String second)
    {
        return areAnagrams("", first, second);
    }

    private static boolean areAnagrams(String soFar, String remaining, String target)
    {
        if (remaining.length() == 0)
            return soFar.equals(target);
        
            for (int i = 0; i < remaining.length(); i++)
            {
                String whatsLeft =  remaining.substring(0, i) +
                                    remaining.substring(i+1);
                System.out.println(whatsLeft);
                if (areAnagrams(soFar + remaining.charAt(i), whatsLeft, target))
                    return true;
            }
        
        return false;
    }
}