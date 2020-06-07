import javax.swing.tree.TreeNode;

class Solution {
    /*
     * Given an N-ary tree, find the subtree with the maximum average
     * Return the root of the subtree
     * Note: A subtree is the node which have at least 1 child plus its descendants
     * Note: The average value of a subtree is the sum of its values, divided by the number of nodes
     */

    double max = Integer.MIN_VALUE;
    TreeNode maxNode = null;

    public TreeNode maximumAverageSubTree(TreeNode root)
    {
        if (root == null)
            return null;
        
        helper(root);
        return maxNode;
    }

    private double[] helper (TreeNode root)
    {
        if (root == null)
            return new double[] {0, 0};
        
        double curTotal = root.val;
        double count = 1;
        
        for (TreeNode child: root.children)
        {
            double[] cur = helper(child);
            curTotal += cur[0];
            count += cur[1];
        }

        double avg = curTotal / count;
        if (count > 1 && avg > max)
        {
            max = avg;
            maxNode = root;
        }

        return new double[] {curTotal, count};
    }

    public static void main(String[] args) {
        System.out.println("Its working");
    }
}