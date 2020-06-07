class Solution {
    public final static int d = 256;

    public static void search(String pat, String txt, int q)
    {
        int M = pat.length();
        int N = txt.length();
        int p = 0;
        int t = 0;
        int h = 1;
        
        // The value of h would be
        // "pow(d, M-1) % q"
        for (int i = 0; i < M-1; i++)
        {
            h = (h * d) % q;
        }
        //  h will calculate the offset value

        System.out.println("INTIAL (H): " + h);
        // Calculate the hash value of the PATTERN window of text
        for (int i = 0; i < M; i++)
        {
            p = (d*p + pat.charAt(i)) %q;
            t = (d*t + txt.charAt(i)) %q;
        }

        System.out.println("INTIAL (P): " + p);
        System.out.println("INITAL (T): " + t);
        
        // Slide the pattern over text one by one
        for (int j,i = 0; i <= N - M; i++)
        {
            // Check the hash values of current window of the text
            // and pattern. If the hash values match then only check
            // for characters one by one
            if (p == t)
            {
                
                for (j = 0; j < M; j++)
                {
                    if (txt.charAt(i+j) != pat.charAt(j))
                        break;
                }

                if (j == M)
                {
                    System.out.println("Pattern found at index: " + i);
                }
                
            }
            
            // Recalculate hash value for next window of text:
            // Remove leading digit, add trailing digit
            if (i < N-M)
            {
                t = (d * (t - txt.charAt(i)*h) + txt.charAt(i + M)) % q;
                // We might get a negative value of t, convert it to positive;
                if (t < 0)
                {
                    t += q;
                }
            }

            System.out.println("i:" + i + " REHASH (P): " + p);
            System.out.println("i:" + i + " REHASH (T): " + t);
            System.out.println("----------------------------");
        }

    }

    public static void main(String[] args) {
        String txt = "GEEKS FOR GEEKS";
        String pat = "GEEK";
        int q = 101; // Prime Number
        search(pat, txt, q);
    }
}