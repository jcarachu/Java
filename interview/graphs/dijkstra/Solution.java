import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.lang.Math;

class Solution {

    public static class Edge {
        int cost;
        int from , to;
        
        public Edge(int from, int to, int cost)
        {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static class Node {
        int id;
        int value;

        public Node(int id, int value)
        {
            this.id = id;
            this.value = value;
        }
    }

    private int n;
    private int[] dist;
    private Integer[] prev;
    private List<List<Edge>> graph;

    private Comparator<Node> comparator = new Comparator<Solution.Node>() {
        @Override
        public int compare(Node node1, Node node2)
        {
            if (Math.abs(node1.value - node2.value) < 0)
                return 0;
            return (node1.value - node2.value) > 0 ? + 1 : -1;
        }
    };

    public Solution(int n)
    {
        this.n = n;
        createEmptyGraph();
    }

    public Solution(int n, Comparator<Node> comparator)
    {
        this(n);
        if (comparator == null)
        {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        this.comparator = comparator;
    }

    public int dikstra(int start, int end)
    {
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // Keep a priority queue of the most promising node to vist
        PriorityQueue<Node> pq = new PriorityQueue<>(2 * n, comparator);
        pq.offer(new Node(start, 0));

        // Array used to track which node have already been visited
        boolean[] visited  = new boolean[n];
        prev = new Integer[n];

        while (!pq.isEmpty())
        {
            Node node = pq.poll();
            visited[node.id] = true;

            // We already found a better path before we go to
            // Processing this node so we can ignore it
            if(dist[node.id] < node.value)
                continue;
            
            List<Edge> edges = graph.get(node.id);
            for (int i = 0; i < edges.size(); i++)
            {
                Edge edge = edges.get(i);
                // You cannot get a shorter path by revisiting
                // a node you have already visited before
                if (visited[edge.to])
                    continue;
                
                // Relax edge by updating minimum cost if applicable
                int newdist = dist[edge.from] + edge.cost;
                if (newdist < dist[edge.to])
                {
                    prev[edge.to] = edge.from;
                    dist[edge.to] = newdist;
                    pq.offer(new Node(edge.to, dist[edge.to]));
                }
            }
            // Once we've visted all the nodes spanning from the end
            // node we know we can return the minimum distance value to
            // the end because it cannot get any better after this point
            if (node.id == end)
                return dist[end];
        }

        // End node is unreachable
        return Integer.MAX_VALUE;
    }

    public List<Integer> reconstructPath(int start, int end)
    {
        if (end < 0 || end >= n)
            throw new IllegalArgumentException("Invalid node index");
        if (start < 0 || start >= n)
            throw new IllegalArgumentException("Invalid node index");
        
        int dist = dikstra(start, end);
        List<Integer> path = new ArrayList<>();
        if (dist == Integer.MAX_VALUE)
            return path;
        for (Integer at = end; at != null; at = prev[at])
            path.add(at);
        return path;   
    }

    private void createEmptyGraph()
    {
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
        {
            graph.add(new ArrayList<>());
        }
    }
    public static void main(String[] args) {
        
    }
}