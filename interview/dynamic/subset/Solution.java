public class Solution {
    /*
     * - Given an array of non negative numbers and a total:
     * - is there a subset of numbers in this array which adds up to given total
     * - Another variation is given an array is it possible to split it up into 2 equal sum partitions
     * - Partition need not to be equal sized .. Just equal sum
     * 
     * Time Complexity - O(input.size * total_sum)
     * Space Complexity - O(input.size * total_sum)
     */
    public static boolean subsetsum(int[] input, int total)
    {
        boolean[][] T = new boolean[input.length + 1][total + 1];
        for (int row = 0; row <= input.length; row++)
        {
            T[row][0] = true;
        }

        for (int row = 1; row <= input.length; row++)
        {
            for (int column = 1; column <= total; column++)
            {
                if (column - input[row - 1] >= 0)
                    T[row][column] =  T[row - 1][column] || T[row - 1][column - input[row - 1]];
                else
                    T[row][column] = T[row - 1][column];
            }
        }
        
        return T[input.length][total];
    }
    
    public static boolean partition(int[] arr)
    {
        int sum = 0;
        for (int row = 0; row < arr.length; row++)
        {
            sum +=arr[row];
        }

        if (sum % 2 != 0)
        {
            return false;
        }

        sum /= 2;
        boolean[][] T = new boolean[arr.length + 1][sum + 1];
        for (int row = 1; row <= arr.length; row++)
        {
            T[row][0] = true;
        }

        for (int row = 1; row <= arr.length; row++)
        {
            for (int column = 1; column <= sum; column++)
            {
                if (column - arr[row - 1] >= 0)
                    T[row][column] = T[row-1][column - arr[row - 1]] || T[row - 1][column];
                else
                    T[row][column] = T[row-1][column];
            }
        }

        return T[arr.length][sum];
    }
    

    public static void main(String[] args) {
        int [] arr = new int [] {1, 3, 5, 5, 2, 1, 1, 6};
        System.out.println(partition(arr));

        arr = new int[] {2, 3, 7, 8};
        System.out.println(subsetsum(arr, 11));
    }
}