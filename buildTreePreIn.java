/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

Solution:
Preorder provides position of root
With root, inorder divide into left and right subtree
Recursively.
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	if (preorder == null || inorder == null) return  null;
        if (preorder.length != inorder.length) return null;
        if (preorder.length == 0 || inorder.length == 0) return null;
        int n = preorder.length;
        HashMap<Integer, Integer> inoderMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++)
        	inoderMap.put(inorder[i], i);
        return doBuild(preorder, inorder, 0, n - 1, 0, n - 1, inoderMap);
    }

    private TreeNode doBuild(int[] preorder, int[] inorder, int preStart, int preEnd,
    	int inStart, int inEnd, HashMap<Integer, Integer> inoderMap)
    {
    	if (preStart > preEnd || inStart > inEnd) return null;
    	TreeNode root = new TreeNode(preorder[preStart]);
        int index = inoderMap.get(preorder[preStart]);
    	root.left = doBuild(preorder, inorder, preStart + 1, 
            index - inStart + preStart, inStart, index - 1, inoderMap);
    	root.right = doBuild(preorder, inorder, preStart + index - inStart + 1, 
            preEnd, index + 1, inEnd, inoderMap);

        return root;
    }
}