/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root 
node down to the nearest leaf node.
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

//Recursive
public class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null)
            return minDepth(root.right) + 1;
        if (root.right == null)
            return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}

// Iterative- level order
// BFS guarantee shortest path
public class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int level = 0;
        boolean firstLeaf = false;
        while (!queue.Empty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // break if first leaf node encountered
                if (cur.left == null && cur.right == null) {
                    firstLeaf = true;
                    break;
                }
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
            }

            if (firstLeaf == true) break;
        }

        return level;
    }
}