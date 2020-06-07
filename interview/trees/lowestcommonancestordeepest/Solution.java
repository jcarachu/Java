import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x) {val = x; }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root)
    {
        if (root == null)
        {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> deepest = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty())
        {
            TreeNode node = queue.remove();
            if (node == null)
            {
                if (!queue.isEmpty())
                {
                    queue.add(null);
                    deepest.clear();
                }
            } else {
                deepest.add(node);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }

        while (deepest.size() > 1)
        {
            TreeNode n1 = deepest.remove();
            TreeNode n2 = deepest.remove();
            TreeNode lca = LCA(root, n1, n2);
        }

        return deepest.remove();
    }

    public TreeNode LCA(TreeNode node, TreeNode n1, TreeNode n2)
    {
        if (node == null || node == n1 || node == n2)
            return node;
        
        TreeNode l = LCA(node.left, n1, n2);
        TreeNode r = LCA(node.right, n1, n2);

        if (l != null && r != null)
            return node;
        else if (l != null && r == null)
            return l;
        else if (l == null && r != null)
            return r;
        
        return null;
    }
}