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
