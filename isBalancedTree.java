/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1

Solution: Similar to symmetric tree

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
    public boolean isBalanced(TreeNode root) {
        return balanced(root) >= 0;
    }

    private int balanced(TreeNode root) {
    	// leaf height is 0
    	if (root == null) return 0;
    	int left = balanced(root.left);
    	int right = balanced(root.right);

    	if (left < 0 || right < 0 || Math.abs(left - right) > 1) return -1;
    	// height +1
    	return Math.max(left, right) + 1;
    }
}