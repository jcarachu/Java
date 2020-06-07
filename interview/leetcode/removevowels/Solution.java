import java.util.HashSet;
import java.util.Set;

class Solution {
    public static String removeVowels(String s)
    {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray())
        {
            if (!vowels.contains(c))
                result.append(c);
        }

        return result;
    }

    public static void main(String[] args) {
        
    }
}