/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?

Solution: 
1. stack based (recurse or iterative)
O(n) + O(logn)
2. Morris Traversal
O(n) + O(1)

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

// Recursive
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        preOrder(root, result);
        return result;
    }

    private void preOrder(TreeNode root, List<Integer> result) {
    	if (root == null) return;
    	result.add(root.val);
    	preOrder(root.left, result);
    	preOrder(root.right, result);
    }
}

// Iterative
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.empty()) {
        	cur = stack.pop();
        	// P->L->R
        	result.add(cur.val);
        	if (cur.right != null)
        		stack.push(cur.right);
        	if (cur.left != null)
        		stack.push(cur.left);
        }

        return result;
    }
}

// Iterative with better template
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (cur != null || !stack.empty()) {
        	if (cur != null) {
        		stack.push(cur);
        		result.add(cur.val);
        		cur = cur.left;
        	} else {
        		cur = stack.pop();
        		cur = cur.right;
        	}
        }

        return result;
    }
}

// Morris
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null) {
        	if (cur.left == null) {
        		result.add(cur.val);
        		cur = cur.right;
        	}
        	else {
        		prev = cur.left;
        		while (prev.right != null && prev.right != cur)
        			prev = prev.right;
        		if (prev.right == null) {
        			// first time to reach right most node
        			// point to parent cur which should be traced back
        			result.add(cur.val);
        			prev.right = cur;
        			cur = cur.left;
        		} else {
        			// returned from right most node
        			// recover right most node and go to right subtree
        			cur = cur.right;
        			prev.right = null;
        		}
        	}
        }

        return result;
    }
}