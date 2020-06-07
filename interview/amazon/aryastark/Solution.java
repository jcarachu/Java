class Solution {

    /**
     * Calculates the total possibilities of Araya winning the battle satisfying the given conditions
     * @param p
     * @param r
     * @return
     */

    public static int winsPossibelCounter(int p, int r)
    {
        /*
         * Aray always has to win first p number of rounds
         */
        int totalPossibilities = fight(p,0,0,p,r);
        return totalPossibilities % (10 ^ 9 + 7);
    }

    /**
     * Recursive function that simulates all possible combinations for N number of rounds
     * @param arayaWins
     * @param sansaWins
     * @param possibilities
     * @param p
     * @param r
     * @return
     */
    public static int fight(int arayaWins, int sansaWins, int possibilities, final int p, final int r)
    {
        // If Aray victory criteria fails then return current count
        if (arayaWins < sansaWins * p)
        {
            return possibilities;
        }

        if (arayaWins + sansaWins == r)
        {
            possibilities += 1;
            return possibilities;
        }

        return fight(arayaWins + 1, sansaWins, possibilities, p, r) + fight(arayaWins, sansaWins + 1, possibilities, p, r);
    }

    public static void main(String[] args) {
        System.out.println("Possibilities: " + winsPossibelCounter(2, 4));
    }
}