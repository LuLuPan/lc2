/*
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.

Solution:
For a subtree, max subpath in it is max path sum of left subtree
+ max path sum of right subtree + root value

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
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        List<Integer> max = new ArrayList<Integer>();
        max.add(Integer.MIN_VALUE);
        doPathSum(root, max);
        return max.get(0);
    }

    private int doPathSum(TreeNode root, List<Integer> max) {
    	if (root == null) return 0;
    	// No need to process leaf node
    	// or leaf node will not be added
    	//if (root.left == null && root.right == null)
    	//	return root.val;

    	int leftMax = doPathSum(root.left, max);
    	int rightMax = doPathSum(root.right, max);
    	int cur = root.val + Math.max(0, leftMax) + Math.max(0, rightMax);

    	if (cur > max.get(0))
    		max.set(0, cur);

    	// Notice: should return max value of one subtree, left or right,
    	// cannot be max sum included root
    	return root.val + Math.max(leftMax, Math.max(rightMax, 0));
    }
}