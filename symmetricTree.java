/*
Given a binary tree, check whether it is a mirror of itself 
(ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return isSymmetricTree(root.left, root.right);
    }

    private boolean isSymmetricTree(TreeNode left, TreeNode right) {
    	if (left == null && right == null) return true;
    	if (left == null || right == null) return false;
    	if (left.val != right.val) return false;

    	return isSymmetricTree(left.right, right.left) &&
    	       isSymmetricTree(left.left, right.right);
    }
}

//iterative
// Push two node in symmetric position at the same time and pop out
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root.left);
        stack.push(root.right);

        while (!stack.isEmpty()) {
        	TreeNode p = stack.pop();
        	TreeNode q = stack.pop();

        	if (p == null && q == null) continue;
        	if (p == null || q == null) return false;
        	if (p.val != q.val) return false;
        	stack.push(p.right);
        	stack.push(q.left);
        	stack.push(p.left);
        	stack.push(q.right);
        }

        return true;
    }
}