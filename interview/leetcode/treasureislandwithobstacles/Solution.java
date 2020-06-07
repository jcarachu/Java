import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    public static final int[] dirs = {-1, 0, 1, 0, -1};

    public static int treasureIsland(char[][] islands)
    {
        if (islands.length == 0 || islands[0].length == 0 || islands[0][0] == 'D')
            return -1;
        
        int R = islands.length;
        int C = islands[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int steps = 1;
        queue.add(new int[] {0,0});
        islands[0][0] = 'D';

        while (!queue.isEmpty())
        {
            int size = queue.size();
            for (int i = 0; i < size; i++)
            {
                int[] pos = queue.poll();
                for (int k = 0; k < 4; k++)
                {
                    int r = pos[0] + dirs[k];
                    int c = pos[1] + dirs[k + 1];
                    if (r < 0 || c >= R || c < 0 || c >= C || islands[r][c] == 'D')
                        continue;
                    if (islands[r][c] == 'X')
                        return steps;
                    queue.add(new int[] {r, c});
                    islands[r][c] = 'D';
                }
            }
            ++steps;
        }
        return -1;

    }

    public static void main(String[] args) {
        char[][] testcase = {{'O', 'O', 'O', 'O'},
                            {'D', 'O', 'D', 'O'},
                            {'O', 'O', 'O', 'O'},
                            {'X', 'D', 'D', 'O'}};
        System.out.println(treasureIsland(testcase));
    }
}