/*
 * Compute the Greatest Common Divisor (GCD) of a & b
 */
public class Solution {
    public static long gcd (long a, long b)
    {
        return b == 0 ? (a < 0 ? -a : a) : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(gcd(21,30));
    }
}