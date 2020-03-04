public class DepthFirstSearch {

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost)
        {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int dfs (Map<Integer, List<Edge>> graph, int start, int n)
    {
        int count = 0;
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack();

        stack.push(start);
        while (!stack.empty())
        {
            int node = stack.pop();
            if (!visited[node])
            {
                count++;
                List<Edge> edges = graph.get(node);

                if (edges != null)
                {
                    for (Edge edge: edges)
                    {
                        if (!visited[edge.to])
                            stack.push(edge.to);
                    }
                }
            }
        }
        
        return count;
    }

    private static void addDirectedGraph(Map<Integer, List<Edge>> graph, int from, int to, int cost)
    {
        List<Edge> list = graph.get(from);
        if (list == null)
        {
            list = new ArrayList<Edge>();
            graph.put(from, list);
        }

        list.add(new Edge(from, to, cost));s
    }

    public static void main(String[] args) {
    
    }
}