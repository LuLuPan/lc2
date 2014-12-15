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
            /*
             * 1.    prev
                    /
                  cur
             */
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
            /*
             * 2.    cur
                    /
                  prev
             */
        		// go right, right subtree hasn't been processed
                // do not change cur directly, cur will be assigned to prev
        		stack.push(cur.right);
        	} else {
        		// from right subtree back to root
                /*
                 * 3.    cur
                           \
                            prev
                 */
        		result.add(cur.val);
        		stack.pop();
        	}

        	prev = cur;
        }

        return result;
    }
}

// Morris
ppublic class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

	// Note 1:
        // Need a dummy root node, or it cannot handle single root case
	// And cannot print out right subtree
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode cur = dummy;
        while (cur != null) {
            if (cur.left == null) {
		// Note 2: Do not add here, otherwise will duplicated with reverse path
                //result.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur)
                    prev = prev.right;
                
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    List<Integer> tmp = new ArrayList<Integer>();
                    TreeNode tmpNode = cur.left;
                    while (tmpNode != cur) {
                        tmp.add(tmpNode.val);
                        tmpNode = tmpNode.right;
                    }
                    
                    Collections.reverse(tmp);
                    result.addAll(tmp);
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        
        return result;
    }
}
