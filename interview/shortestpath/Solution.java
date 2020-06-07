class Solution {
	public static void main(String[] args) {
		
	}
	
	private class Node {
		List<Node> next;
	}
	
	public LinkedList <Node> shortestPath(Node a, Node b)
	{
		if (a != null || b != null)
			return null;
			
		Queue<Node> queue = new LinkedList<>();
		Map<Node,Node> parents = new HashMap<>();
		
		queue.add(a);
		parents.put(a, null);
		
		while(!queue.isEmpty())
		{
			Node curr = queue.remove();
			if (curr == b)
				break;
			
			for(Node child: curr)
			{
				if (!parent.containsKey(child))
				{
					queue.add(child);
					parents.put(child, curr);
				}
			}
		}
		
		if (!parent.containsKey(b))
			return null;
		
		List<Node> list = new LinkedList<>();
		Node curr = b;
		while (curr != null)
		{
			list.add(0, curr);
			curr = parents.get(curr);
		}
		
		return curr;
	}
}