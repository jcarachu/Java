// what are our sets of characters?
// upper and lower cases?
// length of the string ?
class Solution {

    public String longestSubString(String a, String b)
    {
        String out = "";
        if (a.length() == 0 || b.length() == 0)
            return out;
        int[][] dp = new int[a.length()][b.length()];
        
        for (int i = 1; i < a.length(); i++)
        {
            for (int j = 1; j < b.length(); j++)
            {
                if (a.charAt(i) == b.charAt(j))
                {
                    if (i == 0 || j == 0)
                        dp[i][j] = 1;
                    else {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }

                    if (dp[i][j] > out.length())
                    {
                        out = a.substring(i - dp[i][j] + 1, i + 1);
                    }
                }
            }
        }
        return out;
    }
    public static void main(String[] args) 
    {
        
    }
}