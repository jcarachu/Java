class Solution {
    /*
     * Given an undirected graph with n nodes labeled 1..n. Some of the nodes are already connected. 
     * The i-th edge connects nodes edges[i][0] and edges[i][1] together. 
     * Your task is to augment this set of edges with additional edges to connect all the nodes. 
     * Find the minimum cost to add new edges between the nodes such that all the nodes are accessible from each other.
     */ 

    class UF {
        private int[] parent;
        private byte[] rank;
        public int count;
        
        public UF(int n)
        {
            if (n < 0) throw new IllegalArgumentException();
            parent = new int[n];
            rank = new byte[n];
            for (int i = 0; i < n; i++)
            {
                parent[i] = i;
            }

            count = n;
        }

        public int find(int p)
        {
            while (p != parent[p])
            {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{1,4}, {4,5}, {2,3}};
        int[][] newEdges = {{ 1, 2, 5}, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}};
        System.out.println(minCost(n, edges, newEdges));
    }

    public static int minCost(int n, int[][] edges, int[][] newEdges)
    {

    }
}