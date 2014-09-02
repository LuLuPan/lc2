/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
    	if (inorder == null || postorder == null) return null;
        if (inorder.length != postorder.length) return null;
        if (inorder.length == 0 || postorder.length == 0) return null;
        int n = inorder.length;
        HashMap<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++)
        	inorderMap.put(inorder[i], i);
        return doBuild(inorder, 0, n - 1, postorder, 0, n - 1, inorderMap);
    }

    private TreeNode doBuild(int[] inorder, int inStart, int inEnd, int[] postorder, 
    	int postStart, int postEnd, HashMap<Integer, Integer> inorderMap)
    {
    	if (inStart > inEnd || postStart > postEnd) return null;
    	TreeNode root = new TreeNode(postorder[postEnd]);
    	int index = inorderMap.get(postorder[postEnd]);
    	root.left = doBuild(inorder, inStart, index - 1, postorder, postStart, 
    		postStart + index - inStart - 1, inorderMap);
    	root.right = doBuild(inorder, index + 1, inEnd, postorder, 
    		postStart + index - inStart, postEnd - 1, inorderMap);
    	return root;
    }
}