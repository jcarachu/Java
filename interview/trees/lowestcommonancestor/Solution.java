class Solution {
    public class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }
    
    public TreeNode lca(TreeNode root, int x, int y)
    {
        return dfs(root, x, y);
    }

    public TreeNode dfs(TreeNode node, int x, int y)
    {
        if (node == null)
            return null;
        
        if (node.val == x || node.val == y)
        {
            return node;
        }
        
        TreeNode left = dfs(node.left, x, y);
        TreeNode right = dfs(node.right, x, y);

        if (left == null)
            return right;
        if (right == null)
            return left;
        
        return node;
    }
    public static void main(String[] args) {
        
    }
}