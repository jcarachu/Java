import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

class Solution {

    private int n, id, rootNodeOutcomingEdgeCount;
    private boolean solved;
    private int[] low, ids;
    private boolean[] visited, isArticulationPoint;
    private List<List<Integer>> graph;

    public static void main(String[] args) {
        int n = 9;
        List<List<Integer>> graph = createGraph(n);
    }

    public boolean[] findArticulationPoints()
    {
        if (solved)
            return isArticulationPoint;
        
        id = 0;
        low = new int[n];
        ids = new int[n];
        visited = new boolean[n]; // discoverytime? 
        isArticulationPoint = new boolean[n]; // discoverytime?

        for (int i = 0; i < n; i++)
        {
            if (!visited[i])
            {
                rootNodeOutcomingEdgeCount = 0;
                dfs(i, i, -1);
                isArticulationPoint[i] = (rootNodeOutcomingEdgeCount > 1);
            }
        }

        solved = true;
        return isArticulationPoint;
    }

    public void dfs(int root, int at, int parent)
    {
        if (parent == root)
            rootNodeOutcomingEdgeCount++;
        
        visited[at] = true;
        low[at] = ids[at] = id++;

        List<Integer> edges = graph.get(at);
        for (Integer to: edges)
        {
            if (!visited[to])
            {
                dfs(root, to, at);
                low[at] = Math.min(low[at], low[to]);
                if (ids[at] <= low[to]) 
                // if child has a higher lowest discover time than the parents discovery time
                {
                    isArticulationPoint[at] = true;
                } else {
                    // Replace the childs lowest time with the parents discoverty time
                    low[at] = Math.min(low[at], ids[to]);
                }
            }
        }
    }

    public static List<List<Integer>> createGraph(int n)
    {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        
        return graph;
    }

    public static void addEdge(List<List<Integer>> graph, int from, int to)
    {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }
}