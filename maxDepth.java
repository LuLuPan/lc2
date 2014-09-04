/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root 
node down to the farthest leaf node.
*/
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

// Iterative - Level Order
public class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        // Error: Level is initated to be 0
        int level = 1;
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	level++;
        	for (int i = 0; i < size; i++) {
        		TreeNode cur = queue.poll();
        		if (cur.left != null)
        			queue.offer(cur.left);
        		if (cur.right != null)
        			queue.offer(cur.right);
        	}
        }

        return level;
    }
}