/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
*/
// Recursive
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        postOrder(root, result);
        return result;
    }

    private void postOrder(TreeNode root, List<Integer> result) {
    	if (root == null) return;
    	postOrder(root.left, result);
    	postOrder(root.right, result);
    	result.add(root.val);
    }
}

// Iterative
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode cur = root;
        TreeNode prev = null;

        while (!stack.empty()) {
        	cur = stack.peek();
        	if (prev == null || prev.left == cur || prev.right == cur) {
        		// top to bottom
                        // go to left
        		if (cur.left != null)
        			stack.push(cur.left);
        		else if (cur.right != null)
        			stack.push(cur.right);
        		else {
        			// leave node
        			result.add(cur.val);
        			stack.pop();
        		}
        	} else if (cur.left == prev && cur.right != null) {
        		// go right, right subtree hasn't been processed
                        // do not change cur directly, cur will be assigned to prev
        		stack.push(cur.right);
        	} else {
        		// from right subtree back to root
        		result.add(cur.val);
        		stack.pop();
        	}

        	prev = cur;
        }

        return result;
    }
}

// Morris
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        // Need a dummy root node, or it cannot handle single root case
        TreeNode dummy = new TreeNode(0);
        // make whole tree as dummy's left subtree
        // dummy.right == null which is terminate condition
        dummy.left = root;
        TreeNode cur = dummy;
        TreeNode prev = null;
        while (cur != null) {
        	if (cur.left == null) {
                        // when back to dummy without right subtree
                        // traversal terminated
        		cur = cur.right;
        	} else {
        		prev = cur.left;
        		while (prev.right != null && prev.right != cur)
        			prev = prev.right;
        		if (prev.right == null) {
        			prev.right = cur;
        			cur = cur.left;
        		} else {
        			// reverse path from cur.left - > prev
        			reverse(cur.left, prev);
        			TreeNode tmp = prev;
        			while (tmp != cur.left) {
        				result.add(tmp.val);
        				tmp = tmp.right;
        			}
        			result.add(tmp.val);
        			// reverse prev->cur.left
        			reverse(prev, cur.left);
        			// Below two have different order as inorder or preorder
        			// since need to process right node
        			// And the very top root is dummy
        			prev.right = null;
        			// do not access subtree root, access right subtree at first
        			cur = cur.right;
        		}
        	}
        }

        return result;
    }

    private void reverse(TreeNode start, TreeNode end) {
    	if (start == end) return;
    	TreeNode prev = start;
    	TreeNode cur = start.right;
    	TreeNode next = null;

    	while (prev != end) {
    		next = cur.right;
    		cur.right = prev;
    		prev = cur;
    		cur = next;
    	}
    }
}
