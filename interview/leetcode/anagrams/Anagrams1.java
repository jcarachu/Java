import java.util.HashMap;
import java.util.Map;;

public class Anagrams1 {
    public int kSimilarity(String A, String B) {
        
        Map<String, Integer> map = new HashMap<>();
        return backtrack(A.toCharArray(), B, map, 0);
    }

    private static int backtrack(char[] A, String B, Map<String, Integer> map, int i) {
        
        String sa = new String(A);
        if (sa.equals(B)) {
            return 0;
        }

        if (map.containsKey(sa)) {
            return map.get(sa);
        }

        int min = Integer.MAX_VALUE;
        while (i < A.length && A[i] == B.charAt(i)) {
            i++;
        }
        
        for (int j = i + 1; j < B.length(); j++) {
            if (A[j] == B.charAt(i)) {
                swap(A, i, j);
                int next = backtrack(A, B, map, i + 1);
                if (next != Integer.MAX_VALUE) {
                    min = Math.min(min, next + 1);
                }
                swap(A, i, j);
            }
        }
        map.put(sa, min);
        return min;
    }

    private static void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

    public static void main(String[] args) {
        
    }
}