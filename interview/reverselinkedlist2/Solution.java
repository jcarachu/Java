class Solution {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public static void main(String[] args) {
	}
	
	public boolean stop;
	public TreeNode left;
	public ListNode reverseBetween(ListNode head, int m, int n)
	{
		this.left = head;
		this.stop = false;
		this.helper(head, m, n)
		return head;
	}
	
	public void helper(ListNode node, int m, int n)
	{
		if (n == 1)
			return;
		
		right = right.next;	
		if (m > 1)
			left = left.next;
		
		helper(right, m - 1, n -1);
		if (left == right || right.next == left)
			stop = true;
		
		if (!stop)
		{
			int t = left.val;
			left.val = right.val;
			right.val = t;
			this.left = left.next;
		}
	}
}