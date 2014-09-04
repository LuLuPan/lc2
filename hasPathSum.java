/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path 
such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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

// DFS
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
    	if (root == null) return false;
        return hasSum(root, 0, sum);
    }

    public boolean hasSum(TreeNode root, int curSum, int sum) {
    	if (root.left == null && root.right == null) {
    		curSum += root.val;
    		if (curSum == sum)
    			return true;
    		else return false;
    	}
    	boolean result = false;
    	if (root.left != null)
    		result = hasSum(root.left, curSum + root.val, sum);
    	if (root.right != null)
    		result |= hasSum(root.right, curSum + root.val, sum);
    	return result;
    }
}

// Normal recursive
// sub node val from sum when go from top to bottom, 
// if reach the leave and the sum left equals to leave node value, then found
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
    	if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum)
        	return true;
        return hasPathSum(root.left, sum - root.val) || 
               hasPathSum(root.right, sum - root.val);
    }
}
