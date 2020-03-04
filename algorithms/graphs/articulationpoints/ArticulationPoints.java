import java.util.ArrayList;
import java.util.List;
import java.lang.Math.min;
/*
 * Find all Articulation Points on an undirected graph.
 * Time Complexity O(n) = E + V
 */

class ArticulationPoints {
    private int n;
    private int id;
    private int[] ids;
    private int[] lows;
    private int rootOutNode;
    private boolean solved;
    private boolean[] visited;
    private boolean[] isArticulationPoint;
    private List<List<Integer>> list;
    private Graph graph;

    public static void main(String[] args) {
        int n = 9;
        ArticulationPoints ap = new ArticulationPoints(n);
        ap.testCase1();
    }

    public ArticulationPoints(int n)
    {
        graph = new Graph(n);
        this.list = graph.list;
        this.n = n;
    }

    public void testCase1()
    {
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(2, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 5);

        boolean[] finalresults = FindArticulationPoints();
        for (int i = 0; i < n; i++)
        {
            if (finalresults[i])
                System.out.printf("Node %d is an articulation\n", i);
        }
        
    }

    public boolean[] FindArticulationPoints()
    {
        if (solved)
            return isArticulationPoint;
        
        id = 0;
        lows = new int[n];
        ids = new int[n];
        visited = new boolean[n];
        isArticulationPoint = new boolean[n];

        for (int i = 0; i < n; i++)
        {
            if (!visited[i])
            {
                rootOutNode = 0;
                dfs(i, i, -1);
                isArticulationPoint[i] = (rootOutNode > 1);
            }
        }

        solved = true;
        return isArticulationPoint;
    }

    private void dfs(int root, int at, int parent)
    {
        if (parent == root)
            rootOutNode++;
        
        visited[at] = true;
        lows[at] = ids[at] = id++;
        List<Integer> edges = graph.list.get(at);
        for (Integer to: edges)
        {
            if (to == parent)
                continue;
            if (!visited[to])
            {
                dfs(root, to, at);
                lows[at] = Math.min(lows[at], lows[to]);
                if (ids[at] <= lows[to])
                    isArticulationPoint[at] = true;
            } else {
                lows[at] = Math.min(lows[at], ids[to]);
            }
        }
    }

    public class Graph {
        private List<List<Integer>> list;

        public Graph(int n)
        {
            createGraph(n);
        }

        public void addEdge(int from, int to)
        {
            list.get(from).add(to);
            list.get(to).add(from);
        }

        public void createGraph(int n)
        {
            list = new ArrayList<>();
            for (int i = 0; i < n; i++)
            {
                list.add(new ArrayList<>());
            }
        }
    }
}