/*
Given a binary tree, return the level order traversal of its nodes' values. 
(ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
        	List<Integer> level = new ArrayList<Integer>();
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		TreeNode cur = queue.poll();
        		level.add(cur.val);
        		if (cur.left != null)
        			queue.offer(cur.left);
        		if (cur.right != null)
        			queue.offer(cur.right);
        	}
        	result.add(level);
        }

        return result;
    }
}

// Recursive DFS
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        traverse(root, 1, result);
        return result;
    }

    private void traverse(TreeNode root, int level, List<List<Integer>> result) {
    	if (root == null) return;

    	if (level > result.size())
    		result.add(new ArrayList<Integer>());
    	// use level as index for which level node belongs to
    	result.get(level - 1).add(root.val);
    	traverse(root.left, level + 1, result);
    	traverse(root.right, level + 1, result);
    }
}