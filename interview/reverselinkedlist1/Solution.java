import javax.naming.ldap.*;

class Solution {
	
	public class ListNode [
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	]
	public static void main(String[] args) {
	}
	
	public ListNode reverseBetween(ListNode head, int m, int n)
	{
		// Empty list
		if (head == null)
			return null;
		
		// Move the two pointers until they reach the proper starting point
		ListNode cur = head;
		ListNode prev = null;
		
		while (m > 1)
		{
			prev = curr;
			cur = cur.next;
			m--;
			n--;
		}
		
		// The two pointers that will fix the final connections
		ListNode con = prev;
		ListNode tail = cur;
		
		// Iteratively reverse the nodes until n becomes 0
		ListNode third = null;
		while (n > 0)
		{
			third = cur.next();
			cur.next() = prev;
			prev = cur;
			cur = third;
			n--;
		}
		
		// Adust the final connections as explained in the algorithm
		if (con != null)
		{
			con.next = prev;
		} else {
			head = prev;
		}
		
		tail.next = cur;
		return head;
	
	}
}