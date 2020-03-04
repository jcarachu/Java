import java.util.ArrayDeque;
import java.util.Collections;

public class BreathFirstSearch {
    public static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost)
        {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        private int n;
        private Integer[] prev;
        private List<List<Edge>> graph;

        public BreathFirstSearch(List<List<Edge>> graph)
        {
            if (graph == null)
                throw new IllegalArgumentException("graph cannot be null");
            n = graph.size();
            this.graph = graph;
        }

        public List<Integer> reconstructPath(int start, int end)w
        {
            bfs(start);
            List<Integer> path = new ArrayList<>();
            for (Integer at = end; at != null; at = prev[at])
                path.add(at);
            Collections.reverse(path);
            if (path.get(0) == start)
                return start;
            path.clear();
            return path;
        }

        public void bfs(int start)
        {
            prev = new Integer[n];
            boolean[] visited = new boolean[n];
            Dequeue<Integer> queue = new ArrayDeque<>(n);

            queue.offer(start);
            visited[start] = true;
            while(!queue.isEmpty())
            {
                int node = queue.poll();
                List<Integer> edges = graph.get(node);

                for (Edge edge: edges)
                {
                    if (!visited[edge])
                    {
                        visited[edge.to] = true;
                        prev[edge.to] = node;
                        queue.offer(edge.to);
                    }    
                }
            }
        }

        public static List<List<Edge>> createEmptyGraph(int n)
        {
            List<List<Edge>> graph = new ArrayList<>(n);
            for (int i = 0; i < n; i++)
                graph.add(new ArrayList<>());
            return graph;
        }

        public static void addUndirectedEdge(List<List<Edge>> graph, int u, int v, int cost) {
            addDirectedEdge(graph, u, v, cost);
            addDirectedEdge(graph, v, u, cost);
        }
        
        public static void addUnweightedUndirectedEdge(List<List<Edge>> graph, int u, int v) {
            addUndirectedEdge(graph, u, v, 1);
        }

    }
}