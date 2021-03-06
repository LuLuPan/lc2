/*
Given a binary tree, flatten it to a linked list [in-place].

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points 
to the next node of a pre-order traversal.
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
// Iterative
public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        // List order is pre-order
        // Pre-order traversal
        while (!stack.isEmpty()) {
          TreeNode cur = stack.pop();
          if (cur.right != null)
            stack.push(cur.right);
          if (cur.left != null)
            stack.push(cur.left);

          // Error: Clear left node
          cur.left = null;
          if (!stack.isEmpty())
              cur.right = stack.peek();
        }
    }
}

// Recursive
public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);
        // similar to Morris
        if (root.left == null) return;
        TreeNode prev = root.left;
        while (prev.right != null)
            prev = prev.right;
        prev.right = root.right;
        root.right = root.left;
        root.left = null;
    }
}

// Recursive optimized
public class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        doFlattern(root);
    }
    
    /**
     * doFlattern returns leave node of currently flattened subtree
     * as an optimization.
     * In this way, no need to find the leaves by while loop
     * when exist recursion
     */ 
    private TreeNode doFlattern(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;
        TreeNode l = doFlattern(root.left);
        TreeNode r = doFlattern(root.right);
        if (l == null) return r;
        l.right = root.right;
        root.right = root.left;
        root.left = null;
        
        // Note: if r is null means root has no right subtree
        // so l should be returned as leave node for current
        // flatterned subtree
        return r == null ? l : r;
    }
}
