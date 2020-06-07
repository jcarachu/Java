class Solution {
	public static void main(String[] args) {
		
	}
}

public LinearSpaceApproach {
	public Node copyRandomList(Node head)
	{
		if (head == null)
			return null;
	}
	
	Map<Node, Node> cloneMap = new HashMap<Node, Node>();
	
	// 1st Pass: Give all nodes their clone in the mapping
	Node curr = head;
	while(curr != null)
	{
		cloneMap.put(curr, new Node(curr.val));
		curr = curr.next;
	}
	
	// 2nd Pass: reset head, give clones their next and random pointers
	curr = head;
	while (curr != null)
	{
		cloneMap.get(curr).next = cloneMap.get(curr.next);
		cloneMap.get(curr).random = cloneMap.get(curr.random);
		curr = curr.next;
	}
	
	return cloneMap.get(head);
	
}

public ConstantSpaceApproach {
	public Node copyRandomList(Node head)
	{
		Node curr = head;
		Node next = null;
		
		// First Pass: Stash the next value of the current Node
		while (curr != null)
		{
			next = curr.next;
			Node copy = new Node(curr.val);
			curr.next = copy;
			copy.next = next;
			curr.next;
		}
		
		// Second Pass: Reset curr to the head and add random 
		curr = head;
		while (curr != null)
		{
			if (curr.random != null)
			{
				curr.next.random = curr.random.next;
			}
			
			curr = curr.next;
		}
		
		// Third Pass: Restore the original list and extract the copy
		curr = head;
		Node dummyHead = new Node(0);
		Node cloneListTail = dummyHead;
		Node copy = null;
		
		while (cur != null)
		{
			next = curr.next.next;
			copy = cur.next;
			cloneListTail.next = copy;
			cur.next = next;
			cur = cur.next;
			
		}
		
		return dummyHead;	
	}
}