class Solution {
	public static void main(String[] args) {
		
	}
	
	private int res = 0;
	public int max(TreeNode root)
	{
		if (node == null)
			return res;
		dfs(root, node.val, node.val);
		return res;
	}
	
	public void dfs(TreeNode node, int min, int max)
	{
		if (node == null)
			return;
		
		res = Math.max(res, Math.max(Math.abs(node.val - min), Math.abs(max - node.val)));
		min = Math.min(min, node.val);
		max = Math.max(max, node.val);
		
		dfs(node.left, min, max);
		dfs(node.right, min, max);
	}
}