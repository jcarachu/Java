public class Solution {

    public static String convert(int N)
    {
        if (N == 1) { return "1"; }
        // int 0 or 1 automatically 
        // converted to String '0' or '1'
        return convert(N/2) + (N % 2);
    }
    public static void main(String[] args) {
        int N = 6;
        System.out.println(convert(N));
    }
}