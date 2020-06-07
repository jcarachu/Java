public class Solution {
    public static void towersofhanoi(int n, String A, String B, String C)
    {
        if(n == 1)
        {
            printMoves(A, C);
            return;
        }

        towersofhanoi(n-1, A, B, C);
        printMoves(A, C);
        towersofhanoi(n-1, B, C, A);
        return;
        
    }

    public static void printMoves(String src, String des)
    {
        System.out.println(src + " : " + des);
    }
    public static void main(String[] args) {
        towersofhanoi(3, "A", "C", "B");
    }
}