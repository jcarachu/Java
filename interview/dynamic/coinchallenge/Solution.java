class Solution {
    /*
     * Given a total and coins of certain denominations find number of ways the 
     * total can be formed from coins assuming inifinity supply of coins
     */

    public static int numberofsolutions(int total, int coins[])
    {
        int[][] temp = new int[coins.length+1][total + 1];
        for (int row = 0; row <= coins.length; row++)
        {
            temp[row][0] = 1;
        }

        for (int row = 1; row <= coins.length; row++)
        {
            for (int column = 1; column <= total; column++)
            {
                if (coins[row - 1] > column)
                    temp[row][column] = temp[row - 1][column];
                else 
                    temp[row][column] = temp[row][column - coins[row - 1]] + temp[row - 1][column];
            }
        }

        return temp[coins.length][total];
    }

    public static int numberofsolutionspace(int total, int arr[])
    {
        int[] temp = new int[total + 1];
        temp[0] = 1;
        for (int row = 0; row < arr.length; row++)
        {
            for (int column = 1; column <= total; column++)
            {
                if (column >= arr[row])
                    temp[column] += temp[column - arr[row]];
            }
        }

        return temp[total];
    }

    public static void main(String[] args) {
        int total = 15;
        int[] coins = new int[] {3,4,6,7,9};
        System.out.println(numberofsolutions(total, coins));
        System.out.println(numberofsolutionspace(total, coins));
    }
}