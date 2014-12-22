/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a 
constant space solution?

Solution:
1. Recursive and record voliating pair..
   Not constant space
2. Morris Traversal Inorder

if prev.val > cur.val, then a violation happens

A.      1 cur
       / \
 prev 2   3

B.      1 prev
       / \
      2   3 cur
C.
     1 cur
    /
    2
     \
      \
       3 prev

Check prev and cur val every time before updated.
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
// Morris
public class Solution {
    public void recoverTree(TreeNode root) {
        if (root ==  null) return;

        List<TreeNode> pair = new ArrayList<TreeNode>();
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null) {
        	if (cur.left == null) {
        		// Case B.
        		detect(pair, prev, cur);
        		//left most of left subtree.
        		prev = cur;
        		cur = cur.right;
        	} else {
        		TreeNode node = cur.left;
        		while (node.right != null && node.right != cur)
        			node = node.right;
        		if (node.right == null) {
        			node.right = cur;
        			cur = cur.left;
        		} else {
        			detect(pair, prev, cur);
        			// prev will become right most in left subtree
        			// cur will become root
        			// Case A. C
        			prev = cur;
        			cur = cur.right;
        			node.right = null;
        		}
        	}
        }

        if (pair.size() == 2) {
        	int tmp = pair.get(0).val;
        	pair.get(0).val = pair.get(1).val;
        	pair.get(1).val = tmp;
        }
    }

    private void detect(List<TreeNode> pair, TreeNode prev, TreeNode cur) {
    	if (prev != null && prev.val > cur.val) {
    		if (pair.size() == 0) {
    			pair.add(prev);
    			pair.add(cur);
    		} else {
    			pair.set(1, cur);
    		}
    	}
    }
}

// Inorder
// http://blog.csdn.net/linhuanmars/article/details/24566995
public class Solution {
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        List<TreeNode> prev = new ArrayList<TreeNode>();
        // Note: need to add something here
        prev.add(null);
        List<TreeNode> result = new ArrayList<TreeNode>();
        inOrder(root, prev, result);
        if (result.size() > 0) {
            int tmp = result.get(0).val;
            result.get(0).val = result.get(1).val;
            result.get(1).val = tmp;
        }
    }
    
    private void inOrder(TreeNode root, List<TreeNode> prev, List<TreeNode> result) {
        if (root == null) return;
        inOrder(root.left, prev, result);
        if (prev.get(0) != null && prev.get(0).val > root.val) {
            if (result.size() == 0) {
                // first reversed order pair
                result.add(prev.get(0));
                result.add(root);
            } else {
                // second reversed order pair
                // only store the second part of the pair
                result.set(1, root);
            }
        }
        
        prev.set(0, root);
        inOrder(root.right, prev, result);
    }
}