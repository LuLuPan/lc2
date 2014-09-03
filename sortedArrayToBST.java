/*
Given an array where elements are sorted in ascending order, 
convert it to a height balanced BST.
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
    public TreeNode sortedArrayToBST(int[] num) {
        return buildBST(0, num.length - 1, num);
    }

    private TreeNode buildBST(int start, int end, int[] num) {
    	TreeNode root = null;
    	if (start > end) return root;
    	int mid = start + (end - start) / 2;
    	root = new TreeNode(num[mid]);
    	root.left = buildBST(start, mid - 1, num);
    	root.right = buildBST(mid + 1, end, num);
    	return root;
    }
}