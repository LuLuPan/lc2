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
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> result = new ArrayList<Integer>();
        if (root == null) return  result;

        inOrder(root, result);
        return result;
    }

    private void inOrder(TreeNode root, List<Integer> result) {
    	if (root == null) return;
    	inOrder(root.left, result);
    	result.add(root.val);
    	inOrder(root.right, result);
    }
}

//Iterative
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        //stack.push(root);
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
        	if (cur != null) {
        		stack.push(cur);
        		cur = cur.left;
        	} else {
        		cur = stack.pop();
        		result.add(cur.val);
        		cur = cur.right;
        	}
        }

        return result;
    }
}

// Morris
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        TreeNode cur = root;
        TreeNode prev = null;

        while (cur != null) {
        	if (cur.left == null) {
        		result.add(cur.val);
        		cur = cur.right;
        	} else {
        		// cur.left != null
        		prev = cur.left;
        		while (prev.right != null && prev.right != cur)
        			prev = prev.right;
        		if (prev.right == null) {
        			prev.right = cur;
        			cur = cur.left;
        		} else {
        			result.add(cur.val);
        			cur = cur.right;
        			prev.right = null;
        		}
        	}
        }

        return result;
    }
}