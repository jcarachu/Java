/*
 * Takes an adjacency matrix of an acyclic graph and returns an array
 * with the indexes of the nodes in a topological order. A topological 
 * ordering of its vertices such that for every directed edge uv from 
 * vertex u to vertex v, u comes before v in the ordering.
 * Time Complexity: O(V^2)
 */
class Solution {

    public static void main(String[] args) {
        final int N = 7;
        Double[][] adjMatrix = new Double[N][N];

        adjMatrix[0][1] = 3.0;
        adjMatrix[0][2] = 2.0;
        adjMatrix[0][5] = 3.0;

        adjMatrix[1][3] = 1.0;
        adjMatrix[1][2] = 6.0;

        adjMatrix[2][3] = 1.0;
        adjMatrix[2][4] = 10.0;

        adjMatrix[3][4] = 5.0;

        adjMatrix[5][4] = 7.0;
    }

    public static int[] topologicalSort(Double[][] adj)
    {
        // Setup
        int n = adj.length;
        boolean[] visited = new boolean[n];
        int[] order = new int[n];
        int index = n - 1;

        // Vist each node
        // Stack focus on a sequetial search
        // Performs a depth first search
        for (int u = 0; u < n; u++)
        {
            if (!visited[u])
                index = visit(adj, visited, order, index, u);
        }

        return order;
    }

    private static int visit(Double[][] adj, boolean[] visited, int[] order, int index, int u)
    {
        if (visited[u])
            return index;
        // Visit all row neighbors
        for (int v = 0; v < adj.length; v++)
        {
            if (adj[u][v] != null && !visited[v])
                index = visit(adj, visited, order, index, u);
        }

        // Place this node at the head of the list
        // Reversing the order
        order[index--] = u;

        return index;
    }

    public static double[] dagShortestPath(Double[][] adj, int start)
    {
        int n = adj.length;
        double[] dist = new double[n];
        java.util.Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0.0;

        // Process nodes in a topologically sorted order
        for (int u: topologicalSort(adj))
        {
            for (int v = 0; v < n; v++)
            {
                if (adj[u][v] != null)
                {
                    double newDist = dist[u] + adj[u][v];
                    if (newDist < dist[v])
                        dist[v] = newDist;
                }
            }
        }

        return dist;
    }
}